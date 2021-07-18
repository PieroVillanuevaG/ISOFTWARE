/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author PIERO
 */
public class Servicio {
    private int idServicio;
    private String f_inicio;
    private String f_vencimiento;
    private String f_corte;
    private String condicionAntena;
    private String mac;
    private String ip;
    private String frecuencia;
    private String anchoBanda;
    private String ultimoCorteEjecutado;
    private String F_Congelada;
    private float saldo;
    private int tarifa;
    private String F_pago_Saldo;
    private String observacion;
    private int idMarca;
    private int idAntena;
    private int idCliente;
    private int idPersonal;
    private int idEstado;

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
    

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }
    

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    
    

    public String getCondicionAntena() {
        return condicionAntena;
    }

    public void setCondicionAntena(String condicionAntena) {
        this.condicionAntena = condicionAntena;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getAnchoBanda() {
        return anchoBanda;
    }

    public void setAnchoBanda(String anchoBanda) {
        this.anchoBanda = anchoBanda;
    }

    public int getIdAntena() {
        return idAntena;
    }

    public void setIdAntena(int idAntena) {
        this.idAntena = idAntena;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    
    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getF_inicio() {
        return f_inicio;
    }

    public void setF_inicio(String f_inicio) {
        this.f_inicio = f_inicio;
    }

    public String getF_vencimiento() {
        return f_vencimiento;
    }

    public void setF_vencimiento(String f_vencimiento) {
        this.f_vencimiento = f_vencimiento;
    }

    public String getF_corte() {
        return f_corte;
    }

    public void setF_corte(String f_corte) {
        this.f_corte = f_corte;
    }

    public String getUltimoCorteEjecutado() {
        return ultimoCorteEjecutado;
    }

    public void setUltimoCorteEjecutado(String ultimoCorteEjecutado) {
        this.ultimoCorteEjecutado = ultimoCorteEjecutado;
    }

    public String getF_Congelada() {
        return F_Congelada;
    }

    public void setF_Congelada(String F_Congelada) {
        this.F_Congelada = F_Congelada;
    }

    public String getF_pago_Saldo() {
        return F_pago_Saldo;
    }

    public void setF_pago_Saldo(String F_pago_Saldo) {
        this.F_pago_Saldo = F_pago_Saldo;
    }

    public int getidMarca() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
