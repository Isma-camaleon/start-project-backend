package __PACKAGE__;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import __PACKAGE_ENTITY__.__CLASS__;

@Repository
public interface __CLASS__Repository extends JpaRepository<__CLASS__, __TIPO_VARIABLE_OBJ__> {

	/**
	 * Retorna un {@link Page} de todos los __CLASS_MIN__s que coinciden con al
	 * query
	 * 
	 * @param search   Texto a buscar
	 * @param pageable {@link PageRequest}
	 * @return Page&lt;__CLASS__&gt;
	 */
	@Query("select p from __CLASS__ p where p.__ATRIBUTO_BUSCADO__ like %?1%")
	Page<__CLASS__> findAllBySearch(String search, Pageable pageable);

}
