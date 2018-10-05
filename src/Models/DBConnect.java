package Models;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {
    private static String userName = "root";
    private static String password = "";

    public static ArrayList<String> getPhoneManufacturers() throws SQLException {
        ArrayList<String> manufacturers = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones?useSSL=false",userName, password);

            //2. create a statement object
            statement = conn.createStatement();

            //3. create and execute the query
            resultSet = statement.executeQuery("SELECT * FROM manufacturers ORDER BY manufacturer");

            //4. loop over the results and add to the arraylist
            while (resultSet.next()){
                manufacturers.add(resultSet.getString("manufacturer"));
            }
        }catch(SQLException e){
            System.err.println(e);
        }
        finally{
            if(conn != null){
                conn.close();
            }
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }

        return manufacturers;
    }

    public static ArrayList<Phone> getPhones() throws SQLException {
        ArrayList<Phone> Phones = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones?useSSL=false",userName, password);

            //2. create a statement object
            statement = conn.createStatement();

            //3. create and execute the query
            resultSet = statement.executeQuery("SELECT * FROM phones");

            //4. loop over the results and add to the arraylist
            while (resultSet.next()){
                Phone newPhone = new Phone(
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getString("os"),
                        resultSet.getDouble("screensize"),
                        resultSet.getDouble("memory"),
                        resultSet.getDouble("frontCamRes"),
                        resultSet.getDouble("rearCamRes"));
                Phones.add(newPhone);
            }
        }catch(SQLException e){
            System.err.println(e);
        }
        finally{
            if(conn != null){
                conn.close();
            }
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }

        return Phones;
    }

    public static ArrayList<String> getOSs() throws SQLException {
        ArrayList<String> manufacturers = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones?useSSL=false",userName, password);

            //2. create a statement object
            statement = conn.createStatement();

            //3. create and execute the query
            resultSet = statement.executeQuery("SELECT DISTINCT os FROM manufacturers");

            //4. loop over the results and add to the arraylist
            while (resultSet.next()){
                manufacturers.add(resultSet.getString("os"));
            }
        }catch(SQLException e){
            System.err.println(e);
        }
        finally{
            if(conn != null){
                conn.close();
            }
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }

        return manufacturers;
    }

    public static String getOSForManufacturer(String manufacturer){
        String os = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try{
            //try to connect with the db
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones?useSSL=false", userName, password);

            //create an sql statement
            String sql = "SELECT os FROM manufacturers WHERE manufacturer=?";

            //create the prepared statement
            ps = conn.prepareStatement(sql);

            //bind values to the params
            ps.setString(1, manufacturer);

            //get the results
            resultSet = ps.executeQuery();

            //loop over the result(s)
            resultSet.next();
            os = resultSet.getString("os");
        }catch (SQLException e){
            System.err.println(e);
        }
        return os;
    }

    public static void insertPhoneIntoDB(Phone newPhone) throws SQLException {
        Connection conn = null;
        //prevent sql injection instead of using normal statement
        PreparedStatement ps = null;
        try{
            //1. Connect to DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones?useSSL=false",userName,password);
            //2. create SQL statement
            String sql = "INSERT INTO phones (make, model, os, screenSize, memory,frontCamRes," +
                    "rearCamRes) VALUES (?, ?, ?, ?, ?, ?, ?);";
            //3. create a prepared statement
            ps = conn.prepareStatement(sql);
            //4. bind params
            ps.setString(1, newPhone.getMake());
            ps.setString(2, newPhone.getModel());
            ps.setString(3, newPhone.getOs());
            ps.setDouble(4, newPhone.getScreensize());
            ps.setDouble(5, newPhone.getMemory());
            ps.setDouble(6, newPhone.getFrontCameraRes());
            ps.setDouble(7, newPhone.getRearCameraRes());
            //5. execute and update
            ps.executeUpdate();
        } catch (SQLException e){
            System.err.println(e);
        } finally {
            if(conn != null){
                conn.close();
            }
            if(ps != null){
                ps.close();
            }
        }
    }
}
