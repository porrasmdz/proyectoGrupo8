/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyecto;

import ec.edu.espol.util.*;
import ec.edu.espol.model.Menu;
import java.util.Locale;
import java.util.Scanner;


  
/**
 *
 * @author ANDRES PORRAS
 */
public class Main 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner entrada = new Scanner(System.in);
        entrada.useDelimiter("\n");
        entrada.useLocale(Locale.US);
        Menu.mostrarMenu(entrada);
    }
}