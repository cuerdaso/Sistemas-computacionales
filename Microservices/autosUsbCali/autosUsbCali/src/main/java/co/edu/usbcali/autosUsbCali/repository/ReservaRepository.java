package co.edu.usbcali.autosUsbCali.repository;

import co.edu.usbcali.autosUsbCali.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{
}
