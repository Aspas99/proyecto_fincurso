package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.Alumno;
import beans.Curso;
import beans.Examen;
import beans.Respuesta;
import modelo.GestionAlumno;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class JTest extends JFrame {
	private  Alumno a;
	private Curso c;
	private  Examen e;
	private GestionAlumno galumno;
	private static JTest jvc;
	private JPanel contentPane;
	private int respuestaok;
	private int [] respuesta= new int[5];
	static private int pregunta;
	private static String respuestasExamen[];
	static
	{
		respuestasExamen= new String[5];
	}
	/**
	 * Create the frame.
	 */
	private JTest(Alumno a, Curso c,Examen e,Respuesta r) {
		setTitle("Q&A pregunta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
					//Se pone en el lblPregunta la pregunta correspondiente a la tabla de preguntas almacenada en bbdd ,posicion 1 a 5
				JLabel lblPregunta = new JLabel("Pregunta numero " + (pregunta +1));
				lblPregunta.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblPregunta.setBounds(10, 34, 137, 14);
				contentPane.add(lblPregunta);
				//Se crean los radio button de las respuestas ,inicialmente con una respuesta aleatoria 
				//y nos aseguramos que ninguna corresponde a la correcta para evitar dos opciones iguales
				//con la respuesta 
				JTextPane textPane = new JTextPane();
				textPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
				textPane.setBounds(157, 11, 277, 65);
				contentPane.add(textPane);
				System.out.println("La pregunta "+ pregunta + "es" +e.getPreguntas()[pregunta]);
				textPane.setText(e.getPreguntas()[pregunta]);
			
				for (int i=0;i<r.getRespuestas().length;i++)
					respuesta[i]= i;
			
				//Aqui marcamos las 5 posibles respuestas 
				
				JRadioButton rdbtnRespuesta1 = new JRadioButton(r.getRespuestas()[respuesta[0]]);
				rdbtnRespuesta1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				rdbtnRespuesta1.setBounds(10, 86, 148, 23);
				contentPane.add(rdbtnRespuesta1);
				
				JRadioButton rdbtnRespuesta2 = new JRadioButton(r.getRespuestas()[respuesta[1]]);
				rdbtnRespuesta2.setFont(new Font("Tahoma", Font.PLAIN, 11));
				rdbtnRespuesta2.setBounds(10, 112, 148, 23);
				contentPane.add(rdbtnRespuesta2);
				
				JRadioButton rdbtnRespuesta3 = new JRadioButton(r.getRespuestas()[respuesta[2]]);
				rdbtnRespuesta3.setFont(new Font("Tahoma", Font.PLAIN, 11));
				rdbtnRespuesta3.setBounds(10, 138, 148, 23);
				contentPane.add(rdbtnRespuesta3);
				
				JRadioButton rdbtnRespuesta4 = new JRadioButton(r.getRespuestas()[respuesta[3]]);
				rdbtnRespuesta4.setFont(new Font("Tahoma", Font.PLAIN, 11));
				rdbtnRespuesta4.setBounds(10, 164, 148, 23);
				contentPane.add(rdbtnRespuesta4);
				
				JRadioButton rdbtnRespuesta5 = new JRadioButton(r.getRespuestas()[respuesta[4]]);
				rdbtnRespuesta5.setFont(new Font("Tahoma", Font.PLAIN, 11));
				rdbtnRespuesta5.setBounds(10, 190, 148, 23);
				contentPane.add(rdbtnRespuesta5);
				respuestaok=(int)( Math.floor(Math.random()*5+1));
				

				JButton btnSiguiente = new JButton("Siguiente pregunta");
				btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnSiguiente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
 int marcado= rdbtnRespuesta1.isSelected()?0:rdbtnRespuesta2.isSelected()?1:rdbtnRespuesta3.isSelected()?2:rdbtnRespuesta4.isSelected()?3:rdbtnRespuesta5.isSelected()?5:0;
						
						switch (marcado) {
							case 0:
								respuestasExamen[pregunta]=rdbtnRespuesta1.getText();
								break;
							case 1:
								respuestasExamen[pregunta]=rdbtnRespuesta2.getText();
								break;
							case 2:
								respuestasExamen[pregunta]=rdbtnRespuesta3.getText();
								break;
							case 3:
								respuestasExamen[pregunta]=rdbtnRespuesta4.getText();
								break;
							case 5:
								respuestasExamen[pregunta]=rdbtnRespuesta5.getText();
								break;
						}

						if (pregunta<4) {
						    jvc.setVisible(false);
							pregunta++; 
							JTest jtest = new JTest(a,c,e,r);
							jtest.setVisible(true);
							jvc.dispose();
							
						}else {
							e.setRespuestas(respuestasExamen);
							
							galumno = new GestionAlumno();
						    JNotas jnotas =  JNotas.nuevaVentanaNotas(galumno.evaluarTest(a, c, e));		
							btnSiguiente.setEnabled(false);
							jnotas.setVisible(true);
							jvc.dispose();
						}
					}
				});
			
				btnSiguiente.setBounds(162, 227, 89, 23);
				contentPane.add(btnSiguiente);
				
				
				}
	
	
    public static JTest nuevaVentanaTest(Alumno a, Curso c,Examen e,Respuesta r) { //El método que crea 
		
		if (jvc==null) { //si el obj no existe o no ha sido creado :solo hay un objeto prvate y static ,es decir ambito de clase 
						// y static ,es decir que se comparte por todas las instancias
			jvc=new JTest(a,c,e,r);
			
		}
		return jvc;
	} 
}
