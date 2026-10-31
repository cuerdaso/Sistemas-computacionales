package co.edu.usbcali.autosUsbCali.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Entity
@Table(name="contratos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @Column(name = "numero_contrato", nullable = false, length = 50)
    private String numeroContrato;

    @Column(name = "url_documento", length = 500)
    private String urlDocumento;

    @ColumnDefault("false")
    @Column(name = "firmado_comprador", nullable = false)
    private Boolean firmadoComprador;

    @ColumnDefault("false")
    @Column(name = "firmado_asesor", nullable = false)
    private Boolean firmadoAsesor;

    @Column(name = "fecha_firma")
    private OffsetDateTime fechaFirma;

    @ColumnDefault("now()")
    @Column(name = "fecha_generacion", nullable = false)
    private OffsetDateTime fechaGeneracion;

}
