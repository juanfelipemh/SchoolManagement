package modelos;

import java.util.List;

public class ModeloEstudiante {
    
    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private int edad;
    private String grado;
    private List<ModeloMateria> materiasEstudiante;
    
    public ModeloEstudiante(){};

    public ModeloEstudiante(int id, String nombre, String apellido, String direccion, int edad, String grado, List<ModeloMateria> materiasEstudiante) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.edad = edad;
        this.grado = grado;
        this.materiasEstudiante = materiasEstudiante;
    }
    
    public ModeloEstudiante(int id, String nombre, String apellido, String direccion, int edad, String grado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.edad = edad;
        this.grado = grado;
    }

    public List<ModeloMateria> getMaterias() {
        return materiasEstudiante;
    }

    public void setMaterias(List<ModeloMateria> materiasEstudiante) {
        this.materiasEstudiante = materiasEstudiante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }  
}
