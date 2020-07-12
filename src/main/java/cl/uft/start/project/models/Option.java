package cl.uft.start.project.models;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Option implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean useCommonsUftDependency;
    private boolean isMasculineEntity;

}