package co.edu.usbcali.autosUsbCali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="roles_permisos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolPermiso{

    @EmbeddedId //Reemplaza el @id, y usa el grupo de campos dentro de RolPermisoId
    private RolPermisoId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("rolId")
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    //@MapsId("rolId") y @MapsId("permiso_id")
    //toma el id del objeto Rol y se asigna directamente al campo rolID que esta dentro
    //la clavr compuesta RolPermisoId, lo que evita datos duplicados

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("permisoId")
    @JoinColumn(name = "permiso_id", nullable = false)
    private Permiso permiso;
}
