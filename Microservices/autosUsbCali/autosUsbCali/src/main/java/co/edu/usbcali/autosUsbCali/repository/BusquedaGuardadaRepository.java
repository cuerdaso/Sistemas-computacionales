package co.edu.usbcali.autosUsbCali.repository;

import co.edu.usbcali.autosUsbCali.domain.BusquedaGuardada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusquedaGuardadaRepository extends JpaRepository<BusquedaGuardada,Long>{
}
