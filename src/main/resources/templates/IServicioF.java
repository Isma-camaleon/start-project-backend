package __PACKAGE__;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import cl.uft.commons.model.ResultadoProc;
import cl.uft.commons.model.SearchPagination;

import __PACKAGE_ENTITY__.__CLASS__;

public interface I__CLASS__Service {

	/**
	 * Busca aa __CLASS_MIN__ por su ID.
	 * 
	 * @param __CLASS_MIN__Id (Identificador de la __CLASS_MIN__)
	 * @return ResultadoProc&lt;__CLASS__&gt; __CLASS__ con el ID dado
	 */
	ResultadoProc<__CLASS__> findById(__TIPO_VARIABLE_PK__ __CLASS_MIN__Id);

	/**
	 * Busca una __CLASS_MIN__ por su ID y que se encuentre activa.
	 * 
	 * @param __CLASS_MIN__Id (Identificador de la __CLASS_MIN__)
	 * @return ResultadoProc&lt;__CLASS__&gt; __CLASS__ activa con el ID dado
	 */
	ResultadoProc<__CLASS__> findByIdAndActivoTrue(__TIPO_VARIABLE_PK__ __CLASS_MIN__Id);

	/**
	 * Guarda una __CLASS_MIN__
	 * 
	 * @param __CLASS_MIN__ {@link __CLASS__} que se desea guardar
	 * @return ResultadoProc&lt;__CLASS__&gt; __CLASS__ guardada
	 */
	ResultadoProc<__CLASS__> save(__CLASS__ __CLASS_MIN__);

	/**
	 * Retorna una {@link Page} de todas las __CLASS_MIN__s registradas
	 * 
	 * @param pageable Entidad {@link PageRequest} que contiene los datos de la
	 *                 paginación
	 * @return ResultadoProc&lt;Page&lt;__CLASS__&gt;&gt; Una página de
	 *         __CLASS_MIN__s
	 */
	ResultadoProc<Page<__CLASS__>> findAllPaginated(PageRequest pageable);

	/**
	 * Retorna una {@link Page} de todas las __CLASS_MIN__s registradas
	 * 
	 * @param pageable Entidad {@link PageRequest} que contiene los datos de la
	 *                 paginación
	 * @param search   Texto a buscar
	 * @return ResultadoProc&lt;Page&lt;__CLASS__&gt;&gt; Una página de
	 *         __CLASS_MIN__s
	 */
	ResultadoProc<Page<__CLASS__>> findAllPaginatedBySearch(PageRequest pageable, String search);

	/**
	 * Cambia el estado de la __CLASS_MIN__ <br>
	 * Si activo es <code><b>true</b></code> lo cambia a <code><b>false</b></code>
	 * <br>
	 * Si activo es <code><b>false</b></code> lo cambia a <code><b>true</b></code>
	 * <br>
	 * 
	 * @param __CLASS_MIN__Id (Id de la __CLASS_MIN__)
	 * @return ResultadoProc&lt;__CLASS__&gt; __CLASS_MIN__ a la que le cambio el
	 *         estado
	 */
	// ResultadoProc<__CLASS__> changeState(__TIPO_VARIABLE_PK__ __CLASS_MIN__Id);

	/**
	 * Actualiza una __CLASS_MIN__
	 * 
	 * @param __CLASS_MIN__ {@link __CLASS__} que se desea actualizar
	 * @return ResultadoProc&lt;__CLASS__&gt; __CLASS__ actualizada
	 */
	ResultadoProc<__CLASS__> update(__CLASS__ __CLASS_MIN__Param);

}
