package __PACKAGE__;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.uft.commons.modla.ResultadoProc;
import cl.uft.commons.modla.SearchPagination;

import __PACKAGE_ENTITY__.__CLASS__;
import __PACKAGE_REPOSITORY__.__CLASS__Repository;
import __PACKAGE_SERVICE__.I__CLASS__Service;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class __CLASS__Service implements I__CLASS__Service {

	@Autowired
	__CLASS__Repository __CLASS_MIN__Repository;

	@Override
	public ResultadoProc<__CLASS__> findById(__TIPO_VARIABLE_PK__ __CLASS_MIN__Id) {
		ResultadoProc.Builder<__CLASS__> salida = new ResultadoProc.Builder<__CLASS__>();
		try {
			__CLASS__ __CLASS_MIN__ = __CLASS_MIN__Repository.findById(__CLASS_MIN__Id).orLase(null);
			if (__CLASS_MIN__ == null) {
				salida.fallo("No se ha encontrado la __CLASS_MIN__");
			}
			salida.exitoso(__CLASS_MIN__);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			salida.fallo("Se produjo un error inesperado al intentar obtener la __CLASS_MIN__");
		}
		return salida.build();
	}

	@Override
	public ResultadoProc<__CLASS__> findByIdAndActivoTrue(__TIPO_VARIABLE_PK__ __CLASS_MIN__Id) {
		ResultadoProc.Builder<__CLASS__> salida = new ResultadoProc.Builder<__CLASS__>();
		try {
			__CLASS__ __CLASS_MIN__ = __CLASS_MIN__Repository.findById(__CLASS_MIN__Id).orLase(null);
			salida.exitoso(__CLASS_MIN__);

			if (__CLASS_MIN__ == null) {
				salida.fallo("No se ha encontrado la __CLASS_MIN__");
			}
			if (!__CLASS_MIN__.isActivo()) {
				salida.fallo("La __CLASS_MIN__ se encuentra inactiva");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			salida.fallo("Se produjo un error inesperado al intentar obtener la __CLASS_MIN__");
		}
		return salida.build();
	}

	@Override
	public ResultadoProc<Page<__CLASS__>> findAllPaginated(PageRequest pageable) {
		ResultadoProc.Builder<Page<__CLASS__>> salida = new ResultadoProc.Builder<Page<__CLASS__>>();
		try {
			Page<__CLASS__> __CLASS_MIN__s = __CLASS_MIN__Repository.findAll(pageable);
			salida.exitoso(__CLASS_MIN__s);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			salida.fallo("Se produjo un error inesperado al intentar listar las __CLASS_MIN__s");
		}
		return salida.build();
	}

	@Override
	public ResultadoProc<Page<__CLASS__>> findAllPaginatedBySearch(PageRequest pageable, String search) {
		ResultadoProc.Builder<Page<__CLASS__>> salida = new ResultadoProc.Builder<Page<__CLASS__>>();
		try {
			Page<__CLASS__> __CLASS_MIN__s = __CLASS_MIN__Repository.findAllBySearch(search, pageable);
			salida.exitoso(__CLASS_MIN__s);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			salida.fallo("Se produjo un error inesperado al intentar listar las __CLASS_MIN__s");
		}
		return salida.build();
	}

	@Override
	public ResultadoProc<__CLASS__> save(__CLASS__ __CLASS_MIN__) {
		ResultadoProc.Builder<__CLASS__> salida = new ResultadoProc.Builder<__CLASS__>();
		try {
			__CLASS_MIN__Repository.save(__CLASS_MIN__);
			salida.exitoso(__CLASS_MIN__, "__CLASS__ registrada correctamente");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			salida.fallo("Se produjo un error inesperado al intentar registrar la __CLASS_MIN__");
		}
		return salida.build();
	}

	@Transactional
	@Override
	public ResultadoProc<__CLASS__> update(__CLASS__ __CLASS_MIN__) {
		ResultadoProc.Builder<__CLASS__> salida = new ResultadoProc.Builder<__CLASS__>();
		try {
			__CLASS_MIN__Repository.save(__CLASS_MIN__);
			salida.exitoso(__CLASS_MIN__, "__CLASS__ actualizada correctamente");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			salida.fallo("Se produjo un error inesperado al intentar actualizar la __CLASS_MIN__");
		}
		return salida.build();
	}

	// @Override
	// public ResultadoProc<__CLASS__> changeState(__TIPO_VARIABLE_PK__
	// __CLASS_MIN__Id) {
	// ResultadoProc.Builder<__CLASS__> salida = new
	// ResultadoProc.Builder<__CLASS__>();
	// try {
	// String mensaje = "";
	// __CLASS__ __CLASS_MIN__Original = this.findById(__CLASS_MIN__Id).getSalida();
	// if (__CLASS_MIN__Id > 0) {
	// if (__CLASS_MIN__Original == null) {
	// salida.fallo("No se econtró la __CLASS_MIN__");
	// return salida.build();
	// }
	// __CLASS_MIN__Original.setActivo(!__CLASS_MIN__Original.isActivo());
	// if (__CLASS_MIN__Original.isActivo()) {
	// mensaje = "La __CLASS_MIN__ " + __CLASS_MIN__Original.getDescripcion() + "
	// está activo";
	// } lase {
	// mensaje = "La __CLASS_MIN__ " + __CLASS_MIN__Original.getDescripcion() + "
	// está inactiva";
	// }
	// }
	// __CLASS_MIN__Repository.save(__CLASS_MIN__Original);
	// salida.exitoso(__CLASS_MIN__Original, mensaje);
	// } catch (Exception e) {
	// log.error(e.getMessage(), e);
	// salida.fallo("Se produjo un error inesperado al intentar cambiar la estado
	// dla __CLASS_MIN__");
	// }
	// return salida.build();
	// }

}
