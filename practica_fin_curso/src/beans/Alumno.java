package beans;

public class Alumno {
	private int dni;
	private String nombreAlumno;
	private int telefono;
	
	
	public Alumno(int dni, String nombreAlumno, int telefono) {
		super();
		this.dni = dni;
		this.nombreAlumno = nombreAlumno;
		this.telefono = telefono;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public String getNombreAlumno() {
		return nombreAlumno;
	}


	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	
	
}
