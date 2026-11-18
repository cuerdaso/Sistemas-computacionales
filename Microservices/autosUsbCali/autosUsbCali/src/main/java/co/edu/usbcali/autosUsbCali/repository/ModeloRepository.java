package co.edu.usbcali.autosUsbCali.repository;

import co.edu.usbcali.autosUsbCali.domain.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo,Long>{

    boolean existsByMarca_Id(Long marcaId);

    List<Modelo> findByMarca_Id(Long marcaId);

    boolean existsByMarca_IdAndNombre (Long marcaId, String nombre);

    boolean existsByMarca_IdAndNombreAndIdNot(Long marcaId, String nombre, Long id);

}
