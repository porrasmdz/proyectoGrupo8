/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.util.ArrayList;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Usuario {
    protected int id;
    protected String nombres;
    protected String apellidos;
    protected String organizacion;
    protected String correo_electronico;
    protected String clave;
    protected ArrayList<Registro> registros; //Es un atributo, estaba como metodo
    protected static ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
    

    public Usuario(int id, String nombres, String apellidos, String organizacion,
            String correo_electronico, String clave) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo_electronico = correo_electronico;
        this.clave = clave;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public void setRegistros(ArrayList<Registro> registros)
    {
        this.registros = registros;
    }
    
    public ArrayList<Registro> getRegistros()
    {
        return registros;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos)
    {
        this.vehiculos = vehiculos;
    }
    
    public ArrayList<Vehiculo> getListaVehiculos()
    {
        return vehiculos;
    }
    //Fin de getters y setters
    
    public static Usuario searchById(ArrayList<Usuario> users,int userId)
    {
        for (Usuario user:users)
        {
            if(user.getId() == userId)
            {
                return user;
            }
        }
        System.out.println("El usuario no se encuentra en los registros");
        return null;
    }
    
        
    public static Usuario crearUsuario(Scanner entrada, String filename)
    {
        ArrayList<Usuario> existentes = readFile(filename);
        
        System.out.println("Nombres?");
        String nombres = entrada.nextLine();
        System.out.println("Apellidos?");
        String apellidos = entrada.nextLine();
        System.out.println("Organizacion?");
        String organizacion = entrada.nextLine();
        System.out.println("Correo electronico?");
        String correo = entrada.nextLine();
        
        for(Usuario u:existentes)
        {
            if(u.getCorreo_electronico().equals(correo))
            {
                System.out.println("Este correo ya existe");
                return null;
            }
        }
        
        System.out.println("Clave?");
        String clave = entrada.nextLine();
        String claveFinal;
        int id = Util.nextId(filename);
        
        try
        {
            claveFinal = Util.toHexString(Util.getSHA(clave));
            Usuario user = new Usuario(id, nombres, apellidos, organizacion, correo, claveFinal);
            return user;
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Error al crear usuario.");
            return null;
        }
        
    } 
    
    public void saveFile(String filename)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(filename), true)))
        {
            String[] tokens = this.getClass().toString().split("\\.");
            String clase = tokens[tokens.length - 1];
            
            pw.println(this.id+","+clase+","+this.nombres+","+this.apellidos+","+this.organizacion+
                    ","+this.correo_electronico+","+this.clave);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }   
    }
    
    public static ArrayList<Usuario> readFile(String filename)
    {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try(Scanner sc = new Scanner(new File(filename)))
        {
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                
                if(tokens[1].equals("Vendedor"))
                {
                    usuarios.add(new Vendedor(Integer.parseInt(tokens[0]),tokens[2],tokens[3],
                            tokens[4],tokens[5],tokens[6]));
                }
                else if(tokens[1].equals("Comprador"))
                {
                    usuarios.add(new Comprador(Integer.parseInt(tokens[0]),tokens[2],tokens[3],
                            tokens[4],tokens[5],tokens[6]));
                }
            }
            return usuarios;
        }
        catch(Exception e)
        {
            System.out.println("Error al leer el archivo");
            return readFile(filename);
        }
        
    }
    
        
    public void registrarOferta(int idVehiculo, double precio)
    {
        int index = Util.nextId("ofertas.txt");
        Oferta oferta = new Oferta(index, this.id, idVehiculo, precio);
        oferta.saveFile("ofertas.txt");
        
    }

}
