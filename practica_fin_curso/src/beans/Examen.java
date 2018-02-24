package beans;

public class Examen {
	
	private int idCurso;
	private String [] preguntas;
	private String [] respuestas;
	
	
	
	public Examen(int idCurso,String [] preguntas) {
		
		this.idCurso = idCurso;

		this.preguntas = preguntas;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setidCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	

	public String[] getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(String[] preguntas) {
		this.preguntas = preguntas;
	}
	
	public String[] getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(String[] respuestas) {
		this.respuestas = respuestas;
	}

}
