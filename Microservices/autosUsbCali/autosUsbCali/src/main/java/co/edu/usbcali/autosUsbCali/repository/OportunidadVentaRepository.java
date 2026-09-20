package co.edu.usbcali.autosUsbCali.repository;

import co.edu.usbcali.autosUsbCali.domain.OportunidadVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OportunidadVentaRepository extends JpaRepository<OportunidadVenta, Long>{
}
