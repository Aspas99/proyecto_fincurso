package presentacion.adaptadores;

import java.util.Date;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class AdaptadorListaCombo implements ComboBoxModel<Date> {
	List<Date> fechas;
	Date fechasel;
	
	public AdaptadorListaCombo(List<Date> fechas) {
		this.fechas=fechas;
	}
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getElementAt(int pos) {
		
		return fechas.get(pos);
	}

	@Override
	public int getSize() {
		
		return fechas.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getSelectedItem() {
		
		return fechasel;
	}

	@Override
	public void setSelectedItem(Object arg0) {

        this.fechasel=(Date)arg0;
		
	}
	

}
