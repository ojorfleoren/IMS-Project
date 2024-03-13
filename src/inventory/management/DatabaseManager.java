package inventory.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager(Connection connection) {
        this.connection = connection;
    }

    public String checkLogin(String username, String password, String userType) {
        public String LogIn(Connection connection, String username, String password, String userType) {
        String accountType = "";
        try {
            String query = "SELECT AccountType FROM User WHERE UserName=? AND Password=? AND AccountType=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, userType);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        accountType = resultSet.getString("AccountType");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountType;
    }
}
