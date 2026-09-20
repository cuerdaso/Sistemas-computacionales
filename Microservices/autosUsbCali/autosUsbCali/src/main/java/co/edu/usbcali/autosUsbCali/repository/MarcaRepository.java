package co.edu.usbcali.autosUsbCali.repository;

import co.edu.usbcali.autosUsbCali.domain.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>{
}
