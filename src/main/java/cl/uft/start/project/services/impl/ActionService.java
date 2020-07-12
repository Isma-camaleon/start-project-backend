package cl.uft.start.project.services.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.extern.apachecommons.CommonsLog;

import cl.uft.start.project.models.Archivo;
import cl.uft.start.project.models.Atributo;
import cl.uft.start.project.models.BaseArchivo;
import cl.uft.start.project.services.IActionService;
import cl.uft.start.project.utils.ResultadoProc;

@CommonsLog
@Service
public class ActionService implements IActionService {

	@Value(value = "classpath:templates/Entidad.java")
	private Resource templateEntity;

	@Value(value = "classpath:templates/Controlador.java")
	private Resource templateController;

	@Value(value = "classpath:templates/ServicioM.java")
	private Resource templateServiceImplM;

	@Value(value = "classpath:templates/ServicioF.java")
	private Resource templateServiceImplF;

	@Value(value = "classpath:templates/IServicioM.java")
	private Resource templateServiceM;

	@Value(value = "classpath:templates/IServicioF.java")
	private Resource templateServiceF;

	@Value(value = "classpath:templates/Repositorio.java")
	private Resource templateRepositorio;

	@Override
	public ResultadoProc<Boolean> createBase(BaseArchivo archivo) {
		ResultadoProc.Builder<Boolean> salida = new ResultadoProc.Builder<Boolean>();
		String mensaje = "<ul>";
		boolean isError = false;
		if (this.createEntity(archivo).isError()) {
			isError = true;
			mensaje += "<li class=\"text-left\">No se pudo crear la entidad</li>\n";
		}
		if (this.createController(archivo).isError()) {
			isError = true;
			mensaje += "<li class=\"text-left\">No se pudo crear el controlador</li>\n";
		}
		if (this.createRepository(archivo).isError()) {
			isError = true;
			mensaje += "<li class=\"text-left\">No se pudo crear el repositorio</li>\n";
		}
		if (this.createIService(archivo).isError()) {
			isError = true;
			mensaje += "<li class=\"text-left\">No se pudo crear la interfaz del servicio</li>\n";
		}
		if (this.createServiceImpl(archivo).isError()) {
			isError = true;
			mensaje += "<li class=\"text-left\">No se pudo crear el servicio</li>\n";
		}
		mensaje += "</ul>";
		if (!isError) {
			return salida.exitoso(true, "Clases creadas correctamente");
		} else {
			return salida.fallo(mensaje);
		}
	}

	/**
	 * Crea la clase en el package de las entidades
	 * 
	 * @param archivo {@link BaseArchivo}
	 * @return ResultadoProc&lt;Archivo&gt;
	 */
	@SuppressWarnings("unused")
	@Override
	public ResultadoProc<Archivo> createEntity(BaseArchivo archivo) {

		ResultadoProc.Builder<Archivo> salida = new ResultadoProc.Builder<Archivo>();
		String codigo = "";
		InputStreamReader template = null;
		try {
			template = new InputStreamReader(templateEntity.getInputStream());
			if (template == null) {
				return salida.fallo("No se pudo leer leer el template");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return salida.fallo("Error al intentar leer el template");
		}
		archivo.setPackageArchivo(archivo.getPackageBase() + ".entities");
		archivo.setPathArchivo(archivo.getPathDirectorioBase() + "/entities/" + archivo.getNombreClase() + ".java");
		return writeCode(archivo, salida, codigo, template);
	}

	@SuppressWarnings("unused")
	@Override
	public ResultadoProc<Archivo> createController(BaseArchivo archivo) {

		ResultadoProc.Builder<Archivo> salida = new ResultadoProc.Builder<Archivo>();
		String codigo = "";
		InputStreamReader template = null;
		try {
			template = new InputStreamReader(templateController.getInputStream());
			if (template == null) {
				return salida.fallo("No se pudo leer el template");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return salida.fallo("Error al intentar leer el template");
		}
		archivo.setPackageArchivo(archivo.getPackageBase() + ".controllers");
		archivo.setPathArchivo(
				archivo.getPathDirectorioBase() + "/controllers/" + archivo.getNombreClase() + "RestController.java");
		return writeCode(archivo, salida, codigo, template);
	}

	@SuppressWarnings("unused")
	@Override
	public ResultadoProc<Archivo> createRepository(BaseArchivo archivo) {

		ResultadoProc.Builder<Archivo> salida = new ResultadoProc.Builder<Archivo>();
		String codigo = "";
		InputStreamReader template = null;
		try {
			template = new InputStreamReader(templateRepositorio.getInputStream());
			if (template == null) {
				return salida.fallo("No se pudo leer leer el template");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return salida.fallo("Error al intentar leer el template");
		}
		archivo.setPackageArchivo(archivo.getPackageBase() + ".repositories");
		archivo.setPathArchivo(
				archivo.getPathDirectorioBase() + "/repositories/" + archivo.getNombreClase() + "Repository.java");
		return writeCode(archivo, salida, codigo, template);
	}

	@SuppressWarnings("unused")
	@Override
	public ResultadoProc<Archivo> createIService(BaseArchivo archivo) {

		ResultadoProc.Builder<Archivo> salida = new ResultadoProc.Builder<Archivo>();
		String codigo = "";
		InputStreamReader template = null;
		try {
			template = new InputStreamReader(templateServiceM.getInputStream());
			if (template == null) {
				return salida.fallo("No se pudo leer leer el template");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return salida.fallo("Error al intentar leer el template");
		}
		archivo.setPackageArchivo(archivo.getPackageBase() + ".services");
		archivo.setPathArchivo(
				archivo.getPathDirectorioBase() + "/services/I" + archivo.getNombreClase() + "Service.java");
		return writeCode(archivo, salida, codigo, template);
	}

	@SuppressWarnings("unused")
	@Override
	public ResultadoProc<Archivo> createServiceImpl(BaseArchivo archivo) {

		ResultadoProc.Builder<Archivo> salida = new ResultadoProc.Builder<Archivo>();
		String codigo = "";
		InputStreamReader template = null;
		try {
			template = new InputStreamReader(templateServiceImplM.getInputStream());
			if (template == null) {
				return salida.fallo("No se pudo leer leer el template");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return salida.fallo("Error al intentar leer el template");
		}
		archivo.setPackageArchivo(archivo.getPackageBase() + ".services.impl");
		archivo.setPathArchivo(
				archivo.getPathDirectorioBase() + "/services/impl/" + archivo.getNombreClase() + "Service.java");
		return writeCode(archivo, salida, codigo, template);
	}

	private ResultadoProc<Archivo> writeCode(BaseArchivo archivo, ResultadoProc.Builder<Archivo> salida, String codigo,
			InputStreamReader template) {
		if (archivo.getNombreClase() == null || archivo.getNombreClase().isEmpty()) {
			return salida.fallo("No se encuentra el nombre de la clase");
		}
		try {
			String line = "";
			StringBuilder out = new StringBuilder();
			BufferedReader reader3 = new BufferedReader(template);
			line = "";
			out = new StringBuilder();
			while ((line = reader3.readLine()) != null) {
				out.append(line + "\n");
			}
			codigo = remplazarContenido(archivo, out);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return salida.fallo("Se produjo un error al intentar leer el template");
		}

		try {
			File file = new File(archivo.getPathArchivo());

			if (file.exists()) {
				return salida.fallo("El archivo ya existe");
			} else {
				file.createNewFile();
				FileWriter fileWriter = new FileWriter(file);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(codigo);
				bufferedWriter.close();
			}
			return salida.exitoso(this.leerArchivo(archivo.getPathArchivo()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return salida.fallo("Se produjo un error al intentar el archivo");
		}
	}

	@Override
	public ResultadoProc<Archivo> vistaPreviaEntity(BaseArchivo archivo) {
		ResultadoProc.Builder<Archivo> salida = new ResultadoProc.Builder<Archivo>();
		try {
			if (archivo.getNombreClase() == null || archivo.getNombreClase().isEmpty()) {
				return salida.exitoso(new Archivo("", "", "", true));
			}

			String codigo = "";
			String line = "";
			StringBuilder out = new StringBuilder();

			long generatedVersionUID = generateVersionUID();

			try {
				BufferedReader reader3 = new BufferedReader(new InputStreamReader(templateEntity.getInputStream()));
				line = "";
				out = new StringBuilder();
				while ((line = reader3.readLine()) != null) {
					out.append(line + "\n");
				}
				codigo = out.toString().replace("__CLASS__", archivo.getNombreClase().trim());
				codigo = codigo.toString().replace("__CLASS_MIN__", archivo.getNombreClaseLower().trim());
				codigo = codigo.toString().replace("__PACKAGE__", archivo.getPackageArchivo().trim());
				codigo = codigo.toString().replace("__VERSION_UID_", generatedVersionUID + "L");
				codigo = codigo.toString().replace("__ATRIBUTOS__", this.atributos(archivo));

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return salida.fallo("Se produjo un error al intentar leer el template de la entidad");
			}
			Archivo entityPreview = new Archivo();
			entityPreview.setNombre(archivo.getNombreClase());
			entityPreview.setPath(archivo.getPathArchivo());
			entityPreview.setContenido(codigo);
			entityPreview.setEsPreview(true);
			salida.exitoso(entityPreview);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return salida.fallo("Se produjo un error al intentar visualizar la entidad");
		}
		return salida.build();
	}

	@Override
	public ResultadoProc<Archivo> vistaPreviaServiceImpl(BaseArchivo archivo) {
		ResultadoProc.Builder<Archivo> salida = new ResultadoProc.Builder<Archivo>();
		try {
			if (archivo.getNombreClase() == null || archivo.getNombreClase().isEmpty()) {
				return salida.exitoso(new Archivo("", "", "", true));
			}
			String codigo = "";
			String line = "";
			StringBuilder out = new StringBuilder();

			try {
				BufferedReader reader3 = new BufferedReader(
						new InputStreamReader(templateServiceImplM.getInputStream()));
				line = "";
				out = new StringBuilder();
				while ((line = reader3.readLine()) != null) {
					out.append(line + "\n");
				}
				codigo = remplazarContenido(archivo, out);

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return salida.fallo("Se produjo un error al intentar leer el template del servicio");
			}
			Archivo entityPreview = new Archivo();
			entityPreview.setNombre(archivo.getNombreClase());
			entityPreview.setPath(archivo.getPathArchivo());
			entityPreview.setContenido(codigo);
			entityPreview.setEsPreview(true);
			salida.exitoso(entityPreview);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return salida.fallo("Se produjo un error al intentar visualizar el servicio");
		}
		return salida.build();
	}

	@Override
	public ResultadoProc<Archivo> vistaPreviaService(BaseArchivo archivo) {
		ResultadoProc.Builder<Archivo> salida = new ResultadoProc.Builder<Archivo>();
		try {
			if (archivo.getNombreClase() == null || archivo.getNombreClase().isEmpty()) {
				return salida.exitoso(new Archivo("", "", "", true));
			}
			String codigo = "";
			String line = "";
			StringBuilder out = new StringBuilder();

			try {
				BufferedReader reader3 = new BufferedReader(new InputStreamReader(templateServiceM.getInputStream()));
				line = "";
				out = new StringBuilder();
				while ((line = reader3.readLine()) != null) {
					out.append(line + "\n");
				}
				codigo = remplazarContenido(archivo, out);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return salida.fallo("Se produjo un error al intentar leer el template del servicio");
			}
			Archivo entityPreview = new Archivo();
			entityPreview.setNombre(archivo.getNombreClase());
			entityPreview.setPath(archivo.getPathArchivo());
			entityPreview.setContenido(codigo);
			entityPreview.setEsPreview(true);
			salida.exitoso(entityPreview);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return salida.fallo("Se produjo un error al intentar visualizar el servicio");
		}
		return salida.build();
	}

	@Override
	public ResultadoProc<Archivo> vistaPreviaRepository(BaseArchivo archivo) {
		ResultadoProc.Builder<Archivo> salida = new ResultadoProc.Builder<Archivo>();
		try {
			if (archivo.getNombreClase() == null || archivo.getNombreClase().isEmpty()) {
				return salida.exitoso(new Archivo("", "", "", true));
			}
			String codigo = "";
			String line = "";
			StringBuilder out = new StringBuilder();

			try {
				BufferedReader reader3 = new BufferedReader(
						new InputStreamReader(templateRepositorio.getInputStream()));
				line = "";
				out = new StringBuilder();
				while ((line = reader3.readLine()) != null) {
					out.append(line + "\n");
				}
				codigo = remplazarContenido(archivo, out);

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return salida.fallo("Se produjo un error al intentar leer el template del repositorio");
			}
			Archivo entityPreview = new Archivo();
			entityPreview.setNombre(archivo.getNombreClase());
			entityPreview.setPath(archivo.getPathArchivo());
			entityPreview.setContenido(codigo);
			entityPreview.setEsPreview(true);
			salida.exitoso(entityPreview);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return salida.fallo("Se produjo un error al intentar visualizar el repositorio");
		}
		return salida.build();
	}

	@Override
	public ResultadoProc<Archivo> vistaPreviaController(BaseArchivo archivo) {
		ResultadoProc.Builder<Archivo> salida = new ResultadoProc.Builder<Archivo>();
		try {
			if (archivo.getNombreClase() == null || archivo.getNombreClase().isEmpty()) {
				return salida.exitoso(new Archivo("", "", "", true));
			}
			String codigo = "";
			String line = "";
			StringBuilder out = new StringBuilder();

			try {
				BufferedReader reader3 = new BufferedReader(new InputStreamReader(templateController.getInputStream()));
				line = "";
				out = new StringBuilder();
				while ((line = reader3.readLine()) != null) {
					out.append(line + "\n");
				}
				codigo = remplazarContenido(archivo, out);

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return salida.fallo("Se produjo un error al intentar leer el template del controlador");
			}
			Archivo entityPreview = new Archivo();
			entityPreview.setNombre(archivo.getNombreClase());
			entityPreview.setPath(archivo.getPathArchivo());
			entityPreview.setContenido(codigo);
			entityPreview.setEsPreview(true);
			salida.exitoso(entityPreview);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return salida.fallo("Se produjo un error al intentar visualizar el controlador");
		}
		return salida.build();
	}

	@Override
	public Archivo leerArchivo(String pathArchivo) {
		Archivo salida = new Archivo("", pathArchivo, "", false);
		try {

			File file = new File(pathArchivo);
			if (file.exists()) {
				salida.setNombre(file.getName());
			}

			FileReader fr = new FileReader(pathArchivo);
			BufferedReader br = new BufferedReader(fr);

			String linea;
			String codigo = "";
			while ((linea = br.readLine()) != null) {
				codigo += linea + "\n";
			}
			fr.close();
			salida.setContenido(codigo);
			return salida;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return salida;
	}

	/**
	 * Remplaza las palabras en los templates
	 * 
	 * @param archivo
	 * @param out
	 * @return
	 */
	private String remplazarContenido(BaseArchivo archivo, StringBuilder out) {

		String codigo;
		codigo = out.toString().replace("__CLASS__", archivo.getNombreClase().trim());
		codigo = codigo.toString().replace("__CLASS_MIN__", archivo.getNombreClaseLower().trim());
		codigo = codigo.toString().replace("__VERSION_UID_", this.generateVersionUID() + "L");
		codigo = codigo.toString().replace("__PACKAGE__", archivo.getPackageArchivo().trim());
		codigo = codigo.toString().replace("__PACKAGE_ENTITY__", archivo.getPackageBase() + ".entities".trim());
		codigo = codigo.toString().replace("__PACKAGE_SERVICE__", archivo.getPackageBase() + ".services".trim());
		codigo = codigo.toString().replace("__PACKAGE_REPOSITORY__", archivo.getPackageBase() + ".repositories".trim());
		codigo = codigo.toString().replace("__ATRIBUTOS__", this.atributos(archivo));
		codigo = codigo.toString().replace("__TIPO_VARIABLE_OBJ__",
				this.tipoVariableForRepository(archivo.getAtributos()));
		codigo = codigo.toString().replace("__TIPO_VARIABLE_PK__", this.tipoVariablePK(archivo.getAtributos()));

		return codigo;
	}

	/**
	 * Escribe el código en el archivo
	 * 
	 * @param file   {@link File}
	 * @param codigo {@link String}
	 * @throws IOException
	 */
	// private void writerCode(File file, String codigo) throws IOException {
	// FileWriter fileWriter = new FileWriter(file);
	// BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	// bufferedWriter.write(codigo);
	// bufferedWriter.close();
	// }

	/**
	 * Genera el serialVersionUID de la entidad
	 * 
	 * @return serialVersionUID
	 */
	private long generateVersionUID() {
		long leftLimit = 1234567891234567891L;
		long rightLimit = 9123456789123456789L;
		long generatedVersionUID = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
		return generatedVersionUID;
	}

	private String tipoVariableForRepository(List<Atributo> atributos) {

		for (Atributo atributo : atributos) {
			if (atributo.isPrimaryKey()) {
				switch (atributo.getTipo().toLowerCase()) {
					case "int":
						return "Integer";
					case "integer":
						return "Integer";
					case "double":
						return "Double";
					case "float":
						return "Float";
					case "long":
						return "Long";
					case "string":
						return "String";
				}
			}
		}
		return "_SIN_PK_";
	}

	private String tipoVariablePK(List<Atributo> atributos) {

		for (Atributo atributo : atributos) {
			if (atributo.isPrimaryKey()) {
				return atributo.getTipo();
			}
		}
		return "_SIN_PK_";
	}

	private String atributos(BaseArchivo archivo) {
		String salida = "";
		String constructor = "";
		List<Atributo> atributos = archivo.getAtributos();
		for (Atributo atributo : atributos) {
			if (atributo.isPrimaryKey()) {
				salida += "\t@Id\n";
				if (!atributo.getTipo().toLowerCase().equals("string")) {
					salida += "\t@GeneratedValue(strategy = GenerationType.IDENTITY)\n";
				}
				constructor = "\n\tpublic " + archivo.getNombreClase() + "(" + atributo.getTipo() + " "
						+ atributo.getVariable() + "){ \n";
				constructor += "\t\tthis." + atributo.getVariable() + " = " + atributo.getVariable() + ";\n";
				constructor += "\t}\n";
			}

			if (atributo.getTipo().contains("List<")
					&& !(atributo.getTipo().contains("List<String>") || atributo.getTipo().contains("List<Integer>"))) {
				salida += "\n\t@ManyToMany(mappedBy = \"" + archivo.getNombreClaseLower()
						+ "\", fetch = FetchType.EAGER)\n";
				salida += "\t@JsonIgnoreProperties(value = { \"" + archivo.getNombreClaseLower() + "\" })\n";
			}
			if (!atributo.getTipo().contains("List<") && !this.esVariablePropiaDeJava(atributo.getTipo())) {
				salida += "\n\t@ManyToOne\n";
				salida += "\t@JoinColumn(name = \"" + atributo.getTipo().toLowerCase() + "_id\")\n";
			}
			salida += "\tprivate " + atributo.getTipo() + " " + atributo.getVariable() + ";\n";

		}
		salida += constructor;
		return salida;
	}

	/**
	 * Verifica si el tipo de variable es propia java
	 * 
	 * @param tipoVariable
	 * @return <code>TRUE</code> si es propia de java <br>
	 *         <code>FALSE</code> si <b>no</b> es propia de java
	 */
	private boolean esVariablePropiaDeJava(String tipoVariable) {
		switch (tipoVariable.toLowerCase()) {
			case "string":
				return true;
			case "integer":
				return true;
			case "int":
				return true;
			case "long":
				return true;
			case "double":
				return true;
			case "boolean":
				return true;
			case "float":
				return true;
			case "char":
				return true;
			case "short":
				return true;
			case "byte":
				return true;
			case "date":
				return true;
			default:
				return false;
		}
	}

}
