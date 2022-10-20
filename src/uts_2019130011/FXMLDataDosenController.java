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
public class FXMLDataDosenController implements Initializable {

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
    private TableView<DosenModel> tbvdosen;
    @FXML
    private TextField searchdosen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    

    public void showdata(){
        ObservableList<DosenModel> data=FXMLDocumentController.dtdosen.Load();
        if(data!=null){            
            tbvdosen.getColumns().clear();            
            tbvdosen.getItems().clear();
            TableColumn col=new TableColumn("nodosen");
            col.setCellValueFactory(new PropertyValueFactory<DosenModel, String>("nodosen"));
            tbvdosen.getColumns().addAll(col);
            col=new TableColumn("namadosen");
            col.setCellValueFactory(new PropertyValueFactory<DosenModel, String>("namadosen"));
            tbvdosen.getColumns().addAll(col);
            col=new TableColumn("email");
            col.setCellValueFactory(new PropertyValueFactory<DosenModel, String>("email"));
            tbvdosen.getColumns().addAll(col);
            col=new TableColumn("kontak");
            col.setCellValueFactory(new PropertyValueFactory<DosenModel, String>("kontak"));
            tbvdosen.getColumns().addAll(col);       
            tbvdosen.setItems(data);
        }else {  
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvdosen.getScene().getWindow().hide();;
        }                
    }
    
    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputDosen.fxml"));    
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
        DosenModel s= new DosenModel();
        s=tbvdosen.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputDosen.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputDosenController isidt=(FXMLInputDosenController)loader.getController();
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
        DosenModel s= new DosenModel();       
        s=tbvdosen.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtdosen.delete(s.getNodosen())){
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
        tbvdosen.getSelectionModel().selectFirst();        
        tbvdosen.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvdosen.getSelectionModel().selectAboveCell();       
        tbvdosen.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvdosen.getSelectionModel().selectBelowCell();        
        tbvdosen.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvdosen.getSelectionModel().selectLast();        
        tbvdosen.requestFocus();
    }
    
    @FXML
    private void cariData(KeyEvent event) {
        DosenModel s = new DosenModel();
        String key = searchdosen.getText();
        if(key!=""){
        ObservableList<DosenModel> data=FXMLDocumentController.dtdosen.CariDosen(key,key);
        if(data!=null){            
            tbvdosen.getColumns().clear();            
            tbvdosen.getItems().clear();
            TableColumn col=new TableColumn("nodosen");
            col.setCellValueFactory(new PropertyValueFactory<DosenModel, String>("nodosen"));
            tbvdosen.getColumns().addAll(col);
            col=new TableColumn("namadosen");
            col.setCellValueFactory(new PropertyValueFactory<DosenModel, String>("namadosen"));
            tbvdosen.getColumns().addAll(col);
            col=new TableColumn("email");
            col.setCellValueFactory(new PropertyValueFactory<DosenModel, String>("email"));
            tbvdosen.getColumns().addAll(col);
            col=new TableColumn("kontak");
            col.setCellValueFactory(new PropertyValueFactory<DosenModel, String>("kontak"));
            tbvdosen.getColumns().addAll(col);       
            tbvdosen.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvdosen.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            }        
    }
    
}
