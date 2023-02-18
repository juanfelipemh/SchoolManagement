package ejerciciocuatro;

import controlador.Estudiante;
import controlador.Materia;
import java.util.Date;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        int opcion = 0;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                                                                    horario() + ", Seleccione el modulo con el cual desea interactuar\n" 
                                                                    + """    
                                                                    1. Materias
                                                                    2. Estudiantes
                                                                    3. Salir
                                                                        """));
            switch (opcion) {
                case 1 -> {
                    Materia materia = new Materia();
                    materia.navegarMateria();
                }
                case 2 -> {
                    Estudiante estudiante = new Estudiante();
                    estudiante.navegarEstudiante();
                }
                case 3 -> {
                }
                default -> throw new AssertionError();
            }
        } while (opcion
                != 3);
    }

    public static String horario() {
        Date hora = new Date();
        if (hora.getHours() >= 5 && hora.getHours() <= 11) {
            return "Buenos dias";
        } else if (hora.getHours() >= 12 && hora.getHours() <= 18) {
            return "Buenos tardes";
        } else {
            return "Buenas noches";
        }
    }
}
