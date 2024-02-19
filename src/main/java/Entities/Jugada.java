package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Jugada implements Serializable {
    @EmbeddedId
    private JugadaId idJugada;

    @Column
    private boolean correcto;
    @Column
    private int puntos;

    public Jugada() {
    }

    public Jugada(JugadaId idJugada, boolean correcto, int puntos) {
        this.idJugada = idJugada;
        this.correcto = correcto;
        this.puntos = puntos;
    }

    public JugadaId getIdJugada() {
        return idJugada;
    }

    public void setIdJugada(JugadaId idJugada) {
        this.idJugada = idJugada;
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

