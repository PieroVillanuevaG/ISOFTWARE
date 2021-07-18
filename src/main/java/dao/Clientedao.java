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
import modelo.Cliente;

/**
 *
 * @author PIERO
 */
public class Clientedao {

    public static ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        String sql = "select * from cliente";
        Cliente cl = null;
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                cl = new Cliente();
                cl.setIdCliente(rs.getInt("idCliente"));
                cl.setNombre(rs.getString("nombres"));
                cl.setApellidoPaterno(rs.getString("apellidoPaterno"));
                cl.setApellidoMaterno(rs.getString("apellidoMaterno"));
                cl.setDNI(rs.getString("DNI"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setCorreo(rs.getString("correo"));
                lista.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static Cliente buscar(String dni) {
        Connection cn = conexion.conexion.abrir();
        String sql = "select * from cliente where dni=?";
        Cliente cl = null;
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, dni);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                cl = new Cliente();
                cl.setIdCliente(rs.getInt(1));
                cl.setNombre(rs.getString(2));
                cl.setApellidoPaterno(rs.getString(3));
                cl.setApellidoMaterno(rs.getString(4));
                cl.setDNI(rs.getString(5));
                cl.setDireccion(rs.getString(6));
                cl.setCorreo(rs.getString(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cl;
    }

    public static Cliente listarClienteId(int id) {
        Connection cn = conexion.conexion.abrir();
        String sql = "select * from cliente where idCliente=?";
        Cliente cl = null;
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                cl = new Cliente();
                cl.setIdCliente(rs.getInt("idCliente"));
                cl.setNombre(rs.getString("nombres"));
                cl.setApellidoPaterno(rs.getString("apellidoPaterno"));
                cl.setApellidoMaterno(rs.getString("apellidoMaterno"));
                cl.setDNI(rs.getString("DNI"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setCorreo(rs.getString("correo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cl;
    }
    public static Cliente listarClienteDni(String dni) {
        Connection cn = conexion.conexion.abrir();
        String sql = "select * from cliente where dni=?";
        Cliente cl = null;
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, dni);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                cl = new Cliente();
                cl.setIdCliente(rs.getInt("idCliente"));
                cl.setNombre(rs.getString("nombres"));
                cl.setApellidoPaterno(rs.getString("apellidoPaterno"));
                cl.setApellidoMaterno(rs.getString("apellidoMaterno"));
                cl.setDNI(rs.getString("DNI"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setCorreo(rs.getString("correo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cl;
    }
    public static Cliente validarCliente(String dni) {
        Connection cn = conexion.conexion.abrir();
        String sql = "select * from cliente where dni=?";
        Cliente cl = null;
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, dni);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                cl = new Cliente();
                cl.setIdCliente(rs.getInt("idCliente"));
                cl.setNombre(rs.getString("nombres"));
                cl.setApellidoPaterno(rs.getString("apellidoPaterno"));
                cl.setApellidoMaterno(rs.getString("apellidoMaterno"));
                cl.setDNI(rs.getString("DNI"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setCorreo(rs.getString("correo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cl;
    }

    public static boolean insertarCliente(Cliente cl) {
        String sql = "insert into cliente (nombres,apellidoPaterno,apellidoMaterno,DNI,direccion,correo)values(?,?,?,?,?,?)";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, cl.getNombre());
            stm.setString(2, cl.getApellidoPaterno());
            stm.setString(3, cl.getApellidoMaterno());
            stm.setString(4, cl.getDNI());
            stm.setString(5, cl.getDireccion());
            stm.setString(6, cl.getCorreo());
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean guardarCliente(Cliente cl) {
        String sql = "update cliente set nombres=?,apellidoPaterno=?,apellidoMaterno=?,DNI=?,direccion=?,correo=? where idCliente=?";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, cl.getNombre());
            stm.setString(2, cl.getApellidoPaterno());
            stm.setString(3, cl.getApellidoMaterno());
            stm.setString(4, cl.getDNI());
            stm.setString(5, cl.getDireccion());
            stm.setString(6, cl.getCorreo());
            stm.setInt(7, cl.getIdCliente());
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteCliente(int id) {
        String sql = "delete from cliente where idCliente=?";
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
