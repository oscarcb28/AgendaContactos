package PSP.AgendaContactos.Servidor;

import java.io.*;
import java.net.Socket;

public class ManejadorCliente implements Runnable {
    private final Socket socket;
    private final Agenda agenda;

    public ManejadorCliente(Socket socket, Agenda agenda) {
        this.socket = socket;
        this.agenda = agenda;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            String entrada = in.readLine();
            String respuesta = procesarPeticion(entrada);
            out.write(respuesta);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            System.err.println("Error al atender cliente: " + e.getMessage());
        }
    }

    private String procesarPeticion(String peticion) {
        if (peticion == null) return "ERR03\nPetición vacía.\n^";

        if (peticion.startsWith("buscar:")) {
            return agenda.buscar(peticion.substring(7).trim());
        } else if (peticion.startsWith("eliminar:")) {
            return agenda.eliminar(peticion.substring(9).trim());
        } else if (peticion.equals("contactos")) {
            return agenda.listar();
        } else if (peticion.contains(":")) {
            String[] partes = peticion.split(":", 2);
            if (partes.length == 2)
                return agenda.añadir(partes[0].trim(), partes[1].trim());
        }

        int posError = peticion.indexOf(':') >= 0 ? peticion.indexOf(':') : peticion.length();
        return "ERR03 - Error de sintaxis en la petición.\n" + peticion + "\n" + " ".repeat(posError) + "^";
    }
}
