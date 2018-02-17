package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.Alumno;
import beans.Curso;
import beans.Examen;
import modelo.GestionAlumno;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JTest extends JFrame {
	private  Alumno a;
	private Curso c;
	private  Examen e;
	private GestionAlumno galumno;
	private static JTest jvc;
	private JPanel contentPane;
	private int respuestaok;
	private int respuesta[]= new int[5];


	/**
	 * Create the frame.
	 */
	public JTest(Alumno a, Curso c,Examen e,int pregunta) {
		setTitle("Q&A pregunta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPregunta = new JLabel(e.getPreguntas()[pregunta]);
		lblPregunta.setBounds(10, 34, 73, 14);
		contentPane.add(lblPregunta);
		//Se crean los radio button de las respuestas ,inicialmente con una respuesta aleatoria 
		//y nos aseguramos que ninguna corresponde a la correcta para evitar dos opciones iguales
		//con la respuesta 
		JTextPane textPane = new JTextPane();
		textPane.setBounds(93, 11, 308, 65);
		contentPane.add(textPane);
		int i=0;
		boolean completo =false;
		while (i<5 && !completo) {
			respuesta[i]=(int) Math.floor(Math.random()*e.getRespuestas().length+1);
			if (respuesta[i]==pregunta ) {
				respuesta[i]=(int) Math.floor(Math.random()*e.getRespuestas().length+1);
			}
			
			if (++i == 5) completo=true;
		}
		
		JRadioButton rdbtnRespuesta1 = new JRadioButton(e.getRespuestas()[respuesta[0]]);
		rdbtnRespuesta1.setBounds(10, 86, 148, 23);
		contentPane.add(rdbtnRespuesta1);
		
		JRadioButton rdbtnRespuesta2 = new JRadioButton(e.getRespuestas()[respuesta[1]]);
		rdbtnRespuesta2.setBounds(10, 112, 148, 23);
		contentPane.add(rdbtnRespuesta2);
		
		JRadioButton rdbtnRespuesta3 = new JRadioButton(e.getRespuestas()[respuesta[2]]);
		rdbtnRespuesta3.setBounds(10, 138, 148, 23);
		contentPane.add(rdbtnRespuesta3);
		
		JRadioButton rdbtnRespuesta4 = new JRadioButton(e.getRespuestas()[respuesta[3]]);
		rdbtnRespuesta4.setBounds(10, 164, 148, 23);
		contentPane.add(rdbtnRespuesta4);
		
		JRadioButton rdbtnRespuesta5 = new JRadioButton(e.getRespuestas()[respuesta[4]]);
		rdbtnRespuesta5.setBounds(10, 190, 148, 23);
		contentPane.add(rdbtnRespuesta5);
		respuestaok=(int)( Math.floor(Math.random()*5+1));
		
		
		switch (respuestaok) {
		case 1:rdbtnRespuesta1.setText(e.getRespuestas()[pregunta]);
			break;
		case 2:rdbtnRespuesta2.setText(e.getRespuestas()[pregunta]);
			break;
		case 3:rdbtnRespuesta3.setText(e.getRespuestas()[pregunta]);
			break;
		case 4:rdbtnRespuesta4.setText(e.getRespuestas()[pregunta]);
			break;
		case 5:rdbtnRespuesta5.setText(e.getRespuestas()[pregunta]);
			break;
		}
		
		JLabel lblNota = new JLabel("");
		lblNota.setBounds(289, 231, 46, 14);
		contentPane.add(lblNota);
		
		JButton btnEvaluar = new JButton("Evaluar");
		btnEvaluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				galumno=new GestionAlumno();
				int nota=galumno.evaluarTest(a, c, e);
				
			}
		});
		btnEvaluar.setBounds(162, 227, 89, 23);
		contentPane.add(btnEvaluar);
		
		
	}
	
    public static JTest nuevaVentanaTest(Alumno a, Curso c,Examen e,int p) { //El método que crea 
		
		if (jvc==null) { //si el obj no existe o no ha sido creado :solo hay un objeto prvate y static ,es decir ambito de clase 
						// y static ,es decir que se comparte por todas las instancias
			jvc=new JTest(a,c,e,p);
			
		}
		return jvc;
	} 
}
