import java.util.Enumeration;
import java.net.NetworkInterface;
import java.net.InetAddress;

public class Oss7_2 {
    public static void main(String[] args) {
        try {
            // Retrieve all network interfaces
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            
            // Loop through each network interface
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface iface = netInterfaces.nextElement();
                System.out.println("Network Interface: " + iface.getName());
                
                // Get IP addresses assigned to the current interface
                Enumeration<InetAddress> ipAddresses = iface.getInetAddresses();
                
                if (!ipAddresses.hasMoreElements()) {
                    System.out.println("  No IP addresses found.");
                } else {
                    System.out.println("  IP addresses for this interface:");
                    while (ipAddresses.hasMoreElements()) {
                        InetAddress ipAddr = ipAddresses.nextElement();
                        System.out.println("    " + ipAddr.getHostAddress());
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("Error occurred: " + ex.getMessage());
        }
    }
}

