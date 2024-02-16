package Entities;

import javax.persistence.*;

@Entity
public class Palabra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPalabra;
    @Column
    private String palabra;

    public Palabra() {
    }

    public Palabra(int idPalabra, String palabra) {
        this.idPalabra = idPalabra;
        this.palabra = palabra;
    }

    public Palabra(String palabra) {
        this.palabra = palabra;
    }

    public int getId() {
        return idPalabra;
    }

    public void setId(int id) {
        this.idPalabra = idPalabra;
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
                "id=" + idPalabra +
                ", palabra='" + palabra + '\'' +
                '}';
    }
}
