package co.edu.usbcali.autosUsbCali.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name="cotizaciones")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "comprador_id")
    private Usuario comprador;

    @ManyToOne
    @JoinColumn(name = "asesor_id")
    private Usuario asesor;

    @Column(name = "codigo", nullable = false, length = 30)
    private String codigo;

    @Column(name = "precio_base", nullable = false, precision = 15, scale = 2)
    private BigDecimal precioBase;

    @ColumnDefault("0")
    @Column(name = "descuento", nullable = false, precision = 15, scale = 2)
    private BigDecimal descuento;

    @ColumnDefault("0")
    @Column(name = "impuestos", nullable = false, precision = 15, scale = 2)
    private BigDecimal impuestos;

    @Column(name = "precio_total", nullable = false, precision = 15, scale = 2)
    private BigDecimal precioTotal;

    @Column(name = "vigencia_hasta", nullable = false)
    private LocalDate vigenciaHasta;

    @ColumnDefault("'BORRADOR'")
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @ColumnDefault("now()")
    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;

}
