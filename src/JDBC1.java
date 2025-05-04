import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//INSERT MULTIPLE ROWS     // USING STATEMENT
public class JDBC1 {
    private static final String url ="jdbc:mysql://localhost:3306/college";
    private static final String username = "root";
    private static final String password = "Sanc@123" ;
    public static void main(String[] args) throws Exception {
        String[][] students = {
                {"2", "VISHAL", "23", "90"},
                {"3", "AARAV", "22", "88"},
                {"4","NEHA", "24", "92"}
        };

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url , username, password);
        Statement statement = connection.createStatement();
        // Loop to insert each student
        for (String[] student : students) {
            int id = Integer.parseInt(student[0]);
            String name = student[1];
            int age = Integer.parseInt(student[2]);
            int marks = Integer.parseInt(student[3]);

            String query = String.format(
                    "INSERT INTO student(name, age, marks) VALUES('%s', %d, %d)",
                    name, age, marks
            );

            int rows = statement.executeUpdate(query);
            System.out.println("Inserted: " + name + " (" + rows + " row)");
        }
        ResultSet re = statement.executeQuery("select * from student ");
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
    }
}
