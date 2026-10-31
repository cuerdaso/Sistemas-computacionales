package co.edu.usbcali.autosUsbCali.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name="reservas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "oportunidad_id", nullable = false)
    private OportunidadVenta oportunidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comprador_id", nullable = false)
    private Usuario comprador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asesor_id")
    private Usuario asesor;

    @Column(name = "monto_reserva", precision = 12, scale = 2)
    private BigDecimal montoReserva;

    @Column(name = "vigencia_hasta", nullable = false)
    private OffsetDateTime vigenciaHasta;

    @ColumnDefault("'ACTIVA'")
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @ColumnDefault("now()")
    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;

}
