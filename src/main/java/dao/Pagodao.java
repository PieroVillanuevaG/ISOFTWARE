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
import modelo.Pago;

/**
 *
 * @author PIERO
 */
public class Pagodao {

    public static ArrayList<Pago> listarPagos() {
        ArrayList<Pago> lista = new ArrayList<Pago>();
        Pago p = null;
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement("select * from pago");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                p = new Pago();
                p.setIdPago(rs.getInt("idPago"));
                p.setFechaPago(rs.getString("fechaPago"));
                p.setMonto(rs.getFloat("monto"));
                p.setP_inicial(rs.getString("P_inicial"));
                p.setP_final(rs.getString("P_final"));
                p.setIdPersonal(rs.getInt("idPersonal"));
                p.setIdCliente(rs.getInt("idCliente"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static ArrayList<Pago> listarPagosXId(int idCliente) {
        ArrayList<Pago> lista = new ArrayList<Pago>();
        Pago p = null;
        Connection cn = conexion.conexion.abrir();

        try {
            PreparedStatement stm = cn.prepareStatement("select * from pago where idCliente=?");
            stm.setInt(1, idCliente);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                p = new Pago();
                p.setIdPago(rs.getInt("idPago"));
                p.setFechaPago(rs.getString("fechaPago"));
                p.setMonto(rs.getFloat("monto"));
                p.setP_inicial(rs.getString("P_inicial"));
                p.setP_final(rs.getString("P_final"));
                p.setIdPersonal(rs.getInt("idPersonal"));
                p.setIdCliente(rs.getInt("idCliente"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public static ArrayList<Pago> listarPagosFechas(String fechaInicio,String fechaFinal) {
        ArrayList<Pago> lista = new ArrayList<Pago>();
        Pago p = null;
        Connection cn = conexion.conexion.abrir();

        try {
            PreparedStatement stm = cn.prepareStatement("select * from pago where fechaPago BETWEEN ? and ?");
            stm.setString(1, fechaInicio);
            stm.setString(2, fechaFinal);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                p = new Pago();
                p.setIdPago(rs.getInt("idPago"));
                p.setFechaPago(rs.getString("fechaPago"));
                p.setMonto(rs.getFloat("monto"));
                p.setP_inicial(rs.getString("P_inicial"));
                p.setP_final(rs.getString("P_final"));
                p.setIdPersonal(rs.getInt("idPersonal"));
                p.setIdCliente(rs.getInt("idCliente"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public static ArrayList<Pago> PagosFechaInicio(int idCliente,String fecha) {
        ArrayList<Pago> lista = new ArrayList<Pago>();
        Pago p = null;
        Connection cn = conexion.conexion.abrir();

        try {
            PreparedStatement stm = cn.prepareStatement("select * from pago where idCliente=? and P_inicial=?");
            stm.setInt(1, idCliente);
            stm.setString(2,fecha);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                p = new Pago();
                p.setIdPago(rs.getInt("idPago"));
                p.setFechaPago(rs.getString("fechaPago"));
                p.setMonto(rs.getFloat("monto"));
                p.setP_inicial(rs.getString("P_inicial"));
                p.setP_final(rs.getString("P_final"));
                p.setIdPersonal(rs.getInt("idPersonal"));
                p.setIdCliente(rs.getInt("idCliente"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static ArrayList<Pago> listarPagosXMes(int mes) {
        ArrayList<Pago> lista = new ArrayList<Pago>();
        Pago p = null;
        Connection cn = conexion.conexion.abrir();

        try {
            PreparedStatement stm = cn.prepareStatement("select * from pago where MONTH(fechaPago)=?");
            stm.setInt(1, mes);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                p = new Pago();
                p.setIdPago(rs.getInt("idPago"));
                p.setFechaPago(rs.getString("fechaPago"));
                p.setMonto(rs.getFloat("monto"));
                p.setP_inicial(rs.getString("P_inicial"));
                p.setP_final(rs.getString("P_final"));
                p.setIdPersonal(rs.getInt("idPersonal"));
                p.setIdCliente(rs.getInt("idCliente"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public static ArrayList<Pago> listarPagosXAño(int año) {
        ArrayList<Pago> lista = new ArrayList<Pago>();
        Pago p = null;
        Connection cn = conexion.conexion.abrir();

        try {
            PreparedStatement stm = cn.prepareStatement("select * from pago where YEAR(fechaPago)=?");
            stm.setInt(1, año);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                p = new Pago();
                p.setIdPago(rs.getInt("idPago"));
                p.setFechaPago(rs.getString("fechaPago"));
                p.setMonto(rs.getFloat("monto"));
                p.setP_inicial(rs.getString("P_inicial"));
                p.setP_final(rs.getString("P_final"));
                p.setIdPersonal(rs.getInt("idPersonal"));
                p.setIdCliente(rs.getInt("idCliente"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public static ArrayList<Pago> listarPagosXFecha(String fecha) {
        ArrayList<Pago> lista = new ArrayList<Pago>();
        Pago p = null;
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement("select * from pago where fechaPago=?");
            stm.setString(1, fecha);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                p = new Pago();
                p.setIdPago(rs.getInt("idPago"));
                p.setFechaPago(rs.getString("fechaPago"));
                p.setMonto(rs.getFloat("monto"));
                p.setP_inicial(rs.getString("P_inicial"));
                p.setP_final(rs.getString("P_final"));
                p.setIdPersonal(rs.getInt("idPersonal"));
                p.setIdCliente(rs.getInt("idCliente"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static boolean insertarPago(Pago p) {
        String sql = "insert into pago (fechaPago,monto,P_inicial,P_final,idPersonal,idCliente)values(?,?,?,?,?,?)";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, p.getFechaPago());
            stm.setFloat(2, p.getMonto());
            stm.setString(3, p.getP_inicial());
            stm.setString(4, p.getP_final());
            stm.setInt(5, p.getIdPersonal());
            stm.setInt(6, p.getIdCliente());
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean guardarPago(Pago p) {
        String sql = "update pago set monto=?  where idPago=?";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setFloat(1, p.getMonto());
            stm.setInt(2, p.getIdPago());
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deletePago(int id) {
        String sql = "delete from pago where idPago=?";
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

    public static Pago listarPagosID(int id) {
        Connection cn = conexion.conexion.abrir();
        Pago p = null;
        try {
            PreparedStatement stm = cn.prepareStatement("select * from pago where idPago=?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                p = new Pago();
                p.setIdPago(rs.getInt("idPago"));
                p.setFechaPago(rs.getString("fechaPago"));
                p.setMonto(rs.getFloat("monto"));
                p.setP_inicial(rs.getString("P_inicial"));
                p.setP_final(rs.getString("P_final"));
                p.setIdPersonal(rs.getInt("idPersonal"));
                p.setIdCliente(rs.getInt("idCliente"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
