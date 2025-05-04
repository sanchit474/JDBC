import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
//InsertStudentFromInput     //USING STATEMENT
public class JDBC3 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "Sanc@123";

        Scanner sc = new Scanner(System.in);

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            // Ask how many students to insert
            System.out.print("How many students do you want to insert? ");
            int n = sc.nextInt();
            sc.nextLine(); // consume newline

            for (int i = 1; i <= n; i++) {
                System.out.println("Enter details for student " + i + ":");
                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Age: ");
                int age = sc.nextInt();

                System.out.print("Marks: ");
                int marks = sc.nextInt();
                sc.nextLine(); // consume newline

                String query = String.format(
                        "INSERT INTO student(name, age, marks) VALUES('%s', %d, %d)",
                        name, age, marks
                );

                int rows = statement.executeUpdate(query);
                System.out.println("Inserted " + name + " (" + rows + " row)\n");
            }

            // Clean up
            statement.close();
            connection.close();
            sc.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}