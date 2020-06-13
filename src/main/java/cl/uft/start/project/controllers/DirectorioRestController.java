package cl.uft.start.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.uft.start.project.models.Directorio;
import cl.uft.start.project.services.IDirectorioService;

@RestController
@RequestMapping("/api/directorio")
public class DirectorioRestController {
	@Autowired
	IDirectorioService directorioService;

	@PostMapping
	public ResponseEntity<List<Directorio>> getDirectorios(@RequestParam String path,
			@RequestBody List<String> directoriosDesplegados) {
		return new ResponseEntity<List<Directorio>>(directorioService.getDirectorios(path, directoriosDesplegados),
				HttpStatus.OK);
	}

}
