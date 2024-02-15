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

            for(int i=0;i<intentos;i++){

                System.out.println("Desea introducir una palabra o una letra? (1 o 2)?");
                int opcion=new Scanner(System.in).nextInt();

                switch(opcion){
                    case 1:
                        System.out.println("Introduzca la palabra");
                        String palabra=leerString();
                        flujoSalida.writeUTF(palabra);
                        System.out.println(flujoEntrada.readUTF());
                        if(respuesta.equals("Has ganado enhorabuena")){
                            System.out.println(respuesta);
                            flujoEntrada.close();
                            flujoSalida.close();
                            cliente.close();}
                        break;
                    case 2:
                        System.out.print("Introduce letra: ");
                        String letra = leerString();
                        flujoSalida.writeUTF(letra);
                        System.out.println(flujoEntrada.readUTF());
                        if(respuesta.equals("Has ganado enhorabuena")){
                            System.out.println(respuesta);

                            flujoEntrada.close();
                            flujoSalida.close();
                            cliente.close();}

                        break;

                    default:
                        break;
                }

        }
            cliente.close();
            flujoEntrada.close();
            flujoSalida.close();



                System.out.print("Introduce letra: ");
                String letra = leerString();
                flujoSalida.writeUTF(letra);
                System.out.println(flujoEntrada.readUTF());




        }catch (IOException ex) {
            throw new RuntimeException(ex);

        }
    }
    private static String leerString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}





