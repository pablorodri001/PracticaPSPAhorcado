package Entities;

import jdk.jfr.Timespan;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Jugada implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Jugador idJugador;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPalabra")
    private Palabra idPalabra;

   /* @ManyToOne
    @JoinColumn(name = "idJugador")
    private Jugador idJugador;
*/
    @Column
    private int puntuacion;
    @Id
    @Timespan
    private LocalDateTime fechaHora;
    @Column
    private boolean correcto;

    public Jugada(Jugador idJugador, Palabra idPalabra, int puntuacion, LocalDateTime fechaHora, boolean correcto) {
        this.idJugador = idJugador;
        this.idPalabra = idPalabra;
        this.puntuacion = puntuacion;
        this.fechaHora = fechaHora;
        this.correcto = correcto;
    }
}

