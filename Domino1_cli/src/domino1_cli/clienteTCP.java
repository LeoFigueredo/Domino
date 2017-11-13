package domino1_cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class clienteTCP extends Thread{
    
    ventana _ventana;
    clienteUDP _clienteUDP;
    int portTCP = 10022;
    int capacidad = 256;
    String ip;
    boolean jugando;
    Socket _socket;
    InputStream entrada;
    OutputStream salida;
    JSON json;
    String cadena,mensaje;
    Servidor IpCliente;
    //clienteMULTICAST _clienteMULTICAST;
    
    public clienteTCP(ventana _ventana,String ip,clienteUDP _clienteUDP) throws UnknownHostException{
        this._ventana = _ventana;
        this.ip = ip;
        this._clienteUDP = _clienteUDP;
        jugando = true;  
        json= new JSON();
        IpCliente= new Servidor();
    }
    @Override
    public void run(){

    }
   public void decodificar_numeros(int carton, String numeros){

       int aux;
        String[] numerosComoArray = numeros.split("]");
        for (int i = 0; i < numerosComoArray.length; i++) {
            numerosComoArray[i]=numerosComoArray[i].substring(2,numerosComoArray[i].length());
          
            String[] columnas= numerosComoArray[i].split(",");
            for (int j = 0; j<columnas.length; j++) {
                aux=Integer.parseInt(columnas[j]);
      }
         
        }
  
       
         
  
   }
}

