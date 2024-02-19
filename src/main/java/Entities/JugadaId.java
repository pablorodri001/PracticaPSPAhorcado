package Entities;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;
@Embeddable
public class JugadaId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "idJugador")
    private Jugador jugador;
    @ManyToOne
    @JoinColumn(name = "idPalabra")
    private Palabra palabra;
    private LocalDateTime fechaHora;


    public JugadaId() {
    }

    public JugadaId(Jugador jugador, Palabra palabra, LocalDateTime fechaHora) {
        this.jugador = jugador;
        this.palabra = palabra;
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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}