package ro.unibuc.doctorOffice.repository;

import ro.unibuc.doctorOffice.config.DatabaseConnection;
import ro.unibuc.doctorOffice.model.Pacient;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.sql.ResultSet;

public final class PacientRepository
{
    public List<Pacient> readAll() {
        String sqlQuery = "SELECT * FROM pacient";
        List<Pacient> pacients = new ArrayList<>();

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Pacient pacient = createPacientFromResultSet(resultSet);
                pacients.add(pacient);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pacients;
    }

    public Pacient readByName(String firstName, String lastName) {
        String sqlQuery = "SELECT * FROM pacient WHERE firstName = ? AND lastName = ?";
        Pacient pacient = null;

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    pacient = createPacientFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pacient;
    }

    public Pacient readById(UUID id) {
        String sqlQuery = "SELECT * FROM pacient WHERE id = ?";
        Pacient pacient = null;

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            statement.setObject(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    pacient = createPacientFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pacient;
    }

    private Pacient createPacientFromResultSet(ResultSet resultSet) throws SQLException {
        UUID id = (UUID)resultSet.getObject("id");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String phoneNumber = resultSet.getString("phoneNumber");
        String address = resultSet.getString("address");
        java.sql.Date dateOfBirth = resultSet.getDate("dateOfBirth");

        return new Pacient(firstName, lastName, phoneNumber, address, new java.util.Date(dateOfBirth.getTime()),id);
    }

    public int insert(Pacient p)
    {
        String sqlQuery = "INSERT INTO pacient(id,firstName,lastName,phoneNumber,address,dateOfBirth VALUES(?,?,?,?,?,?)";

        try(PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            statement.setObject(1,p.getId());
            statement.setString(2,p.getFirstName());
            statement.setString(3,p.getLastName());
            statement.setString(4,p.getPhoneNumber());
            statement.setString(5, p.getAddress());
            statement.setDate(6,new java.sql.Date(p.getDateOfBirth().getTime()));

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(Pacient p) {
        String sqlQuery = "UPDATE pacient SET firstName = ?, lastName = ?, phoneNumber = ?, address = ?, dateOfBirth = ? WHERE id = ?";

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            statement.setString(1, p.getFirstName());
            statement.setString(2, p.getLastName());
            statement.setString(3, p.getPhoneNumber());
            statement.setString(4, p.getAddress());
            statement.setDate(5, new java.sql.Date(p.getDateOfBirth().getTime()));
            statement.setObject(6, p.getId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(Pacient p) {
        String sqlQuery = "DELETE FROM pacient WHERE id = ?";

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            statement.setObject(1, p.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
