
package domino1_cli;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.layout.BackgroundRepeat.ROUND;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public final class ventana extends JFrame{
    
    JLabel Modal, tableroo;
    String mesa,cliente, clientes[],IDJuegoServidor;
    JButton ini_server,ini_cliente, select_Server, ini_partida, select_cartones;
    JLabel texto;
    JComboBox lista;
    serverUDP _serverUDP;
    serverTCP obj2;
    servidorMULTICAST _servidorMULTICAST;
    clienteUDP _clienteUDP;
    String SIdJuego,SServidor;
    int cantCL=0;
    
    public ventana(){
        super("D O M I NO");
        this.setResizable(false);
        this.setSize(605, 628);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(null);
        this.setVisible(true);
        
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev){
                System.exit(0);
            }
        });
        
        tableroo = new JLabel(new ImageIcon("imagenes/tablero.jpg"));
        tableroo.setBounds(0, 0, 600, 600);
        this.getContentPane().add(tableroo);
        
        Modal = new JLabel(new ImageIcon("imagenes/modal.png"));
        Modal.setBounds(0, 0, 600, 600);
        this.getContentPane().add(Modal,0);
        
        texto = new JLabel();
        texto.setBounds(340, -3, 300, 30);
        texto.setFont(new java.awt.Font("Arial", 0, 22)); 
        texto.setForeground(Color.white);
        this.getContentPane().add(texto,0);
        
        this.iniciar();
    }
    public void iniciar(){
        ini_server = new JButton("Iniciar como Servidor");
        ini_server.setBounds(100, 400, 200, 50);
        ini_server.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent ev){
                try {
                    IniciarComoServer();
                } catch (UnknownHostException ex) {
                    Logger.getLogger(ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
        
        super.getContentPane().add(ini_server,0);
        
        ini_cliente = new JButton("Iniciar como Cliente");
        ini_cliente.setBounds(300, 400, 200, 50);
        ini_cliente.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent ev){
                try {
                    IniciarComoCliente();
                } catch (UnknownHostException ex) {
                    Logger.getLogger(ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
        super.getContentPane().add(ini_cliente,0);
        this.repaint();
    }
    public void IniciarComoServer() throws UnknownHostException{
        System.out.println("###################");
        System.out.println("# S E R V I D O R #");
        System.out.println("###################");
        System.out.println("");
        
        ini_server.setVisible(false);
        ini_cliente.setVisible(false);
        this.setTitle("D O M I N O - SERVIDOR");        
        //sala
        ObtenerMesa();
        //ENVIO INVITACION BROADCAST
        _serverUDP = new serverUDP(mesa);//Instancio objeto de la clase
        _serverUDP.start();//Corro el hilo para enviar la invitacion cada tantos segundos
        //ACEPTA cliente
          obj2 = new serverTCP(this, _serverUDP);
          obj2.start();//espera para aceptar cliente
        
        ini_partida = new JButton("JUGAR");
        ini_partida.setBounds(450, 500, 100, 50);
        ini_partida.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent ev){
                try {
                    servidor_ventana();
                } catch (UnknownHostException ex) {
                    Logger.getLogger(ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
        
        super.getContentPane().add(ini_partida,0);        
        texto.setText("Esperando peticiones...");
    }
    public void servidor_ventana() throws UnknownHostException{
    this.setBounds(0, 0, 1370,750);
    Modal.setVisible(false);
    tableroo.setVisible(false);
    ini_partida.setVisible(false);
    texto.setVisible(false);
    
    JLabel tablero = new JLabel(new ImageIcon("imagenes/tablero2.jpg"));
    tablero.setBounds(0, 0, 1370, 750);
    this.repaint();
     
//    obj2.detener();//detiene MENSAJES DE SERVIDOR UDP
    
  //  _servidorMULTICAST = new servidorMULTICAST(this);//Instancio objeto de la clase
   // _servidorMULTICAST.start();//Corro el hilo para enviar la invitacion cada tantos segundos
        //ACEPTA cliente
    //    obj2 = new serverTCP(this, _serverUDP);
      //  obj2.start();//espera para aceptar cliente
    
    
    
    this.getContentPane().add(tablero);
    
    }
    public void IniciarComoCliente() throws UnknownHostException{
        System.out.println("#################");
        System.out.println("# C L I E N T E #");
        System.out.println("#################");
        System.out.println("");
        
        ObtenerNombre();
        ini_server.setVisible(false);
        ini_cliente.setVisible(false);
        
        _clienteUDP  = new clienteUDP(this);
        _clienteUDP.start();
        
        lista = new JComboBox();
        lista.setBounds(170, 400, 270, 30);
        this.getContentPane().add(lista,0);
        
        select_Server = new JButton("Conectar");
        select_Server.setBounds(252, 450, 100, 40);
        select_Server.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent ev){         
                try {
                    //PUES INICIA!
                    PeticionServer();
                } catch (UnknownHostException ex) {
                    Logger.getLogger(ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
        
        this.getContentPane().add(select_Server,0);
        this.repaint();
  
    }
    public void PeticionServer() throws UnknownHostException{
            if(this.lista.getSelectedIndex() != -1){
            
            String ip = _clienteUDP.ips[ this.lista.getSelectedIndex() ];
            select_Server.setVisible(false);
            clienteTCP obj = new clienteTCP(this, ip,this._clienteUDP);
            obj.start();
            
            }
        }    
    public void tablero_cliente(String cantidad){
        Modal.setVisible(false);
        tableroo.setVisible(false);
        texto.setVisible(false);

        this.repaint();
        
        this.setBounds(0, 0, 1370,750);
        int cant,x=40, y=220, cont=1,x2;
        cant=Integer.parseInt(cantidad);
        JLabel tablero = new JLabel(new ImageIcon("imagenes/tablero2.jpg"));
        tablero.setBounds(0, 0, 1370, 750);
        
        
        this.getContentPane().add(tablero);
        
    }
    public void ObtenerMesa(){
          mesa = JOptionPane.showInputDialog(this, "MESA", "1" );
          while((mesa == null) || ("".equals(mesa.trim()))){
              mesa = JOptionPane.showInputDialog(this, "La mesa es obligatorio","1");
          }
    }
    public void ObtenerNombre() throws UnknownHostException{ 
            InetAddress maquina = InetAddress.getLocalHost();
            cliente = JOptionPane.showInputDialog(this, "Cliente", maquina.getHostName() );
            while(cliente == null){
                 cliente = JOptionPane.showInputDialog(this, "EL nombre es obligatorio","LEONARDO-DIEGO-ISRAEL");
            }
    }  
}

