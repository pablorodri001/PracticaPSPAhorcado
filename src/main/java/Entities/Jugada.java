package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Jugada implements Serializable {
    @EmbeddedId
    JugadaId id;
    @Column
    private boolean correcto;
    @Column
    private int puntos;

    public Jugada() {
    }

    public Jugada(JugadaId id, boolean correcto, int puntos) {
        this.id = id;
        this.correcto = correcto;
        this.puntos = puntos;
    }

    public JugadaId getId() {
        return id;
    }

    public void setId(JugadaId id) {
        this.id = id;
    }

    public boolean isCorrecto() {
        return correcto;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}

