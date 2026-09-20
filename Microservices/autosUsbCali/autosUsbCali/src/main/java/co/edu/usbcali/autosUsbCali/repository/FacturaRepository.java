package co.edu.usbcali.autosUsbCali.repository;

import co.edu.usbcali.autosUsbCali.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long>{
}
