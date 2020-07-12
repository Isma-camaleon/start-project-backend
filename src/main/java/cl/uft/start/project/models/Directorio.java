package cl.uft.start.project.models;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Directorio implements Serializable {

	private static final long serialVersionUID = 846754889384586208L;
	private String nombre;
	private String path;
	private String packageBase;
	private boolean desplegado;
	private boolean esFile;

	private List<Directorio> directorios;

	private boolean pathValid;

	/**
	 * Verifica si el path del directorio base es el package principal de la
	 * aplicación<br>
	 * <br>
	 * Debe terner por lo menos los packages:
	 * <ul>
	 * <li>repositories</li>
	 * <li>entities</li>
	 * <li>controllers</li>
	 * <li>services</li>
	 * </ul>
	 * 
	 * @param directoriosBase Listado de {@link Directorio} que estan dentro del
	 *                        package base del pryecto
	 */
	public void setPathValid(List<Directorio> directoriosBase) {
		if (this.path == null || this.path.isEmpty()) {
			this.pathValid = false;
		}

		if (this.path.indexOf("src/main/java/") == -1) {
			this.pathValid = false;
		}

		if (directoriosBase != null && directoriosBase.size() > 0) {
			boolean isReposiroty = false;
			boolean isEntity = false;
			boolean isController = false;
			boolean isService = false;

			for (int index = 0; index < directoriosBase.size(); index++) {
				Directorio directorio = directoriosBase.get(index);
				if (this.esFile) {
					continue;
				}
				if (directorio.path.indexOf("repositories") != -1 || directorio.path.indexOf("repository") != -1) {
					isReposiroty = true;
				}
				if (directorio.path.indexOf("entities") != -1 || directorio.path.indexOf("entity") != -1) {
					isEntity = true;
				}
				if (directorio.path.indexOf("controllers") != -1 || directorio.path.indexOf("controller") != -1) {
					isController = true;
				}
				if (directorio.path.indexOf("services") != -1 || directorio.path.indexOf("service") != -1) {
					isService = true;
				}
			}

			if (isReposiroty && isEntity && isController && isService) {
				this.pathValid = true;
			} else {
				this.pathValid = false;
			}
		} else {
			this.pathValid = false;
		}

	}

	public void setPackageBase() {
		String path = this.path;
		path = path.substring(path.indexOf("src/main/java/"), path.length());
		path = path.replace("src/main/java/", "");
		if (path.indexOf("/repositories") != -1) {
			path = path.substring(0, path.indexOf("/repositories"));
		}
		if (path.indexOf("/controllers") != -1) {
			path = path.substring(0, path.indexOf("/controllers"));
		}
		if (path.indexOf("/services") != -1) {
			path = path.substring(0, path.indexOf("/services"));
		}
		if (path.indexOf("/entities") != -1) {
			path = path.substring(0, path.indexOf("/entities"));
		}
		if (path.indexOf("/utils") != -1) {
			path = path.substring(0, path.indexOf("/utils"));
		}
		if (path.indexOf("/repository") != -1) {
			path = path.substring(0, path.indexOf("/repository"));
		}
		if (path.indexOf("/controller") != -1) {
			path = path.substring(0, path.indexOf("/controller"));
		}
		if (path.indexOf("/service") != -1) {
			path = path.substring(0, path.indexOf("/service"));
		}
		if (path.indexOf("/entity") != -1) {
			path = path.substring(0, path.indexOf("/entity"));
		}
		this.packageBase = path.replaceAll("/", ".");
	}

}
