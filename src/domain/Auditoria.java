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
@Table(name="auditoria")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "accion", nullable = false, length = 100)
    private String accion;

    @Column(name = "entidad", nullable = false, length = 50)
    private String entidad;

    @Column(name = "entidad_id")
    private Long entidadId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "datos_anteriores")
    private Map<String, Object> datosAnteriores;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "datos_nuevos")
    private Map<String, Object> datosNuevos;

    @Column(name = "ip_origen", length = 45)
    private String ipOrigen;

    @Column(name = "user_agent", length = Integer.MAX_VALUE)
    private String userAgent;

    @ColumnDefault("now()")
    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;


}
