package beans;

public class Respuesta {
	private int idCurso;
	private String [] respuestas;
	
	public Respuesta(int idCurso, String[] respuestas) {
	
		this.idCurso = idCurso;
		this.respuestas = respuestas;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String[] getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(String[] respuestas) {
		this.respuestas = respuestas;
	}
	
	
	
}
