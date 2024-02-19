package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Jugada implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "idJugador")
    private Jugador jugador;
    @Id
    @ManyToOne
    @JoinColumn(name = "idPalabra")
    private Palabra palabra;
    @Id
    private LocalDateTime fechaHora;

    @Column
    private boolean correcto;
    @Column
    private int puntos;

    public Jugada() {
    }

    public Jugada(Jugador jugador, Palabra palabra, LocalDateTime fechaHora, boolean correcto, int puntos) {
        this.jugador = jugador;
        this.palabra = palabra;
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

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Palabra getPalabra() {
        return palabra;
    }

    public void setPalabra(Palabra palabra) {
        this.palabra = palabra;
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

