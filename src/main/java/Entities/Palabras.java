package Entities;

public class Palabras {
    private int id;
    private String palabra;

    @Override
    public String toString() {
        return "Palabras{" +
                "id=" + id +
                ", palabra='" + palabra + '\'' +
                '}';
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





    public Palabras(int id, String palabra) {
        this.id = id;
        this.palabra = palabra;
    }
}
