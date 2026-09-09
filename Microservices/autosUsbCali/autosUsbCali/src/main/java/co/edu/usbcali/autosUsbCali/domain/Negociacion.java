package co.edu.usbcali.autosUsbCali.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name="negociaciones")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Negociacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "oportunidad_id")
    private OportunidadVenta oportunidad;

    @ManyToOne
    @JoinColumn(name = "asesor_id")
    private Usuario asesor;

    @ManyToOne
    @JoinColumn(name = "aprobador_id")
    private Usuario aprobador;

    @Column(name = "precio_ofertado", nullable = false, precision = 15, scale = 2)
    private BigDecimal precioOfertado;

    @Column(name = "descuento_porcentaje", precision = 5, scale = 2)
    private BigDecimal descuentoPorcentaje;

    @Column(name = "descuento_valor", precision = 15, scale = 2)
    private BigDecimal descuentoValor;

    @ColumnDefault("false")
    @Column(name = "requiere_aprobacion", nullable = false)
    private Boolean requiereAprobacion;

    @Column(name = "estado_aprobacion", length = 20)
    private String estadoAprobacion;

    @Column(name = "fecha_aprobacion")
    private OffsetDateTime fechaAprobacion;

    @Column(name = "observaciones", length = Integer.MAX_VALUE)
    private String observaciones;

    @ColumnDefault("now()")
    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;


}
