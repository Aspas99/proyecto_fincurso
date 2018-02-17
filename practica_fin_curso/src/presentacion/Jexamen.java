package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.Alumno;
import beans.Curso;
import beans.Examen;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jexamen extends JFrame {

	private JPanel contentPane;
	static int pantallas;
	private static Jexamen jvc;
	private Alumno alumno;
	private Curso curso;
	private Examen examen;



	/**
	 * Create the frame.
	 */
	public Jexamen(Alumno a,Curso c,Examen e) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtMensajeTest = new JTextArea();
		txtMensajeTest.setText("A continuaci\u00F3n, se presentar\u00E1n las preguntas del test en varias pantallas.Pulse el boton de \"siguiente\" para avanzar y al finalizar, pulse el boton de salir.");
		txtMensajeTest.setBounds(64, 28, 299, 133);
		contentPane.add(txtMensajeTest);
		
		JButton btnComenzarTest = new JButton("Comenzar test");
		btnComenzarTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnComenzarTest.setBounds(202, 205, 116, 23);
		contentPane.add(btnComenzarTest);
	}
	
public static Jexamen nuevaVentanaExamen(Alumno a,Curso c,Examen e) { //El método que crea 
		
		if (jvc==null) { //si el obj no existe o no ha sido creado :solo hay un objeto prvate y static ,es decir ambito de clase 
						// y static ,es decir que se comparte por todas las instancias
			jvc=new Jexamen(a,c,e);
		}
		return jvc;
	} 
}
