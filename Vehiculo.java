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

/**
 *
 * @author Dell
 */
public class Vehiculo {
    protected int id;
    protected String placa;
    protected String marca;
    protected String modelo;
    protected String tipo_motor;
    protected int anio;
    protected int recorrido;
    protected String color;
    protected String tipo_combustible;
    protected double precio;
    protected ArrayList<Oferta> ofertas;
    
    public Vehiculo(){
    }
    public Vehiculo(int id,String placa, String marca, String modelo, String tipo_motor, int anio, int recorrido, String color, String tipo_combustible, double precio){
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public int getId()
    {
        return id;
    }
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo_motor() {
        return tipo_motor;
    }

    public void setTipo_motor(String tipo_motor) {
        this.tipo_motor = tipo_motor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo_combustible() {
        return tipo_combustible;
    }

    public void setTipo_combustible(String tipo_combustible) {
        this.tipo_combustible = tipo_combustible;
    }
    
    public void setRecorrido(int recorrido)
    {
        this.recorrido = recorrido;
    }
    
    public int getRecorrido()
    {
        return recorrido;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    
    //Fin de setters y getters
        
    public static Vehiculo searchById(ArrayList<Vehiculo> vehicles,int vehicleId)
    {
        for (Vehiculo vehicle : vehicles)
        {
            if(vehicle.getId() == vehicleId)
            {
                return vehicle;
            }
        }
        System.out.println("El vehiculo no se encuentra en los registros");
        return null;
    }
    
    public static Vehiculo crearVehiculo(Scanner entrada, String filename)
    {
        System.out.println("Placa?");
        String placaN = entrada.nextLine();
        ArrayList<Vehiculo> existentes = readFile(filename);
        for (Vehiculo vehiculo : existentes)
        {
            if (vehiculo.placa.equals(placaN))
            {    
                System.out.println("Error, este vehiculo ya existe");
                return null;
            }
        }
       
        
        System.out.println("Marca?");
        String marcaN = entrada.nextLine();
        System.out.println("Modelo?");
        String modeloN = entrada.nextLine();
        System.out.println("Tipo Motor?");
        String tipo_motorN = entrada.nextLine();
        System.out.println("Anio?");
        String anioN = entrada.nextLine();
        while(!Util.isAnio(anioN))
        {
           System.out.println("Error al registrar, por favor intente de nuevo");
           anioN = entrada.nextLine();
        }
        int anioF = Integer.parseInt(anioN);
        System.out.println("Recorrido?");
        String recorridoN = entrada.nextLine();
        while(!Util.isRecorrido(recorridoN))
        {
            System.out.println("El recorrido ingresado debe ser un numero!");
            recorridoN = entrada.nextLine();
        }
        int recorridoF = Integer.parseInt(recorridoN);
        System.out.println("Color?");
        String colorN = entrada.nextLine();
        System.out.println("Tipo Combustible?");
        String tipoCombN = entrada.nextLine();
        System.out.println("Precio?");
        String precioN = entrada.nextLine();
        while(!Util.isPrecio(precioN))
        {
            System.out.println("El precio ingresado no es valido, intente de nuevo por favor.");
            precioN = entrada.nextLine();
        }
        double precioF = Double.parseDouble(precioN);
        int id = Util.nextId(filename);
        Vehiculo ve = new Vehiculo(id, placaN,marcaN,modeloN,tipo_motorN,anioF,recorridoF,colorN,tipoCombN,precioF);
        
        return ve;
    } 
    
    public static ArrayList<Vehiculo> readFile(String filename)
    {
        ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        try(Scanner sc = new Scanner(new File(filename)))
        {
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                
                switch(tokens.length)
                {
                    case 14:
                        //camioneta
                        Camioneta cam = new Camioneta(Integer.parseInt(tokens[0]), tokens[2], 
                                tokens[3], tokens[4], tokens[5], Integer.parseInt(tokens[6]), 
                                Integer.parseInt(tokens[7]), tokens[8], tokens[9], 
                                Double.parseDouble(tokens[10]), tokens[11], tokens[12], 
                                tokens[13]);
                        vehiculos.add(cam);
                        break;
                    case 13:
                        //carro
                        Carro ca = new Carro(Integer.parseInt(tokens[0]), tokens[2], 
                                tokens[3], tokens[4], tokens[5], Integer.parseInt(tokens[6]), 
                                Integer.parseInt(tokens[7]), tokens[8], tokens[9], 
                                Double.parseDouble(tokens[10]), tokens[11], tokens[12]);
                        vehiculos.add(ca);
                        break;
                    case 11:
                        //moto
                        Moto mo = new Moto(Integer.parseInt(tokens[0]), tokens[2], 
                                tokens[3], tokens[4], tokens[5], Integer.parseInt(tokens[6]), 
                                Integer.parseInt(tokens[7]), tokens[8], tokens[9], 
                                Double.parseDouble(tokens[10]));
                        vehiculos.add(mo);
                        break;
                                
                }
                
            }
            return vehiculos;
        }
        catch(Exception e)
        {
            System.out.println("Error al leer el archivo");
            return null;
        }
        
    }
    public void saveFile(String filename)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(filename), true)))
        {
            pw.println(this.id+","+"Vehiculo"+","+this.placa+","+this.marca+","+this.modelo+
                    ","+this.tipo_motor+","+this.anio+","+this.recorrido+","+this.color+
                    ","+this.tipo_combustible+","+this.precio);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
            
    }
    
    public void aggOferta(Oferta o)
    {
        this.ofertas.add(o);
    }
    
    public void removeFromOfertaFile(String filename)
    {
        ArrayList<String> lineasArchivo = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(filename)))
        {
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.toString().split(",");
                
                if(Integer.parseInt(tokens[2]) != this.id)
                {
                    lineasArchivo.add(linea);
                }
            }
        }
        catch(Exception e){}
        restartFile(filename);
        for (String linea : lineasArchivo)
        {
            try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(filename),true)))
            {
                pw.println(linea);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    public void removeFromVehicleFile(String filename)
    {
        ArrayList<String> lineasArchivo = new ArrayList<>();
        int conteo = 0;
        try(Scanner sc = new Scanner(new File(filename)))
        {
            
            while(sc.hasNextLine())
            {

                String linea = sc.nextLine();
                String[] tokens = linea.toString().split(",");
                if(Integer.parseInt(tokens[0]) != this.id)
                {
                    
                    lineasArchivo.add(linea);
                }
            }
        }
        catch(Exception e){}
        restartFile(filename);
        for (String linea : lineasArchivo)
        {
            try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(filename),true)))
            {
                pw.println(linea);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static void restartFile(String filename)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(filename), false)))
        {
                
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    @Override
    public String toString()
    {

        String cadena = String.format("Placa: %s%nMarca: %s%nModelo: %s%nTipo de motor: %s%nAnio: %d%nRecorrido: %d%nColor: %s%nTipo de combustible; %s%nPrecio: %.2f%n", placa,
                marca, modelo, tipo_motor,anio, recorrido,color, tipo_combustible, precio);
        System.out.println(cadena);
        return cadena;
    }
            
}
