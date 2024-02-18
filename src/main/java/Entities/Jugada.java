package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@IdClass(JugadaId.class)
public class Jugada implements Serializable {
    @Id
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Jugador idJugador;
    @Id
    @ManyToOne
    private Palabra idPalabra;

    @Id
    private LocalDateTime fechaHora;

    @Column
    private boolean correcto;
    @Column
    private int puntos;

    public Jugada() {
    }

    public Jugada(Jugador idJugador, Palabra idPalabra, LocalDateTime fechaHora, boolean correcto, int puntos) {
        this.idJugador = idJugador;
        this.idPalabra = idPalabra;
        this.fechaHora = fechaHora;
        this.correcto = correcto;
        this.puntos = puntos;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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

