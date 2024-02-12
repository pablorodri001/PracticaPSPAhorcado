package Servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAhorcado {

    //Por hacer:
    //  1. Recibir nombre del jugador. Crear objeto y subirlo a tabla jugadores
    //  2. Enviar mensaje al cliente: Nº de intentos (longitud de palabra) y mostrar caracteres
    //          (ejemplo: Palabra: _______ , Intentos restantes: 7)
    //  3. Recibir letra del cliente y comprobar si está en la palabra
    //  4. Guardar en tabla información de la jugada
    //  5. Envíar respuesta al cliente:
    //      a. Si la letra está en la palabra mostrarla en su posicion (ejemplo: Palabra: ___R___ , Intentos restantes: 6)
    //      b. Si la letra no está solo se resta el intento (ejemplo: Palabra: _______ , Intentos restantes: 6)
    //  5. Repetir hasta adivinar todas las letras o hasta que se agoten los intentos.

    public static void main(String[] args) {

        //Si no está creada la Tabla de palabras -> poner create en el .cfg y ejecutar la siguiente línea:
        //HibernateUtil.leerPalabras();

        int puerto = 4500;
        int idPalabra = (int) (Math.random() * 100) + 1;

        try {
            ServerSocket servidor = new ServerSocket(puerto);

            Socket cliente = null;
            System.out.println("Esperando al cliente..........");
            cliente = servidor.accept();

            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            ObjectOutputStream flujoSalida = new ObjectOutputStream(cliente.getOutputStream());

            while(true) {

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
