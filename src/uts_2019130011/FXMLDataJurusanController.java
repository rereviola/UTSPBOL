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
public class FXMLDataJurusanController implements Initializable {

    @FXML
    private TableView<JurusanModel> tbvjurusan;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;
    @FXML
    private TextField searchjurusan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    

    public void showdata(){
        ObservableList<JurusanModel> data=FXMLDocumentController.dtjurusan.Load();
        if(data!=null){            
            tbvjurusan.getColumns().clear();            
            tbvjurusan.getItems().clear();
            TableColumn col=new TableColumn("kodejurusan");
            col.setCellValueFactory(new PropertyValueFactory<JurusanModel, String>("kodejurusan"));
            tbvjurusan.getColumns().addAll(col);
            col=new TableColumn("jurusan");
            col.setCellValueFactory(new PropertyValueFactory<JurusanModel, String>("jurusan"));
            tbvjurusan.getColumns().addAll(col);
            tbvjurusan.setItems(data);
        }else {  
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvjurusan.getScene().getWindow().hide();;
        }                
    }
    
    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputJurusan.fxml"));    
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
        JurusanModel s= new JurusanModel();
        s=tbvjurusan.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputJurusan.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputJurusanController isidt=(FXMLInputJurusanController)loader.getController();
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
        JurusanModel s= new JurusanModel();       
        s=tbvjurusan.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtjurusan.delete(s.getKodejurusan())){
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
        tbvjurusan.getSelectionModel().selectFirst();        
        tbvjurusan.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvjurusan.getSelectionModel().selectAboveCell();       
        tbvjurusan.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvjurusan.getSelectionModel().selectBelowCell();        
        tbvjurusan.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvjurusan.getSelectionModel().selectLast();        
        tbvjurusan.requestFocus();
    }
    
    @FXML
    private void cariData(KeyEvent event) {
        JurusanModel s = new JurusanModel();
        String key = searchjurusan.getText();
        if(key!=""){
        ObservableList<JurusanModel> data=FXMLDocumentController.dtjurusan.CariJurusan(key,key);
        if(data!=null){            
            tbvjurusan.getColumns().clear();            
            tbvjurusan.getItems().clear();
            TableColumn col=new TableColumn("kodejurusan");
            col.setCellValueFactory(new PropertyValueFactory<JurusanModel, String>("kodejurusan"));
            tbvjurusan.getColumns().addAll(col);
            col=new TableColumn("jurusan");
            col.setCellValueFactory(new PropertyValueFactory<JurusanModel, String>("jurusan"));
            tbvjurusan.getColumns().addAll(col);
            tbvjurusan.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvjurusan.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            }        
    }
    
}
