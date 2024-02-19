package Servidor;

import Entities.Jugada;
import Entities.JugadaId;
import Entities.Jugador;
import Entities.Palabra;
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

    public static void main(String[] args) {
        int puerto = 4500;

        ArrayList<Jugador> jugadores = new ArrayList<>();
        ArrayList<Jugada> jugadas = new ArrayList<>();

        int idPalabra = (int) (Math.random() * 100) + 1;
        String palabra = HibernateUtil.getPalabra(idPalabra).toLowerCase();
        Palabra palabraGenerada=new Palabra(idPalabra,palabra);
        int intentos = palabra.length();
        char[] pista = generarPista(palabra);
        boolean acierto;

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
            Jugador jugador = new Jugador(nombreJugador);
            HibernateUtil.persistenciaJugadores(jugador);

            flujoSalida.writeUTF("Bienvenido al juego del ahorcado, " + nombreJugador + ".\nPalabra: " +
                    Arrays.toString(pista) + " Nº de intentos: " + intentos);

            boolean fin = false;
            while(!fin){
                String letra = flujoEntrada.readUTF();
                LocalDateTime hora = LocalDateTime.now();
                int puntos = 0;

                pista = comprobarLetra(pista, letra, palabra);
                String palabraCliente = new String(pista);
                System.out.println(palabraCliente);

                if(Arrays.toString(pista).contains(letra)){
                    acierto=true;
                    puntos = 10;
                    flujoSalida.writeUTF("Acierto!");
                }
                else{
                    acierto = false;
                    flujoSalida.writeUTF("Error!");
                    intentos--;
                }

                if(palabraCliente.equals(palabra)){
                    flujoSalida.writeUTF("Has ganado, enhorabuena! La palabra era: " + palabra);
                    puntos = 50;
                    fin = true;
                }
                 else if (intentos==0){
                    flujoSalida.writeUTF("Has agotado todos los intentos, la palabra era: " + palabra);
                    fin = true;
                }
                else{
                    flujoSalida.writeUTF("Palabra a buscar: " + Arrays.toString(pista) + " Nº de intentos: " + intentos);
                }

                jugadas.add(new Jugada(new JugadaId(jugador, palabraGenerada, hora), acierto, puntos));
            }

            cerrarServer(jugadas, jugadores, flujoEntrada, flujoSalida, cliente, servidor);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void cerrarServer(ArrayList<Jugada> jugadas, ArrayList<Jugador> jugadores, DataInputStream flujoEntrada, DataOutputStream flujoSalida, Socket cliente, ServerSocket servidor) throws IOException {

        HibernateUtil.persistenciaJugadas(jugadas);

        flujoEntrada.close();
        flujoSalida.close();
        cliente.close();
        servidor.close();
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
