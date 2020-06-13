package cl.uft.start.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.uft.start.project.models.Archivo;
import cl.uft.start.project.models.BaseArchivo;
import cl.uft.start.project.services.IActionService;
import cl.uft.start.project.utils.ResultadoProc;

@RestController
@RequestMapping("/api/action")
public class ActionRestController {

	@Autowired
	IActionService actionService;

	@PostMapping("/create/base")
	public ResponseEntity<ResultadoProc<Boolean>> createBase(@RequestBody BaseArchivo archivo) {
		return new ResponseEntity<ResultadoProc<Boolean>>(actionService.createBase(archivo), HttpStatus.OK);
	}

	@PostMapping("/create/entity")
	public ResponseEntity<ResultadoProc<Archivo>> createEntity(@RequestBody BaseArchivo archivo) {
		return new ResponseEntity<ResultadoProc<Archivo>>(actionService.createEntity(archivo), HttpStatus.OK);
	}

	@PostMapping("/preview/entity")
	public ResponseEntity<ResultadoProc<Archivo>> vistaPreviaEntity(@RequestBody BaseArchivo archivo) {
		return new ResponseEntity<ResultadoProc<Archivo>>(actionService.vistaPreviaEntity(archivo), HttpStatus.OK);
	}

	@PostMapping("/preview/controlador")
	public ResponseEntity<ResultadoProc<Archivo>> vistaPreviaController(@RequestBody BaseArchivo archivo) {
		return new ResponseEntity<ResultadoProc<Archivo>>(actionService.vistaPreviaController(archivo), HttpStatus.OK);
	}

	@PostMapping("/preview/servicio")
	public ResponseEntity<ResultadoProc<Archivo>> vistaPreviaServiceImpl(@RequestBody BaseArchivo archivo) {
		return new ResponseEntity<ResultadoProc<Archivo>>(actionService.vistaPreviaService(archivo), HttpStatus.OK);
	}

	@PostMapping("/preview/servicio-impl")
	public ResponseEntity<ResultadoProc<Archivo>> vistaPreviaService(@RequestBody BaseArchivo archivo) {
		return new ResponseEntity<ResultadoProc<Archivo>>(actionService.vistaPreviaServiceImpl(archivo), HttpStatus.OK);
	}

	@PostMapping("/preview/repositorio")
	public ResponseEntity<ResultadoProc<Archivo>> vistaPreviaRepository(@RequestBody BaseArchivo archivo) {
		return new ResponseEntity<ResultadoProc<Archivo>>(actionService.vistaPreviaRepository(archivo), HttpStatus.OK);
	}

	@GetMapping("/read/file")
	public ResponseEntity<Archivo> leerArchivo(@RequestParam String pathFile) {
		return new ResponseEntity<Archivo>(actionService.leerArchivo(pathFile), HttpStatus.OK);
	}
}
