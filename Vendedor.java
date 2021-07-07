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
import java.util.ArrayList;
import java.util.Scanner;

public class Vendedor extends Usuario 
{
    
    public Vendedor(int id, String nombres, String apellidos, String organizacion, String correo_electronico, String clave) 
    {
        super(id, nombres, apellidos, organizacion, correo_electronico, clave);
    }
    
    
    public static void registrarVendedor(Scanner entrada, String filename)
    {
        Usuario u = Usuario.crearUsuario(entrada, filename);
        if (u != null)
        {
            Vendedor v = new Vendedor(u.getId(),u.getNombres(),u.getApellidos(),
            u.getOrganizacion(),u.getCorreo_electronico(),u.getClave());

            v.saveFile(filename);
        }
        else
        {
            System.out.println("Correo ya registrado");
        }
        
    }
    
    
    
}   
    
    
    
    
    
    
    
    
    
    /*
    public static void registrar_vehiculo(Scanner sc)
    {
        System.out.println("Esta registrando un auto, camioneta o moto?: ");
        String tipo_vehiculo = (sc.nextLine()).toLowerCase();
 
        if(tipo_vehiculo.equals("moto"))
            registrarMoto(sc);
        else if(tipo_vehiculo.equals("auto"))
            registrarAuto(sc);
        else if(tipo_vehiculo.equals("camioneta"))
            registrarCamioneta(sc);
        else
            System.out.println("El tipo de vehiculo no aplica");
            
    }
    
    public static void registrarMoto(Scanner entrada)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File("vehiculos.txt"),true)))//true quiere decir modo append
        {
            String placa;
            String marca;
            String modelo;
            String tipo_motor;
            String anio;
            String recorrido;
            String color;
            String tipo_combustible;
            String precio;
            
            System.out.println("A continuacion digite los datos solicitados:");
            System.out.println("Placa?");
            placa = entrada.nextLine();
            System.out.println("Marca?");
            marca = entrada.nextLine();
            System.out.println("Modelo?");
            modelo = entrada.nextLine();
            System.out.println("Tipo de motor?");
            tipo_motor = entrada.nextLine();
            System.out.println("Anio?");
            anio = entrada.nextLine();
            while(!isAnio(anio))
            {
               System.out.println("Error al registrar, por favor intente de nuevo");
               anio = entrada.nextLine();
            }
            System.out.println("Recorrido?");
            recorrido = entrada.nextLine();
            while(!isRecorrido(recorrido))
            {
                System.out.println("El recorrido ingresado debe ser un numero!");
                recorrido = entrada.nextLine();
            }
            System.out.println("Color?");
            color = entrada.nextLine();
            System.out.println("Tipo de combustible?");
            tipo_combustible = entrada.nextLine();
            System.out.println("Precio?");
            precio = entrada.nextLine();
            while(!isPrecio(precio))
            {
                System.out.println("El precio ingresado no es valido, intente de nuevo por favor.");
                precio = entrada.nextLine();
            }
            
            pw.printf("##,%s,%s,%s,%s,%s,%s,%s,%s,%s,,,%n", placa, marca, modelo,//9 para motos, 11 autos, 12 camionetas
                    tipo_motor, anio, recorrido,color,tipo_combustible, precio);
        }
        catch(Exception e)
        {            
            System.out.println(e.getMessage());
        }
    }
    
    public static void registrarAuto(Scanner entrada)
    {
       
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File("vehiculos.txt"), true)))
        {
            String placa;
            String marca;
            String modelo;
            String tipo_motor;
            String anio;
            String recorrido;
            String color;
            String tipo_combustible;
            String precio;
            String vidrios;
            String transmision;
            
            System.out.println("A continuacion digite los datos solicitados:");
            System.out.println("Placa?");
            placa = entrada.nextLine();
            System.out.println("Marca?");
            marca = entrada.nextLine();
            System.out.println("Modelo?");
            modelo = entrada.nextLine();
            System.out.println("Tipo de motor?");
            tipo_motor = entrada.nextLine();
            System.out.println("Anio?");
            anio = entrada.nextLine();
            while(!isAnio(anio))
            {
               System.out.println("El anio ingresado no es valido por favor intente de nuevo");
               anio = entrada.nextLine();
            }
            System.out.println("Recorrido?");
            recorrido = entrada.nextLine();
            while(!isRecorrido(recorrido))
            {
                System.out.println("El recorrido ingresado debe ser un numero!");
                recorrido = entrada.nextLine();
            }
            System.out.println("Color?");
            color = entrada.nextLine();
            System.out.println("Tipo de combustible?");
            tipo_combustible = entrada.nextLine();
            System.out.println("Precio?");
            precio = entrada.nextLine();
            System.out.println("Vidrios?");
            vidrios = entrada.nextLine();
            System.out.println("Transmision?");
            transmision = entrada.nextLine();
            while(!isPrecio(precio))
            {
                System.out.println("El precio ingresado no es valido, intente de nuevo por favor.");
                precio = entrada.nextLine();
            }
            
            pw.printf("##,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,,%n", placa, marca, modelo,//9 para motos, 11 autos, 12 camionetas
                    tipo_motor, anio, recorrido,color,tipo_combustible, precio, vidrios, transmision);
        }
        catch(Exception e)
        {            
            System.out.println(e.getMessage());
        } 
    }
    
    public static void registrarCamioneta(Scanner entrada)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File("vehiculos.txt"),true)))
        {
            String placa;
            String marca;
            String modelo;
            String tipo_motor;
            String anio;
            String recorrido;
            String color;
            String tipo_combustible;
            String precio;
            String vidrios;
            String transmision;
            String traccion;
            
            System.out.println("A continuacion digite los datos solicitados:");
            System.out.println("Placa?");
            placa = entrada.nextLine();
            System.out.println("Marca?");
            marca = entrada.nextLine();
            System.out.println("Modelo?");
            modelo = entrada.nextLine();
            System.out.println("Tipo de motor?");
            tipo_motor = entrada.nextLine();
            System.out.println("Anio?");
            anio = entrada.nextLine();
            while(!isAnio(anio))
            {
               System.out.println("El anio ingresado no es valido por favor intente de nuevo");
               anio = entrada.nextLine();
            }
            System.out.println("Recorrido?");
            recorrido = entrada.nextLine();
            while(!isRecorrido(recorrido))
            {
                System.out.println("El recorrido ingresado debe ser un numero!");
                recorrido = entrada.nextLine();
            }
            System.out.println("Color?");
            color = entrada.nextLine();
            System.out.println("Tipo de combustible?");
            tipo_combustible = entrada.nextLine();
            System.out.println("Precio?");
            precio = entrada.nextLine();
            System.out.println("Vidrios?");
            vidrios = entrada.nextLine();
            System.out.println("Transmision?");
            transmision = entrada.nextLine();
            System.out.println("Traccion?");
            traccion = entrada.nextLine();
            while(!isPrecio(precio))
            {
                System.out.println("El precio ingresado no es valido, intente de nuevo por favor.");
                precio = entrada.nextLine();
            }
            
            pw.printf("##,%s,%s,%s,%s,%s,%s,%s,%s,%s%n", placa, marca, modelo,//9 para motos, 11 autos, 12 camionetas
                    tipo_motor, anio, recorrido,color,tipo_combustible, precio, vidrios, transmision, traccion);
        }
        catch(Exception e)
        {            
            System.out.println(e.getMessage());
        } 
        
    }
    
    public static boolean isAnio(String s)
    {
        try
        {
            int entero = Integer.parseInt(s);
            return entero > 1900 && entero < 2200;

        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }
        
    public static boolean isRecorrido(String s)
    {
        try
        {
            Integer.parseInt(s);    
            return true;
            
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }
        
    public static boolean isPrecio(String s)
    {
        try
        {
            Double.parseDouble(s);    
            return true;
            
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }
    */
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
    
    
    
    
//}
