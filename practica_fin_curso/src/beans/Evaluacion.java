package beans;

public class Evaluacion {
	private int dni;
	private int idCurso;
	private int nota;
	
	public Evaluacion(int dni, int idCurso, int nota) {
		super();
		this.dni = dni;
		this.idCurso = idCurso;
		this.nota = nota;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	
	
	
	
}
