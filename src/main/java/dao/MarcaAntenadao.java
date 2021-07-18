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
import modelo.MarcaAntena;

/**
 *
 * @author PIERO
 */
public class MarcaAntenadao {
     public static ArrayList<MarcaAntena> listarMarcaAntena(){
        ArrayList<MarcaAntena> lista = new ArrayList<>();
        MarcaAntena ma=null;
        Connection cn = conexion.conexion.abrir();
        try{
            PreparedStatement stm = cn.prepareStatement("select * from marcaantena");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
             ma = new MarcaAntena();
             ma.setIdMarca(rs.getInt("idMarca"));
             ma.setMarca(rs.getString("marca"));
             lista.add(ma);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}
