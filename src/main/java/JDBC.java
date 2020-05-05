import java.sql.*;

public class JDBC {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/umuzi";
        String user = "root";
        String password = "password";
        Connection connection = null;

        try {
            if(!Driver.isRegistered()){
                Driver.register();//Register postgres
            }

            connection = DriverManager.getConnection(url, user, password);//Connects to the database
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from customers");//query data from the customer table
            while (resultSet.next()) { //Printing customer data from customer table
                System.out.println(
                        resultSet.getString("customerid") + "\t"
                                + resultSet.getString("firstname") + "\t"
                                + resultSet.getString("lastname") + "\t"
                                + resultSet.getString("gender") + "\t"
                                + resultSet.getString("address") + "\t"
                                + resultSet.getString("phone") + "\t"
                                + resultSet.getString("email") + "\t"
                                + resultSet.getString("city") + "\t"
                                + resultSet.getString("country")
                );
            }
        }catch(SQLException e){
            e.printStackTrace();//Handle errors for JDBC
        }catch (Exception ex){
            ex.printStackTrace();//Handle errors for Drivers
        }finally {
            try{
                if(connection!=null)
                    connection.close();//Close database connection
            }catch(SQLException se){
                se.printStackTrace();//Handle errors for JDBC
            }
        }

    }

}