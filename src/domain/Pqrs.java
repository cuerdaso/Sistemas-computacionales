package co.edu.usbcali.autosUsbCali.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Entity
@Table(name="pqrs")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pqrs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comprador_id", nullable = false)
    private Usuario comprador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_asignado_id")
    private Usuario usuarioAsignado;

    @Column(name = "numero_radicado", nullable = false, length = 30)
    private String numeroRadicado;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "asunto", nullable = false, length = 200)
    private String asunto;

    @Column(name = "descripcion", nullable = false, length = Integer.MAX_VALUE)
    private String descripcion;

    @ColumnDefault("'RADICADA'")
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "respuesta", length = Integer.MAX_VALUE)
    private String respuesta;

    @ColumnDefault("now()")
    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;

    @Column(name = "fecha_cierre")
    private OffsetDateTime fechaCierre;

}
