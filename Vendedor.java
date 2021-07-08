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
