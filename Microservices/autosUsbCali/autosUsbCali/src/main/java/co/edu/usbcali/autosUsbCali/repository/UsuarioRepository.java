package co.edu.usbcali.autosUsbCali.repository;

import co.edu.usbcali.autosUsbCali.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    // "Roles" = el campo Set<Rol> roles de Usuario
    // "_Id"   = entra al Rol y compara su id
    // Spring genera el JOIN con usuarios_roles por ti.
    boolean existsByRoles_Id(Long rolId);
}
