package PSP.AgendaContactos.Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Servidor iniciado en el puerto 5000...");
        Agenda agenda = new Agenda();

        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new ManejadorCliente(socket, agenda)).start();
        }
    }
}
