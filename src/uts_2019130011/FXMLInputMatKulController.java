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
public class FXMLInputMatKulController implements Initializable {
    boolean editdata = false;
    
    @FXML
    private TextField txtstatus;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnsimpan;
    @FXML
    private TextField txtsks;
    @FXML
    private TextField txtmatkul;
    @FXML
    private TextField txtkodematkul;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void execute(MatKulModel d){
        if(!d.getKodematkul().isEmpty()){
          editdata=true;
          txtkodematkul.setText("");        
          txtmatkul.setText("");
          txtsks.setText(String.valueOf(d.getSks()));  
          txtstatus.setText("");          
          txtkodematkul.setEditable(false);          
          txtmatkul.requestFocus();         
        }
    }


    @FXML
    private void simpanklik(ActionEvent event) {
      MatKulModel n=new MatKulModel();        
        n.setKodematkul(txtkodematkul.getText());
        n.setMatakuliah(txtmatkul.getText());     
        n.setSks(Integer.parseInt(txtsks.getText()));
        n.setStatus(txtstatus.getText());
        FXMLDocumentController.dtmatkul.setMatKulModel(n);
        if(editdata){
            if(FXMLDocumentController.dtmatkul.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtkodematkul.setEditable(true);        
               batalklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            }else if(FXMLDocumentController.dtmatkul.validasi(n.getKodematkul())<=0){
            if(FXMLDocumentController.dtmatkul.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtkodematkul.requestFocus();
        }
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtkodematkul.setText("");        
        txtmatkul.setText("");
        txtsks.setText("");  
        txtstatus.setText("");
        txtkodematkul.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
    
}
