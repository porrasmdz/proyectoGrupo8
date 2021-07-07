/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Oferta 
{
    private int id; //Falta getter y setter
    private int idComprador; //Falta getter y setter
    private int idVehiculo; // Falta getter y setter
    private double precio_ofertado;
    
    private Vehiculo veh;
    private Usuario usuario;
    
    public Oferta (int id, int idComprador,int idVehiculo, double precio_ofertado)
    {
        this.idComprador = idComprador;
        this.idVehiculo = idVehiculo;
       
        this.precio_ofertado = precio_ofertado;
    }
    
    public int getId()
    {
        return this.id;
    }
    public int getIdComprador()
    {
        return this.idComprador;
    }
    public int getIdVehiculo()
    {
        return this.idVehiculo;
    }
    
    public void setVehiculo(Vehiculo v)
    {
        this.veh = v;
    }
    public void setUsuario(Usuario us)
    {
        this.usuario = us;
    }
    
    public Usuario getUsuario()
    {
        return this.usuario;
    }
    public Vehiculo getVehiculo()
    {
        return this.veh;
    }


    public double getPrecio_ofertado() {
        return precio_ofertado;
    }

    public void setPrecio_ofertado(double precio_ofertado) {
        this.precio_ofertado = precio_ofertado;
    }
    
    
    @Override
    public String toString(){
       return String.format("Oferta: $%.2f", this.precio_ofertado); 
    }
    
    public static ArrayList<Vehiculo> linkVehiculos(ArrayList<Oferta> ofertas, ArrayList<Usuario> users, ArrayList<Vehiculo> vehiculos)
    {
        ArrayList<Vehiculo> vehiculoListos = new ArrayList<>();
        for (Oferta off : ofertas)
        {
            for(Usuario us : users)
            {
                if(us.getId() == off.getIdComprador())
                {
                    off.setUsuario(us);
                    
                }
                
            }
            
            for (Vehiculo v : vehiculos)
            {
                if(off.getIdVehiculo() == v.getId())
                {
                    off.setVehiculo(v);
                    v.getOfertas().add(off);
                    
                    if(!vehiculoListos.contains(v))
                        vehiculoListos.add(v);
                    
                }
            }
        }
        return vehiculoListos;
    }
    
    
    
    
    public static ArrayList<Oferta> readFile(String filename)
    {
        ArrayList<Oferta> ofertas = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(filename)))
        {
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                Oferta o = new Oferta(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),
                        Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]));
                ofertas.add(o);
            }    
            return ofertas;

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
            pw.println(this.id + "," + this.idComprador + "," + this.idVehiculo + "," + this.precio_ofertado);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
            
    }
}
