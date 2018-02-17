package presentacion.adaptadores;

import java.util.Date;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import beans.Curso;

public class AdaptadorListaComboCursos implements ComboBoxModel<Curso> {
	List<Curso> cursos;
	Curso cursosel;
	
	public AdaptadorListaComboCursos(List<Curso> cursos) {
		this.cursos=cursos;
	}
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Curso getElementAt(int pos) {
		
		return cursos.get(pos);
	}

	@Override
	public int getSize() {
		
		return cursos.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getSelectedItem() {
		
		return cursosel;
	}

	@Override
	public void setSelectedItem(Object arg0) {

        this.cursosel=(Curso)arg0;
		
	}
	

}
