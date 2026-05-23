import java.sql.*;

private static final String URL = "jdbc:mysql://localhost:3306/demo_db";
private static final String USER = "root";
private static final String PASSWORD = "Nile@2002";

void main() {

    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

        IO.println("Connected to database successfully");

        // Start transaction
        conn.setAutoCommit(false);

        try {

            // Insert into orders table
            int orderId = insertOrder(conn, 101, "alice01", 2000.0);

            // Insert into order_items table
            insertOrderItems(conn, orderId, "Laptop", 2, 2000);

            // Save permanently
            conn.commit();

            IO.println("Transaction committed successfully");

        } catch (Exception e) {

            // Undo all changes
            conn.rollback();

            IO.println("Transaction rolled back");

            e.printStackTrace();
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private static void insertOrderItems(Connection conn,
                                     int orderId,
                                     String productName,
                                     int quantity,
                                     double price) throws SQLException {

    String sql = "INSERT INTO order_items " +
            "(order_id, product_name, quantity, price) " +
            "VALUES (?, ?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, orderId);
        pstmt.setString(2, productName);
        pstmt.setInt(3, quantity);
        pstmt.setDouble(4, price);

        int rows = pstmt.executeUpdate();

        IO.println("Inserted " + rows +
                " rows into order_items");
    }
}

private static int insertOrder(Connection conn,
                               int customerId,
                               String customerName,
                               double price) throws SQLException {

    String sql = "INSERT INTO orders " +
            "(user_id, customer_name, total_amount) " +
            "VALUES (?, ?, ?)";

    try (PreparedStatement pstmt =
                 conn.prepareStatement(sql,
                         Statement.RETURN_GENERATED_KEYS)) {

        pstmt.setInt(1, customerId);
        pstmt.setString(2, customerName);
        pstmt.setDouble(3, price);

        int rows = pstmt.executeUpdate();

        IO.println("Inserted " + rows +
                " rows into orders");

        try (ResultSet rs = pstmt.getGeneratedKeys()) {

            if (rs.next()) {

                int orderId = rs.getInt(1);

                IO.println("ORDER ID: " + orderId);

                return orderId;
            }
        }
    }

    throw new SQLException("Order insertion failed");
}