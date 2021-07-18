/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static conexion.conexion.close;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Antena;

/**
 *
 * @author PIERO
 */
public class Antenadao {

    public static Connection cn;
    public static ResultSet rs;
    public static PreparedStatement stm;

    public static ArrayList<Antena> listarAntenas() {
        ArrayList<Antena> lista = new ArrayList<>();
        String sql = "select * from antena";
        Antena a = null;
        try {
            cn = conexion.conexion.abrir();
            stm = cn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                a = new Antena();
                a.setIdAntena(rs.getInt("idAntena"));
                a.setNombreAntena(rs.getString("nombreAntena"));
                a.setIp(rs.getString("ip"));
                a.setMac(rs.getString("mac"));
                a.setFrecuencia(rs.getString("frecuencia"));
                a.setCanal(rs.getString("canal"));
                a.setPasswConfig(rs.getString("passwConfig"));
                a.setPasswConeccion(rs.getString("passwConeccion"));
                a.setIdServidor(rs.getInt("idServidor"));
                a.setIdTorre(rs.getInt("idTorre"));
                a.setIdTipo(rs.getInt("idTipo"));
                lista.add(a);
            }
            close(cn);
            close(stm);
            close(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public static Antena listarAntenasId(int id){
        Connection cn = conexion.conexion.abrir();
        Antena a=null;
        try{
            PreparedStatement stm =   cn.prepareStatement("select * from antena where idAntena=?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                a = new Antena();
                a.setIdAntena(rs.getInt("idAntena"));
                a.setNombreAntena(rs.getString("nombreAntena"));
                a.setIp(rs.getString("ip"));
                a.setMac(rs.getString("mac"));
                a.setFrecuencia(rs.getString("frecuencia"));
                a.setCanal(rs.getString("canal"));
                a.setPasswConfig(rs.getString("passwConfig"));
                a.setPasswConeccion(rs.getString("passwConeccion"));
                a.setIdServidor(rs.getInt("idServidor"));
                a.setIdTorre(rs.getInt("idTorre"));
                a.setIdTipo(rs.getInt("idTipo"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
           
        return a;
    }
    public static Antena validarAntena(String nom){
        Connection cn = conexion.conexion.abrir();
        Antena a=null;
        try{
            PreparedStatement stm =   cn.prepareStatement("select * from antena where nombreAntena=?");
            stm.setString(1, nom);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                a = new Antena();
                a.setIdAntena(rs.getInt("idAntena"));
                a.setNombreAntena(rs.getString("nombreAntena"));
                a.setIp(rs.getString("ip"));
                a.setMac(rs.getString("mac"));
                a.setFrecuencia(rs.getString("frecuencia"));
                a.setCanal(rs.getString("canal"));
                a.setPasswConfig(rs.getString("passwConfig"));
                a.setPasswConeccion(rs.getString("passwConeccion"));
                a.setIdServidor(rs.getInt("idServidor"));
                a.setIdTorre(rs.getInt("idTorre"));
                a.setIdTipo(rs.getInt("idTipo"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
           
        return a;
    }
    public static boolean insertarAntena(Antena a) {
        String sql = "insert into antena (nombreAntena,ip,mac,frecuencia,canal,passwConfig,passwConeccion,idServidor,idTorre,idTipo)values(?,?,?,?,?,?,?,?,?,?)";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm=cn.prepareStatement(sql);
            stm.setString(1,a.getNombreAntena());
            stm.setString(2, a.getIp());
            stm.setString(3, a.getMac());
            stm.setString(4, a.getFrecuencia());
            stm.setString(5,a.getCanal());
            stm.setString(6, a.getPasswConfig());
            stm.setString(7, a.getPasswConeccion());
            stm.setInt(8, a.getIdServidor());
            stm.setInt(9, a.getIdTorre());
            stm.setInt(10, a.getIdTipo());
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean guardarAntena(Antena a) {
        String sql = "update antena set nombreAntena=?,ip=?,mac=?,frecuencia=?,canal=?,passwConfig=?,passwConeccion=?,idServidor=?,idTorre=?,idTipo=? where idAntena=?";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm=cn.prepareStatement(sql);
            stm.setString(1,a.getNombreAntena());
            stm.setString(2, a.getIp());
            stm.setString(3, a.getMac());
            stm.setString(4, a.getFrecuencia());
            stm.setString(5,a.getCanal());
            stm.setString(6, a.getPasswConfig());
            stm.setString(7, a.getPasswConeccion());
            stm.setInt(8, a.getIdServidor());
            stm.setInt(9, a.getIdTorre());
            stm.setInt(10, a.getIdTipo());
            stm.setInt(11, a.getIdAntena());
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteAntena(int id) {
        String sql = "delete from antena where idAntena=?";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm=cn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
