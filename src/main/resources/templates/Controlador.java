package __PACKAGE__;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.uft.commons.model.ResultadoProc;
import cl.uft.commons.model.SearchPagination;

import __PACKAGE_ENTITY__.__CLASS__;
import __PACKAGE_SERVICE__.I__CLASS__Service;

@RequestMapping("/api/__CLASS_MIN__")
@RestController
public class __CLASS__RestController {
	@Autowired
	I__CLASS__Service __CLASS_MIN__Service;

	@GetMapping("/{id}")
	public ResponseEntity<ResultadoProc<__CLASS__>> findById(@PathVariable("id") __TIPO_VARIABLE_PK__ __CLASS_MIN__Id) {
		ResultadoProc<__CLASS__> salida = __CLASS_MIN__Service.findById(__CLASS_MIN__Id);
		return new ResponseEntity<ResultadoProc<__CLASS__>>(salida, HttpStatus.OK);
	}

	@GetMapping("/id/active")
	public ResponseEntity<ResultadoProc<__CLASS__>> findByIdAndActiveTrue(@PathVariable __TIPO_VARIABLE_PK__ __CLASS_MIN__Id) {
		ResultadoProc<__CLASS__> salida = __CLASS_MIN__Service.findByIdAndActivoTrue(__CLASS_MIN__Id);
		return new ResponseEntity<ResultadoProc<__CLASS__>>(salida, HttpStatus.OK);
	}

	@GetMapping("/page-all")
	public ResponseEntity<ResultadoProc<Page<__CLASS__>>> findAllPaginated(
			@RequestBody SearchPagination<String> searchPagination) {
		PageRequest pageable = searchPagination.getPageRequest();
		ResultadoProc<Page<__CLASS__>> salida = __CLASS_MIN__Service.findAllPaginated(pageable);
		return new ResponseEntity<ResultadoProc<Page<__CLASS__>>>(salida, HttpStatus.OK);
	}


	@PostMapping
	public ResponseEntity<ResultadoProc<__CLASS__>> save(@RequestBody __CLASS__ __CLASS_MIN__) {
		ResultadoProc<__CLASS__> salida = __CLASS_MIN__Service.save(__CLASS_MIN__);
		return new ResponseEntity<ResultadoProc<__CLASS__>>(salida, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<ResultadoProc<__CLASS__>> update(@RequestBody __CLASS__ __CLASS_MIN__) {
		ResultadoProc<__CLASS__> salida = __CLASS_MIN__Service.update(__CLASS_MIN__);
		return new ResponseEntity<ResultadoProc<__CLASS__>>(salida, HttpStatus.OK);
	}

}
