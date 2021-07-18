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
import modelo.Servicio;

/**
 *
 * @author PIERO
 */
public class Serviciodao {

    public static ArrayList<Servicio> listarServicios() {
        ArrayList<Servicio> lista = new ArrayList<>();
        Servicio c = null;
        String sql = "select * from servicio LIMIT 100";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                c = new Servicio();
                c.setIdServicio(rs.getInt("idServicio"));
                c.setF_inicio(rs.getString("f_inicio"));
                c.setF_vencimiento(rs.getString("f_vencimiento"));
                c.setF_corte(rs.getString("f_corte"));
                c.setCondicionAntena(rs.getString("condicionAntena"));
                c.setMac(rs.getString("mac"));
                c.setIp(rs.getString("ip"));
                c.setFrecuencia(rs.getString("frecuencia"));
                c.setAnchoBanda(rs.getString("anchoBanda"));
                c.setUltimoCorteEjecutado(rs.getString("ultimoCorteEjecutado"));
                c.setF_Congelada(rs.getString("F_congelada"));
                c.setSaldo(rs.getFloat("saldo"));
                c.setF_pago_Saldo(rs.getString("F_pago_Saldo"));
                c.setObservacion(rs.getString("observacion"));
                c.setIdMarca(rs.getInt("idMarca"));
                c.setIdAntena(rs.getInt("idAntena"));
                c.setIdCliente(rs.getInt("idCliente"));
                c.setIdPersonal(rs.getInt("idPersonal"));
                c.setIdEstado(rs.getInt("idEstado"));
                c.setTarifa(rs.getInt("tarifa"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static Servicio listarServiciosId(int id) {
        Connection cn = conexion.conexion.abrir();
        Servicio c = null;
        try {
            PreparedStatement stm = cn.prepareStatement("select * from servicio where idServicio=?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                c = new Servicio();
                c.setIdServicio(rs.getInt("idServicio"));
                c.setF_inicio(rs.getString("f_inicio"));
                c.setF_vencimiento(rs.getString("f_vencimiento"));
                c.setF_corte(rs.getString("f_corte"));
                c.setCondicionAntena(rs.getString("condicionAntena"));
                c.setMac(rs.getString("mac"));
                c.setIp(rs.getString("ip"));
                c.setFrecuencia(rs.getString("frecuencia"));
                c.setAnchoBanda(rs.getString("anchoBanda"));
                c.setUltimoCorteEjecutado(rs.getString("ultimoCorteEjecutado"));
                c.setF_Congelada(rs.getString("F_congelada"));
                c.setSaldo(rs.getFloat("saldo"));
                c.setF_pago_Saldo(rs.getString("F_pago_Saldo"));
                c.setObservacion(rs.getString("observacion"));
                c.setIdMarca(rs.getInt("idMarca"));
                c.setIdAntena(rs.getInt("idAntena"));
                c.setIdCliente(rs.getInt("idCliente"));
                c.setIdEstado(rs.getInt("idEstado"));
                c.setTarifa(rs.getInt("tarifa"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }
    public static Servicio listarServiciosCodCliente(int id) {
        Connection cn = conexion.conexion.abrir();
        Servicio c = null;
        try {
            PreparedStatement stm = cn.prepareStatement("select * from servicio where idCliente=?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                c = new Servicio();
                c.setIdServicio(rs.getInt("idServicio"));
                c.setF_inicio(rs.getString("f_inicio"));
                c.setF_vencimiento(rs.getString("f_vencimiento"));
                c.setF_corte(rs.getString("f_corte"));
                c.setCondicionAntena(rs.getString("condicionAntena"));
                c.setMac(rs.getString("mac"));
                c.setIp(rs.getString("ip"));
                c.setFrecuencia(rs.getString("frecuencia"));
                c.setAnchoBanda(rs.getString("anchoBanda"));
                c.setUltimoCorteEjecutado(rs.getString("ultimoCorteEjecutado"));
                c.setF_Congelada(rs.getString("F_congelada"));
                c.setSaldo(rs.getFloat("saldo"));
                c.setF_pago_Saldo(rs.getString("F_pago_Saldo"));
                c.setObservacion(rs.getString("observacion"));
                c.setIdMarca(rs.getInt("idMarca"));
                c.setIdAntena(rs.getInt("idAntena"));
                c.setIdCliente(rs.getInt("idCliente"));
                c.setIdEstado(rs.getInt("idEstado"));
                c.setTarifa(rs.getInt("tarifa"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }
    public static Servicio listarServiciosCodCliente(String dni) {
        Connection cn = conexion.conexion.abrir();
        Servicio c = null;
        try {
            PreparedStatement stm = cn.prepareStatement("select * from servicio s , cliente c where c.idCliente = s.idCliente and c.DNI=?");
            stm.setString(1, dni);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                c = new Servicio();
                c.setIdServicio(rs.getInt("idServicio"));
                c.setF_inicio(rs.getString("f_inicio"));
                c.setF_vencimiento(rs.getString("f_vencimiento"));
                c.setF_corte(rs.getString("f_corte"));
                c.setCondicionAntena(rs.getString("condicionAntena"));
                c.setMac(rs.getString("mac"));
                c.setIp(rs.getString("ip"));
                c.setFrecuencia(rs.getString("frecuencia"));
                c.setAnchoBanda(rs.getString("anchoBanda"));
                c.setUltimoCorteEjecutado(rs.getString("ultimoCorteEjecutado"));
                c.setF_Congelada(rs.getString("F_congelada"));
                c.setSaldo(rs.getFloat("saldo"));
                c.setF_pago_Saldo(rs.getString("F_pago_Saldo"));
                c.setObservacion(rs.getString("observacion"));
                c.setIdMarca(rs.getInt("idMarca"));
                c.setIdAntena(rs.getInt("idAntena"));
                c.setIdCliente(rs.getInt("idCliente"));
                c.setIdEstado(rs.getInt("idEstado"));
                c.setTarifa(rs.getInt("tarifa"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    public static boolean insertar_srv(Servicio c) {
        String sql = "insert into servicio (f_inicio,f_vencimiento,f_corte,condicionAntena,mac,ip,frecuencia,anchoBanda,idMarca,tarifa,idAntena,idCliente,idPersonal,idEstado) values (?,?,?,?,?,?,?,?,?,?,?,?,?,1)";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, c.getF_inicio());
            stm.setString(2, c.getF_vencimiento());
            stm.setString(3, c.getF_corte());
            stm.setString(4, c.getCondicionAntena());
            stm.setString(5, c.getMac());
            stm.setString(6, c.getIp());
            stm.setString(7, c.getFrecuencia());
            stm.setString(8, c.getAnchoBanda());
            stm.setInt(9, c.getIdMarca());
            stm.setInt(10, c.getTarifa());
            stm.setInt(11, c.getIdAntena());
            stm.setInt(12, c.getIdCliente());
            stm.setInt(13, c.getIdPersonal());
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean actualizarFechas(int idServicio){
       String sql = "update servicio set f_inicio = adddate(f_inicio,INTERVAL 1 MONTH), f_vencimiento = adddate(f_vencimiento,INTERVAL 1 MONTH),f_corte = adddate(f_corte,INTERVAL 1 MONTH),idEstado=1 where idServicio = ?";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, idServicio);
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }
     public static boolean actualizarEstados(int estado ,int idServicio){
       String sql = "update servicio set idEstado=? where idServicio = ?";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, estado);
            stm.setInt(2, idServicio);
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }
    public static boolean guardar_srv(Servicio c) {
        String sql = "update servicio set f_inicio=?,f_vencimiento=?,f_corte=?,condicionAntena=?,mac=?,ip=?,frecuencia=?,anchoBanda=?,idMarca=?,tarifa=?,idAntena=?,idCliente=?,idPersonal=?,idEstado=1 where idServicio=?";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, c.getF_inicio());
            stm.setString(2, c.getF_vencimiento());
            stm.setString(3, c.getF_corte());
            stm.setString(4, c.getCondicionAntena());
            stm.setString(5, c.getMac());
            stm.setString(6, c.getIp());
            stm.setString(7, c.getFrecuencia());
            stm.setString(8, c.getAnchoBanda());
            stm.setInt(9, c.getIdMarca());
            stm.setInt(10, c.getTarifa());
            stm.setInt(11, c.getIdAntena());
            stm.setInt(12, c.getIdCliente());
            stm.setInt(13, c.getIdPersonal());
            stm.setInt(14, c.getIdServicio());
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete_srv(int id) {
        String sql = "delete from servicio where idServicio=?";
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
