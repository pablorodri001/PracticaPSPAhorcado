package Entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
@Embeddable
public class JugadaId implements Serializable {
    private int idJugador;
    private int idPalabra;
    private LocalDateTime fechaHora;


    public JugadaId() {
    }

    public JugadaId(int idJugador, int idPalabra, LocalDateTime fechaHora) {
        this.idJugador = idJugador;
        this.idPalabra = idPalabra;
        this.fechaHora = fechaHora;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public int getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(int idPalabra) {
        this.idPalabra = idPalabra;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
