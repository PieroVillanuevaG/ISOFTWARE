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
import modelo.Torre;

/**
 *
 * @author PIERO
 */
public class Torredao {

    public static ArrayList<Torre> listarTorres() {
        ArrayList<Torre> lista = new ArrayList<>();
        Connection cn = conexion.conexion.abrir();
        String sql = "select * from torre";
        Torre t = null;
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                t = new Torre();
                t.setIdTorre(rs.getInt("idTorre"));
                t.setNombreTorre(rs.getString("nombreTorre"));
                t.setDireccion(rs.getString("direccion"));
                t.setDueñoLocal(rs.getString("dueñoLocal"));
                t.setTelefono(rs.getString("telefono"));
                lista.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static Torre listarTorresId(int id) {
        Connection cn = conexion.conexion.abrir();
        String sql = "select * from torre where idTorre=?";
        Torre t = null;
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                t = new Torre();
                t.setIdTorre(rs.getInt("idTorre"));
                t.setNombreTorre(rs.getString("nombreTorre"));
                t.setDireccion(rs.getString("direccion"));
                t.setDueñoLocal(rs.getString("dueñoLocal"));
                t.setTelefono(rs.getString("telefono"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }
     public static Torre validarTorre(String nombreTorre) {
        Connection cn = conexion.conexion.abrir();
        String sql = "select * from torre where nombreTorre=?";
        Torre t = null;
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, nombreTorre);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                t = new Torre();
                t.setIdTorre(rs.getInt("idTorre"));
                t.setNombreTorre(rs.getString("nombreTorre"));
                t.setDireccion(rs.getString("direccion"));
                t.setDueñoLocal(rs.getString("dueñoLocal"));
                t.setTelefono(rs.getString("telefono"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }

    public static boolean insertarTorre(Torre t) {
        String sql = "insert into torre (nombreTorre,direccion,dueñoLocal,telefono)values(?,?,?,?)";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, t.getNombreTorre());
            stm.setString(2, t.getDireccion());
            stm.setString(3, t.getDueñoLocal());
            stm.setString(4, t.getTelefono());
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean guardarTorre(Torre t) {
        String sql = "update torre set nombreTorre=?,direccion=?,dueñoLocal=?,telefono=? where idTorre=?";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, t.getNombreTorre());
            stm.setString(2, t.getDireccion());
            stm.setString(3, t.getDueñoLocal());
            stm.setString(4, t.getTelefono());
            stm.setInt(5, t.getIdTorre());
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteTorre(int id) {
        String sql = "delete from torre where idTorre=?";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
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
