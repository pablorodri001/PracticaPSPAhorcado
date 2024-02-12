package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteAhorcado {

    //Por hacer:
    //  1. Pedir nombre al jugador
    //  2. Enviar nombre al servidor para añadir a la tabla jugadores
    //  3. Recibir respuesta del servidor: Nº de intentos (longitud de palabra) y mostrar caracteres
    //          (ejemplo: Palabra: _______ , Intentos restantes: 7)
    //  4. Pedir letra al jugador
    //  5. Envíar letra al servidor para comprobar si está en la palabra
    //  6. Recibir y mostrar respuesta del servidor:
    //      a. Si la letra está en la palabra mostrarla en su posicion (ejemplo: Palabra: ___R___ , Intentos restantes: 6)
    //      b. Si la letra no está solo se resta el intento (ejemplo: Palabra: _______ , Intentos restantes: 6)
    //  7. Repetir hasta adivinar todas las letras o hasta que se agoten los intentos.


    public static void main( String[] args ) {
        String host = "localhost";
        int puerto = 4500;

        try {
            Socket cliente = new Socket(host, puerto);
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

            System.out.print("Introduzce nombre: ");
            String nombre = leerString();
            flujoSalida.writeUTF(nombre);

            String respuesta = flujoEntrada.readUTF();
            System.out.println(respuesta);

            while (true) {
                System.out.print("Introduce letra: ");
                String letra = leerString();
                flujoSalida.writeUTF(letra);

                System.out.println(flujoEntrada.readUTF());
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    private static String leerString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
