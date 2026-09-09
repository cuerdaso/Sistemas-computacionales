package co.edu.usbcali.autosUsbCali.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Entity
@Table(name="imagenes_vehiculo")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImagenVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @ColumnDefault("0")
    @Column(name = "orden", nullable = false)
    private Short orden;

    @ColumnDefault("false")
    @Column(name = "es_principal", nullable = false)
    private Boolean esPrincipal;

    @ColumnDefault("now()")
    @Column(name = "fecha_carga", nullable = false)
    private OffsetDateTime fechaCarga;


}
