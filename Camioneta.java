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
public class Camioneta extends Carro{
    private String traccion;
    
    public Camioneta(String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, double precio, int vidrios, String transmision, String traccion){
        super(placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_combustible, precio, vidrios, transmision);
        this.traccion = traccion;
    }

    public String getTraccion() {
        return traccion;
    }
}
