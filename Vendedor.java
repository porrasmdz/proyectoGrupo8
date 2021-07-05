/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.Model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Vendedor extends Usuario {
    Vehiculo vehiculo;
    public Vendedor(int id, String nombres, String apellidos, String organizacion, String correo_electronico, String clave) {
        super(id, nombres, apellidos, organizacion, correo_electronico, clave);
    }
    
    public Vehiculo registrar_vehiculo(Scanner sc){
        System.out.println("Ingrese el tipo de vehiculo: ");
        String tipo_vehiculo = sc.nextLine();
        if(tipo_vehiculo == "moto" || tipo_vehiculo == "Moto"){
            Vehiculo vehiculo = Vehiculo.Moto();
            return vehiculo;}
        else if(tipo_vehiculo == "carro" || tipo_vehiculo == "Carro"){
            Vehiculo vehiculo = Vehiculo.Carro();
            return vehiculo;}
        else if(tipo_vehiculo == "camioneta" || tipo_vehiculo == "Camioneta"){
            Vehiculo vehiculo = Vehiculo.Camioneta();
            return vehiculo;}
        else
            System.out.println("Tipo de vehiculo incorrecto");
            return null;
    }
    

    /*
    //Moto
    //String Placa, String marca, String modelo, String tipo_motor, int año, int recorrido, String color, String tipo_combustible, float precio
    
    
    //Carro
    //String Placa, String marca, String modelo, String tipo_motor, int año, int recorrido, String color, String tipo_combustible, float precio, String vidrios, String transmision
    public Vehiculo registrar_vehiculo(){
        Vehiculo vehiculo = Vehiculo.Carro();
        return vehiculo;
    }
    
    //Camioneta
    public Vehiculo registrar_vehiculo(String Placa, String marca, String modelo, String tipo_motor, int año, int recorrido, String color, String tipo_combustible, float precio, String vidrios, String transmision, String traccion){
        Vehiculo vehiculo = Vehiculo.Camioneta();
        return vehiculo;
    }
    */
    
    
    
    
}
