/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepito.manejopersonal.entidades.vehiculo;

/**
 *
 * @author USER
 */
public class Vehiculo {
    private long id;
    private String modelo;
    private String marca;
    private short puertas;
    private String placa;
    private String color;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public short getPuertas() {
        return puertas;
    }

    public void setPuertas(short puertas) {
        this.puertas = puertas;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setFecha(String color) {
        this.color = color;
    }

    public Vehiculo(long id, String modelo, String marca,
            short puertas, String placa, String color) {
        
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.puertas = puertas;
        this.placa = placa;
        this.color = color;
    }   
}
