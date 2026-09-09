package co.edu.usbcali.autosUsbCali.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name="pagos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @Column(name = "tipo_pago", nullable = false, length = 30)
    private String tipoPago;

    @Column(name = "monto", nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(name = "medio_pago", nullable = false, length = 30)
    private String medioPago;

    @ColumnDefault("'PENDIENTE'")
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "referencia_externa", length = 100)
    private String referenciaExterna;

    @Column(name = "fecha_pago")
    private OffsetDateTime fechaPago;

    @ColumnDefault("now()")
    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;
}
