import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

// BATCH PROCESSING
public class JDBC7 {
    private static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/college";
    private static final String username = "root";
    private static final String password = "Sanc@123";

    public static void main(String[] args) throws Exception {
        Class.forName(Driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        String query = "INSERT INTO student(id, name, age, marks) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter id: ");
            int id = sc.nextInt();
            System.out.print("Enter name: ");
            String name = sc.next();
            System.out.print("Enter age: ");
            int age = sc.nextInt();
            System.out.print("Enter marks: ");
            double marks = sc.nextDouble();

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setDouble(4, marks);
            preparedStatement.addBatch(); // ✅ correct method call

            System.out.print("Do you want to insert another record? (yes/no): ");
            sc.nextLine(); // consume newline
            String choice = sc.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                break;
            }
        }

        int[] results = preparedStatement.executeBatch(); // ✅ execute once, after loop
        System.out.println("Batch insert completed. Rows inserted: " + results.length);

        preparedStatement.close();
        connection.close();
        sc.close();
    }
}
