package cl.uft.start.project.models;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author ismaelcuevas
 *
 */
@Data
@NoArgsConstructor
public class BaseArchivo implements Serializable {

	private static final long serialVersionUID = 3631374146969236458L;

	private String nombreClase;
	private String pathArchivo;
	private String packageArchivo;
	private String pathDirectorioBase;
	private String packageBase;
	private String contenido;
	private Option option;

	private List<Atributo> atributos;

	public String getNombreClaseLower() {
		return this.nombreClase.toLowerCase();
	}

	/**
	 * 
	 * @param nombreClase {@link String} Nombre de la entidad
	 * @param pathArchivo {@link String} Path de la carpeta base en donde estan los
	 *                    controlladores, entidaddes, servicio, etc.
	 *                    (Users/_user_/proyecto/cl/uft/demo)
	 * @param packageBase {@link String} Package base del proyecto (cl.uft.demo)
	 * @param contenido
	 * @param atributos   {@link List} of {@link Atributo} Lista de atributos para
	 *                    la creación de la clase
	 */
	public BaseArchivo(String nombreClase, String pathArchivo, String packageArchivo, String contenido,
			List<Atributo> atributos) {
		super();
		this.nombreClase = nombreClase;
		this.pathArchivo = pathArchivo;
		this.packageArchivo = packageArchivo;
		this.contenido = contenido;
		this.atributos = atributos;
	}

}
