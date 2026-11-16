package co.edu.usbcali.autosUsbCali.repository;

import co.edu.usbcali.autosUsbCali.domain.Rol;
import co.edu.usbcali.autosUsbCali.domain.RolPermiso;
import co.edu.usbcali.autosUsbCali.domain.RolPermisoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// ⚠️ El segundo tipo genérico es RolPermisoId, NO Long,
// porque RolPermiso usa una clave compuesta (@EmbeddedId).
@Repository
public interface RolPermisoRepository extends  JpaRepository<RolPermiso, RolPermisoId>{

    boolean existsByRol_Id(Long rolId);
}
