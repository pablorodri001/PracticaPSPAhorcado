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
import org.hibernate.query.Query;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
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

    public static void persistenciaJugadores(ArrayList<Jugador> listaJugadores) { //Crear tabla Jugadores

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

    public static void persistenciaJugadas(ArrayList<Jugada> listaJugadas) { //Crear tabla Jugadas

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

    public static void persistenciaPalabras(ArrayList<String> listaPalabras) { //Crear tabla Palabras

        StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

        Session sesion = sf.openSession();
        sesion.beginTransaction();

        for(String palabra : listaPalabras) {
            sesion.save(new Palabra(palabra));
        }

        sesion.getTransaction().commit();

        sesion.close();
        sf.close();
    }

    public static void leerPalabras(){ //Este método lee las palabras del .txt y crea la tabla
        try {
            ArrayList<String> palabras = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/palabras.txt"));
            Stream<String> lineas = br.lines();
            lineas.forEach(linea-> {
                palabras.add(linea);
            });

            persistenciaPalabras(palabras);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPalabra(int id){ //Este método devuelve palabra correspondiente al id que le metas
        StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

        Session sesion = sf.openSession();
        sesion.beginTransaction();

        Query<Palabra> query1 = sesion.createQuery("FROM Palabra", Palabra.class);
        List<Palabra> palabras = query1.getResultList();

        String target = null;
        for(Palabra p : palabras){
            if (p.getId() == id){
                target = p.getPalabra();
            }
        }

        sesion.getTransaction().commit();

        sesion.close();
        sf.close();

        return target;
    }

    public static int getPartida(){
        StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

        Session sesion = sf.openSession();
        sesion.beginTransaction();

        Query<Jugada> query1 = sesion.createQuery("FROM Jugada ORDER BY idPartida DESC", Jugada.class);
        List<Jugada> jugadas = query1.getResultList();
        int partidaActual=0;

        for(Jugada j : jugadas){
            partidaActual = j.getIdPartida() + 1;
        }

        sesion.getTransaction().commit();

        sesion.close();
        sf.close();

        return partidaActual;
    }

    public static void main(String[] args) {
        //Si no está creada la Tabla de palabras -> poner create en el .cfg y ejecutar la siguiente línea:
        HibernateUtil.leerPalabras();
    }
}