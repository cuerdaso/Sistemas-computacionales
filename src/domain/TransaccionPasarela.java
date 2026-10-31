package co.edu.usbcali.autosUsbCali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;

@Entity
@Table(name="transacciones_pasarela")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransaccionPasarela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pago_id")
    private Pago pago;

    @Column(name = "proveedor", nullable = false, length = 50)
    private String proveedor;

    @Column(name = "referencia_pasarela", nullable = false, length = 100)
    private String referenciaPasarela;

    @Column(name = "monto", precision = 15, scale = 2)
    private BigDecimal monto;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "respuesta_raw")
    private Map<String, Object> respuestaRaw;

    @ColumnDefault("now()")
    @Column(name = "fecha_transaccion", nullable = false)
    private OffsetDateTime fechaTransaccion;

}
