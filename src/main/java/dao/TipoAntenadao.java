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
import modelo.TipoAntena;

/**
 *
 * @author PIERO
 */
public class TipoAntenadao {
    public static ArrayList<TipoAntena> listarTipos(){
        ArrayList<TipoAntena> lista = new ArrayList<TipoAntena>();
        TipoAntena ta = null;
        Connection cn = conexion.conexion.abrir();
        try{
            PreparedStatement stm = cn.prepareStatement("select * from tipoantena");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
               ta = new TipoAntena();
               ta.setIdtipo(rs.getInt("idTipo"));
               ta.setTipoAntena(rs.getString("tipoAntena"));
               lista.add(ta);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista; 
    }
    public static TipoAntena listarTiposId(int id){
        TipoAntena ta = null;
        Connection cn = conexion.conexion.abrir();
        try{
            PreparedStatement stm = cn.prepareStatement("select * from tipoantena where idtipo=?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
               ta = new TipoAntena();
               ta.setIdtipo(rs.getInt("idTipo"));
               ta.setTipoAntena(rs.getString("tipoAntena"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ta; 
    }
}
