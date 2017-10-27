package modelo;

import java.util.Date;

/**
 * Created by Lautaro on 20/9/2017.
 */

public class Tarjeta implements java.io.Serializable {
    @Override
    public String toString() {
        return "Tarjeta{" +
                "nombre='" + nombre + '\'' +
                ", numero=" + numero +
                ", seguridad=" + seguridad +
                ", fechaVencimiento=" + fechaVencimiento +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public Tarjeta(String nombre) {
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(Integer seguridad) {
        this.seguridad = seguridad;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    private String nombre;
    private Integer numero;
    private Integer seguridad;
    private Date fechaVencimiento;

}
