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
public class Servidor {

    private int idServidor;
    private String nombreServidor;
    private String ipEntrada;
    private String ipSalida;

    public int getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(int idServidor) {
        this.idServidor = idServidor;
    }

    public String getNombreServidor() {
        return nombreServidor;
    }

    public void setNombreServidor(String nombreServidor) {
        this.nombreServidor = nombreServidor;
    }

    public String getIpEntrada() {
        return ipEntrada;
    }

    public void setIpEntrada(String ipEntrada) {
        this.ipEntrada = ipEntrada;
    }

    public String getIpSalida() {
        return ipSalida;
    }

    public void setIpSalida(String ipSalida) {
        this.ipSalida = ipSalida;
    }

}
