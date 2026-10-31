package co.edu.usbcali.autosUsbCali.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name="ventas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "oportunidad_id")
    private OportunidadVenta oportunidad;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "comprador_id")
    private Usuario comprador;

    @ManyToOne
    @JoinColumn(name = "asesor_id")
    private Usuario asesor;

    @ManyToOne
    @JoinColumn(name = "solicitud_credito_id")
    private SolicitudCredito solicitudCredito;

    @Column(name = "numero_venta", nullable = false, length = 30)
    private String numeroVenta;

    @Column(name = "precio_final", nullable = false, precision = 15, scale = 2)
    private BigDecimal precioFinal;

    @Column(name = "forma_pago", nullable = false, length = 10)
    private String formaPago;

    @ColumnDefault("'EN_PROCESO'")
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "fecha_cierre")
    private OffsetDateTime fechaCierre;

    @ColumnDefault("now()")
    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;


}
