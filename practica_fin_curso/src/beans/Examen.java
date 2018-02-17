package beans;

public class Examen {
	
	private String nombreCurso;
	private String [] preguntas;
	private String [] respuestas;
	
	public Examen(String nombreCurso, String[] respuestas) {
		
		this.nombreCurso = nombreCurso;
		this.respuestas = respuestas;
	}
	public Examen(String nombreCurso,String [] preguntas, String[] respuestas) {
		
		this.nombreCurso = nombreCurso;
		this.respuestas = respuestas;
		this.preguntas = preguntas;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public String[] getRespuestas() {
		return respuestas;
	}
	public void setRespuestas(String[] respuestas) {
		this.respuestas = respuestas;
	}
	public String[] getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(String[] preguntas) {
		this.preguntas = preguntas;
	}
	
	

}
