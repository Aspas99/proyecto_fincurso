package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.Alumno;
import beans.HistoricoNotas;
import modelo.GestionAdmin;
import modelo.GestionAlumno;
import presentacion.adaptadores.AdaptadorListaComboCursos;
import presentacion.adaptadores.AdaptadorTabla;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class JMisCursos extends JFrame {

	private static JMisCursos jvc;
	private JPanel contentPane;
	private JTable table;
	private AdaptadorTabla adp;
    private Alumno a;

	/**
	 * Create the frame.
	 */
	private JMisCursos(Alumno a) {
		setTitle("Mis Cursos ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblIdAlumno = new JLabel("New label");
		lblIdAlumno.setBounds(10, 11, 46, 14);
		contentPane.add(lblIdAlumno);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(36, 44, 344, 183);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 414, 114);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		
		GestionAlumno galumno=new GestionAlumno();
	    List<HistoricoNotas> expediente=galumno.listadoCursosAlumno(a.getDni());
	  //creamos objeto adaptador
	  		 adp=new AdaptadorTabla(expediente);
	  		table.setModel(adp);
	  		
	  		this.setVisible(true);
	}
	
   public static JMisCursos nuevaVentanaMisCursos(Alumno a) { //El método que crea 
		
		if (jvc==null) { //si el obj no existe o no ha sido creado :solo hay un objeto prvate y static ,es decir ambito de clase 
						// y static ,es decir que se comparte por todas las instancias
			jvc=new JMisCursos(a);
		}
		return jvc;
	} 
}

