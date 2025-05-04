//delete the row from the database using prepared statement
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC6 {
    private static final String Driver ="com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/college";
    private static final String username = "root";
    private static final String password = "Sanc@123";

    public static void main(String[] args) {
        try {
            Class.forName(Driver);
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try (Connection connection = DriverManager.getConnection(url, username, password);){
            String query = "DELETE FROM student WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,10);
            int re = preparedStatement.executeUpdate();
            System.out.println(" removed row : " + re);

            preparedStatement.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
