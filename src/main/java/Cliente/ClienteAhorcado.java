package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteAhorcado {

    //Por hacer:
    //  7. Repetir hasta adivinar todas las letras o hasta que se agoten los intentos.


    public static void main( String[] args ) throws IOException {
        String host = "localhost";
        int puerto = 4500;
        boolean ganar=true;

        try {
            Socket cliente = new Socket(host, puerto);
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            System.out.print("Introduzce nombre: ");
            String nombre = leerString();
            flujoSalida.writeUTF(nombre);

            String respuesta = flujoEntrada.readUTF();
            String[] auxiliar=respuesta.split(": ");
            //Implemente la variable auxiliar para determinar un numero maximo de iteraciones tanto en el cliente como en el servidor

            int intentos= Integer.parseInt(auxiliar[2]);
            System.out.println(respuesta);

            while(true) {
                System.out.print("Introduce letra: ");
                String letra = leerString();
                flujoSalida.writeUTF(letra);
                String respuesta2 = flujoEntrada.readUTF();
                System.out.println(respuesta2);
                if (respuesta2.equals("Has ganado enhorabuena")) {
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





