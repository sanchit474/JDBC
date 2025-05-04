import java.sql.*;
//INSERTING VALUES TO DB    // USING STATEMENT
class JDBC0 {
    private static final String url ="jdbc:mysql://localhost:3306/college";
    private static final String username = "root";
    private static final String password = "Sanc@123" ;
    public static void main(String[] args) throws Exception {
        //LOAD DRIVER
        Class.forName("com.mysql.cj.jdbc.Driver");

        //ESTABLISH CONNECTION
        Connection connection = DriverManager.getConnection(url , username, password);

        //CREATE STATEMENT
        Statement statement = connection.createStatement();

        // insert values
        String query = String.format(
                "INSERT INTO student(id, name, age, marks) VALUES(%d, '%s', %d, %f)",
               1, "shivam", 24, 87.0
        );

        int rowsEffected = statement.executeUpdate(query);
        System.out.println(rowsEffected + " row(s) inserted.");

        //Close the connection
        statement.close();
        connection.close();
    }
}