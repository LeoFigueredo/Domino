package domino1_cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.apache.commons.codec.digest.DigestUtils;

public class serverTCP extends Thread{
    int portTCP = 3001;
    int capacidad = 256;
    int y=10;
    Socket[] clientes;
    serverUDP _serverUDP;
    ventana _ventana;
    String prueba, cadena;
    JSON json;
    Servidor Servidor;
    InputStream entrada;
    OutputStream salida;
    ServerSocket server_acepta_peticiones;
    
    public serverTCP(ventana _ventana,serverUDP _serverUDP) throws UnknownHostException{
        this._ventana = _ventana;
        this._serverUDP = _serverUDP;
        clientes =  new Socket[4];
        this._ventana.clientes= new String[4];
        json= new JSON();
        Servidor= new Servidor();
    }
    @Override
    public void run(){
        server_acepta_peticiones = (ServerSocket)null; //Para que el cliente acepte las peticiones NULL
        try {
            //primer parametro (puerto,tiempo que dura activo en segundos)
            server_acepta_peticiones = new ServerSocket( portTCP);
        } catch( IOException e ) {
            System.out.println( e );
        }
        try {
           do{System.out.println(_ventana.cantCL);
               clientes[_ventana.cantCL] = server_acepta_peticiones.accept();
               //acepto cliente 
               salida = clientes[_ventana.cantCL].getOutputStream();//output es escritura en bytes
               entrada = clientes[_ventana.cantCL].getInputStream();
               //instancio puente de comunicacion
               byte _bytes[]= new byte[capacidad];
               //espero solicitud  
                conexion();
                
            }while(_ventana.cantCL<4);
            
            
            server_acepta_peticiones.close();
           _serverUDP.invitacion=false;//deja de enviar mensaje broadcast y de enviar la invitacion a los clientes
           _ventana.setSize(100, 100);//cambia el tamaño de la ventana del servidor
           _ventana.Modal.setVisible(false);
           _ventana.repaint();
        } 
        catch( Exception e ) {
            try {
                server_acepta_peticiones.close();    
            } catch (Exception ex) {
                //Logger.getLogger(serverTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println( "error de Servidor TCP: " +e );
                    e.printStackTrace();
            System.exit(1);
        }
    }
    int idC = 0;
    int idJ=0;
    public void conexion() throws IOException, InterruptedException{
                
                String idCmd5;
                int cant=0;
                String idJmd5;
                Random rdn = new Random();
                     
                byte _byte[]= new byte[capacidad];
    
                               
                //leer 
                entrada.read(_byte);
                String mensaje = new String(_byte);
                System.out.println("recibeSERVIDOR "+mensaje);
                mensaje = mensaje.trim(); 
               
                
                if(json.deco_(mensaje,"0").compareToIgnoreCase("DOMINIOCOMUNICACIONESI")==0){
                   System.out.println("Solicitud de Conexion, Cliente: "+json.deco_(mensaje, "0"));
                  
                }
                
                _byte= new byte[capacidad];
                entrada.read(_byte);
                mensaje = new String(_byte);
                System.out.println("recibeSERVIDOR "+mensaje);
                mensaje = mensaje.trim(); 
                
                
                
           
                
                _ventana.cantCL++;
   }
   public void detener(){
       _serverUDP.invitacion=false;
   } 
    public void decodificar_numeros(int carton, String numeros){
       System.out.println("numeros "+numeros);
       int aux;
        String[] numerosComoArray = numeros.split("]");
        for (int i = 0; i < numerosComoArray.length; i++) {
            numerosComoArray[i]=numerosComoArray[i].substring(2,numerosComoArray[i].length());
       
            String[] columnas= numerosComoArray[i].split(",");
            for (int o = 0; o<columnas.length; o++) {
                aux=Integer.parseInt(columnas[o]);
              
            }
            
        }

  }        
        
  
   }

