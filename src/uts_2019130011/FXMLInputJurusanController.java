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
public class FXMLInputJurusanController implements Initializable {
    boolean editdata = false;
    
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnsimpan;
    @FXML
    private TextField txtjurusan;
    @FXML
    private TextField txtkodejuru;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void execute(JurusanModel d){
        if(!d.getKodejurusan().isEmpty()){
          editdata=true;
          txtkodejuru.setText(d.getKodejurusan());
          txtjurusan.setText(d.getJurusan());          
          txtkodejuru.setEditable(false);          
          txtjurusan.requestFocus();         
        }
    }


    @FXML
    private void simpanklik(ActionEvent event) {
      JurusanModel n=new JurusanModel();        
        n.setKodejurusan(txtkodejuru.getText());
        n.setJurusan(txtjurusan.getText());     
        FXMLDocumentController.dtjurusan.setJurusanModel(n);
        if(editdata){
            if(FXMLDocumentController.dtjurusan.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtkodejuru.setEditable(true);        
               batalklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            }else if(FXMLDocumentController.dtjurusan.validasi(n.getKodejurusan())<=0){
            if(FXMLDocumentController.dtjurusan.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtkodejuru.requestFocus();
        }
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtkodejuru.setText("");        
        txtjurusan.setText("");
        txtkodejuru.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
    
}
