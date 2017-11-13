/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino1_cli;

/**
 *
 * @author pc
 */
public class Cliente {

    JSON json= new JSON();
    String ServidorConectado;
    ventana _ventana;
    
    public String servidor_conectado(String mensaje){
        
        ServidorConectado=json.deco_(mensaje,"1");
        System.out.println("en cliente-> "+ServidorConectado);
      //  _ventana.lista.addItem("AAA");//aqui muestra el mensaje que envia servidor UDP
      return ServidorConectado;
    }
    
}