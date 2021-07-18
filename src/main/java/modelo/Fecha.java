/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author PIERO
 */
public class Fecha {
    public static String fecha_actual(){
        Date fecha_act = new Date();
        SimpleDateFormat formato= new SimpleDateFormat("YYYY-MM-dd");
        return formato.format(fecha_act);
    }
    public static String fecha_vencimiento(){
        SimpleDateFormat formato= new SimpleDateFormat("YYYY-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date fecha = calendar.getTime();  
        return formato.format(fecha);
    }
    
    public static String fecha_corte(){
        SimpleDateFormat formato= new SimpleDateFormat("YYYY-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE,3);
        Date fecha = calendar.getTime();  
        return formato.format(fecha);
    }
    public static String fecha_pago (String fecha){
        SimpleDateFormat formato= new SimpleDateFormat("YYYY-MM-dd");
        String [] part = fecha.split("-");
        String anio = part[0];
        String mes = part[1];
        String dia = part[2];
        int anio_n = Integer.parseInt(anio);
        int mes_n = Integer.parseInt(mes);
        int dia_n = Integer.parseInt(dia);
        Calendar calendar = Calendar.getInstance();
        calendar.set(anio_n, mes_n-2,dia_n);
        Date fecha_n = calendar.getTime();
        return formato.format(fecha_n);
    }
}
