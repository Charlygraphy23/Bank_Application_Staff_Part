package sample.database;

import sample.model.Staff;

import java.sql.*;

public class DatabseHandler {
    private PreparedStatement preparedStatement;
    private Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
        return connection;
    }

    public ResultSet getDesignation() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=null;

        preparedStatement=getConnection().prepareStatement("SELECT designation from staffs");
        resultSet=preparedStatement.executeQuery();
        return resultSet;
    }

    public void setStaffData(Staff staff) throws SQLException, ClassNotFoundException {
        preparedStatement=getConnection().prepareStatement("INSERT INTO staffs(firstname,lastname,username,password,mobileno,dob,age,designation,gender,dateofjoinning,address,emailid,photo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,staff.getFirstname());
        preparedStatement.setString(2,staff.getLastname());
        preparedStatement.setString(3,staff.getUsername());
        preparedStatement.setString(4,staff.getPassword());
        preparedStatement.setString(5,staff.getMobileno());
        preparedStatement.setString(6,staff.getDob());
        preparedStatement.setString(7,staff.getAge());
        preparedStatement.setString(8,staff.getDesignation());
        preparedStatement.setString(9,staff.getGender());
        preparedStatement.setString(10,staff.getDateofjoinning());
        preparedStatement.setString(11,staff.getAddress());
        preparedStatement.setString(12,staff.getEmailId());
        preparedStatement.setBinaryStream(13,staff.getPhoto());
        preparedStatement.execute();
        preparedStatement.close();
    }
}
