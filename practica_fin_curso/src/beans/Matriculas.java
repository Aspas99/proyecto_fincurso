package beans;

public class Matriculas {
	private String nombreCurso;
	private int dniAlumno;
	
	public Matriculas(String nombreCurso, int dniAlumno) {
		super();
		this.nombreCurso = nombreCurso;
		this.dniAlumno = dniAlumno;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public int getDniAlumno() {
		return dniAlumno;
	}

	public void setDniAlumno(int dniAlumno) {
		this.dniAlumno = dniAlumno;
	};
	
	
	
	
	
}
