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
public class Moto extends Vehiculo
{
    
    public Moto (int id, String placa, String marca, String modelo, String tipo_motor, 
            int anio, int recorrido, String color, String tipo_combustible, double precio)
    {
        super(id, placa, marca, modelo, tipo_motor, anio, recorrido, color, tipo_combustible, precio);
    }
    
    
    public static void registrarMoto(Scanner entrada, String filename)
    {
        Vehiculo v = Vehiculo.crearVehiculo(entrada, filename);

        if (v != null)
        {
            Moto mo = new Moto(v.getId(),v.getPlaca(),v.getMarca(),
                v.getModelo(),v.getTipo_motor(),v.getAnio(),v.getRecorrido(),
                v.getColor(),v.getTipo_combustible(),v.getPrecio());
            mo.saveFile(filename);
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
            pw.println(this.id+","+"Moto"+","+this.placa+","+this.marca+","+this.modelo+
                    ","+this.tipo_motor+","+this.anio+","+this.recorrido+","+this.color+
                    ","+this.tipo_combustible+","+this.precio);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
            
    }
}
