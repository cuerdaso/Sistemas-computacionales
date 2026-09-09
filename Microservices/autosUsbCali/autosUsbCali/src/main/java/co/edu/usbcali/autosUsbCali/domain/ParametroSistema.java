package co.edu.usbcali.autosUsbCali.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Entity
@Table(name="parametros_sistema")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParametroSistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_actualiza_id")
    private Usuario usuarioActualiza;

    @Column(name = "clave", nullable = false, length = 100)
    private String clave;

    @Column(name = "valor", nullable = false, length = Integer.MAX_VALUE)
    private String valor;

    @ColumnDefault("'STRING'")
    @Column(name = "tipo_dato", nullable = false, length = 10)
    private String tipoDato;

    @Column(name = "descripcion", length = Integer.MAX_VALUE)
    private String descripcion;

    @Column(name = "fecha_actualizacion")
    private OffsetDateTime fechaActualizacion;

}
