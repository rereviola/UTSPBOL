/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uts_2019130011;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Regina Viola Mauretha-2019130011
 */
public class FXMLInputDosenController implements Initializable {
    boolean editdata = false;
    
    @FXML
    private TextField txtnodosen;
    @FXML
    private TextField txtnamadosen;
    @FXML
    private TextField txtemail;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnkeluar;
    @FXML
    private TextField txtkontak;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void execute(DosenModel d){
        if(!d.getNodosen().isEmpty()){
          editdata=true;
          txtnodosen.setText(d.getNodosen());
          txtnamadosen.setText(d.getNamadosen());          
          txtemail.setText(d.getEmail());          
          txtnodosen.setEditable(false);          
          txtnamadosen.requestFocus();         
        }
    }


    @FXML
    private void simpanklik(ActionEvent event) {
      DosenModel n=new DosenModel();        
        n.setNodosen(txtnodosen.getText());
        n.setNamadosen(txtnamadosen.getText());     
        n.setEmail(txtemail.getText());
        n.setKontak(txtkontak.getText());
        FXMLDocumentController.dtdosen.setDosenModel(n);
        if(editdata){
            if(FXMLDocumentController.dtdosen.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtnodosen.setEditable(true);        
               batalklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            }else if(FXMLDocumentController.dtdosen.validasi(n.getNodosen())<=0){
            if(FXMLDocumentController.dtdosen.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtnodosen.requestFocus();
        }
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtnodosen.setText("");        
        txtnamadosen.setText("");
        txtemail.setText("");  
        txtkontak.setText("");
        txtnodosen.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
    
}
