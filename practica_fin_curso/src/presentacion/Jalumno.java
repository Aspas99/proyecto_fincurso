package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.Alumno;
import beans.Curso;
import beans.Examen;
import modelo.GestionAdmin;
import presentacion.adaptadores.AdaptadorListaComboCursos;
import utilidades.Tools;
import java.awt.Font;

public class Jalumno extends JFrame {

	private JPanel contentPane;
    private static Jalumno jvc;
    private static  Alumno alumno;
    private static Curso c;
    private static Examen examen;
    private AdaptadorListaComboCursos adp;
	

	/**
	 * Create the frame.
	 */
	private Jalumno(Alumno a) {
		setTitle("Bienvenido alumno");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		alumno=a;
		
		
		JLabel lblCursosDisponibles = new JLabel("Cursos Disponibles");
		lblCursosDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCursosDisponibles.setBounds(10, 63, 121, 14);
		contentPane.add(lblCursosDisponibles);
		
		JButton btnMisCursos = new JButton("Mis Cursos");
		btnMisCursos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMisCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JMisCursos jmc = JMisCursos.nuevaVentanaMisCursos(alumno);
				jmc.setVisible(true);
			}
		});
		btnMisCursos.setBounds(10, 29, 89, 23);
		contentPane.add(btnMisCursos);
		
		JComboBox <Curso> cmboxCursosActivos = new JComboBox<Curso>();
		cmboxCursosActivos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cmboxCursosActivos.setBounds(152, 60, 272, 20);
		contentPane.add(cmboxCursosActivos);
	
		cmboxCursosActivos.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				GestionAdmin gadmin=new GestionAdmin();//idea sumar cursos con examen + crusos sin examen por alumno en este metodo en una sola lista
				 adp=new presentacion.adaptadores.AdaptadorListaComboCursos(gadmin.recuperaCursosActivos(a));
				 cmboxCursosActivos.setModel(adp);
			}
		});
		
		JButton btnRealizarTest = new JButton("Realizar test");
		btnRealizarTest.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRealizarTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionAdmin admin = new GestionAdmin();
			    c=(Curso)cmboxCursosActivos.getSelectedItem();
			    if (c!=null) {
				    examen = Tools.buscarExamen(c.getNombreCurso());
					Jexamen jexa = Jexamen.nuevaVentanaExamen(a, c, examen);
					jexa.setVisible(true);
			    }
			}
		});
		btnRealizarTest.setBounds(10, 133, 105, 23);
		contentPane.add(btnRealizarTest);
		
		
	}
	
public static Jalumno nuevaVentanaAlumno(Alumno a) { //El método que crea 
		
		if (jvc==null) { //si el obj no existe o no ha sido creado :solo hay un objeto prvate y static ,es decir ambito de clase 
						// y static ,es decir que se comparte por todas las instancias
			jvc=new Jalumno(a);
		}
		return jvc;
	} 
}
