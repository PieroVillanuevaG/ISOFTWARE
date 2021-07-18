
package modelo;

import java.util.Date;

public class Pago {
    private int idPago;
    private String fechaPago;
    private float monto;
    private String P_inicial;
    private String P_final;
    private int idCliente;
    private int idPersonal;
    
    
    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getP_inicial() {
        return P_inicial;
    }

    public void setP_inicial(String P_inicial) {
        this.P_inicial = P_inicial;
    }

    public String getP_final() {
        return P_final;
    }

    public void setP_final(String P_final) {
        this.P_final = P_final;
    }

    

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    
    
}
