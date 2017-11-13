
package domino1_cli;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSON {
    String ip, cadena_JSON,  error;
    String identificador ="identificador", nombre_mesa="nombre_mesa",nombre_jugador="nombre_jugador";
    String multicast_ip="multicast_ip", disponible="disponible", fichas="fichas", token="token";
    String entero_uno="entero_uno", entero_dos="entero_dos", punta_uno="punta_uno", punta_dos="punta_dos";
    String evento_pasado="evento_pasado", jugador="jugador", puntuacion="puntuacion", razon="razon";
    String puntuacion_general="puntuacion_general", ficha="ficha",punta="punta", DOMINIOCOMUNICACIONESI="DOMINIOCOMUNICACIONESI";
    String tipo="tipo";
    
    public JSON(){
        this.cadena_JSON = null;
        this.error = "No esta seleccionando una opcion valida.";
        this.ip = InetAddress.getLoopbackAddress().getHostAddress();
    }               
   
    public String code_(String[][] campo){
        JsonObject paquete = new JsonObject();
        for(int i=0; i<campo.length;i++){
          paquete.addProperty(campo[i][0], campo[i][1]);
        }
        cadena_JSON = paquete.toString();        
        return cadena_JSON;
    }
    
    public JsonObject code_paquete(String[][] campo){
        JsonObject paquete = new JsonObject();
        for(int i=0; i<campo.length;i++){
          paquete.addProperty(campo[i][0], campo[i][1]);
        }     
        return paquete;
    }
    
    public String deco_(String JSON, String opcion){// Opcion: 1-IP, 2-Cliente
        JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
        JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
            return Objeto.getAsJsonObject().get(opcion).getAsString();
    }
    
    public int get_tipo(String JSON){// Opcion: 1-IP, 2-Cliente
        JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
        JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
        try{
            return Objeto.getAsJsonObject().get(tipo).getAsInt();
        }
          catch(Exception e){
          return -1;
        }   
    }
    public String identificacion(){
       String [][] info= {{identificador,DOMINIOCOMUNICACIONESI}};
       m(code_(info));
       return code_(info);
    }
    public String paq_identificacion(String JSON){
        JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
        JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
        return Objeto.getAsJsonObject().get(identificador).getAsString();
    }
    
    public String dar_nombreMesa(String nombreMesa){
       String [][] info= {{identificador,DOMINIOCOMUNICACIONESI},
                          {nombre_mesa  ,nombreMesa}};
       m(code_(info));
       return code_(info);
    }
    public HashMap<String, String> paq_nombre_mesa(String JSON){
       HashMap<String, String> fields = new HashMap<String, String>();
         JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
         JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
           fields.put(identificador, Objeto.getAsJsonObject().get(identificador).getAsString());
           fields.put(nombre_jugador, Objeto.getAsJsonObject().get(nombre_mesa).getAsString());
       return fields;
    }
    
    public String dar_nombreJugador(String nombreJugador){
       String [][] info= {{identificador,DOMINIOCOMUNICACIONESI},
                          {nombre_jugador  ,nombreJugador}};
       m(code_(info));
       return code_(info);
    }
    
    public HashMap<String, String> paq_nombre_jugador(String JSON){
       HashMap<String, String> fields = new HashMap<String, String>();
         JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
         JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
           fields.put(identificador, Objeto.getAsJsonObject().get(identificador).getAsString());
           fields.put(nombre_jugador, Objeto.getAsJsonObject().get(nombre_jugador).getAsString());
       return fields;
    }
    
    public String serv_disponible(String ip_multicast){
       String [][] info= {{identificador,DOMINIOCOMUNICACIONESI},
                          {multicast_ip  ,ip_multicast}};
        JsonObject paquete= code_paquete(info);                                           
        cadena_JSON = paquete.toString();
        m(cadena_JSON);        
        return cadena_JSON;
    }
    public HashMap<String, String> paq_serv_disponible(String JSON){
       HashMap<String, String> fields = new HashMap<String, String>();
         JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
         JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
           fields.put(identificador, Objeto.getAsJsonObject().get(identificador).getAsString());
           fields.put(multicast_ip, Objeto.getAsJsonObject().get(multicast_ip).getAsString());
       return fields;
    }
        
    public String serv_fichas(int [][] _fichas_){
         cadena_JSON="";
        JsonObject paquete=  new JsonObject();
       
        paquete.addProperty(identificador,DOMINIOCOMUNICACIONESI);
        JsonArray paquete_fic=  new JsonArray();
        JsonObject paquete_obj=  new JsonObject();
        for(int i=0; i<_fichas_.length;i++){   
          paquete_obj = new JsonObject(); 
          paquete_obj.addProperty(token, _fichas_[i][0]);  
          paquete_obj.addProperty(entero_uno, _fichas_[i][1]);
          paquete_obj.addProperty(entero_dos, _fichas_[i][2]);
          
          paquete_fic.add(paquete_obj);
        } 
         
         paquete.add(fichas  ,paquete_fic);
         m("paq create: "+ paquete.toString());
       return paquete.toString() ;
    }
    
     public carta[] paq_fichas(String JSON){
         //JSON parser = new JsonParser();
         JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
         JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
         String cartas = Objeto.getAsJsonObject().get(fichas).toString();
         m("Paq Fichas "+cartas);
         
         JsonArray arrObj = Objeto.getAsJsonObject().get(fichas).getAsJsonArray();
            System.out.println("\nDirect Reports:");
            carta [] _fichas = new carta[arrObj.size()];
            carta cart;
            int i =0;
            for(JsonElement value : arrObj){
                _fichas[i]=new carta(value.getAsJsonObject().get(token).getAsInt(),value.getAsJsonObject().get(entero_uno).getAsInt(),value.getAsJsonObject().get(entero_dos).getAsInt());
                 i++;
            }
        return _fichas;
     }
     
    public JsonObject ficha(int ent1, int ent2, boolean _punta){
       JsonObject paquete=  new JsonObject();
             paquete.addProperty(entero_uno,ent1);
             paquete.addProperty(entero_dos,ent2);
             paquete.addProperty(punta,_punta);
        cadena_JSON = paquete.toString();
        m(cadena_JSON);        
        return paquete;
    }
    public JsonObject evento_pasado(int _tipo, String _jugador, JsonObject _ficha){
       JsonObject paquete=  new JsonObject();
             paquete.addProperty(tipo,_tipo);
             paquete.addProperty(jugador,_jugador);
             paquete.add(ficha,_ficha);
        cadena_JSON = paquete.toString();
        m(cadena_JSON);        
        return paquete;
    }
    public String serv_mensaje_juego(String _jugador,int _punta1, int _punta2, JsonObject _eventoPasado){
        JsonObject paquete=  new JsonObject();
             paquete.addProperty(identificador,DOMINIOCOMUNICACIONESI);    
             paquete.addProperty(jugador,_jugador);
             paquete.addProperty(tipo,0);
             paquete.addProperty(punta_uno,_punta1);
             paquete.addProperty(punta_dos,_punta2);
             paquete.add(evento_pasado,_eventoPasado);
        cadena_JSON = paquete.toString();
        m(cadena_JSON);        
        return cadena_JSON;
    }
    
    public HashMap<String, String> paq_mensajeJuego(String JSON){
       HashMap<String, String> fields = new HashMap<String, String>();
         JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
         JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
           fields.put(identificador, Objeto.getAsJsonObject().get(identificador).getAsString());
           fields.put("tipo_mensaje", Objeto.getAsJsonObject().get(tipo).getAsString());
           fields.put("jugador_actual", Objeto.getAsJsonObject().get(jugador).getAsString());
           fields.put(punta_uno, Objeto.getAsJsonObject().get(punta_uno).getAsString());
           fields.put(punta_dos, Objeto.getAsJsonObject().get(punta_dos).getAsString());
           //fields.put(evento_pasado, Objeto.getAsJsonObject().get(evento_pasado).getAsString());
           JsonElement ev_pas = Objeto.getAsJsonObject().get(evento_pasado);
           fields.put("tipo_juego", ev_pas.getAsJsonObject().get(tipo).getAsString());
           fields.put(jugador, ev_pas.getAsJsonObject().get(jugador).getAsString());
           //fields.put(ficha, ev_pas.getAsJsonObject().get(ficha).getAsString());
           JsonElement fic = ev_pas.getAsJsonObject().get(ficha);
           fields.put(entero_uno, fic.getAsJsonObject().get(entero_uno).getAsString());
           fields.put(entero_dos, fic.getAsJsonObject().get(entero_dos).getAsString());
           fields.put(punta, fic.getAsJsonObject().get(punta).getAsString());
       return fields;
    }
    
    public String serv_fin_ronda(String _jugador, int _puntuacion, String _razon){
     JsonObject paquete=  new JsonObject();
             paquete.addProperty(identificador,DOMINIOCOMUNICACIONESI); 
             paquete.addProperty(jugador,_jugador);
             paquete.addProperty(tipo,1);
             paquete.addProperty(puntuacion,_puntuacion);
             paquete.addProperty(razon,_razon);
        cadena_JSON = paquete.toString();
        m(cadena_JSON);        
        return cadena_JSON;
    }
    
    public HashMap<String, String> paq_fin_ronda(String JSON){
       HashMap<String, String> fields = new HashMap<String, String>();
         JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
         JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
           fields.put(identificador, Objeto.getAsJsonObject().get(identificador).getAsString());
           fields.put(jugador, Objeto.getAsJsonObject().get(jugador).getAsString());
           fields.put(tipo, Objeto.getAsJsonObject().get(tipo).getAsString());
           fields.put(puntuacion, Objeto.getAsJsonObject().get(puntuacion).getAsString());
           fields.put(razon, Objeto.getAsJsonObject().get(razon).getAsString());
       return fields;
    }
 
    public JsonArray Puntuaciones(String[] jug, int[] punt){
        JsonArray paquete_fic=  new JsonArray();
        JsonObject paquete_obj=  new JsonObject();
        for(int i=0; i<jug.length;i++){   
          paquete_obj = new JsonObject(); 
          paquete_obj.addProperty(jugador, jug[i]);  
          paquete_obj.addProperty(puntuacion, punt[i]);
          paquete_fic.add(paquete_obj);
        } 
       return paquete_fic ;
    }
    public String serv_fin_partida(String _jugador, JsonArray _puntuacion_general, String _razon){
       JsonObject paquete=  new JsonObject();
             paquete.addProperty(identificador,DOMINIOCOMUNICACIONESI); 
             paquete.addProperty(jugador,_jugador);
             paquete.addProperty(tipo,2);
             paquete.add(puntuacion_general,_puntuacion_general);
             paquete.addProperty(razon,_razon);
        cadena_JSON = paquete.toString();
        m(cadena_JSON);        
        return cadena_JSON;
    }
    
    public HashMap<String, String> paq_fin_partida_inf(String JSON){
         HashMap<String, String> fields = new HashMap<String, String>();
         JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
         JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
           fields.put(identificador, Objeto.getAsJsonObject().get(identificador).getAsString());
           fields.put(jugador, Objeto.getAsJsonObject().get(jugador).getAsString());
           fields.put(tipo, Objeto.getAsJsonObject().get(tipo).getAsString());
           fields.put(razon, Objeto.getAsJsonObject().get(razon).getAsString());
       return fields;
     }
    
    public HashMap<String, Integer> paq_fin_partida_puntaje(String JSON){
         HashMap<String, Integer> fields = new HashMap<String, Integer>();
         JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
         JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
         JsonArray arrObj = Objeto.getAsJsonObject().get(puntuacion_general).getAsJsonArray();
            System.out.println("\nScore Reports:");
            int i =0;
            for(JsonElement value : arrObj){
                fields.put(value.getAsJsonObject().get(jugador).getAsString(),value.getAsJsonObject().get(puntuacion).getAsInt());
                 i++;
            }
        return fields;
     }
    
    public String serv_desconexion(String _jugador){
       JsonObject paquete=  new JsonObject();
             paquete.addProperty(identificador,DOMINIOCOMUNICACIONESI); 
             paquete.addProperty(jugador,_jugador);
             paquete.addProperty(tipo,3);
        cadena_JSON = paquete.toString();
        m(cadena_JSON);        
        return cadena_JSON;
    }
    public HashMap<String, String> paq_serv_desconexion(String JSON){
       HashMap<String, String> fields = new HashMap<String, String>();
         JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
         JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
           fields.put(identificador, Objeto.getAsJsonObject().get(identificador).getAsString());
           fields.put(jugador, Objeto.getAsJsonObject().get(jugador).getAsString());
           fields.put(tipo, Objeto.getAsJsonObject().get(tipo).getAsString());;
       return fields;
    }
  
    public String cli_jugada(String _jugador, String _token, boolean _punta){
       JsonObject paquete=  new JsonObject();
        paquete.addProperty(token,_token);
        String Aux_JSON = paquete.toString();
       JsonObject pq= paquete; 
       paquete=  new JsonObject();
             paquete.addProperty(identificador,DOMINIOCOMUNICACIONESI); 
             paquete.add(ficha,pq);
             paquete.addProperty(punta,_punta);
        cadena_JSON = paquete.toString();
        m(cadena_JSON);        
        return cadena_JSON;
    }
    
    public HashMap<String, String> paq_cli_jugada(String JSON){
       HashMap<String, String> fields = new HashMap<String, String>();
         JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
         JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
           fields.put(identificador, Objeto.getAsJsonObject().get(identificador).getAsString());
           //fields.put(ficha, Objeto.getAsJsonObject().get(ficha).getAsString());
           JsonElement fic= Objeto.getAsJsonObject().get(ficha);
           fields.put(token, fic.getAsJsonObject().get(token).getAsString());
           fields.put(punta, Objeto.getAsJsonObject().get(punta).getAsString());
    return fields;
    }

      public String cli_conexion(String _jugador){
       JsonObject paquete=  new JsonObject();
             paquete.addProperty(identificador,DOMINIOCOMUNICACIONESI); 
             paquete.addProperty(jugador,_jugador);
        cadena_JSON = paquete.toString();
        m(cadena_JSON);        
        return cadena_JSON;
    }
    public HashMap<String, String> paq_cli_conexion(String JSON){
       HashMap<String, String> fields = new HashMap<String, String>();
         JsonParser parser = new JsonParser();//Representa un analizador basado en eventos que puede leer datos JSON en un stream. 
         JsonElement Objeto = parser.parse(JSON);    //como que recibe la cadena JSON y la decodifica en partes
           fields.put(identificador, Objeto.getAsJsonObject().get(identificador).getAsString());
           fields.put(jugador, Objeto.getAsJsonObject().get(jugador).getAsString());
       return fields;
    }
      
    public void m(String message){
        System.out.println(message);
    }
}

class carta {
 int ent1;
 int ent2;
 int token;
 boolean punta;
 
    public carta(int ent1, int ent2, int token) {
        this.ent1 = ent1;
        this.ent2 = ent2;
        this.token = token;
        this.punta = false;
    }
}
class fichas_jugador{
 public carta[] cartas;
 public boolean [] usada;
    public fichas_jugador(carta[] cartas) {
     this.cartas=cartas;
     //usada = new boolean[];
    }
}

