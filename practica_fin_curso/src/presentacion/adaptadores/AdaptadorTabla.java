package presentacion.adaptadores;

import javax.swing.table.AbstractTableModel;
import java.util.List;

import beans.Curso;
import beans.HistoricoNotas;

public class AdaptadorTabla<HistoricoNotas> extends AbstractTableModel {

	List<HistoricoNotas> cursos;
	public AdaptadorTabla(List<HistoricoNotas> cursos) {
		this.cursos=cursos;
	}
	
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return cursos.size();
	}

	@Override
	public Object getValueAt(int fila, int col) {
		String valor="";
		switch(col){
			case 0:
				valor=((Curso) cursos.get(fila)).getNombreCurso();
				break;
			case 1:
				valor=((Curso) cursos.get(fila)).getFechaInicio().toString();
				break;
			case 2:
				valor=((Curso) cursos.get(fila)).getFechaFin().toString();
				break;
			case 3:
				valor=String.valueOf(((beans.HistoricoNotas) cursos.get(fila)).getNota());
				break;
		}
		
		
		return valor;
	}
	
	@Override
	public String getColumnName(int arg0) {
		String nombre="";
		switch(arg0) {
			case 0:
				nombre="Nombre Curso";
				break;
			case 1:
				nombre="Fecha Inicio";
				break;
			case 2:
				nombre="Fecha Fin";
				break;
			case 3:
				 nombre="Nota";
				break;
		}
		return nombre;
	}

}
