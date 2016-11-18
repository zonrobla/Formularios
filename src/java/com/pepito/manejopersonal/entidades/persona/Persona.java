/**
 *
 * @author USER
 */
package com.pepito.manejopersonal.entidades.persona;

import java.util.Date;

public class Persona  {
    

    private long id;
    private String nombres;
    private String apellidos;
    private String nivel;
    private String residencia;
    private Date fecha;
    private long idVehiculo;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Persona(long id, String nombres, String apellidos,
            String nivel, String residencia, Date fecha, long idVehiculo) {
        
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nivel = nivel;
        this.residencia = residencia;
        this.fecha = fecha;
        this.idVehiculo = idVehiculo;
    }
}
