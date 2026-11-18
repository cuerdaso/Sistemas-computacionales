package co.edu.usbcali.autosUsbCali.repository;

import co.edu.usbcali.autosUsbCali.domain.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    // ¿Algún vehículo usa este modelo? (vehiculos.modelo_id → modelos.id)
    boolean existsByModelo_Id(Long modeloId);
}
