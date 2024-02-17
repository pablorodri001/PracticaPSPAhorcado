package Entities;

import jdk.jfr.Timespan;

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

    public Jugada() {
    }

    public Jugada(Jugador idJugador, Palabra idPalabra, LocalDateTime fechaHora, boolean correcto) {
        this.idJugador = idJugador;
        this.idPalabra = idPalabra;
        this.fechaHora = fechaHora;
        this.correcto = correcto;
    }

    public Jugador getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Jugador idJugador) {
        this.idJugador = idJugador;
    }

    public Palabra getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(Palabra idPalabra) {
        this.idPalabra = idPalabra;
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
}

