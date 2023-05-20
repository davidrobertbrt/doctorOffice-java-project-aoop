package ro.unibuc.doctorOffice.repository;

import ro.unibuc.doctorOffice.config.DatabaseConnection;
import ro.unibuc.doctorOffice.model.Prescription;
import ro.unibuc.doctorOffice.repository.*;
import ro.unibuc.doctorOffice.model.Pacient;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.*;


public class PrescriptionRepository
{
    public List<Prescription> readAll()
    {
        String sqlQuery = "SELECT * FROM prescriptions";
        List<Prescription> prescriptions = new ArrayList<>();

        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                UUID id = (UUID)rs.getObject("id");
                UUID pacient_id = (UUID)rs.getObject("pacient_id");
                Date dateOfUse = new Date(rs.getDate("dateOfUse").getTime());
                Map<String,Integer> pills = readPills(id);

                PacientRepository pacientRepo = new PacientRepository();
                Pacient p = pacientRepo.readById(pacient_id);

                Prescription prescription = new Prescription(p,pills,dateOfUse,id);

                prescriptions.add(prescription);
            }
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }

        return prescriptions;
    }

    private Map<String, Integer> readPills(UUID prescriptionId) {
        String sqlQuery = "SELECT * FROM pills WHERE prescription_id = ?";
        Map<String, Integer> pills = new HashMap<>();

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            stmt.setObject(1, prescriptionId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String pillName = rs.getString("pill_name");
                int quantity = rs.getInt("quantity");

                pills.put(pillName, quantity);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return pills;
    }

    public int update(Prescription pr) {
        String sqlQuery = "UPDATE prescriptions SET pacient_id = ?, dateOfUse = ? WHERE id = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            stmt.setObject(1, pr.getPacient().getId());
            stmt.setDate(2, new java.sql.Date(pr.getDateOfUse().getTime()));
            stmt.setObject(3, pr.getId());

            int rowsAffected = stmt.executeUpdate();

            // Update pills
            updatePills(pr.getId(), pr.getPills());

            return rowsAffected;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void updatePills(UUID prescriptionId, Map<String, Integer> pills) {
        String deleteQuery = "DELETE FROM pills WHERE prescription_id = ?";
        String insertQuery = "INSERT INTO pills (prescription_id, pill_name, quantity) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery);
             PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {

            // Delete existing pills for the prescription
            deleteStmt.setObject(1, prescriptionId);
            deleteStmt.executeUpdate();

            // Insert new pills
            for (Map.Entry<String, Integer> entry : pills.entrySet()) {
                String pillName = entry.getKey();
                int quantity = entry.getValue();

                insertStmt.setObject(1, prescriptionId);
                insertStmt.setString(2, pillName);
                insertStmt.setInt(3, quantity);
                insertStmt.addBatch();
            }

            insertStmt.executeBatch();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int insert(Prescription pr) {
        String insertPrescriptionQuery = "INSERT INTO prescriptions (id, pacient_id, dateOfUse) VALUES (?, ?, ?)";
        String insertPillsQuery = "INSERT INTO pills (prescription_id, pill_name, quantity) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement insertPrescriptionStmt = connection.prepareStatement(insertPrescriptionQuery);
             PreparedStatement insertPillsStmt = connection.prepareStatement(insertPillsQuery)) {

            connection.setAutoCommit(false);

            insertPrescriptionStmt.setObject(1, pr.getId());
            insertPrescriptionStmt.setObject(2, pr.getPacient().getId());
            insertPrescriptionStmt.setDate(3, new java.sql.Date(pr.getDateOfUse().getTime()));
            int rowsAffected = insertPrescriptionStmt.executeUpdate();

            if (rowsAffected == 1) {
                insertPills(pr.getId(), pr.getPills(), insertPillsStmt);
                connection.commit();
            } else {
                connection.rollback();
                throw new SQLException("Failed to insert prescription.");
            }

            connection.setAutoCommit(true);

            return rowsAffected;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void insertPills(UUID prescriptionId, Map<String, Integer> pills, PreparedStatement insertPillsStmt) throws SQLException {
        for (Map.Entry<String, Integer> entry : pills.entrySet()) {
            String pillName = entry.getKey();
            int quantity = entry.getValue();

            insertPillsStmt.setObject(1, prescriptionId);
            insertPillsStmt.setString(2, pillName);
            insertPillsStmt.setInt(3, quantity);
            insertPillsStmt.addBatch();
        }

        insertPillsStmt.executeBatch();
    }


}
