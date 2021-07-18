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
public class Antena {

    private int idAntena;
    private String nombreAntena;
    private String ip;
    private String mac;
    private String frecuencia;
    private String canal;
    private String passwConfig;
    private String passwConeccion;
    private int idServidor;
    private int idTorre;
    private int idTipo;

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public int getIdAntena() {
        return idAntena;
    }

    public void setIdAntena(int idAntena) {
        this.idAntena = idAntena;
    }

    public String getNombreAntena() {
        return nombreAntena;
    }

    public void setNombreAntena(String nombreAntena) {
        this.nombreAntena = nombreAntena;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getPasswConfig() {
        return passwConfig;
    }

    public void setPasswConfig(String passwConfig) {
        this.passwConfig = passwConfig;
    }

    public String getPasswConeccion() {
        return passwConeccion;
    }

    public void setPasswConeccion(String passwConeccion) {
        this.passwConeccion = passwConeccion;
    }

    public int getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(int idServidor) {
        this.idServidor = idServidor;
    }

    public int getIdTorre() {
        return idTorre;
    }

    public void setIdTorre(int idTorre) {
        this.idTorre = idTorre;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

}
