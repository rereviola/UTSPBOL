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
public class DBMatKul {
    private MatKulModel dt=new MatKulModel();    
    public MatKulModel getMatKulModel(){ return(dt);}
    public void setMatKulModel(MatKulModel s){ dt=s;}
    
    public ObservableList<MatKulModel>  Load() {
        try {
            ObservableList<MatKulModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.db_uts_pbol.createStatement();
            ResultSet rs = con.statement.executeQuery("Select kodematkul, matakuliah, sks, status from matakuliah");
            int i = 1;
            while (rs.next()) {
                MatKulModel d=new MatKulModel();
                d.setKodematkul(rs.getString("kodematkul"));                
                d.setMatakuliah(rs.getString("matakuliah"));
                d.setSks(rs.getInt("sks"));
                d.setStatus(rs.getString("status"));
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
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from matakuliah where kodematkul = '" + nomor + "'");
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
            con.preparedStatement = con.db_uts_pbol.prepareStatement("insert into matakuliah (kodematkul, matakuliah, sks, status) values (?,?,?,?)");
            con.preparedStatement.setString(1, getMatKulModel().getKodematkul());           
            con.preparedStatement.setString(2, getMatKulModel().getMatakuliah());
            con.preparedStatement.setInt(3, getMatKulModel().getSks());
            con.preparedStatement.setString(4, getMatKulModel().getStatus());
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
            con.preparedStatement = con.db_uts_pbol.prepareStatement("delete from matakuliah where kodematkul  = ? ");
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
            con.preparedStatement = con.db_uts_pbol.prepareStatement("update matakuliah set matakuliah = ? , sks = ?, status = ? where  kodematkul = ? ");
            con.preparedStatement.setString(1, getMatKulModel().getKodematkul());           
            con.preparedStatement.setString(2, getMatKulModel().getMatakuliah());
            con.preparedStatement.setInt(3, getMatKulModel().getSks());
            con.preparedStatement.setString(4, getMatKulModel().getStatus());
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
    
    public ObservableList<MatKulModel>  CariMatKul(String kode, String nama) {
        try {    
            ObservableList<MatKulModel> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.db_uts_pbol.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from matakuliah WHERE kodematkul LIKE '" + kode + "%' OR matakuliah LIKE '" + nama + "%'");
        int i = 1;
        while(rs.next()){
            MatKulModel d = new MatKulModel();
            d.setKodematkul(rs.getString("kodematkul"));                
            d.setMatakuliah(rs.getString("matakuliah"));
            d.setSks(rs.getInt("sks"));
            d.setStatus(rs.getString("status"));
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
