/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;


public class Menu 
{
        
    public static void mostrarMenu(Scanner entrada)
    {
        clearScreen();
        System.out.println("##############VEHISHOP2.0##############");
        System.out.println("Elija un tipo de usuario:");
        System.out.println("1. Vendedor");
        System.out.println("2. Comprador");
        System.out.println("3. Salir");
//        System.out.println("#######################################");
        
        switch(entrada.nextInt())
        {
            case 1:
                clearScreen();                
                menuVendedores(entrada);
                break;
            case 2:
                clearScreen();
                System.out.println("Comprador");
                menuCompradores(entrada);
                break;
            case 3:
                clearScreen();
                System.out.println("Adios ;)");
                break;
            default:
                clearScreen();
                System.out.println("Opcion invalida. Por favor intente de nuevo");
                mostrarMenu(entrada);
                break;
        }
        
    }
    
    public static void menuVendedores(Scanner entrada)
    {   clearScreen();
        System.out.println("##############VENDEDORES###############");
        System.out.println("1. Registrar un nuevo vendedor");           
        System.out.println("2. Ingresar un nuevo vehiculo");
        System.out.println("3. Aceptar Oferta");
        System.out.println("4. Regresar");
        System.out.println("#######################################");
        switch(entrada.nextInt())
        {
            case 1:
                clearScreen();
                entrada.nextLine();
                Vendedor.registrarVendedor(entrada, "usuarios.txt");
                menuVendedores(entrada);
            break;
            
            case 2:
                clearScreen();
                entrada.nextLine();
                Usuario u = usuarioIsValid(entrada,"usuarios.txt");
                if(u != null)
                {
                    registrarVehiculo(entrada, "vehiculos.txt");
                    menuVendedores(entrada);
                }
                else
                {
                    System.out.println("Clave y/o usuario incorrecto");
                    menuVendedores(entrada);
                }
                
                
            break;
            case 3:
                clearScreen();
                entrada.nextLine();
                //mostrarOfertas
                Usuario uV = usuarioIsValid(entrada,"usuarios.txt");
                if(uV != null)
                {
                    mostrarOfertasVendedores(entrada);    
                    menuVendedores(entrada);
                }
                else
                {
                    System.out.println("Clave y/o usuario incorrecto");
                    menuVendedores(entrada);
                }
                
                
            break;
            case 4:
                clearScreen();
                entrada.nextLine();
                mostrarMenu(entrada);
            break;
            default:
                clearScreen();
                entrada.nextLine();
                System.out.println("Error, opcion invalida vuelva a intentarlo");
                menuVendedores(entrada);
            break;
        }
        
    }
    
    public static void menuCompradores(Scanner entrada)
    {   clearScreen();
        System.out.println("##############COMPRADORES###############");
        System.out.println("1. Registrar un nuevo comprador");           
        System.out.println("2. Ofertar por un vehiculo");
        System.out.println("3. Regresar");
        System.out.println("#######################################");
        switch(entrada.nextInt())
        {
            case 1:
                clearScreen();
                entrada.nextLine();
                Comprador.registrarComprador(entrada, "usuarios.txt");
                menuCompradores(entrada);
            break;
            
            case 2:
                clearScreen();
                entrada.nextLine();
                Usuario user = usuarioIsValid(entrada,"usuarios.txt");
                if(user != null)
                {
                    ArrayList<Vehiculo> listaVehiculos = Vehiculo.readFile("vehiculos.txt");
                    /*for(Vehiculo v : listaVehiculos)
                    {
                        String[] tokens = v.getClass().toString().split("\\.");
                        String clase = tokens[tokens.length - 1];
                        System.out.printf("Tipo: %s | Marca: %s | Modelo: %s | Motor: %s | Anio: %d | Recorrido: %d%n",
                                clase, v.getMarca(), v.getModelo(), v.getTipo_motor(), v.getAnio(), v.getRecorrido());
                    }*/
                    ArrayList<Vehiculo> listaFiltrada = filtrarVehiculos(entrada, listaVehiculos);
                    menuOfertar(listaFiltrada, entrada, user);
                    menuCompradores(entrada);
                }
                else
                {
                    System.out.println("Clave y/o usuario incorrecto");
                    menuCompradores(entrada);
                }
                
                
            break;
            case 3:
                clearScreen();
                entrada.nextLine();
                mostrarMenu(entrada);
            break;
            default:
                clearScreen();
                entrada.nextLine();
                System.out.println("Error, opcion invalida vuelva a intentarlo");
                menuCompradores(entrada);
            break;
        }
        
    }
    
    public static void mostrarOfertasVendedores(Scanner entrada)
    {
        ArrayList<Vehiculo> vehicles = Vehiculo.readFile("vehiculos.txt");
        ArrayList<Usuario> usuarios = Usuario.readFile("usuarios.txt");
        ArrayList<Oferta> ofertas = Oferta.readFile("ofertas.txt");
        
        ArrayList<Vehiculo> vehiculosListos = Oferta.linkVehiculos(ofertas, usuarios, vehicles);
        int opcion;
        int index = 0;
        String cadena;
        int cantidadOfertas = 0;
        for(Vehiculo v : vehiculosListos)
        {
            cantidadOfertas += v.getOfertas().size();
        }
        System.out.printf("Se han realizado %d ofertas en los vehiculos con las placas:%n",cantidadOfertas);
        for(Vehiculo v : vehiculosListos)
        {
            System.out.println(v.getPlaca());
        }
        
        System.out.println("Ingrese la placa: ");
        cadena = entrada.nextLine();
        
        for (Vehiculo v: vehiculosListos)
        {
            if(v.getPlaca().equals(cadena))
            {
                System.out.println(v);
                if(v.getOfertas().size() > 0)
                {
                    System.out.printf("Se han realizado %d ofertas%n", v.getOfertas().size());
                    while(true)
                    {
                        System.out.printf("Oferta %d%n", index);
                        System.out.printf("Correo: %s%n", v.getOfertas().get(index).getUsuario().getCorreo_electronico());
                        System.out.printf("Precio Ofertado: %.2f%n", v.getOfertas().get(index).getPrecio_ofertado());
                        
                        System.out.println("1. Oferta Previa");
                        System.out.println("2. Siguiente Oferta");
                        System.out.println("3. Aceptar Oferta");
                        System.out.println("4. Regresar");
                        cadena = entrada.nextLine();
                        while(!isNumero(cadena))
                        {
                            System.out.println("Debe escribir un numero entero");
                            cadena = entrada.nextLine();
                        }
                        opcion = Integer.parseInt(cadena);
                        
                        switch(opcion)
                        {
                            case 1:
                                if(index - 1 < 0)
                                    index = v.getOfertas().size() - 1;
                                else
                                    index-- ;
                                break;
                            case 2:
                                if(index + 1 >= v.getOfertas().size())
                                    index = 0;
                                else
                                    index++;
                                break;
                            case 3:
                                
                                v.removeFromVehicleFile("vehiculos.txt");
                                v.removeFromOfertaFile("ofertas.txt");
                                
                                String destinatario = v.getOfertas().get(index).getUsuario().getCorreo_electronico();
                                String asunto = "Oferta aceptada por un vendedor de la tienda ABC";
                                String cuerpo = "Este es un mensaje automatizado. Por favor no responda";
                                Util.enviarConGMail(destinatario, asunto, cuerpo);
                                return;
                            case 4:
                                return;
                            default:
                                break;
                                
                        }
                    }
                }
                System.out.println("No se han realizado ofertas por este vehiculo.");
            }
        }
       
        
        
    }
    
    public static void menuOfertar(ArrayList<Vehiculo> listaFiltrada, Scanner entrada, Usuario user)
    {
        int index = 0;
        int opcion = 0;
        String cadena;
        if (listaFiltrada.size() > 0)
        {
            while(index != -1)
            {
                clearScreen();
                String[] tokens = listaFiltrada.get(index).getClass().toString().split("\\.");
                String clase = tokens[tokens.length - 1];
                System.out.printf("Tipo: %s%n", clase);
                System.out.println(listaFiltrada.get(index).toString());

                System.out.println("1. Ver anterior vehiculo");
                System.out.println("2. Ver siguiente vehiculo");
                System.out.println("3. Realizar oferta por este vehiculo");
                System.out.println("4. Volver al menu de opciones");
        
                cadena = entrada.nextLine();
                while(!isNumero(cadena))
                {
                    System.out.println("Opcion invalida. Solo ingrese un numero por favor");
                    cadena = entrada.nextLine();
                }
                opcion = Integer.parseInt(cadena);
                switch(opcion)
                {
                    case 1:
                        if (index - 1 < 0)
                            index = listaFiltrada.size() - 1;
                        else
                            index--;
                        break;
                    case 2:
                        if(index + 1 >= listaFiltrada.size())
                            index = 0;
                        else
                            index++;
                        break;
                    case 3:
                        System.out.println("Por favor indique el precio ofertado");
                        cadena = entrada.nextLine();
                        while (!isPrecioValido(cadena))
                        {
                            System.out.println("Por favor ingrese un precio valido.\n(Use el punto . como separador decimal)");
                            cadena = entrada.nextLine();
                        }
                        double precioOfertado = Double.parseDouble(cadena);
                        user.registrarOferta(listaFiltrada.get(index).getId(),precioOfertado);
                        break;
                    case 4:
                        return;
                      
                    default:
                        System.out.println("Opcion invalida. Se volvera al primer vehiculo");
                        index = 0;
                        break;
                                
                }
            }
        }
        else
        {
            clearScreen();
            System.out.println("No existen vehiculos con estas caracteristicas. Regresando...");
        }    
        
            
    }
    public static ArrayList<Vehiculo> filtrarVehiculos(Scanner entrada, ArrayList<Vehiculo> listaVeh)
    {
        String tipo;
        int rangoInicial;
        int rangoFinal;
        int anioInicial;
        int anioFinal;
        double precioInicial;
        double precioFinal;
        ArrayList<Vehiculo> listaFinal = new ArrayList<Vehiculo>();
        
        System.out.println("Se le presentaran todos los vehiculos en existencia.");
        System.out.println("Al dejar un criterio vacio se consideraran todas las posibilidades");
        System.out.println("Tipo de vehiculo (Carro, moto o camioneta): ");
        tipo = entrada.nextLine();
        if(!tipo.equals(""))
        {
            while(!validarTipoVehiculo(tipo))
            {
                System.out.println("Tipo invalido, solo existen: [Carro, moto o camioneta]");
                tipo = entrada.nextLine();
            }
            
            for(Vehiculo v : listaVeh)
            {
                String[] tokens = v.getClass().toString().split("\\.");
                String clase = tokens[tokens.length - 1];
                if(clase.equals(tipo))
                {    
                    listaFinal.add(v);
                }
            }
        }
        else
        {
            tipo = "todos";
            for(Vehiculo v : listaVeh)
            {
                listaFinal.add(v);
            }

        }
        
        System.out.println("A continuacion registre el recorrido del vehiculo");
        System.out.println("Recorrido Menor? (Deje en blanco para ver todas las opciones)");
        String cadena = entrada.nextLine();
        if(!cadena.equals(""))
        {
            while(!isNumeroPositivo(cadena))
            {
                System.out.println("Debe ingresar un numero positivo.");
                System.out.println("Recorrido Menor?");
                cadena = entrada.nextLine();
            }
            rangoInicial = Integer.parseInt(cadena);
            System.out.println("Recorrido Mayor? (No puede quedar en blanco)");
            cadena = entrada.nextLine();
            while(!isNumeroPositivo(cadena))
            {
                System.out.println("Debe ingresar un numero positivo.");
                System.out.println("Recorrido Mayor?");
                cadena = entrada.nextLine();
            }
            rangoFinal = Integer.parseInt(cadena);
            
            for (Vehiculo v : listaFinal)
            {
                if(v.getRecorrido() < rangoInicial || v.getRecorrido() > rangoFinal)
                    listaFinal.remove(v);
            }
        }
        else
        {
            rangoInicial = -3;
            rangoFinal = -3;
        }
        System.out.println("A continuacion registre el rango de anios");
        System.out.println("Anio Menor? (Deje en blanco para ver todas las opciones)");
        cadena = entrada.nextLine();
        if(!cadena.equals(""))
        {
            while(!isNumeroPositivo(cadena))
            {
                System.out.println("Debe ingresar un numero positivo.");
                System.out.println("Anio Menor?");
                cadena = entrada.nextLine();
            }
            anioInicial = Integer.parseInt(cadena);
            System.out.println("Anio Mayor? (No puede quedar en blanco)");
            cadena = entrada.nextLine();
            while(!isNumeroPositivo(cadena))
            {
                System.out.println("Debe ingresar un numero positivo.");
                System.out.println("Anio Mayor?");
                cadena = entrada.nextLine();
            }
            anioFinal = Integer.parseInt(cadena);
            for (Vehiculo v : listaFinal)
            {
                if(v.getAnio() < anioInicial || v.getAnio() > anioFinal)
                    listaFinal.remove(v);
            }
        }
        else
        {
            anioInicial = -3;
            anioFinal = -3;
        }
        
        System.out.println("A continuacion registre el rango de precios");
        System.out.println("Precio Menor? (Deje en blanco para ver todas las opciones)");
        cadena = entrada.nextLine();
        if(!cadena.equals(""))
        {
            while(!isNumeroPositivo(cadena))
            {
                System.out.println("Debe ingresar un numero positivo.");
                System.out.println("Precio Menor?");
                cadena = entrada.nextLine();
            }
            precioInicial = Double.parseDouble(cadena);
            System.out.println("Precio Mayor? (No puede quedar en blanco)");
            cadena = entrada.nextLine();
            while(!isNumeroPositivo(cadena))
            {
                System.out.println("Debe ingresar un numero positivo.");
                System.out.println("Precio Mayor?");
                cadena = entrada.nextLine();
            }
            precioFinal = Double.parseDouble(cadena);
                     
            for (Vehiculo v : listaFinal)
            {
                if(v.getPrecio() < precioInicial || v.getPrecio() > precioFinal)
                    listaFinal.remove(v);
            }
        }
        else
        {
            precioInicial = -3;
            precioFinal = -3;
        }
        return listaFinal;
    }
    public static void registrarVehiculo(Scanner entrada, String filename)
    {
        System.out.println("Elija el vehiculo a registrar:");
        System.out.println("1.Auto\n2.Moto\n3.Camioneta");
        String opcion = entrada.nextLine();
        while(!isNumero(opcion))
        {
            System.out.println("Opcion debe ser un numero.\nPruebe de nuevo.");
            opcion = entrada.nextLine();
        }
        int op = Integer.parseInt(opcion);
        
        switch(op)
        {
            case 1:
                Carro.registrarCarro(entrada, filename);
                break;
            case 2:
                Moto.registrarMoto(entrada, filename);
                break;
            case 3:
                Camioneta.registrarCamioneta(entrada, filename);
                break;
            default:
                System.out.println("Opcion invalida. Intente de nuevo");
                registrarVehiculo(entrada, filename);
                break;
        }
    }
    public static Usuario usuarioIsValid(Scanner entrada, String filename)
    {
        clearScreen();
        System.out.println("################LOG-IN#################");
        System.out.printf("Correo: %n");
        String user = entrada.nextLine();
        System.out.printf("Clave: %n");
        String clave = entrada.nextLine();
        String finalClave = "";
                /////////Convierta la clave al hash SHA-256 y su equivalente en string
        try
        {
            finalClave = Util.toHexString(Util.getSHA(clave));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        try(Scanner archivo = new Scanner(new File(filename)))
        {
            while(archivo.hasNextLine())
            {
                String linea = archivo.nextLine();
                String[] tokens = linea.split(",");
                if (tokens[5].equals(user) && tokens[6].equals(finalClave))
                {
                    if(tokens[1].equals("Vendedor"))
                    {
                        return new Vendedor(Integer.parseInt(tokens[0]), 
                                tokens[2],tokens[3],tokens[4],tokens[5], tokens[6]);
                    }
                    else if(tokens[1].equals("Comprador"))
                    {
                        return new Comprador(Integer.parseInt(tokens[0]), 
                                tokens[2],tokens[3],tokens[4],tokens[5], tokens[6]);
                    }
                }

            }
            
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
        
    }
    
    public static boolean isNumero(String num)
    {
        try
        {
            Integer.parseInt(num);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    public static boolean isNumeroPositivo(String num)
    {
        try
        {
            int n = Integer.parseInt(num);
            if (n >= 0)
                return true;
            else
                return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    public static boolean isPrecioValido(String num)
    {
        try
        {
            double n = Double.parseDouble(num);
            if (n >= 0)
                return true;
            else
                return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    public static boolean validarTipoVehiculo(String cadena)
    {
        if (cadena.toLowerCase().equals("carro") || 
                cadena.toLowerCase().equals("camioneta") || 
                cadena.toLowerCase().equals("moto") || cadena.toLowerCase().equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static void clearScreen() 
    {
        try 
        {

            if (System.getProperty("os.name").contains("Windows"))

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            else

                Runtime.getRuntime().exec("clear");

        } 
        catch (IOException | InterruptedException ioe) {}

    }
}

    

