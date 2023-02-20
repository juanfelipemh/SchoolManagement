package controlador;

import interfaces.CRUD;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import modelos.ModeloEstudiante;
import modelos.ModeloMateria;

public class Materia implements CRUD {
    
    static public List<ModeloMateria> materias = new ArrayList<>();

    public List<ModeloMateria> getMaterias() {
        return materias;
    }

    @Override
    public void agregar() {
        try {
            int idNuevo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un ID unico: "));
            boolean existe = false;
            for (ModeloMateria materia : materias) {
                if (materia.getId() == idNuevo) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                JOptionPane.showMessageDialog(null, "El código de material ya existe");
            } else {
                String descripcion = JOptionPane.showInputDialog("Ingrese el nombre de la materia: ");
                ModeloMateria materia = new ModeloMateria(idNuevo, descripcion);
                materias.add(materia);
                StringBuilder mensaje = new StringBuilder("Materia " + descripcion + " con codigo " + idNuevo + " almacenado exitosamente");
                JOptionPane.showMessageDialog(null, mensaje);
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void eliminar() {
        try {
            int idMateria = Integer.parseInt(JOptionPane.showInputDialog("Ingrese codigo: "));
            boolean existe = false, eliminado = false;
            Iterator<ModeloMateria> iterMaterias = materias.iterator();
            while (iterMaterias.hasNext()) {
                ModeloMateria materia = iterMaterias.next();
                if (materia.getId() == idMateria) {
                    existe = true;
                    for (ModeloEstudiante estudiante : Estudiante.estudiantes) {
                        Iterator<ModeloMateria> iterMateriasEstudiante = estudiante.getMaterias().iterator();
                        while (iterMateriasEstudiante.hasNext()) {
                            ModeloMateria materiaEstudiante = iterMateriasEstudiante.next();
                            if (materiaEstudiante.getId() == idMateria) {
                                int respConfirmacion = JOptionPane.showConfirmDialog(null, "La materia tiene estudiantes inscritos, ¿Esta seguro que desea eliminarla?", "Alerta!", JOptionPane.YES_NO_OPTION);
                                if (respConfirmacion == JOptionPane.YES_OPTION) {
                                    iterMateriasEstudiante.remove();
                                    eliminado = true;
                                    break;
                                } else {
                                    return;
                                }
                            }
                        }
                    }
                }
                if (existe || eliminado) {
                    iterMaterias.remove();
                    StringBuilder mensaje = new StringBuilder("Materia con codigo " + idMateria + " eliminada");
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                }
            }
            if (!existe) {
                StringBuilder mensaje = new StringBuilder("Materia con codigo " + idMateria + " no existe");
                JOptionPane.showMessageDialog(null, mensaje);
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void actualizar() {
        try {
            int idMateria = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID Materia: "));
            boolean existe = false;
            for (ModeloMateria materia : materias) {
                if (materia.getId() == idMateria) {
                    existe = true;
                    String descripcion = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la materia: ");
                    materia.setDescripcion(descripcion);
                    break;
                }
            }
            if (!existe) {
                JOptionPane.showMessageDialog(null, "No existe la materia");
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("Error: " + e);
        }
    }

    public void verMateria() {
        try {
            int idMateria = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID Materia: "));
            boolean existe = false;
            for (ModeloMateria materia : materias) {
                int contador = 0;
                if (materia.getId() == idMateria) {
                    existe = true;
                    for (ModeloEstudiante estudiante : Estudiante.estudiantes) { // Trae de la clase estudiante la variable global estudiantes tipo List
                        for (ModeloMateria materiaEstudiante : estudiante.getMaterias()) {
                            if (materiaEstudiante.getId() == idMateria) {
                                contador++;
                            }
                        }
                    }
                    StringBuilder mensaje = new StringBuilder("""
                                                              DETALLES MATERIA
                                                              ID: """ + materia.getId() + "\nDescripcion: " + materia.getDescripcion() + "\nN° Inscritos: " + contador);

                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                }
            }
            if (!existe) {
                JOptionPane.showMessageDialog(null, "No existe la materia");
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("Error: " + e);
        }
    }

    public void historialMaterias() {
        try {
            StringBuilder resultado = new StringBuilder();
            for (ModeloMateria materia : materias) {
                int contador = 0;
                for (ModeloEstudiante estudiante : Estudiante.estudiantes) {
                    for (ModeloMateria materiaEstudiante : estudiante.getMaterias()) {
                        if (materiaEstudiante.getId() == materia.getId()) {
                            contador++;
                            break;
                        }
                    }
                }
                resultado.append("\n").append("ID: ").append(materia.getId()).append(" - ").append("Materia: ").append(materia.getDescripcion()).append(" - N° Inscritos: ").append(contador);
            }
            JOptionPane.showMessageDialog(null, resultado.toString(), "Historial Materias", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException e) {
            System.out.println("Error: " + e);
        }
    }

    public void navegarMateria() {
        try {
            int opcion = 0;
            do {
                opcion = Integer.parseInt(JOptionPane.showInputDialog("""
                                                                            MATERIAS
                                                                            
                                                                    Seleccione una opcion:
                                                                    1. Agregar
                                                                    2. Eliminar
                                                                    3. Actualizar
                                                                    4. Consultar una materia
                                                                    5. Historial materias
                                                                    6. Salir
                                                                        """));
                switch (opcion) {
                    case 1 ->
                        agregar();
                    case 2 ->
                        eliminar();
                    case 3 ->
                        actualizar();
                    case 4 ->
                        verMateria();
                    case 5 ->
                        historialMaterias();
                    case 6 -> {
                    }
                    default ->
                        JOptionPane.showMessageDialog(null, "Opcion " + opcion + " no existe");
                }
            } while (opcion != 6);
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("Error: " + e);
        }
    }
}
