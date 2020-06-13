package cl.uft.start.project.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atributo implements Serializable {

	private static final long serialVersionUID = 7098721309560816057L;

	private String tipo;
	private String variable;
	private boolean primaryKey;

}
