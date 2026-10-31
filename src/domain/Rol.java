package co.edu.usbcali.autosUsbCali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table (name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", length = Integer.MAX_VALUE)
    private String descripcion;

    @ColumnDefault("true")
    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
