package co.edu.usbcali.autosUsbCali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "modelos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

}
