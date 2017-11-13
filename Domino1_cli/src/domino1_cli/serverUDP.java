package domino1_cli;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import sun.awt.windows.ThemeReader;

public class serverUDP extends Thread{
    
    int PortUDP = 3001;
    int capacidad = 256;
    boolean invitacion=true;
    DatagramSocket socket;
    InetAddress address;
    DatagramPacket paquete;
    JSON json;
    Servidor Servidor;
    String sala,cadena;
    boolean recibiendo;
    
    public serverUDP(String _sala) throws UnknownHostException{
        json= new JSON();
        Servidor= new Servidor();
        recibiendo=true;
        sala=_sala;
    }
    
    @Override
    public void run(){
        try {
       
         socket = new DatagramSocket(PortUDP);
        
         while(recibiendo){//mientras recibiendo sea verdadero
           JSON json= new JSON();
           byte[] mensaje_bytes = new byte[capacidad];
           DatagramPacket paquete = new DatagramPacket(mensaje_bytes,capacidad);//paquete
           socket.receive(paquete);//recibe el paquete
           String mensaje = new String(mensaje_bytes).trim();// convierte en String el PAQUETE
           
           System.out.println("Anuncio cliente: "+paquete.getAddress().getHostAddress());
                   }
           
         
      }
      catch (Exception e) {
         e.printStackTrace();
         System.exit(1);
      }
    }
}
