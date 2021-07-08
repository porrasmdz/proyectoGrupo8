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
 * @author ANDRES PORRAS
 */
public class Registro 
{
    private int id;
    private int userId;
    private int vehicleId;
    private Usuario user;
    private Vehiculo vehicle;
    
    public Registro(int id, int userId, int vehicleId)
    {
        this.id = id;
        this.userId = userId;
        this.vehicleId = vehicleId;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    public int getId()
    {
        return id;
    }
    
    public void setUserId(int userId)
    {
        this.userId = userId;
    }
    public int getUserId()
    {
        return userId;
    }
    
    public void setVehicleId(int vehicleId)
    {
        this.vehicleId = vehicleId;
    }
    public int getVehicleId()
    {
        return vehicleId;
    }
    
    public void setUsuario(Usuario user)
    {
        this.user = user;
    }
    public Usuario getUsuario()
    {
        return user;
    }
    
    public void setVehicle(Vehiculo vehicle)
    {
        this.vehicle = vehicle;
    }
    public Vehiculo getVehicle()
    {
        return vehicle;
    }
    
    ///Fin de getters y setters
    public void saveFile(String filename)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(filename), true)))
        {
            pw.println(this.id+","+this.userId+","+this.vehicleId);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Registro> readFile(String filename)
    {
        ArrayList<Registro> registros = new ArrayList<Registro>();
        
        try(Scanner sc = new Scanner(new File(filename)))
        {
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                Registro r = new Registro(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),
                Integer.parseInt(tokens[2]));
                registros.add(r);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return registros;
    }
    
    
}
