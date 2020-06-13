package cl.uft.start.project.services;

import java.util.List;

import cl.uft.start.project.models.Archivo;
import cl.uft.start.project.models.BaseArchivo;
import cl.uft.start.project.utils.ResultadoProc;

public interface IActionService {

	/**
	 * Retorna una vista previa de la clase que va a crear
	 * 
	 * @param archivo {@link BaseArchivo} <br>
	 *                <ul>
	 *                <li>{@link String} nombreClase</li>
	 *                <li>{@link String} packageArchivo</li>
	 *                <li>{@link String} pathArchivo</li>
	 *                <li>{@link List} of {@link String} atributos</li>
	 *                </ul>
	 * @return ResultadoProc&lt;Archivo&gt;
	 */
	ResultadoProc<Archivo> vistaPreviaEntity(BaseArchivo archivo);

	/**
	 * Lee el contenido de un archivo
	 * 
	 * @param pathArchivo Path completo del archivo
	 * @return {@link Archivo}
	 */
	Archivo leerArchivo(String pathArchivo);

	ResultadoProc<Archivo> vistaPreviaController(BaseArchivo archivo);

	ResultadoProc<Archivo> vistaPreviaServiceImpl(BaseArchivo archivo);

	ResultadoProc<Archivo> vistaPreviaRepository(BaseArchivo archivo);

	ResultadoProc<Archivo> vistaPreviaService(BaseArchivo archivo);

	ResultadoProc<Boolean> createBase(BaseArchivo archivo);

	/**
	 * 
	 * @param archivo
	 * @return
	 */
	ResultadoProc<Archivo> createEntity(BaseArchivo archivo);

	ResultadoProc<Archivo> createController(BaseArchivo archivo);

	ResultadoProc<Archivo> createRepository(BaseArchivo archivo);

	ResultadoProc<Archivo> createIService(BaseArchivo archivo);

	ResultadoProc<Archivo> createServiceImpl(BaseArchivo archivo);

}
