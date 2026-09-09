package co.edu.usbcali.autosUsbCali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="solicitudes_credito")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SolicitudCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "entidad_financiera_id", nullable = false)
    private EntidadFinanciera entidadFinanciera;

    @Column(name = "monto_solicitado", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoSolicitado;

    @Column(name = "cuota_inicial", nullable = false, precision = 15, scale = 2)
    private BigDecimal cuotaInicial;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "observaciones")
    private String observaciones;

    @ColumnDefault("now()")
    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio;

    @Column(name="fecha_respuesta")
    private LocalDateTime fechaRespuesta;

}
