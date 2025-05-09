package PSP.AgendaContactos.Servidor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Agenda {
    private final Map<String, Set<String>> contactos = new ConcurrentHashMap<>();

    public synchronized String añadir(String nombre, String telefono) {
        if (!telefono.matches("\\d+")) return "ERR03\nEntrada inválida: '" + nombre + ":" + telefono + "'\n          ^";


        contactos.putIfAbsent(nombre, new TreeSet<>());
        Set<String> telefonos = contactos.get(nombre);

        if (!telefonos.add(telefono)) return "ERR01 - El número ya está registrado para este contacto.";
        return "✓ Contacto actualizado correctamente.";
    }

    public synchronized String buscar(String nombre) {
        if (!contactos.containsKey(nombre)) return "ERR02 - El contacto '" + nombre + "' no existe.";
        StringBuilder sb = new StringBuilder("✓ Teléfonos de " + nombre + ":");
        contactos.get(nombre).forEach(t -> sb.append("\n• ").append(t));
        return sb.toString();
    }

    public synchronized String eliminar(String nombre) {
        if (contactos.remove(nombre) != null)
            return "✓ Contacto '" + nombre + "' eliminado con éxito.";
        return "ERR02 - El contacto '" + nombre + "' no existe.";
    }

    public synchronized String listar() {
        if (contactos.isEmpty()) return "✓ La agenda está vacía.";
        StringBuilder sb = new StringBuilder("✓ Lista de contactos:");
        contactos.keySet().stream().sorted().forEach(n -> sb.append("\n• ").append(n));
        return sb.toString();
    }
}
