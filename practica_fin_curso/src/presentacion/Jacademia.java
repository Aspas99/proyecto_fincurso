package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jacademia extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jacademia frame = new Jacademia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Jacademia() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido a la aplicacion,escoja su perfil");
		lblNewLabel.setBounds(107, 11, 205, 78);
		contentPane.add(lblNewLabel);
		
		JButton btnAdministracion = new JButton("Administracion");
		btnAdministracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jadministracion jvc=Jadministracion.nuevaVentanaAdministracion();
				jvc.setVisible(true);
			}
		});
		btnAdministracion.setBounds(20, 115, 157, 23);
		contentPane.add(btnAdministracion);
		
		JButton btnAlumno = new JButton("Alumno");
		btnAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jlogin jvc = Jlogin.nuevaVentanaLogin();	
				jvc.setVisible(true);
			}
		});
		btnAlumno.setBounds(252, 115, 146, 23);
		contentPane.add(btnAlumno);
	}
}
