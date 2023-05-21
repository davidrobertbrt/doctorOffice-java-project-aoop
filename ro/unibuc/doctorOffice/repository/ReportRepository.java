package ro.unibuc.doctorOffice.repository;

import java.sql.Date;
import java.util.*;
import java.sql.*;

import ro.unibuc.doctorOffice.config.DatabaseConnection;
import ro.unibuc.doctorOffice.model.Report;
import ro.unibuc.doctorOffice.model.Medic;
import ro.unibuc.doctorOffice.model.Pacient;
import ro.unibuc.doctorOffice.repository.MedicRepository;
import ro.unibuc.doctorOffice.repository.ReportRepository;

public class ReportRepository
{
    public List<Report> readAll()
    {
        String sqlQuery = "SELECT * FROM reports";
        List<Report> reports = new ArrayList<>();

        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                UUID id = (UUID)rs.getObject("id");
                UUID pacient_id = (UUID)rs.getObject("pacient_id");
                UUID medic_id = (UUID)rs.getObject("medic_id");
                String description = rs.getString("description");
                java.util.Date dateOfWritten = new java.util.Date(rs.getDate("dateofwrite").getTime());

                MedicRepository medicRepo = new MedicRepository();
                Medic extractMedic = medicRepo.readById(medic_id);
                PacientRepository pacientRepo = new PacientRepository();
                Pacient p = pacientRepo.readById(pacient_id);

                Report report = new Report(p,extractMedic,description,dateOfWritten,id);
                reports.add(report);
            }
        }
        catch(SQLException ex)
        {
            throw new RuntimeException(ex);
        }

        return reports;
    }

    public List<Report> readByPacient(Pacient p)
    {
        String sqlQuery = "SELECT * FROM reports WHERE pacient_id = ?";
        List<Report> reports = new ArrayList<>();

        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            UUID pacientUUID = p.getId();
            stmt.setObject(1,pacientUUID);

            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                UUID id = (UUID)rs.getObject("id");
                UUID medic_id = (UUID)rs.getObject("medic_id");
                String description = rs.getString("description");
                java.util.Date dateOfWritten = new java.util.Date(rs.getDate("dateofwrite").getTime());

                MedicRepository medicRepo = new MedicRepository();
                Medic extractMedic = medicRepo.readById(medic_id);

                Report report = new Report(p,extractMedic,description,dateOfWritten,id);
                reports.add(report);
            }
        }
        catch(SQLException ex)
        {
            throw new RuntimeException(ex);
        }

        return reports;
    }

    public int insert(Report r)
    {
        String sqlQuery = "INSERT INTO reports(pacient_id,medic_id,description,dateOfWrite,id) VALUES (?,?,?,?,?)";
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            Pacient pacient = r.getPacient();
            Medic medic = r.getMedic();

            stmt.setObject(1,pacient.getId());
            stmt.setObject(2,medic.getId());
            stmt.setString(3,r.getDescription());
            stmt.setDate(4,new Date(r.getDateOfWrite().getTime()));
            stmt.setObject(5,r.getId());

            return stmt.executeUpdate();

        }catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public int update(Report r)
    {
        String sqlQuery = "UPDATE reports SET pacient_id = ?, medic_id = ?, description = ?, dateOfWrite = ? WHERE id = ?";
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            Pacient pacient = r.getPacient();
            Medic medic = r.getMedic();

            stmt.setObject(1,pacient.getId());
            stmt.setObject(2,medic.getId());
            stmt.setObject(3,r.getDescription());
            stmt.setDate(4,new Date(r.getDateOfWrite().getTime()));
            stmt.setObject(5,r.getId());

            return stmt.executeUpdate();

        }catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public int delete(Report r)
    {
        String sqlQuery = "DELETE FROM report WHERE id = ?";

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            statement.setObject(1, r.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
