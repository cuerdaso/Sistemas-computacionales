package co.edu.usbcali.autosUsbCali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name="facturas")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @Column(name = "numero_factura", nullable = false, length = 50)
    private String numeroFactura;

    @Column(name = "cufe")
    private String cufe;

    @Column(name = "url_documento", length = 500)
    private String urlDocumento;

    @Column(name = "subtotal", nullable = false, precision = 15, scale = 2)
    private BigDecimal subtotal;

    @ColumnDefault("0")
    @Column(name = "iva", nullable = false, precision = 15, scale = 2)
    private BigDecimal iva;

    @Column(name = "total", nullable = false, precision = 15, scale = 2)
    private BigDecimal total;

    @ColumnDefault("'GENERADA'")
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @ColumnDefault("now()")
    @Column(name = "fecha_emision", nullable = false)
    private OffsetDateTime fechaEmision;
}
