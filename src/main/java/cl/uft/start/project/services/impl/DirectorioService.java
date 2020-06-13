package cl.uft.start.project.services.impl;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.uft.start.project.configuration.Configuracion;
import cl.uft.start.project.models.Directorio;
import cl.uft.start.project.services.IDirectorioService;
import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class DirectorioService implements IDirectorioService {

	@Value("${path-inicial}")
	private String carpetaActual;

	@Override
	public List<Directorio> getDirectorios(String path, List<String> directoriosDesplegados) {
		List<Directorio> directorios = new ArrayList<Directorio>();

		try {
			File carpeta = null;
			if (path.isEmpty()) {
				carpeta = new File(carpetaActual);

			} else {
				carpeta = new File(path);
				if (!carpeta.exists()) {
					carpeta = new File(carpetaActual);
				}
			}

			if (!carpeta.exists()) {
				carpeta = new File(System.getProperty("user.dir"));
			}

			FileFilter filtro = new FileFilter() {
				@Override
				public boolean accept(File arch) {
					return !arch.isHidden();
				}
			};
			Directorio dirAnterior = new Directorio();
			dirAnterior.setNombre("..");
			dirAnterior.setPath(carpeta.getParent());

			Directorio dirActual = new Directorio();
			dirActual.setNombre(carpeta.getName());
			dirActual.setPath(carpeta.getAbsolutePath());

			File[] archivos = carpeta.listFiles(filtro);
			if (archivos != null && archivos.length > 0) {

				for (int i = 0; i < archivos.length; i++) {
					File archivo = archivos[i];

					if (archivo.isFile() && !this.isPermitted(archivo.getName())) {
						continue;
					}
					if (this.isIgnored(archivo.getName())) {
						continue;
					}
					String pathDir = archivo.getAbsolutePath();

					Directorio directorio = new Directorio();

					if (archivo.isDirectory()) {
						directorio.setEsFile(false);
					}
					if (archivo.isFile()) {
						directorio.setEsFile(true);
					}

					directorio.setNombre(archivo.getName());
					directorio.setPath(pathDir);
					directorio.setDesplegado(
							this.isDirectorioDesplegado(archivo.getAbsolutePath(), directoriosDesplegados));
					directorio.setDirectorios(
							this.obtenerSubDirectorios(archivo.getAbsolutePath(), filtro, directoriosDesplegados));
					directorios.add(directorio);

				}

			}

			try {
				dirActual.setPathValid(directorios);
				dirActual.setPackageBase();
			} catch (Exception e) {

			}

			directorios.add(0, dirAnterior);
			directorios.add(1, dirActual);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return directorios;
	}

	private List<Directorio> obtenerSubDirectorios(String pathCarpeta, FileFilter isDirectory,
			List<String> directoriosDesplegados) {
		List<Directorio> subDirectorios = new ArrayList<>();

		try {

			File subCarpeta = new File(pathCarpeta);
			File[] subCarpetas = subCarpeta.listFiles();
			if (subCarpetas != null && subCarpetas.length > 0) {
				for (int i = 0; i < subCarpetas.length; i++) {
					File fileSub = subCarpetas[i];

					if (fileSub.isFile() && !this.isPermitted(fileSub.getName())) {
						continue;
					}

					if (this.isIgnored(fileSub.getName())) {
						continue;
					}

					Directorio subDirectorio = new Directorio();

					subDirectorio.setNombre(fileSub.getName());
					subDirectorio.setPath(fileSub.getAbsolutePath());
					subDirectorio
							.setDesplegado(this.isDirectorioDesplegado(fileSub.getParent(), directoriosDesplegados));
					if (subDirectorio.isDesplegado()) {

						subDirectorio.setDirectorios(this.obtenerSubDirectorios(fileSub.getAbsolutePath(), isDirectory,
								directoriosDesplegados));

					}

					if (fileSub.isDirectory()) {
						subDirectorio.setEsFile(false);
					}
					if (fileSub.isFile()) {
						subDirectorio.setEsFile(true);
					}

					subDirectorios.add(subDirectorio);
				}
			}

			return subDirectorios;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ArrayList<Directorio>();
		}

	}

	private String getExtensionByStringHandling(String filename) {
		try {
			return Optional.ofNullable(filename).filter(f -> f.contains("."))
					.map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
		} catch (Exception e) {
			return "";
		}
	}

	private boolean isDirectorioDesplegado(String pathDirectorio, List<String> directoriosDesplegados) {
		for (String directorio : directoriosDesplegados) {
			if (directorio.equals(pathDirectorio)) {
				return true;
			}
		}
		return false;
	}

	private boolean isIgnored(String nombre) {
		for (int i = 0; i < Configuracion.DIR_IGNORADOS.size(); i++) {
			if (nombre.equals(Configuracion.DIR_IGNORADOS.get(i))) {
				return true;
			}
		}
		return false;
	}

	private boolean isPermitted(String nombre) {
		for (int i = 0; i < Configuracion.FILE_ACEPTADOS.size(); i++) {
			if (this.getExtensionByStringHandling(nombre).equals(Configuracion.FILE_ACEPTADOS.get(i))) {
				return true;
			}
		}
		return false;
	}
}
