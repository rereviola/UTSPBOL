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
public class DBDosen {
    private DosenModel dt=new DosenModel();    
    public DosenModel getDosenModel(){ return(dt);}
    public void setDosenModel(DosenModel s){ dt=s;}
    
    public ObservableList<DosenModel>  Load() {
        try {
            ObservableList<DosenModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.db_uts_pbol.createStatement();
            ResultSet rs = con.statement.executeQuery("Select nodosen, namadosen, email, kontak from dosen");
            int i = 1;
            while (rs.next()) {
                DosenModel d=new DosenModel();
                d.setNodosen(rs.getString("nodosen"));                
                d.setNamadosen(rs.getString("namadosen"));
                d.setEmail(rs.getString("email"));                
                d.setKontak(rs.getString("kontak"));           
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
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from dosen where nodosen = '" + nomor + "'");
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
            con.preparedStatement = con.db_uts_pbol.prepareStatement("insert into dosen (nodosen, namadosen, email, kontak) values (?,?,?,?)");
            con.preparedStatement.setString(1, getDosenModel().getNodosen());           
            con.preparedStatement.setString(2, getDosenModel().getNamadosen());
            con.preparedStatement.setString(3, getDosenModel().getEmail());           
            con.preparedStatement.setString(4, getDosenModel().getKontak());        
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
            con.preparedStatement = con.db_uts_pbol.prepareStatement("delete from dosen where nodosen  = ? ");
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
            con.preparedStatement = con.db_uts_pbol.prepareStatement("update dosen set namadosen = ?, email = ?, kontak = ?  where  nodosen = ? ");
            con.preparedStatement.setString(1, getDosenModel().getNodosen());           
            con.preparedStatement.setString(2, getDosenModel().getNamadosen());
            con.preparedStatement.setString(3, getDosenModel().getEmail());           
            con.preparedStatement.setString(4, getDosenModel().getKontak());
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
    
    public ObservableList<DosenModel>  CariDosen(String kode, String nama) {
        try {    
            ObservableList<DosenModel> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.db_uts_pbol.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from dosen WHERE nodosen LIKE '" + kode + "%' OR namadosen LIKE '" + nama + "%'");
        int i = 1;
        while(rs.next()){
            DosenModel d = new DosenModel();
            d.setNodosen(rs.getString("nodosen"));                
            d.setNamadosen(rs.getString("namadosen"));
            d.setEmail(rs.getString("email"));                
            d.setKontak(rs.getString("kontak"));
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
