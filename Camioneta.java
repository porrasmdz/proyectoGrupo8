/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Camioneta extends Carro
{
    private String traccion;
    
    public Camioneta(int id, String placa, String marca, String modelo, 
            String tipo_motor, int anio, int recorrido, String color, String tipo_combustible, 
            double precio, String vidrios, String transmision, String traccion)
    {
        super(id, placa, marca, modelo, tipo_motor, anio, recorrido, 
                color, tipo_combustible, precio, vidrios, transmision);
        this.traccion = traccion;
    }

    public String getTraccion() 
    {
        return traccion;
    }
    
    public void setTraccion(String traccion)
    {
        this.traccion = traccion;
    }
    
    
    public static void registrarCamioneta(Scanner entrada, String filename)
    {
        Vehiculo v = Vehiculo.crearVehiculo(entrada, filename);
        if(v != null)
        {
            System.out.println("Vidrios?");
            String vidriosN = entrada.nextLine();
            System.out.println("Transmision?");
            String transmisionN = entrada.nextLine();
            System.out.println("Traccion?");
            String traccionN = entrada.nextLine();

            Camioneta ca = new Camioneta(v.getId(),v.getPlaca(),v.getMarca(),
                    v.getModelo(),v.getTipo_motor(),v.getAnio(),v.getRecorrido(),
                    v.getColor(),v.getTipo_combustible(),v.getPrecio(),vidriosN,
                    transmisionN, traccionN);
            ca.saveFile(filename);   
        }
        else
        {
            System.out.println("Vehiculo ya registrado");
        }
    } 
    
    @Override
    public void saveFile(String filename)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(filename), true)))
        {
            pw.println(this.id+","+"Camioneta"+","+this.placa+","+this.marca+","+this.modelo+
                    ","+this.tipo_motor+","+this.anio+","+this.recorrido+","+this.color+
                    ","+this.tipo_combustible+","+this.precio+","+this.vidrios+","+
                    this.transmision + "," +this.traccion);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
            
    }
    
}
