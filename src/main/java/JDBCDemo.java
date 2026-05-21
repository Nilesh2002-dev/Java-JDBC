import java.sql.*;

public class JDBCDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/demo_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Nile@2002";


    static void main(String[] args) {

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);){
            System.out.println("Connected to database successfully");
            //insertStudent(conn , "nilesh" , "nile@2002" );
            updateStudent(conn , 3 , "vinay" , "Vinay@gamil.com");
            selectStudents(conn);
            deleteStudent(conn , 4 );
            deleteStudent(conn , 5 );
            deleteStudent(conn , 6 );
            deleteStudent(conn , 7 );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static  void insertStudent(Connection conn,String name,String email){

        String sql = "INSERT INTO student(name,email) values('"+name+"','"+email+"')";
        try (Statement stmt = conn.createStatement()){
            int rows = stmt.executeUpdate(sql);
            System.out.println("Inserted: "+ rows +" rows into student database successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void selectStudents (Connection conn){
        String sql = "SELECT * FROM student";
        try (Statement stmt = conn.createStatement()){
           ResultSet resultSet = stmt.executeQuery(sql);
            System.out.println("All students List :");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println(id + " : " + name + " : " + email);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void updateStudent(Connection conn,int id ,String name,String email  ){
        String sql = "UPDATE student SET name = '"+name +"' , email = '" + email + "' where ID = " + id;
        try (Statement stmt = conn.createStatement()){
            int rows = stmt.executeUpdate(sql);
            System.out.println("UPDATED: "+ rows +" rows into student database successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void deleteStudent(Connection conn,int id ){
        String sql = "DELETE FROM student where id = " + id;
        try (Statement stmt = conn.createStatement()){
            int rows = stmt.executeUpdate(sql);
            System.out.println("DELETED: "+ rows +" rows into student database successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}





/*
    Connection conn = null ;
    try {
             conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                System.out.println("Connection closed successfully");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    */