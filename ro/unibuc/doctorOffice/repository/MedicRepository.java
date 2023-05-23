package ro.unibuc.doctorOffice.repository;
import ro.unibuc.doctorOffice.config.DatabaseConnection;
import ro.unibuc.doctorOffice.model.Medic;
import ro.unibuc.doctorOffice.model.Specialization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public final class MedicRepository
{
    private Medic createMedicFromResultSet(ResultSet rs) throws SQLException
    {
        UUID id = (UUID)rs.getObject("id");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String phoneNumber = rs.getString("phoneNumber");
        String specializationValue = rs.getString("specialization");
        Specialization specialization = Specialization.valueOf(specializationValue.toUpperCase());


        return new Medic(firstName,lastName,specialization,phoneNumber,id);
    }


    public List<Medic> readAll()
    {
        String sqlQuery = "SELECT * FROM medics";
        List<Medic> pacients = new ArrayList<>();

        try(PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                Medic pacient = createMedicFromResultSet(resultSet);
                pacients.add(pacient);
            }
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }

        return pacients;
    }

    public Medic readById(UUID id)
    {
        String sqlQuery = "SELECT * FROM medics WHERE id = ?";
        Medic medic = null;

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            statement.setObject(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    medic = createMedicFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return medic;
    }

    public Medic readByName(String firstName, String lastName) {
        String sqlQuery = "SELECT * FROM pacient WHERE firstName = ? AND lastName = ?";
        Medic medic = null;

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    medic = createMedicFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return medic;
    }

    public int delete(Medic m) {
        String sqlQuery = "DELETE FROM medics WHERE id = ?";

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            statement.setObject(1, m.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(Medic m)
    {
        String sqlQuery = "UPDATE medics SET firstName = ?, lastName = ?, specialization = ?, phoneNumber = ? WHERE id = ?";

        try(PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)){
            statement.setString(1,m.getFirstName());
            statement.setString(2,m.getLastName());
            statement.setObject(3,m.getSpecialization());
            statement.setString(4,m.getPhoneNumber());
            statement.setObject(5,m.getId());

            return statement.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public int insert(Medic m)
    {
        String sqlQuery = "INSERT INTO medics(firstName,lastName,specialization,phoneNumber,id) VALUES(?,?,?,?,?)";

        try(PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            statement.setString(1,m.getFirstName());
            statement.setString(2,m.getLastName());
            statement.setObject(3,m.getSpecialization(),java.sql.Types.OTHER);
            statement.setString(4,m.getPhoneNumber());
            statement.setObject(5,m.getId());

            return statement.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
