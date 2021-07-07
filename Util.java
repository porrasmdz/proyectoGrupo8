/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import javax.activation.*;
import javax.mail.*;
import javax.naming.*;
import javax.mail.internet.*;
import java.io.File;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author ANDRES PORRAS
 */
public class Util 
{
    private Util(){}
    
    
    
    public static int nextId(String filename)
    {
        int id = 0;
        try(Scanner sc = new Scanner(new File(filename)))
        {
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                id = Integer.parseInt(tokens[0]);
            }
        }
        catch(Exception e)
        {
            
        }
        return id+1;
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
    
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    { 
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
        return md.digest(input.getBytes(StandardCharsets.UTF_8)); 
    }
    
    public static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash); 
  
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
  
        while (hexString.length() < 32) 
        { 
            hexString.insert(0, '0'); 
        } 
  
        return hexString.toString(); 
    }
    
        
    public static void enviarConGMail(String destinatario, String asunto, String cuerpo) 
    {
        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
        String remitente = "freddyprueba75";  //Para la dirección nomcuenta@gmail.com

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", "proyecto8");    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            
            message.setFrom(new InternetAddress(remitente));
            
            message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, "proyecto8");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
}

}
