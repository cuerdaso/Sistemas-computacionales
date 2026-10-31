package co.edu.usbcali.autosUsbCali.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Entity
@Table(name="citas_servicio")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CitaServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comprador_id")
    private Usuario comprador;

    @ManyToOne
    @JoinColumn(name = "asesor_id")
    private Usuario asesor;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @Column(name = "tipo_servicio", nullable = false, length = 100)
    private String tipoServicio;

    @Column(name = "fecha_cita", nullable = false)
    private OffsetDateTime fechaCita;

    @ColumnDefault("'AGENDADA'")
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "observaciones", length = Integer.MAX_VALUE)
    private String observaciones;

    @ColumnDefault("now()")
    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;

}
