/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Carro extends Vehiculo 
{
    protected String vidrios;
    protected String transmision;
    
    public Carro (int id, String placa, String marca, String modelo, String tipo_motor, int año, int recorrido, String color, String tipo_combustible, double precio, String vidrios, String transmision){
        super(id, placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_combustible, precio);
        this.vidrios = vidrios;
        this.transmision = transmision;
    }

    public String getVidrios() {
        return vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }
    
   
    public static void registrarCarro(Scanner entrada, String filename)
    {
        Vehiculo v = Vehiculo.crearVehiculo(entrada, filename);
        if(v != null)
        {
            System.out.println("Vidrios?");
            String vidriosN = entrada.nextLine();
            System.out.println("Transmision?");
            String transmisionN = entrada.nextLine();

            Carro ca = new Carro(v.getId(),v.getPlaca(),v.getMarca(),v.getModelo(),v.getTipo_motor(),v.getAnio(),v.getRecorrido(),v.getColor(),v.getTipo_combustible(),v.getPrecio(),vidriosN,transmisionN);
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
            pw.println(this.id+","+"Carro"+","+this.placa+","+this.marca+","+this.modelo+
                    ","+this.tipo_motor+","+this.anio+","+this.recorrido+","+this.color+
                    ","+this.tipo_combustible+","+this.precio+","+this.vidrios+","+this.transmision);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
            
    }
    
    
}
