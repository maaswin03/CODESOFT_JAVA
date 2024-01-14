package com.example.studentmanagementsystem;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import java.sql.*;

public class HelloController {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/studentmanagemnetsystem";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "#Aswin2005";

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField pno;

    @FXML
    private TextField pname;

    @FXML
    private TextField rno;

    @FXML
    private TextArea address;

    @FXML
    private DatePicker dob;

    @FXML
    private Label label1;

    @FXML
    private CheckBox checkBox1;

    @FXML
    private CheckBox checkBox2;

    @FXML
    private TextField search;

    @FXML
    protected void editinfo() throws Exception {
        String firstname = fname.getText();
        String lastname = lname.getText();
        String parentname = pname.getText();
        String dateofbirth = (dob.getValue() != null) ? dob.getValue().toString() : "";
        String phonenumber = pno.getText();
        String studentAddress = address.getText();
        String gender;

        if (firstname.isEmpty() || lastname.isEmpty() || parentname.isEmpty() ||
                dateofbirth.isEmpty() || phonenumber.isEmpty() || studentAddress.isEmpty()) {
            label1.setText("Please enter all the fields");
            return;
        }

        if (!checkBox1.isSelected() && !checkBox2.isSelected()) {
            label1.setText("Please select a gender");
            return;
        }

        gender = checkBox1.isSelected() ? "male" : "female";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            String sql = "UPDATE infromation SET firstname=?, lastname=?, parentname=?, dateofbirth=?, phonenumber=?, gender=?, address=? WHERE rollno=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, firstname);
                statement.setString(2, lastname);
                statement.setString(3, parentname);
                statement.setString(4, dateofbirth);
                statement.setString(5, phonenumber);
                statement.setString(6, gender);
                statement.setString(7, studentAddress);
                statement.setString(8, rno.getText());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    label1.setText("INFO UPDATED SUCCESSFULLY");
                } else {
                    label1.setText("No records found for the given Rollno");
                }
            }
        } catch (SQLException e) {
            label1.setText("Error occurred while updating information");
        }
    }


    @FXML
    protected void addinfo() throws Exception {
        String firstname = fname.getText();
        String lastname = lname.getText();
        String rollno = rno.getText();
        String parentname = pname.getText();
        String dateofbirth = dob.getValue().toString();
        String phonenumber = pno.getText();
        String studentAddress = address.getText();
        String gender;


        if (firstname.isEmpty() || lastname.isEmpty() || rollno.isEmpty() || parentname.isEmpty() ||
                dateofbirth.isEmpty() || phonenumber.isEmpty() || studentAddress.isEmpty()) {
            label1.setText("Please enter all the fields");
            return;
        }

        if (!checkBox1.isSelected() && !checkBox2.isSelected()) {
            label1.setText("Please select a gender");
            return;
        }

        gender = checkBox1.isSelected() ? "male" : "female";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO infromation (firstname, lastname, rollno, parentname, dateofbirth, phonenumber, gender, address) VALUES (?,?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, firstname);
                statement.setString(2, lastname);
                statement.setString(3, rollno);
                statement.setString(4, parentname);
                statement.setString(5, dateofbirth);
                statement.setString(6, phonenumber);
                statement.setString(7, gender);
                statement.setString(8, studentAddress);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    label1.setText("INSERTED SUCCESSFULLY");
                } else {
                    label1.setText("Insertion Failed");
                }
            }
        }
    }


    @FXML
    protected void search() throws SQLException {
        String value = search.getText();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM infromation WHERE Rollno=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, value);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        fname.setText(resultSet.getString("firstname"));
                        lname.setText(resultSet.getString("lastname"));
                        rno.setText(resultSet.getString("rollno"));
                        pname.setText(resultSet.getString("parentname"));
                        dob.setValue(resultSet.getDate("dateofbirth").toLocalDate());
                        pno.setText(resultSet.getString("phonenumber"));
                        checkBox1.setSelected(resultSet.getString("gender").equals("male"));
                        checkBox2.setSelected(resultSet.getString("gender").equals("female"));
                        address.setText(resultSet.getString("address"));
                    } else {
                        label1.setText("Student Not Found");
                    }
                }
            }
        }
    }
}