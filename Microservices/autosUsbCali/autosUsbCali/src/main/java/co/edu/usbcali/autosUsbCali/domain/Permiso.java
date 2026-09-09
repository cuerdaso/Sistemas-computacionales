package co.edu.usbcali.autosUsbCali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "permisos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "modulo", nullable = false, length = 50)
    private String modulo;

    @Column(name = "accion", nullable = false, length = 30)
    private String accion;

    @Column(name = "descripcion", length = Integer.MAX_VALUE)
    private String descripcion;
}
