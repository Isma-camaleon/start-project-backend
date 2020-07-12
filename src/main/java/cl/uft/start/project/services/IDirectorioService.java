package cl.uft.start.project.services;

import java.util.List;

import cl.uft.start.project.configuration.Configuracion;
import cl.uft.start.project.models.Directorio;

public interface IDirectorioService {

	/**
	 * Retorna una lista de directorios y subdirectorios (3 niveles) <br>
	 * Tambien se ignoran las carpetas y se aprueban las extensiones indicadas en la
	 * clase {@link Configuracion} <br>
	 * 
	 * @see cl.uft.start.project.configuration.Configuracion
	 * @param path                   Path del directorio base
	 * @param directoriosDesplegados {@link List}of {@link String} Listado de paths
	 *                               de los directorios que estan desplegados
	 * @return List&lt;Directorio&gt;
	 */
	List<Directorio> getDirectorios(String path, List<String> directoriosDesplegados);

}
