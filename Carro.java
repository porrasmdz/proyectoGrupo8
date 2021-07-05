/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

/**
 *
 * @author Dell
 */
public class Carro extends Vehiculo {
    private int vidrios;
    private String transmision;
    
    public Carro (String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, double precio, int vidrios, String transmision){
        super(placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_combustible, precio);
        this.vidrios = vidrios;
        this.transmision = transmision;
    }

    public int getVidrios() {
        return vidrios;
    }

    public void setVidrios(int vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }
    
}
