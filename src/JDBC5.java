// insert the multiple row taking user input using prepared statement;
import java.sql.*;
import java.util.Scanner;

public class JDBC5 {
    private static final String url = "jdbc:mysql://localhost:3306/college";
    private static final String username = "root";
    private static final String password = "Sanc@123";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
            return;
        }

        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                Scanner scanner = new Scanner(System.in)
        ) {
            String query = "insert into student(id, name, age, marks) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            while (true) {
                System.out.print("Enter student ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // consume newline

                System.out.print("Enter student Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter student Age: ");
                int age = scanner.nextInt();

                System.out.print("Enter student Marks: ");
                double marks = scanner.nextDouble();

                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, age);
                preparedStatement.setDouble(4, marks);

                int rowsInserted = preparedStatement.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted.");

                System.out.print("Do you want to insert another record? (yes/no): ");
                scanner.nextLine(); // consume newline
                String choice = scanner.nextLine();
                if (!choice.equalsIgnoreCase("yes")) {
                    break;
                }
            }

            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
