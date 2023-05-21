package ro.unibuc.doctorOffice.repository;

import java.sql.Date;
import java.util.*;
import java.sql.*;

import ro.unibuc.doctorOffice.config.DatabaseConnection;
import ro.unibuc.doctorOffice.model.Appointment;
import ro.unibuc.doctorOffice.model.Report;
import ro.unibuc.doctorOffice.model.Medic;
import ro.unibuc.doctorOffice.model.Pacient;

import ro.unibuc.doctorOffice.repository.MedicRepository;
import ro.unibuc.doctorOffice.repository.PacientRepository;

import javax.xml.crypto.Data;

public class AppointmentRepository
{
    public List<Appointment> readByPacient(Pacient p)
    {
        String sqlQuery = "SELECT * FROM appointments WHERE pacient_id = ?";
        List<Appointment> appointments = new ArrayList<>();

        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            stmt.setObject(1,p.getId());
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                UUID id = (UUID)rs.getObject("id");
                UUID p_id = p.getId();
                UUID m_id = (UUID)rs.getObject("medic_id");
                java.util.Date appointmentDate = new java.util.Date(rs.getTimestamp("appointmentDate").getTime());

                MedicRepository medicRepo = new MedicRepository();

                Medic crMedic = medicRepo.readById(m_id);

                Appointment ap = new Appointment(appointmentDate,p,crMedic,id);
                appointments.add(ap);
            }
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }

        return appointments;
    }

    public List<Appointment> readByMedic(Medic m)
    {
        String sqlQuery = "SELECT * FROM appointments WHERE medic_id = ?";
        List<Appointment> appointments = new ArrayList<>();

        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            stmt.setObject(1,m.getId());
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                UUID id = (UUID)rs.getObject("id");
                UUID m_id = m.getId();
                UUID p_id = (UUID)rs.getObject("pacient_id");
                java.util.Date appointmentDate = new java.util.Date(rs.getTimestamp("appointmentDate").getTime());

                PacientRepository repo = new PacientRepository();

                Pacient crPacient = repo.readById(p_id);

                Appointment ap = new Appointment(appointmentDate,crPacient,m,id);
                appointments.add(ap);
            }
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }

        return appointments;
    }

    public List<Appointment> readAll()
    {
        String sqlQuery = "SELECT * FROM appointments";
        List<Appointment> appointments = new ArrayList<>();

        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                UUID id = (UUID)rs.getObject("id");
                UUID m_id = (UUID)rs.getObject("medic_id");
                UUID p_id = (UUID)rs.getObject("pacient_id");
                java.util.Date appointmentDate = new java.util.Date(rs.getTimestamp("appointmentDate").getTime());

                PacientRepository pacientRepo = new PacientRepository();
                MedicRepository medicRepo = new MedicRepository();

                Medic medic = medicRepo.readById(m_id);
                Pacient pacient = pacientRepo.readById(p_id);

                Appointment ap = new Appointment(appointmentDate,pacient,medic,id);
                appointments.add(ap);
            }
        }
        catch(SQLException ex)
        {
            throw new RuntimeException(ex);
        }

        return appointments;
    }


    public int insert(Appointment ap)
    {
        String sqlQuery = "INSERT INTO appointments (id,appointmentDate,pacient_id,medic_id) VALUES(?,?,?,?)";

        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            stmt.setObject(1,ap.getId());
            stmt.setTimestamp(2,new Timestamp(ap.getAppointmentDate().getTime()));
            stmt.setObject(3,ap.getPacient().getId());
            stmt.setObject(4,ap.getMedic().getId());

            return stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int delete(Appointment ap)
    {
        String sqlQuery = "DELETE FROM appointments WHERE id = ?";

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            statement.setObject(1, ap.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(Appointment ap)
    {
        String sqlQuery = "UPDATE appointments SET appointmentDate = ?, pacient_id = ?, medic_id = ? WHERE id = ?";

        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            stmt.setTimestamp(1,new Timestamp(ap.getAppointmentDate().getTime()));
            stmt.setObject(2,ap.getPacient().getId());
            stmt.setObject(3,ap.getMedic().getId());
            stmt.setObject(4,ap.getId());

            return stmt.executeUpdate();
        }
        catch(SQLException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
