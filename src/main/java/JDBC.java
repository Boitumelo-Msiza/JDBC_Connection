import java.sql.*;

public class JDBC {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/umuzi";
        String username = "root";
        String password = "";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            Statement stmt = connection.createStatement();

            //Selecting everything from customer table
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customers");
            while (rs.next()) {
                String CustomerID = rs.getString("CustomerID");
                System.out.println(CustomerID + "\n");
                String FirstName = rs.getString("FirstName");
                System.out.println(FirstName + "\n");
                String lastName = rs.getString("Lname");
                System.out.println(lastName + "\n");
                String Gender = rs.getString("Gender");
                System.out.println(Gender + "\n");
                String Address = rs.getString("Address");
                System.out.println(Address + "\n");
                String Phone = rs.getString("Phone");
                System.out.println(Phone + "\n");
                String City = rs.getString("City");
                System.out.println(City + "\n");
                String Country = rs.getString("Country");
                System.out.println(Country + "\n");
            }
                //Selecting first names from customers
            ResultSet rn = stmt.executeQuery("SELECT FirstName FROM Customers");
            while (rn.next()) {
                String lastName = rn.getString("Lname");
                System.out.println(lastName + "\n");
            }

                //Selecting first name from customer id=1
            ResultSet cn = stmt.executeQuery("SELECT FirstName FROM Customers WHERE CustomerID = 1");
            while (cn.next()) {
                String FirstName = cn.getString("Lname");
                System.out.println(FirstName + "\n");
            }

            //update customers first name and last name
            Statement statement = connection.createStatement();
            String sql  = "UPDATE Customers SET FirstName = 'Lerato', LastName = 'Mabitso' WHERE CustomerID = 1;";
            int rowsAffected    = statement.executeUpdate(sql);

            //Deleting from customer where id=2
            String    sql1       = "DELETE FROM Customers WHERE CustomerID = 2;";
            int rowsAffected1    = statement.executeUpdate(sql1);

        //Selecting all unique statuses from the Orders table
            stmt = connection.createStatement();
            String query = "SELECT COUNT(DISTINCT STATUS) FROM Orders;";
            ResultSet count=stmt.executeQuery(query);
            //Extact result from ResultSet count
            while(count.next()){
                System.out.println("COUNT(*)="+count.getInt("COUNT(*)"));
            }
            //Returning the MAXIMUM payment made on the PAYMENTS table
            ResultSet max = stmt.executeQuery("SELECT MAX(Amount) as MaxAmount FROM Payments;");
            while (max.next())
            {
                int amnt = max.getInt("MaxAmount");
                System.out.println("Max Amount: " + amnt);

            }


        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }

}
