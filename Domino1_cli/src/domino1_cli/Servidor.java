package domino1_cli;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class Servidor {

    InetAddress address;
    String IpLocal;

    public Servidor() throws UnknownHostException {
        this.address = InetAddress.getLocalHost();
        IpLocal=InetAddress.getLocalHost().getHostAddress();
        //System.out.println("aaa "+IpLocal);
    }
}