package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import beans.Alumno;
import utilidades.Tools;

public class Jlogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtDni;
	private Alumno a;
	private static Jlogin jvc;
	


	/**
	 * Create the frame.
	 */
	public Jlogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtDni = new JTextField();
		txtDni.setBounds(73, 38, 159, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblDniAlumno = new JLabel("Dni Alumno");
		lblDniAlumno.setBounds(10, 41, 68, 14);
		contentPane.add(lblDniAlumno);
		
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jalumno jalumno=Jalumno.nuevaVentanaAlumno(a);
				jalumno.setVisible(true);
				jvc.setVisible(false);//Cerramos esta ventana
			}
		});
		btnLogin.setBounds(133, 155, 89, 23);
		btnLogin.setEnabled(false);
		contentPane.add(btnLogin);
		
		JButton btnValidar = new JButton("Validar ");
		btnValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    a = Tools.buscarAlumno(Integer.parseInt(txtDni.getText()));
				if (a!=null) {
					btnLogin.setEnabled(true);
				}
			}
		});
		btnValidar.setBounds(48, 84, 89, 23);
		contentPane.add(btnValidar);
	}
	
  public static Jlogin nuevaVentanaLogin() { //El método que crea 
		
		if (jvc==null) { //si el obj no existe o no ha sido creado :solo hay un objeto prvate y static ,es decir ambito de clase 
						// y static ,es decir que se comparte por todas las instancias
			jvc=new Jlogin();
		}
		return jvc;
	} 
}
