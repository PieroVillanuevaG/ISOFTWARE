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
import modelo.EstadoCliente;

/**
 *
 * @author PIERO
 */
public class EstadoClientedao {
    public static EstadoCliente listarEstadoClienteId(int id) {
        ArrayList<EstadoCliente> lista = new ArrayList<> ();
        String sql = "select * from estadocliente where idEstado =?";
        EstadoCliente ec=null;
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                ec = new EstadoCliente();
                ec.setIdEstado(rs.getInt("idEstado"));
                ec.setDescripcion(rs.getString("descripcion"));
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ec;
    }

    public static ArrayList<EstadoCliente> listarEstadoCliente() {
        ArrayList<EstadoCliente> lista = new ArrayList<> ();
        EstadoCliente ec=null;
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement("select * from estadocliente");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                ec = new EstadoCliente();
                ec.setIdEstado(rs.getInt("idEstado"));
                ec.setDescripcion(rs.getString("descripcion"));
                lista.add(ec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      return lista;
    }
}
