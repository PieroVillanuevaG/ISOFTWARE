/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Servidor;

/**
 *
 * @author PIERO
 */
public class Servidordao {
    public static ArrayList<Servidor> listarServidores(){
        ArrayList<Servidor> lista = new ArrayList<>();
        Connection cn = conexion.conexion.abrir();
        Servidor s=null;
        try{
            PreparedStatement stm =   cn.prepareStatement("select * from servidor");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                s = new Servidor();
                s.setIdServidor(rs.getInt("idServidor"));
                s.setNombreServidor(rs.getString("nombreServidor"));
                s.setIpEntrada(rs.getString("ipEntrada"));
                s.setIpSalida(rs.getString("ipSalida"));
                lista.add(s);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
           
        return lista;
    }
    public static Servidor validarServidor(String nom){
        Connection cn = conexion.conexion.abrir();
        Servidor s=null;
        try{
            PreparedStatement stm =   cn.prepareStatement("select * from servidor where nombreServidor=?");
            stm.setString(1, nom);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                s = new Servidor();
                s.setIdServidor(rs.getInt("idServidor"));
                s.setNombreServidor(rs.getString("nombreServidor"));
                s.setIpEntrada(rs.getString("ipEntrada"));
                s.setIpSalida(rs.getString("ipSalida"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
           
        return s;
    }
    public static Servidor listarServidoresId(int id){
        Connection cn = conexion.conexion.abrir();
        Servidor s=null;
        try{
            PreparedStatement stm =   cn.prepareStatement("select * from servidor where idServidor=?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                s = new Servidor();
                s.setIdServidor(rs.getInt("idServidor"));
                s.setNombreServidor(rs.getString("nombreServidor"));
                s.setIpEntrada(rs.getString("ipEntrada"));
                s.setIpSalida(rs.getString("ipSalida"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
           
        return s;
    }
    public static boolean insertarServidor(Servidor s) {
        String sql = "insert into servidor (nombreServidor,ipEntrada,ipSalida)values(?,?,?)";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm=cn.prepareStatement(sql);
            stm.setString(1,s.getNombreServidor());
            stm.setString(2, s.getIpEntrada());
            stm.setString(3, s.getIpSalida());
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean guardarServidor(Servidor s) {
        String sql = "update servidor set nombreServidor=?,ipEntrada=?,ipSalida=? where idServidor=?";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm=cn.prepareStatement(sql);
            stm.setString(1,s.getNombreServidor());
            stm.setString(2, s.getIpEntrada());
            stm.setString(3, s.getIpSalida());
            stm.setInt(4, s.getIdServidor());
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteServidor(int id) {
        String sql = "delete from servidor where idServidor=?";
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
