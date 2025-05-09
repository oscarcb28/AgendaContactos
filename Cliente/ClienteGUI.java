package PSP.AgendaContactos.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ClienteGUI extends JFrame {
    private final JTextArea resultado = new JTextArea(10, 30);
    private final JTextField entrada = new JTextField(30);

    public ClienteGUI() {
        setTitle("Cliente Agenda Telefónica");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        resultado.setEditable(false);
        add(new JScrollPane(resultado), BorderLayout.CENTER);
        add(entrada, BorderLayout.SOUTH);

        entrada.addActionListener(this::enviarPeticion);

        pack();
        setVisible(true);
    }

    private void enviarPeticion(ActionEvent e) {
        String mensaje = entrada.getText().trim();
        entrada.setText("");

        String respuesta = ClienteConexion.enviar(mensaje);
        resultado.setText(respuesta);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClienteGUI::new);
    }
}
