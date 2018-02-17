package beans;

import java.util.Date;

public class HistoricoNotas extends Curso {
	private int nota;
	
	public HistoricoNotas(String nombreCurso, Date fechaInicio, Date fechaFin,int nota) {
		super(nombreCurso, fechaInicio, fechaFin);
		this.nota=nota;
		
	}
	
	
	
}
