package co.edu.usbcali.autosUsbCali.repository;

import co.edu.usbcali.autosUsbCali.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}
