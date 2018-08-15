/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.lahiru.sms.view.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.lahiru.sms.business.CourseBO;
import lk.lahiru.sms.business.StudentBO;
import lk.lahiru.sms.dto.CourseDTO;
import lk.lahiru.sms.dto.StudentDTO;

/**
 * FXML Controller class
 *
 * @author LAHIRU SASANKA
 */
public class CourseController implements Initializable {

    @FXML
    private JFXTextField courseIdText;
    @FXML
    private JFXTextField courseNameText;
    @FXML
    private JFXTextField courseFeeText;
    @FXML
    private JFXTextField courseText;
    @FXML
    private TableView<CourseDTO> tblCourse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//            getAllCourse();
    }    
    
    private void clearText(){
        courseIdText.clear();
        courseNameText.clear();
        courseFeeText.clear();
        courseText.clear();
    }

    @FXML
    private void searchCourse(ActionEvent event) {
        try {
            CourseBO sbo= new CourseBO();
            CourseDTO employee=sbo.serachCourse(Integer.parseInt(courseIdText.getText()));
            if(employee !=null){
                courseIdText.setText(""+employee.getId());
                courseNameText.setText(employee.getCourseName());
                courseFeeText.setText(""+employee.getFee());
                courseText.setText(employee.getDuration());
                courseIdText.setDisable(true);
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR,"Name Search",ButtonType.OK);
                alert.show();
                clearText();
            }
        } catch (Exception ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getAllCourse(){   
        try {
            CourseBO sbo= new CourseBO();
            tblCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
            tblCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
            tblCourse.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("fee"));
            tblCourse.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("duration"));
            ArrayList<CourseDTO> aa=sbo.getAllCourse();
            tblCourse.setItems(FXCollections.observableArrayList(aa));
        } catch (Exception ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addCourse(ActionEvent event) {
        try {
            CourseBO co= new CourseBO();
            int id=Integer.parseInt(courseIdText.getText());
            String name = courseNameText.getText();
            double fee = Double.parseDouble(courseFeeText.getText());
            String tell = courseText.getText();
            CourseDTO studentDTO= new CourseDTO(id, name, fee, tell);
            boolean isAdded=co.addCourse(studentDTO);
            if (isAdded) {
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"Added SuccessFully",ButtonType.OK);
                alert.show();
                getAllCourse();
                clearText();
            } else {
                Alert alert=new Alert(Alert.AlertType.ERROR,"Added UnSuccessFully",ButtonType.OK);
                alert.show();
            }
        } catch (Exception ex) {
            Logger.getLogger(lk.lahiru.sms.view.controller.StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void removeCourse(ActionEvent event) {
        try {
            CourseBO sbo= new CourseBO();
            boolean isDelete=sbo.deleteCourse(courseIdText.getText());
            if(isDelete==true){
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"OK",ButtonType.OK);
                alert.show();
                getAllCourse();
                clearText();
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR,"Error",ButtonType.OK);
                alert.show();
                clearText();
            }
        } catch (Exception ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateCourse(ActionEvent event) {
        try {
            int id=Integer.parseInt(courseIdText.getText());
            String name = courseNameText.getText();
            double fee = Double.parseDouble(courseFeeText.getText());
            String tell = courseText.getText();
            CourseDTO studentDTO= new CourseDTO(id, name, fee, tell);
            CourseBO bO= new CourseBO();
            boolean isUpdate=bO.updateCourse(studentDTO);
            if (isUpdate) {
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"Update OK",ButtonType.OK);
                alert.show();
                getAllCourse();
            } else {
                Alert alert=new Alert(Alert.AlertType.ERROR,"Update Error",ButtonType.OK);
                alert.show();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
