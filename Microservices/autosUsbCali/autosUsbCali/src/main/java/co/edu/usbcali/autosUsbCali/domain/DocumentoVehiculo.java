package co.edu.usbcali.autosUsbCali.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name="documentos_vehiculo")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentoVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @Column(name = "tipo_documento", nullable = false, length = 50)
    private String tipoDocumento;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Column(name = "fecha_expedicion")
    private LocalDate fechaExpedicion;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @ColumnDefault("false")
    @Column(name = "validado", nullable = false)
    private Boolean validado;

    @Column(name = "fecha_validacion")
    private OffsetDateTime fechaValidacion;

    @Column(name = "usuario_valida_id")
    private Long usuarioValidaId;


}
