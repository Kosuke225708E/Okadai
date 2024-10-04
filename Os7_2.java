import java.util.Enumeration;
import java.net.NetworkInterface;
import java.net.InetAddress;

public class Os7_2 {
    public static void main(String[] args) {
        try {
            // ネットワークインターフェースをすべて取得
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                System.out.println("ネットワークインターフェース名: " + networkInterface.getName());
                
                // 各インターフェースに関連付けられたIPアドレスを列挙
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                
                if (!addresses.hasMoreElements()) {
                    System.out.println("  利用可能なIPアドレスはありません。");
                }
                
                while (addresses.hasMoreElements()) {
                    InetAddress ipAddress = addresses.nextElement();
                    System.out.println("  IPアドレス: " + ipAddress.getHostAddress());
                }
            }
        } catch (Exception e) {
            System.err.println("エラーが発生しました: " + e.getMessage());
        }
    }
}
