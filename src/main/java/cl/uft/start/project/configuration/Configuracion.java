package cl.uft.start.project.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que contiene los directorios que serán ignorados al momento de leer el
 * contenido de las carpetas "<code><b>DIR_IGNORADOS</b></code>" <br>
 * Tambien contiene las extensiones de los archivos que serán aceptados
 * "<code><b>FILE_ACEPTADOS</b></code>"
 * 
 * @author Ismael Cuevas
 * @since 20/05/2020
 * 
 */
public class Configuracion {

	private static final String[] DIR_DENEGADOS_LIST = new String[] { "target", "node_modules", "dist", ".vscode",
			".git", "e2e", ".settings", ".mvn", ".metadata" };

	private static final String[] FILE_ACEPTADOS_LIST = new String[] { "java", "ts", "js", "json", "css", "html",
			"sass", "scss", "md", "sql" };

	/**
	 * Lista de los directorios que serán ignorados
	 */
	public static final List<String> DIR_IGNORADOS = new ArrayList<String>(Arrays.asList(DIR_DENEGADOS_LIST));

	/**
	 * Lista de extensiones que son aceptadas para mostrar en la lista de
	 * directorios
	 */
	public static final List<String> FILE_ACEPTADOS = new ArrayList<String>(Arrays.asList(FILE_ACEPTADOS_LIST));
}
