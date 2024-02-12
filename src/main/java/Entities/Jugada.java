package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Jugada implements Serializable {
    @Id
    private int idPartida;
    @Id
    private int idJugada;
    @Column
    private int puntuacion;
    @Column
    private LocalDateTime fechaHora;

    public Jugada() {
    }

    public Jugada(int idPartida, int idJugada, int puntuacion, LocalDateTime fechaHora) {
        this.idPartida = idPartida;
        this.idJugada = idJugada;
        this.puntuacion = puntuacion;
        this.fechaHora = fechaHora;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public int getIdJugada() {
        return idJugada;
    }

    public void setIdJugada(int idJugada) {
        this.idJugada = idJugada;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Jugadas{" +
                "idPartida=" + idPartida +
                ", idJugada=" + idJugada +
                ", puntuacion=" + puntuacion +
                ", fechaHora=" + fechaHora +
                '}';
    }
}
