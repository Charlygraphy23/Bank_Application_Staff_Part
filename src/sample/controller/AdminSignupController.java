package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.database.DatabseHandler;
import sample.model.Staff;
import sample.transitions.Shaker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

public class AdminSignupController {

    @FXML
    private JFXTextField firstname;

    @FXML
    private JFXTextField lastname;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXTextField mobileno;

    @FXML
    private JFXDatePicker dateofbirth;

    @FXML
    private JFXTextField age;

    @FXML
    private JFXComboBox<String> designation;

    @FXML
    private JFXComboBox<String> genderCombo;

    @FXML
    private JFXButton uploadPhoto;

    @FXML
    private JFXDatePicker dateofjoinning;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField emailid;

    @FXML
    private JFXButton submitButton;

    @FXML
    private ImageView profileImage;


    @FXML
    void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void maximize(MouseEvent event) {
        Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        if(stage.isMaximized())stage.setMaximized(false);
        else stage.setMaximized(true);
    }

    @FXML
    void minimize(MouseEvent event) {
        Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    private String[] listOfDesignation={"Manager","Assistant Manager","Cashier","Clerk"};
    private String[] listOfGender={"Male","Female","Others"};
    private ObservableList<String> observableList;
    private ObservableList<String> observableListGender;
    private DatabseHandler handler;
    private File file=null;
    private InputStream inputStream=null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        observableList=FXCollections.observableArrayList(listOfDesignation);
        observableListGender=FXCollections.observableArrayList(listOfGender);
        handler=new DatabseHandler();
        ResultSet resultSet=handler.getDesignation();
        while (resultSet.next()){
            if(resultSet.getString(1).equals("Manager")){observableList.remove("Manager");}
            if(resultSet.getString(1).equals("Assistant Manager"))observableList.remove("Assistant Manager");
            if(resultSet.getString(1).equals("Cashier"))observableList.remove("Cashier");
            if(resultSet.getString(1).equals("Clerk"))observableList.remove("Clerk");
        }
        if(observableList.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Don't have any post Open\n Do Check later!!");
            alert.getButtonTypes().setAll(ButtonType.CLOSE);
            alert.setHeaderText(null);
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().setAll(new Image("/sample/assets/download.png"));
            alert.setOnCloseRequest(event -> {
                stage.close();
            });
            alert.show();
        }
        else {
            designation.setItems(observableList);
        }
        genderCombo.setItems(observableListGender);

        dateofbirth.setOnAction(e->{
            if(dateofbirth.getValue()!=null){
                LocalDate currentDate=LocalDate.now();
                LocalDate dateOfBirth=dateofbirth.getValue();
                Period pAge=Period.between(dateOfBirth,currentDate);
                age.setText(String.valueOf(pAge.getYears()));
            }
        });


        mobileno.setOnKeyReleased(e->{
            if(mobileno.getText().matches("\\d+") || e.getCode().equals(KeyCode.TAB) || e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.BACK_SPACE)){
                mobileno.setStyle("-fx-background-color: white; -fx-text-fill: black");
            }
            else {
                mobileno.setStyle("-fx-background-color: red; -fx-text-fill: white");
                new Shaker(mobileno);
            }
        });
        firstname.setOnKeyReleased(e->{
            if(firstname.getText().matches("[A-Z[a-z]]+") || e.getCode().equals(KeyCode.TAB) || e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.BACK_SPACE) || e.getCode().equals(KeyCode.SPACE)){
                firstname.setStyle("-fx-background-color: white; -fx-text-fill: black");
            }
            else {
                firstname.setStyle("-fx-background-color: red; -fx-text-fill: white");
                new Shaker(firstname);
            }
        });
        lastname.setOnKeyReleased(e->{
            if(lastname.getText().matches("[A-Z[a-z]]+") || e.getCode().equals(KeyCode.TAB) || e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.BACK_SPACE) || e.getCode().equals(KeyCode.SPACE)){
                lastname.setStyle("-fx-background-color: white; -fx-text-fill: black");
            }
            else {
                lastname.setStyle("-fx-background-color: red; -fx-text-fill: white");
                new Shaker(lastname);
            }
        });

         uploadPhoto.setOnAction(new UploadPhoto());
         submitButton.setOnAction(new Submit());

    }                               // End of Initialize
    //
    //
    //
    //
    //
    //



    private class UploadPhoto implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            FileChooser fileChooser=new FileChooser();
            file=fileChooser.showOpenDialog(uploadPhoto.getScene().getWindow());
           if(file!=null){
                try {
                    profileImage.setImage(new Image(String.valueOf(file.toURI().toURL())));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Submit implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

        if(emailid.getText().matches("\\S+@\\S+")){
            if(mobileno.getText().matches("^\\d{10}$")){
                if(!firstname.getText().equals("") && !lastname.getText().equals("") && !username.getText().equals("") && !password.getText().equals("") && !mobileno.getText().equals("") && dateofbirth.getValue()!=null && !designation.getValue().equals("") && !genderCombo.getValue().equals("") && dateofjoinning.getValue()!=null &&
                !address.getText().equals("") && !emailid.getText().equals("") && file!=null){
                    try {
                        inputStream=new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    handler=new DatabseHandler();
                    Staff staff=new Staff(firstname.getText(),lastname.getText(),username.getText(),password.getText(),mobileno.getText(),dateofbirth.getValue().toString(),age.getText(),designation.getValue(),genderCombo.getValue(),dateofjoinning.getValue().toString(),address.getText(),emailid.getText(),inputStream);
                    try {
                        handler.setStaffData(staff);
                        Alert alert=new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Staff Details Entered Successfully");
                        alert.getButtonTypes().setAll(ButtonType.CLOSE);
                        alert.setHeaderText(null);
                        Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().setAll(new Image("/sample/assets/u.png"));
                        alert.setOnCloseRequest(e->{
                            try {
                                clearAll();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            } catch (ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        });
                        alert.show();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("!!! Enter Details Correctly !!!");
                    alert.getButtonTypes().setAll(ButtonType.CLOSE);
                    alert.setHeaderText(null);
                    Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().setAll(new Image("/sample/assets/download.png"));
                    alert.show();
                }
            }else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Mobile No should be 10 Digits");
                alert.getButtonTypes().setAll(ButtonType.CLOSE);
                alert.setHeaderText(null);
                Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().setAll(new Image("/sample/assets/download.png"));
                alert.show();
            }
        }else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter Email Correctly");
            alert.getButtonTypes().setAll(ButtonType.CLOSE);
            alert.setHeaderText(null);
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().setAll(new Image("/sample/assets/download.png"));
            alert.show();
        }

        }
    }

    private void clearAll() throws SQLException, ClassNotFoundException {
        handler=new DatabseHandler();
        ResultSet resultSet=handler.getDesignation();
        while (resultSet.next()){
            if(resultSet.getString(1).equals("Manager")){observableList.remove("Manager");}
            if(resultSet.getString(1).equals("Assistant Manager"))observableList.remove("Assistant Manager");
            if(resultSet.getString(1).equals("Cashier"))observableList.remove("Cashier");
            if(resultSet.getString(1).equals("Clerk"))observableList.remove("Clerk");
        }
        if(observableList.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Don't have any post Open\n Do Check later!!");
            alert.getButtonTypes().setAll(ButtonType.CLOSE);
            alert.setHeaderText(null);
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().setAll(new Image("/sample/assets/download.png"));
            alert.setOnCloseRequest(event -> {
                stage.close();
            });
            alert.show();
        }
        else {
            designation.setItems(observableList);
        }

        firstname.setText("");lastname.setText("");username.setText("");password.setText("");mobileno.setText("");dateofbirth.getEditor().clear();
        age.setText("");designation.getSelectionModel().clearSelection();genderCombo.getSelectionModel().clearSelection();dateofjoinning.getEditor().clear();
        address.setText("");emailid.setText("");file=null;profileImage.setImage(new Image("/sample/assets/1200px-Google_Contacts_icon.svg.png"));inputStream=null;
    }
}
