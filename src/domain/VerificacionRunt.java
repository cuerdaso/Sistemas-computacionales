package co.edu.usbcali.autosUsbCali.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.Map;

@Entity
@Table(name="verificaciones_runt")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerificacionRunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "usuario_consulta_id")
    private Usuario usuarioConsulta;

    @Column(name = "placa", nullable = false, length = 10)
    private String placa;

    @Column(name = "tiene_prendas")
    private Boolean tienePrendas;

    @Column(name = "tiene_embargos")
    private Boolean tieneEmbargos;

    @Column(name = "reporte_hurto")
    private Boolean reporteHurto;

    @Column(name = "estado_legal", length = 50)
    private String estadoLegal;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "respuesta_raw")
    private Map<String, Object> respuestaRaw;

    @ColumnDefault("now()")
    @Column(name = "fecha_consulta", nullable = false)
    private OffsetDateTime fechaConsulta;


}
