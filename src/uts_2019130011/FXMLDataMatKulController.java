/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uts_2019130011;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Regina Viola Mauretha-2019130011
 */
public class FXMLDataMatKulController implements Initializable {

    @FXML
    private Button btnakhir;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnubah;
    @FXML
    private Button btntambah;
    @FXML
    private TableView<MatKulModel> tbvmatkul;
    @FXML
    private TextField searchmatkul;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    

    public void showdata(){
        ObservableList<MatKulModel> data=FXMLDocumentController.dtmatkul.Load();
        if(data!=null){            
            tbvmatkul.getColumns().clear();            
            tbvmatkul.getItems().clear();
            TableColumn col=new TableColumn("kodematkul");
            col.setCellValueFactory(new PropertyValueFactory<MatKulModel, String>("kodematkul"));
            tbvmatkul.getColumns().addAll(col);
            col=new TableColumn("matakuliah");
            col.setCellValueFactory(new PropertyValueFactory<MatKulModel, String>("matakuliah"));
            tbvmatkul.getColumns().addAll(col);
            col=new TableColumn("sks");
            col.setCellValueFactory(new PropertyValueFactory<MatKulModel, Integer>("sks"));
            tbvmatkul.getColumns().addAll(col); 
            col=new TableColumn("status");
            col.setCellValueFactory(new PropertyValueFactory<MatKulModel, String>("status"));
            tbvmatkul.getColumns().addAll(col);
            tbvmatkul.setItems(data);
        }else {  
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvmatkul.getScene().getWindow().hide();;
        }                
    }
    
    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputMatKul.fxml"));    
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace();   }
        showdata();        
        awalklik(event);
    }

    @FXML
    private void ubahklik(ActionEvent event) {
        MatKulModel s= new MatKulModel();
        s=tbvmatkul.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputMatKul.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputMatKulController isidt=(FXMLInputMatKulController)loader.getController();
        isidt.execute(s);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  
        awalklik(event);
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        MatKulModel s= new MatKulModel();       
        s=tbvmatkul.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtmatkul.delete(s.getKodematkul())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
           awalklik(event);
        }
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvmatkul.getSelectionModel().selectFirst();        
        tbvmatkul.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvmatkul.getSelectionModel().selectAboveCell();       
        tbvmatkul.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvmatkul.getSelectionModel().selectBelowCell();        
        tbvmatkul.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvmatkul.getSelectionModel().selectLast();        
        tbvmatkul.requestFocus();
    }

    @FXML
    private void cariData(KeyEvent event) {
        MatKulModel s = new MatKulModel();
        String key = searchmatkul.getText();
        if(key!=""){
        ObservableList<MatKulModel> data=FXMLDocumentController.dtmatkul.CariMatKul(key,key);
        if(data!=null){            
            tbvmatkul.getColumns().clear();            
            tbvmatkul.getItems().clear();
            TableColumn col=new TableColumn("kodematkul");
            col.setCellValueFactory(new PropertyValueFactory<MatKulModel, String>("kodematkul"));
            tbvmatkul.getColumns().addAll(col);
            col=new TableColumn("matakuliah");
            col.setCellValueFactory(new PropertyValueFactory<MatKulModel, String>("matakuliah"));
            tbvmatkul.getColumns().addAll(col);
            col=new TableColumn("sks");
            col.setCellValueFactory(new PropertyValueFactory<MatKulModel, Integer>("sks"));
            tbvmatkul.getColumns().addAll(col); 
            col=new TableColumn("status");
            col.setCellValueFactory(new PropertyValueFactory<MatKulModel, String>("status"));
            tbvmatkul.getColumns().addAll(col);
            tbvmatkul.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvmatkul.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            }
    }
}
