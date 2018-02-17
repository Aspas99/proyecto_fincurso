package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.GeneraFechas;
import modelo.GestionAdmin;
import presentacion.adaptadores.AdaptadorListaCombo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import java.awt.Choice;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;

public class Jalta extends JFrame {
    private static Jalta jvc;
	private JPanel contentPane;
	private JTextField txtNombreCurso;
	AdaptadorListaCombo adp;



	/**
	 * Create the frame.
	 */
	public Jalta() {
		setTitle("Alta curso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreCurso = new JLabel("Nombre curso");
		lblNombreCurso.setBounds(30, 11, 66, 14);
		contentPane.add(lblNombreCurso);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setBounds(30, 82, 66, 14);
		contentPane.add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setBounds(30, 161, 46, 14);
		contentPane.add(lblFechaFin);
		
		txtNombreCurso = new JTextField();
		txtNombreCurso.setBounds(25, 34, 178, 20);
		contentPane.add(txtNombreCurso);
		txtNombreCurso.setColumns(10);
		
		
		JComboBox <Date> comboBoxFechaIni = new JComboBox();
		comboBoxFechaIni.setBounds(30, 102, 88, 20);
		contentPane.add(comboBoxFechaIni);
		
		JComboBox <Date> comboBoxFechaFin = new JComboBox();
		comboBoxFechaFin.setBounds(30, 186, 88, 20);
		contentPane.add(comboBoxFechaFin);
		

		comboBoxFechaIni.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				Date aux = new Date();
				int year = GeneraFechas.getAno(aux);
				GeneraFechas fechas= new GeneraFechas(year);
				 adp=new presentacion.adaptadores.AdaptadorListaCombo(fechas.fechas());
				comboBoxFechaIni.setModel(adp);
				
			}
		});
		
		comboBoxFechaFin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				Date aux = new Date();
				int year = GeneraFechas.getAno(aux);
				GeneraFechas fechas= new GeneraFechas(year);
				 adp=new presentacion.adaptadores.AdaptadorListaCombo(fechas.fechas());
				comboBoxFechaFin.setModel(adp);
			}
		});
		
		JButton btnGuardarCurso = new JButton("Guardar Curso");
		btnGuardarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date d1=(Date)comboBoxFechaIni.getSelectedItem();
				Date d2=(Date)comboBoxFechaFin.getSelectedItem();
				if (d1.after(d2)) {
					Jfechas popUp=Jfechas.nuevaVentanaFechas();
				}else {
					GestionAdmin admin = new GestionAdmin();
						admin.altaCurso(txtNombreCurso.getText(),(Date)comboBoxFechaIni.getSelectedItem(),
						(Date)comboBoxFechaFin.getSelectedItem());
						jvc.dispose();
				}
			}
		});
		btnGuardarCurso.setBounds(269, 184, 103, 23);
		contentPane.add(btnGuardarCurso);
	
	}
	
    public static Jalta nuevaVentanaAlta() { //El método que crea 
		
		if (jvc==null) { //si el obj no existe o no ha sido creado :solo hay un objeto prvate y static ,es decir ambito de clase 
						// y static ,es decir que se comparte por todas las instancias
			jvc=new Jalta();
		}
		return jvc;
	} 
}
