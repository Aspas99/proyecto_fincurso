package beans;

import java.util.Date;

public class Curso {
	private int idCurso;
	private String nombreCurso;
	private Date fechaInicio;
	private Date fechaFin;
	
	
	public Curso(String nombreCurso, Date fechaInicio, Date fechaFin) {
		this.nombreCurso = nombreCurso;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public Curso(int idCurso,String nombreCurso, Date fechaInicio, Date fechaFin) {
		this.idCurso=idCurso;
		this.nombreCurso = nombreCurso;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	
	
	public int getIdCurso() {
		return idCurso;
	}



	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}



	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public String toString() {
		return nombreCurso;
		
	}
	
	
	
}
