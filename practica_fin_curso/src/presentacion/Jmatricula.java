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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import beans.Alumno;
import beans.Curso;
import modelo.GestionAdmin;
import presentacion.adaptadores.AdaptadorListaComboCursos;
import utilidades.Tools;

public class Jmatricula extends JFrame {
    private static Jmatricula jvc;
	private JPanel contentPane;
	private JTextField txtDni;
	private JTextField txtNombreAlumno;
	private JTextField txtTelefono;
	private AdaptadorListaComboCursos adp;

	

	/**
	 * Create the frame.
	 */
	public Jmatricula() {
		setTitle("Matriculacion alumno en curso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 11, 74, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(10, 28, 86, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblNombreAlumno = new JLabel("Nombre Alumno");
		lblNombreAlumno.setBounds(204, 11, 86, 14);
		contentPane.add(lblNombreAlumno);
		
		txtNombreAlumno = new JTextField();
		txtNombreAlumno.setBounds(199, 28, 162, 20);
		contentPane.add(txtNombreAlumno);
		txtNombreAlumno.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 84, 46, 14);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(70, 81, 123, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblNombreCurso = new JLabel("Nombre Curso");
		lblNombreCurso.setBounds(10, 124, 86, 14);
		contentPane.add(lblNombreCurso);
		
		
		
		txtDni.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (txtDni.getText()!="" && txtDni.getText()!=null) {
					Alumno a = Tools.buscarAlumno(Integer.parseInt(txtDni.getText()));
					if (a!=null) {
						txtNombreAlumno.setText(a.getNombreAlumno());
						txtTelefono.setText(String.valueOf(a.getTelefono()));
						
					}
				}
			}
		});
		
		JComboBox <Curso>cmboxCursos = new JComboBox();	
		cmboxCursos.setBounds(121, 121, 254, 20);
		contentPane.add(cmboxCursos);
		cmboxCursos.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				GestionAdmin gadmin=new GestionAdmin();
				 adp=new presentacion.adaptadores.AdaptadorListaComboCursos(gadmin.recuperaCursos());
				 cmboxCursos.setModel(adp);
			}
		});
		
		JButton btnGuardarMatricula = new JButton("Guardar Matricula");
		btnGuardarMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionAdmin admin = new GestionAdmin();
				Curso c=(Curso)cmboxCursos.getSelectedItem();
				if (!admin.existeMatricula(Integer.parseInt(txtDni.getText()),c.getNombreCurso())) {
					
					admin.matricularAlumno(Integer.parseInt(txtDni.getText()), txtNombreAlumno.getText(), 
							Integer.parseInt(txtTelefono.getText()),c.getNombreCurso());
					jvc.dispose();
					
				}
			}
		});
		btnGuardarMatricula.setBounds(173, 176, 117, 23);
		contentPane.add(btnGuardarMatricula);
		
		
	}
	
	
	
   public static Jmatricula nuevaVentanaMatricula() { //El método que crea 
		
		if (jvc==null) { //si el obj no existe o no ha sido creado :solo hay un objeto prvate y static ,es decir ambito de clase 
						// y static ,es decir que se comparte por todas las instancias
			jvc=new Jmatricula();
		}
		return jvc;
	} 
}
