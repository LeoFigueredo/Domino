package domino1_cli;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class clienteUDP extends Thread{
    
    int PortUDP = 3001;
    int capacidad = 256;
    ventana _ventana;
    String[] ips;
    boolean recibiendo;
    Cliente Servidor;
    int bandera=0;
    JSON json;
    boolean invitacion=true;
    InetAddress address;
    DatagramPacket paquete;
    
    
    DatagramSocket socket;
    public clienteUDP(ventana _ventana){
        this._ventana = _ventana;
        recibiendo=true;
        ips =  new String[100];//a lo sumo no hay mas de 100 servidores
        json= new JSON();
        
    }
    
    @Override
    public void run(){
      byte[] mensaje_bytes = new byte[capacidad];
      String mensaje = json.identificacion().trim();//trim quita espacios al final del mensaje
      mensaje_bytes = mensaje.getBytes();
      
      try {
         socket = new DatagramSocket();
         address=InetAddress.getByName("255.255.255.255");//Mensaje broadcast para los cliente (DIRECCION IP)
         while (invitacion) {
           //el mensaje, la longitud del mensaje, la dirección Internet y el puerto local del socket de destino
            paquete = new DatagramPacket(mensaje_bytes,mensaje.length(),address,PortUDP);//Encapsula el paquete
            socket.send(paquete);//Lo envia
            Thread.sleep(1000);//y esta siempre enviando las peticiones
            
            
         }   
       }
      catch (Exception e) {
         System.err.println(e.getMessage());
         System.exit(1);
      }
    }
    
}
