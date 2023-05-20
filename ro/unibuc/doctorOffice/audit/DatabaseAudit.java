package ro.unibuc.doctorOffice.audit;
import ro.unibuc.doctorOffice.config.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class DatabaseAudit
{
    private DatabaseAudit() {}

    public static int send(String nameAction,Date dateAction)
    {
        String sqlQuery = "INSERT INTO audit(name_action,datetime_action) VALUES(?,?)";

        try(PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
        {
            Date current = new Date();
            Timestamp timestamp = new Timestamp(current.getTime());
            statement.setString(1,nameAction);
            statement.setTimestamp(2, timestamp);

            return statement.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public static String printByDate(Date dateAction) {
        String auditMsg = null;
        String sqlQuery = "SELECT * FROM audit WHERE datetime_action = ?";

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            Date current = new Date();
            Timestamp timestamp = new Timestamp(current.getTime());
            statement.setTimestamp(1, timestamp);

            ResultSet resultDb = statement.executeQuery();

            if (resultDb.next()) {
                StringBuilder auditMsgBuilder = new StringBuilder("Audit{");
                auditMsgBuilder.append("name_action=").append(resultDb.getString("name_action"));
                auditMsgBuilder.append(", timestamp=").append(resultDb.getTimestamp("datetime_action"));
                auditMsgBuilder.append("}");

                auditMsg = auditMsgBuilder.toString();

                resultDb.close();

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return auditMsg;
    }


    public static List<String> printAll() {
        List<String> auditMessages = new ArrayList<>();
        String sqlQuery = "SELECT * FROM audit";

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            ResultSet resultDb = statement.executeQuery();

            while (resultDb.next()) {
                StringBuilder auditMsgBuilder = new StringBuilder("Audit{");
                auditMsgBuilder.append("name_action=").append(resultDb.getString("name_action"));
                auditMsgBuilder.append(", timestamp=").append(resultDb.getTimestamp("datetime_action"));
                auditMsgBuilder.append("}");

                auditMessages.add(auditMsgBuilder.toString());
            }

            resultDb.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return auditMessages;
    }

}
