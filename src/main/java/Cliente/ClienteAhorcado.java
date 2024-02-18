package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteAhorcado {

    public static void main( String[] args ) throws IOException {
        String host = "localhost";
        int puerto = 4500;

        try {
            Socket cliente = new Socket(host, puerto);
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

            System.out.print("Introduce tu nombre: ");
            String nombre = leerString();
            flujoSalida.writeUTF(nombre);

            String respuesta = flujoEntrada.readUTF();
            System.out.println("\n" + respuesta);

            while(true) {
                System.out.print("Introduce letra: ");
                String letra = leerString();
                flujoSalida.writeUTF(letra);

                String resultado = flujoEntrada.readUTF();
                System.out.println("\n" + resultado);

                String respuesta2 = flujoEntrada.readUTF();
                System.out.println("\n" + respuesta2);
                String[] split = respuesta2.split(",");

                if (split[0].equals("Has ganado") || split[0].equals("Has agotado todos los intentos")) {
                    break;
                }
            }

            cliente.close();
            flujoEntrada.close();
            flujoSalida.close();

        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private static String leerString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}





