/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts_2019130011;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Regina Viola Mauretha-2019130011
 */
public class DBJurusan {
    private JurusanModel dt=new JurusanModel();    
    public JurusanModel getJurusanModel(){ return(dt);}
    public void setJurusanModel(JurusanModel s){ dt=s;}
    
    public ObservableList<JurusanModel>  Load() {
        try {
            ObservableList<JurusanModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.db_uts_pbol.createStatement();
            ResultSet rs = con.statement.executeQuery("Select kodejurusan, jurusan from jurusan");
            int i = 1;
            while (rs.next()) {
                JurusanModel d=new JurusanModel();
                d.setKodejurusan(rs.getString("kodejurusan"));                
                d.setJurusan(rs.getString("jurusan"));
                tableData.add(d);                
                i++;            
            }
            con.tutupKoneksi();            
            return tableData;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
    }
    
    public int validasi(String nomor) {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.db_uts_pbol.createStatement();
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from jurusan where kodejurusan = '" + nomor + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
    
    public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.db_uts_pbol.prepareStatement("insert into jurusan (kodejurusan, jurusan) values (?,?)");
            con.preparedStatement.setString(1, getJurusanModel().getKodejurusan());           
            con.preparedStatement.setString(2, getJurusanModel().getJurusan());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
     }
    
    public boolean delete(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.db_uts_pbol.prepareStatement("delete from jurusan where kodejurusan  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
    
    public boolean update() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            con.preparedStatement = con.db_uts_pbol.prepareStatement("update jurusan set jurusan = ?  where  kodejurusan = ? ");
            con.preparedStatement.setString(1, getJurusanModel().getKodejurusan());           
            con.preparedStatement.setString(2, getJurusanModel().getJurusan());
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
    }
    
    public ObservableList<JurusanModel>  CariJurusan(String kode, String nama) {
        try {    
            ObservableList<JurusanModel> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.db_uts_pbol.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from jurusan WHERE kodejurusan LIKE '" + kode + "%' OR jurusan LIKE '" + nama + "%'");
        int i = 1;
        while(rs.next()){
            JurusanModel d = new JurusanModel();
            d.setKodejurusan(rs.getString("kodejurusan"));                
            d.setJurusan(rs.getString("jurusan"));
            tableData.add(d);
            i++;
        }
        con.tutupKoneksi();
        return tableData;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
