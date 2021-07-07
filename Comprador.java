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
 * @author ANDRES PORRAS
 */
public class Comprador extends Usuario
{
        
    public Comprador(int id, String nombres, String apellidos, String organizacion, String correo_electronico, String clave) 
    {
        super(id, nombres, apellidos, organizacion, correo_electronico, clave);
    }
    
    
    public static void registrarComprador(Scanner entrada, String filename)
    {
        Usuario u = Usuario.crearUsuario(entrada, filename);
        if(u!= null)
        {
            Comprador com = new Comprador(u.getId(),u.getNombres(),u.getApellidos(),
            u.getOrganizacion(),u.getCorreo_electronico(),u.getClave());

            com.saveFile(filename);
        }
        else
        {
            System.out.println("Comprador ya registrado");
        }
        
    }

    
}
