package co.edu.usbcali.autosUsbCali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable //no es una clase sino una entidad, es un grupo de atributos reutilizable
            // que se va a incrustar dentro de otra entidad
public class RolPermisoId implements Serializable{
    @Column(name = "rol_id")
    private Long rolId;

    @Column(name = "permiso_id")
    private Long permisoId;
}