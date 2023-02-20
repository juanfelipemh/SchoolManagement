package modelos;

import java.util.List;

public class ModeloMateria {
    private int id;
    private String descripcion;
    private List<ModeloEstudiante> estudiantes;
    
    public ModeloMateria(){};

    public ModeloMateria(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    
    public ModeloMateria(int id, String descripcion, List<ModeloEstudiante> estudiantes) {
        this.id = id;
        this.descripcion = descripcion;
        this.estudiantes = estudiantes;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ModeloEstudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<ModeloEstudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
