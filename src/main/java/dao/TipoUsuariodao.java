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
import modelo.TipoUsuario;

/**
 *
 * @author PIERO
 */
public class TipoUsuariodao {
     public static final String SQL_LISTAR="select * from tipousuario";
     public static final String SQL_LISTAR_X_CODIGO="select tp.tipoUser from tipousuario tp,usuario u  where tp.idTipoUser = u.idTipoUser";
     
     
     public static ArrayList<TipoUsuario> listarTipos(){
        ArrayList<TipoUsuario> lista = new ArrayList<TipoUsuario> ();
        TipoUsuario tu = null;
        Connection cn = conexion.conexion.abrir();
        try{
            PreparedStatement stm = cn.prepareStatement(SQL_LISTAR);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                tu = new TipoUsuario();
                tu.setIdTipoUser(rs.getInt("idTipoUser"));
                tu.setTipoUser(rs.getString("tipoUser"));
                lista.add(tu);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista; 
    }
     public static TipoUsuario listarTiposXId(int id){
        Connection cn = conexion.conexion.abrir();
        TipoUsuario tu=null;
        try{
            PreparedStatement stm =   cn.prepareStatement("select * from tipousuario where idTipoUser=?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                tu = new TipoUsuario();
                tu.setIdTipoUser(rs.getInt("idTipoUser"));
                tu.setTipoUser(rs.getString("tipoUser"));
            }
            cn.close();
            stm.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return tu;
    }
     public static ArrayList<TipoUsuario> listarTiposxCodigo(){
        ArrayList<TipoUsuario> lista = new ArrayList<TipoUsuario> ();
        TipoUsuario tu = null;
        Connection cn = conexion.conexion.abrir();
        try{
            PreparedStatement stm = cn.prepareStatement(SQL_LISTAR_X_CODIGO);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                tu = new TipoUsuario();
                tu.setIdTipoUser(rs.getInt("idTipoUser"));
                tu.setTipoUser(rs.getString("tipoUser"));
                lista.add(tu);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista; 
    }
}
