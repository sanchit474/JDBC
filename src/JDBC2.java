import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//retrieve data of the table     // USING STATEMENT
public class JDBC2 {
    private static final String url ="jdbc:mysql://localhost:3306/college";
    private static final String username = "root";
    private static final String password = "Sanc@123";

    public static void main(String[] args) throws  Exception {
        //LOAD DRIVER
        Class.forName("com.mysql.cj.jdbc.Driver");

        //ESTABLISH CONNECTION
        Connection connection = DriverManager.getConnection(url , username, password);

        //CREATE STATEMENT
        Statement statement = connection.createStatement();
        String query = "select * from student ";
        ResultSet re = statement.executeQuery(query);
        while(re.next()) {
            System.out.print(re.getInt("id"));
            System.out.print(" ");
            System.out.print(re.getString("name"));
            System.out.print(" ");
            System.out.print(re.getInt("age"));
            System.out.print(" ");
            System.out.print(re.getDouble("marks"));
            System.out.println();
        }
        statement.close();
        connection.close();
    }
}
