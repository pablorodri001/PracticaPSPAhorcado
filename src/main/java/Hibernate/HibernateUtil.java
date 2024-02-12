package Hibernate;

import Entities.Jugada;
import Entities.Jugador;
import Entities.Palabra;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class HibernateUtil {
    private static StandardServiceRegistry registro;
    private static SessionFactory sessionfactory=null;

    public static SessionFactory getSessionfactory() {
        if(sessionfactory == null){
            try{
                // Crea un registro
                registro = new StandardServiceRegistryBuilder().configure().build();
                // Crea un MetadataSources
                MetadataSources sources = new MetadataSources(registro);
                // Crea Metadata
                Metadata metadata = sources.getMetadataBuilder().build();
                // Create SessionFactory
                sessionfactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                if (registro != null)
                    StandardServiceRegistryBuilder.destroy(registro);
            }
        }
        return sessionfactory;
    }

    public static void persistenciaJugadores(ArrayList<Jugador> listaJugadores) {

        StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

        Session sesion = sf.openSession();
        sesion.beginTransaction();

        for(Jugador jugador : listaJugadores) {
            sesion.save(jugador);
        }

        sesion.getTransaction().commit();

        sesion.close();
        sf.close();
    }

    public static void persistenciaJugadas(ArrayList<Jugada> listaJugadas) {

        StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

        Session sesion = sf.openSession();
        sesion.beginTransaction();

        for(Jugada jugada : listaJugadas) {
            sesion.save(jugada);
        }

        sesion.getTransaction().commit();

        sesion.close();
        sf.close();
    }

    public static void persistenciaPalabras(ArrayList<Palabra> listaPalabras) {

        StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

        Session sesion = sf.openSession();
        sesion.beginTransaction();

        for(Palabra palabra : listaPalabras) {
            sesion.save(palabra);
        }

        sesion.getTransaction().commit();

        sesion.close();
        sf.close();
    }

    public static void leerPalabras(){
        try {
            ArrayList<String> strings = new ArrayList<>();
            ArrayList<Palabra> palabras = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/palabras.txt"));
            Stream<String> lineas = br.lines();
            lineas.forEach(linea-> {
                strings.add(linea);
            });

            int contador = 1;
            for (String s : strings){
                palabras.add(new Palabra(contador, s));
                contador++;
            }

            persistenciaPalabras(palabras);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}