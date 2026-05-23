import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TranscationDemo {

    private static final String URL = "jdbc:mysql://localhost:3306/demo_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Nile@2002";

    static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);) {
            System.out.println("Connected to database successfully");

            // Order , OrderedItems

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}