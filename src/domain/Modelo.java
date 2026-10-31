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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

}
