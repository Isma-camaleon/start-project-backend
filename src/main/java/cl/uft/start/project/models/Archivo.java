package cl.uft.start.project.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Archivo implements Serializable {

	private static final long serialVersionUID = 1412493957307859550L;

	private String nombre;
	private String path;
	private String contenido;
	private boolean esPreview;

}
