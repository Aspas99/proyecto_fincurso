package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Jadministracion extends JFrame {

	private JPanel contentPane;
	private static Jadministracion jvc;

	/**
	 * Create the frame.
	 */
	private Jadministracion() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAltaCursos = new JButton("Alta Cursos");
		btnAltaCursos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAltaCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jalta jvc=Jalta.nuevaVentanaAlta();
				jvc.setVisible(true);
			}
		});
		btnAltaCursos.setBounds(38, 132, 141, 23);
		contentPane.add(btnAltaCursos);
		
		JButton btnMatricularAlumnos = new JButton("Matricular alumnos");
		btnMatricularAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMatricularAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jmatricula jvc = Jmatricula.nuevaVentanaMatricula();
				jvc.setVisible(true);
			}
		});
		btnMatricularAlumnos.setBounds(239, 132, 176, 23);
		contentPane.add(btnMatricularAlumnos);
	}

    public static Jadministracion nuevaVentanaAdministracion() { //El método que crea 
		
		if (jvc==null) { //si el obj no existe o no ha sido creado :solo hay un objeto prvate y static ,es decir ambito de clase 
						// y static ,es decir que se comparte por todas las instancias
			jvc=new Jadministracion();
		}
		return jvc;
	} 
}
