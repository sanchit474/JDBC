// insert the row using prepared statement;
import java.sql.*;

class JDBC4{
    private static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String url ="jdbc:mysql://localhost:3306/college";
    private static final String username = "root";
    private static final String password = "Sanc@123";

    public static void main(String[] args) {
        try{
            Class.forName(Driver);                                                                     // load driver
        }catch (ClassNotFoundException e ){
            System.out.println(e.getMessage());
        }

        try(Connection connection  = DriverManager.getConnection(url, username, password);            // create connection
        ) {
            String query = "INSERT INTO student (id, name, age, marks) VALUES (?, ?, ?, ?)";         // SQL Query    to insert update or delete
            PreparedStatement preparedStatement = connection.prepareStatement(query);                //create prepared statement;

            preparedStatement.setInt(1, 8);                                         // set the values means set the values to parameters
            preparedStatement.setString(2, "bob");
            preparedStatement.setInt(3, 17);
            preparedStatement.setDouble(4, 81);

            int re = preparedStatement.executeUpdate();                                           // result effected
            System.out.println("row inserted : "+ re);

            preparedStatement.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
}