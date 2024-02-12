package Entities;

import javax.persistence.*;

@Entity
public class Palabra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String palabra;

    public Palabra() {
    }

    public Palabra(int id, String palabra) {
        this.id = id;
        this.palabra = palabra;
    }

    public Palabra(String palabra) {
        this.palabra = palabra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public String toString() {
        return "Palabras{" +
                "id=" + id +
                ", palabra='" + palabra + '\'' +
                '}';
    }
}
