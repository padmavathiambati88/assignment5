import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DepartmentStore {

    public static void main(String[] args) {
        Department department = new Department(1, "Engineering");

        String url = "jdbc:mysql://localhost/departments";
        String username = "your_username";
        String password = "your_password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO department (id, name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, department.getId());
            preparedStatement.setString(2, department.getName());
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Department inserted successfully.");
            } else {
                System.out.println("Failed to insert department.");
            }
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Department {
        private int id;
        private String name;

        public Department(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
