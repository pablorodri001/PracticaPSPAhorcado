package Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    //Por hacer (Aquí o en clase a parte):
    //  -Método para leer palabras del txt y crear la tabla Palabras
    //  -Método para crear/updatear tabla Jugadores
    //  -Método para crear/updatear tabla Jugadas
    //  -Método para obtener palabra a partir del id

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
}