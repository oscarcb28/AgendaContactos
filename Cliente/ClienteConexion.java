package PSP.AgendaContactos.Cliente;

import java.io.*;
import java.net.Socket;

public class ClienteConexion {
    public static String enviar(String mensaje) {
        try (Socket socket = new Socket("localhost", 5000);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.write(mensaje);
            out.newLine();
            out.flush();

            StringBuilder respuesta = new StringBuilder();
            String linea;
            while ((linea = in.readLine()) != null) {
                respuesta.append(linea).append("\n");
            }
            return respuesta.toString().trim();

        } catch (IOException e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
