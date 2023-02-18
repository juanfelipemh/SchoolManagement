package controlador;

import interfaces.CRUD;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import modelos.ModeloEstudiante;
import modelos.ModeloMateria;

public class Estudiante implements CRUD {

    static public List<ModeloEstudiante> estudiantes = new ArrayList<>();

    public List<ModeloEstudiante> getEstudiantes() {
        return estudiantes;
    }

    @Override
    public void agregar() {
        try {
            int idNuevo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id: "));
            boolean existe = false;
            for (ModeloEstudiante estudiante : estudiantes) {
                if (estudiante.getId() == idNuevo) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                JOptionPane.showMessageDialog(null, "Estudiante ya existe");
            } else {
                String nombre = JOptionPane.showInputDialog("Ingrese su nombre: ");
                String apellido = JOptionPane.showInputDialog("Ingrese su apellido: ");
                String direccion = JOptionPane.showInputDialog("Ingrese su dirección: ");
                int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad: "));
                String grado = JOptionPane.showInputDialog("Ingrese el grado que cursa de 1 a 11: ");
                ModeloEstudiante estudiante = new ModeloEstudiante(idNuevo, nombre, apellido, direccion, edad, grado);
                List<ModeloMateria> materiasEstudiante = new ArrayList<>(); // Se inicializa la lista de materias
                int opcion = 0;
                do {
                    opcion = Integer.parseInt(JOptionPane.showInputDialog("""
                                                 1. Agregar materias
                                                 2. Finalizar y crear estudiante
                                                 """));
                    switch (opcion) {
                        case 1:
                            int idMateria = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código de la materia a asignar:"));
                            boolean agregar = false;
                            Materia materia = new Materia();
                            for (ModeloMateria i : materia.getMaterias()) {
                                if (i.getId() == idMateria) {
                                    agregar = true;
                                    materiasEstudiante.add(i); // Se agrega el objeto ModeloMateria a la lista
                                    JOptionPane.showMessageDialog(null, "Materia agregada");
                                    break;
                                }
                            }
                            if (!agregar) {
                                JOptionPane.showMessageDialog(null, "Codigo de materia incorrecto");
                            }
                            break;
                        case 2:
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opcion incorrecta");
                    }
                } while (opcion != 2);
                estudiante.setMaterias(materiasEstudiante); // Se asigna la lista de materias al estudiante
                estudiantes.add(estudiante);
                StringBuilder mensaje = new StringBuilder("El estudiante " + estudiante.getNombre() + " " + estudiante.getApellido() + " ha sido creado exitosamente con el ID " + estudiante.getId());
                JOptionPane.showMessageDialog(null, mensaje);
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void eliminar() {
        try {
            int idEstudiante = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID estudiante: "));
            boolean existe = false;
            Iterator<ModeloEstudiante> iterEstudiante = estudiantes.iterator();
            while (iterEstudiante.hasNext()) {
                ModeloEstudiante estudiante = iterEstudiante.next();
                if (estudiante.getId() == idEstudiante) {
                    existe = true;
                    int respConfirmacion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el estudiante?", "Alerta!", JOptionPane.YES_NO_OPTION);
                    if (respConfirmacion == JOptionPane.YES_OPTION) {
                        iterEstudiante.remove();
                        JOptionPane.showMessageDialog(null, "Estudiante eliminado");
                        break;
                    } else {
                        break;
                    }
                }
            }
            if (!existe) {
                JOptionPane.showMessageDialog(null, "Codigo de estudiante errado");
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("Error" + e);
        }
    }

    @Override
    public void actualizar() {
        try {
            int idEstudiante = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID estudiante: "));
            boolean existe = false;
            for (ModeloEstudiante estudiante : estudiantes) {
                if (estudiante.getId() == idEstudiante) {
                    existe = true;
                    String nombre = JOptionPane.showInputDialog("Ingrese su nombre: ");
                    String apellido = JOptionPane.showInputDialog("Ingrese su apellido: ");
                    String direccion = JOptionPane.showInputDialog("Ingrese su dirección: ");
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad: "));
                    String grado = JOptionPane.showInputDialog("Ingrese el grado que cursa de 1 a 11: ");
                    estudiante.setNombre(nombre);
                    estudiante.setApellido(apellido);
                    estudiante.setDireccion(direccion);
                    estudiante.setEdad(edad);
                    estudiante.setGrado(grado);
                    List<ModeloMateria> materiasEstudiante = new ArrayList<>(); // Se inicializa la lista de materias
                    materiasEstudiante.clear();
                    int opcion = 0;
                    do {
                        opcion = Integer.parseInt(JOptionPane.showInputDialog("""
                                                 1. Modificar materias                         
                                                 2. Finalizar y actualizar estudiante
                                                 """));
                        switch (opcion) {
                            case 1:
                                int idMateria = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código de la materia a asignar:"));
                                boolean agregar = false;
                                Materia materia = new Materia();
                                for (ModeloMateria i : materia.getMaterias()) {
                                    if (i.getId() == idMateria) {
                                        agregar = true;
                                        materiasEstudiante.add(i); // Se agrega el objeto ModeloMateria a la lista
                                        JOptionPane.showMessageDialog(null, "Materia agregada");
                                        break;
                                    }
                                }
                                if (!agregar) {
                                    JOptionPane.showMessageDialog(null, "Codigo de materia incorrecto");
                                }
                                break;
                            case 2:
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opcion incorrecta");
                        }
                    } while (opcion != 2);
                    estudiante.setMaterias(materiasEstudiante); // Se asigna la lista de materias al estudiante
                    StringBuilder mensaje = new StringBuilder("El estudiante con ID " + estudiante.getId() + " ha sido actualizado exitosamente");
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                }
            }
            if (!existe) {
                JOptionPane.showMessageDialog(null, "ID " + idEstudiante + " de estudiante no existe");
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("Error" + e);
        }
    }

    public String consultarMateriasEstudiante(int id) {
        StringBuilder materias = new StringBuilder("\nMaterias del estudiante:\n");
        for (ModeloEstudiante estudiante : estudiantes) {
            if (estudiante.getId() == id) {
                for (ModeloMateria materia : estudiante.getMaterias()) {
                    materias.append(materia.getDescripcion()).append("\n");
                }
            }
        }
        return materias.toString();
    }

    public void verEstudianteGrado(String grado) {
        try {
            int contador = 0;
            for (ModeloEstudiante estudiante : estudiantes) {
                if (estudiante.getGrado() == null ? grado == null : estudiante.getGrado().equals(grado)) {
                    contador++;
                    System.out.println("Informacion Estudiantes del grado " + grado + "\n");
                    System.out.println(("ID: " + estudiante.getId()
                            + "\nNombre: " + estudiante.getNombre()
                            + "\nApellidos: " + estudiante.getApellido()
                            + "\nDireccion: " + estudiante.getDireccion()
                            + "\nEdad: " + estudiante.getEdad()
                            + "\n=============================================="));
                }
            }
            JOptionPane.showMessageDialog(null, "Revisar consola con lista de estudiantes del grado " + grado + ".\nEn total hay " + contador + " estudiantes");
        } catch (HeadlessException e) {
            System.out.println("Error" + e);
        }
    }

    public void verEstudiante() {
        try {
            int idEstudiante = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID estudiante: "));
            boolean existe = false;
            for (ModeloEstudiante estudiante : estudiantes) {
                if (estudiante.getId() == idEstudiante) {
                    existe = true;
                    StringBuilder mensaje = new StringBuilder("""
                                                              DETALLES ESTUDIANTE
                                                              ID: """ + estudiante.getId()
                            + "\nNombre: " + estudiante.getNombre()
                            + "\nApellidos: " + estudiante.getApellido()
                            + "\nDireccion: " + estudiante.getDireccion()
                            + "\nEdad: " + estudiante.getEdad()
                            + "\nGrado: " + estudiante.getGrado()
                            + consultarMateriasEstudiante(idEstudiante));
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                }
            }
            if (!existe) {
                JOptionPane.showMessageDialog(null, "ID " + idEstudiante + " de estudiante no existe");
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("Error" + e);
        }
    }

    public void historialEstudiantes() {
        try {
            for (ModeloEstudiante estudiante : estudiantes) {
                System.out.println("""
                                    DETALLES ESTUDIANTE
                                    ID: """ + estudiante.getId()
                        + "\nNombre: " + estudiante.getNombre()
                        + "\nApellidos: " + estudiante.getApellido()
                        + "\nDireccion: " + estudiante.getDireccion()
                        + "\nEdad: " + estudiante.getEdad()
                        + "\nGrado: " + estudiante.getGrado()
                        + consultarMateriasEstudiante(estudiante.getId()));
            }
            JOptionPane.showMessageDialog(null, "Ver en consola la información de los estudiantes");
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("Error" + e);
        }
    }

    public void navegarEstudiante() {
        int opcion = 0;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("""
                                                                         ESTUDIANTES
                                                                         
                                                                    Seleccione una opcion:
                                                                    1. Agregar
                                                                    2. Eliminar
                                                                    3. Actualizar
                                                                    4. Consultar un estudiante
                                                                    5. Consultar estudiante por grado
                                                                    6. Consultar estudiantes
                                                                    7. Salir
                                                                        """));
            switch (opcion) {
                case 1 ->
                    agregar();
                case 2 ->
                    eliminar();
                case 3 ->
                    actualizar();
                case 4 ->
                    verEstudiante();
                case 5 -> {
                    String grado = JOptionPane.showInputDialog("Ingrese el grado a consultar de 1 a 11: ");
                    verEstudianteGrado(grado);
                }
                case 6 ->
                    historialEstudiantes();
                case 7 -> {
                }
                default ->
                    JOptionPane.showMessageDialog(null, "Opcion " + opcion + " no existe");
            }
        } while (opcion != 7);
    }

}
