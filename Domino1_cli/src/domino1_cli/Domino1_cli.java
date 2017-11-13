package domino1_cli;

import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import org.omg.CORBA._PolicyStub;

public class Domino1_cli {

    public static void main(String[] args) {
        ventana obj = new ventana();
        
        HashMap<String, String> fields;
        HashMap<String, Integer> fields_int;
        //ventana obj = new ventana();
        JSON _ = new JSON();
        String aux;
        /*** Ejemplo de como colocar saludo inicial**/
        aux = _.identificacion();
        /*** Ejemplo de como obtener saludo inicial**/
        System.out.println(_.paq_identificacion(aux));



        /*** Ejemplo de como colocar nombre de jugador**/
        aux = _.dar_nombreJugador("Leonardo");
        /*** Ejemplo de como colocar nombre de jugador**/
        fields = _.paq_nombre_jugador(aux);
        System.out.println(fields.get(_.identificador));
       System.out.println(fields.get(_.nombre_jugador));

  
        /*** Ejemplo de como colocar nombre de mesa**/
        aux = _.dar_nombreMesa("Mesa_1_");
        /*** Ejemplo de como obtener nombre de mesa**/
        fields = _.paq_nombre_mesa(aux);
        System.out.println(fields.get(_.identificador));
        System.out.println(fields.get(_.nombre_mesa));

        
        /*** Ejemplo de como colocar respuesta multicast servidor**/
        aux = _.serv_disponible("ex.ex.ex.ex");
        /*** Ejemplo de como obtener respuesta multicast servidor**/
        fields = _.paq_serv_disponible(aux);
        System.out.println(fields.get(_.multicast_ip));


        /*** Ejemplo de como colocar fichas para jugador**/
        int[][] _fichas_ = {{0, 0, 0}, {10, 5, 5}, {7, 4, 3}, {36, 3, 6}};
        aux = _.serv_fichas(_fichas_);
        /*** Ejemplo de como obtener fichas para jugador**/
        carta[] cart = _.paq_fichas(aux);
        for (carta value : cart) {
            System.out.println("token: " + value.token + ", entero_uno:" + value.ent1 + ", entero_dos:" + value.ent2);
        }


        /*** Ejemplo de como colocar mensaje de juego**/
        //aux = _.serv_mensaje_juego("xd.xd.xd.xd", 2, 0, _.evento_pasado(0, "ex.ex.ex.ex", _.ficha(1, 2, true)));
        /*** Ejemplo de como obtener mensaje de juego**/
        //fields = _.paq_mensajeJuego(aux);
        //System.out.println(fields.get("tipo_mensaje"));
        //System.out.println(fields.get("jugador_actual"));
        //System.out.println(fields.get(_.punta_uno));
        //System.out.println(fields.get(_.punta_dos));
        //System.out.println(fields.get("tipo_juego"));
        //System.out.println(fields.get(_.ficha));
        //System.out.println(fields.get(_.jugador));
        //System.out.println(fields.get(_.entero_uno));
        //System.out.println(fields.get(_.entero_dos));
        //System.out.println(fields.get(_.punta));

        
        
        /*** Ejemplo de como colocar mensaje fin de ronda**/
          //  aux=_.serv_fin_ronda("dp.dp.dp.dp", 66, "gano por zapato");
        /*** Ejemplo de como obtener mensaje fin de ronda**/
          // fields= _.paq_fin_ronda(aux);
        //System.out.println(fields.get(_.jugador));
        //System.out.println(fields.get(_.ficha));
        //System.out.println(fields.get(_.tipo));
       // System.out.println(fields.get(_.puntuacion));
       // System.out.println(fields.get(_.razon));
     

        /*** Ejemplo de como colocar mensaje de juego**/
       // String jug[]= {"cd.cf.cv.vf","123.123.122.123","er.tr.yt.uy"};
       // int[] punt = {54,76,120};
       // aux=_.serv_fin_partida("ex.ex.ex.ex", _.Puntuaciones(jug,punt),"Zapatero");
        /*** Ejemplo de como obtener mensaje de juego**/
       //     fields= _.paq_fin_partida_inf(aux);
       //     fields_int= _.paq_fin_partida_puntaje(aux);
       //     for (Map.Entry<String, Integer> entry : fields_int.entrySet()) {
       //     System.out.println(entry.getKey() + "/" + entry.getValue());
       //     }
        
        /*** Ejemplo de como colocar mensaje de desconexion**/
       //     aux= _.serv_desconexion("er.er.er.er");
        /*** Ejemplo de como obtener mensaje de desconexion**/
       //     fields =_.paq_serv_desconexion(aux);
       //     System.out.println(fields.get(_.jugador));
            
       
        /*** Ejemplo de como colocar jugador en turno y jugando**/
       //     aux= _.cli_jugada("ex.ex.ex.ex","0", false);
        /*** Ejemplo de como obtener jugador en turno y jugando**/
       //     fields =_.paq_cli_jugada(aux);
       //     System.out.println(fields.get(_.token));
       //     System.out.println(fields.get(_.punta));
        
        /*** Ejemplo de como colocar jugador no esta en turno**/
       //     aux =_.cli_conexion("ex.ex.ex.ex");
        /*** Ejemplo de como obtener jugador no esta en turno**/
       //     fields = _.paq_cli_conexion(aux);
       //     System.out.println(fields.get(_.jugador));
       
    }

}
