package CommonUtils;

import java.sql.*;

public class DBUtils {
    static final String JDBC_DRIVER = "org.h2.Driver";


    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";
    Connection connection;
    Statement statement;

    public void connection(String DB) throws ClassNotFoundException {
        final String DB_URL = "jdbc:h2:~/"+DB;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertRecords(String tableName) throws SQLException {
        try {
            String createTable = "CREATE TABLE IF NOT EXISTS "+tableName+" (id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "account_number VARCHAR(20) UNIQUE, balance DECIMAL(10,2), customer_name VARCHAR(50) NOT NULL);";
            statement.executeUpdate(createTable);
            String accountNumber = "1234567890"; // Validated account number
            double balance = 1000.00; // Validated balance
            String customerName = "Testing H2 database"; // Validated customer name

            String insertQuery = "INSERT INTO "+tableName+" (account_number, balance, customer_name) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, accountNumber);
            preparedStatement.setDouble(2, balance);
            preparedStatement.setString(3, customerName);

            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void validateRecords(String tableName) throws SQLException {
            String sql = "SELECT id, account_number, balance, customer_name FROM "+tableName;
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String accNum=resultSet.getString("account_number");
                double bal = resultSet.getDouble("balance");
                String cName=resultSet.getString("customer_name");
                System.out.println("ID: "+id+" Account Number: "+accNum+" Balance: "+bal+" Customer Name: "+cName);
        }
    }
    public void close() throws SQLException{
        if(statement!= null)
            statement.close();
        if(connection!=null)
            connection.close();
        }
    }
