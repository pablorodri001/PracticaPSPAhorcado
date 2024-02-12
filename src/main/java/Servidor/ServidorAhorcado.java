package Servidor;

import Entities.Jugada;
import Entities.Jugador;
import Hibernate.HibernateUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

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
        int puerto = 4500;

        ArrayList<Jugador> jugadores = new ArrayList<>();
        ArrayList<Jugada> jugadas = new ArrayList<>();

        int idPalabra = (int) (Math.random() * 100) + 1;
        String palabra = HibernateUtil.getPalabra(idPalabra);
        int intentos = palabra.length();
        char[] pista = generarPista(palabra);

        System.out.println("\nEl id a buscar es: " + idPalabra);
        System.out.println("La palabra escogida es: " + palabra);
        System.out.println("El numero de intentos es: " + intentos);
        System.out.println("La pista es: " + Arrays.toString(pista));

        try {
            ServerSocket servidor = new ServerSocket(puerto);

            Socket cliente = null;
            System.out.println("\nEsperando al cliente..........");
            cliente = servidor.accept();

            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

            String nombreJugador = flujoEntrada.readUTF();
            jugadores.add(new Jugador(nombreJugador));
            System.out.println(nombreJugador);

            flujoSalida.writeUTF("Bienvenido al juego del ahorcado, " + nombreJugador + ".\nPalabra a buscar: " +
                    Arrays.toString(pista) + " Nº de intentos: " + intentos);

            int partidaActual = HibernateUtil.getPartida();
            int jugadaActual = 1;

            while(true) {
                String letra = flujoEntrada.readUTF();

                pista = comprobarLetra(pista, letra, palabra);

                LocalDateTime h = LocalDateTime.now();
                //FALTA POR IMPLEMENTAR: PUNTUACIONES Y DETECTAR CUANDO SE HA COMPLETADO LA PALABRA
                jugadas.add(new Jugada(partidaActual, jugadaActual, 1, h));
                jugadaActual++;

                intentos--;
                if (intentos==0){
                    flujoSalida.writeUTF("Has agotado todos los intentos.");
                    break;
                }
                else{
                    flujoSalida.writeUTF("\nPalabra a buscar: " + Arrays.toString(pista) + " Nº de intentos: " + intentos);
                }
            }

            HibernateUtil.persistenciaJugadas(jugadas);
            HibernateUtil.persistenciaJugadores(jugadores);

            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
            servidor.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static char[] comprobarLetra(char[] pista, String letra, String palabra) {
        for (int i =0; i<palabra.length(); i++){
            if (Character.toLowerCase(palabra.charAt(i))==Character.toLowerCase(letra.charAt(0))){
                pista[i]=letra.charAt(0);
            }
        }
        return pista;
    }

    private static char[] generarPista(String palabra) {
        char[]pista = new char[palabra.length()];
        for (int i=0; i<palabra.length(); i++){
            pista[i] = '_';
        }
        return pista;
    }
}
