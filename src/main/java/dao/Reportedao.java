/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Pago;
import modelo.Reporte;

/**
 *
 * @author PIERO
 */
public class Reportedao {

    public static boolean insertarPago(Reporte r) {
        String sql = "insert into reporte (monto,descripcion,fechaRegistro,idPersonal)values(?,?,?,?)";
        Connection cn = conexion.conexion.abrir();
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setFloat(1, r.getMonto());
            stm.setString(2, r.getDescripcion());
            stm.setString(3, r.getFechaRegistro());
            stm.setInt(4, r.getIdPersonal());
            stm.executeUpdate();
            cn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Reporte listarReporteId(int id, String fecha) {
        Connection cn = conexion.conexion.abrir();
        Reporte p = null;
        try {
            PreparedStatement stm = cn.prepareStatement("select * from reporte where idPersonal=? and fechaRegistro=?");
            stm.setInt(1, id);
            stm.setString(2, fecha);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                p = new Reporte();
                p.setIdReporte(rs.getInt("idReporte"));
                p.setMonto(rs.getFloat("monto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setFechaRegistro(rs.getString("fechaRegistro"));
                p.setIdPersonal(rs.getInt("idPersonal"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
