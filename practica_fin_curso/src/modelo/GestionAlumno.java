package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Alumno;
import beans.Curso;
import beans.Evaluacion;
import beans.Examen;
import beans.HistoricoNotas;
import beans.Respuesta;
import utilidades.Tools;

public class GestionAlumno {
	
	public int evaluarTest(Alumno a , Curso c, Examen e) {
		int nota=0;
		Respuesta resp=Tools.buscarRespuestas(c.getNombreCurso()) ;
		Evaluacion el = buscarEvaluacion(a.getDni(),c.getNombreCurso());
		if (el==null) {
		
			for (int i=0; i<e.getPreguntas().length; i++) {
				if (e.getRespuestas()[i].equals(resp.getRespuestas()[i])) {
					nota++;
			    }
			}
			
			try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
			{
				//consultas preparadas. Inserta un registro en la tabla de evaluaciones
				
				String sql="Insert into Evaluacion(dni,idCurso,nota) values(?,?,?)";
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setInt(1,a.getDni());
				ps.setInt(2,c.getIdCurso());
				ps.setInt(3, nota);
				ps.execute();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}else nota=el.getNota();
		return nota;
		
	}
	
	public Evaluacion buscarEvaluacion(int dni,String nombreCurso) {
		Evaluacion e=null;
		Curso c=Tools.buscarCurso(nombreCurso);
		Alumno a= Tools.buscarAlumno(dni);
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			
			String sql="select * from Evaluacion where idCurso=? and dni=?";
			
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1,c.getIdCurso());
			ps.setInt(2,dni);
			
			ResultSet rs=ps.executeQuery();
			//No necesitamos converison de fechas sql a util.date ya que hay asignacion directa por herdar sql de date.
			while (rs.next()) {
				e=new Evaluacion(rs.getInt("dni"),rs.getInt(c.getIdCurso()),rs.getInt("nota"));
			}
						
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
			return e;
	}
	
	
    public List<HistoricoNotas> buscarCursosAlumno(int dni){
    	Alumno a=Tools.buscarAlumno(dni);
    	ArrayList<HistoricoNotas> cursos=new ArrayList<>();
    	try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			 Statement st=cn.createStatement();
			String sql="select * from Evaluacion where dni='" + a.getDni() +"'";
			ResultSet rs=st.executeQuery(sql);
			//No necesitamos converison de fechas sql a util.date ya que hay asignacion directa por herdar sql de date.
			while (rs.next()) {
				Curso c=Tools.buscarCurso(rs.getInt("idCurso"));
				cursos.add(new HistoricoNotas(c.getNombreCurso(),c.getFechaInicio(),c.getFechaFin(),rs.getInt("nota")));
			}		
						
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
    	return cursos;
    }
    
    public List<HistoricoNotas> listadoCursosAlumno(int dni){
    	
    	ArrayList<HistoricoNotas> cursos=null;
    	Alumno a=Tools.buscarAlumno(dni);
    	if (a!=null) {
	    	try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root")){
	    		String sql="select distinct m.dniAlumno,c.nombreCurso,fechainicio,fechaFin,nota from" + 
	    				" (curso c, matriculas m, evaluacion e)" + 
	    				" where (m.nombreCurso=c.nombreCurso and m.dniAlumno=? and  e.dni=?)";
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setInt(1,dni);
				ps.setInt(2,dni);
	    		ResultSet rs=ps.executeQuery();
	    	    cursos=new ArrayList<>();
				while (rs.next()) {
					cursos.add(new HistoricoNotas(rs.getString("nombreCurso"),rs.getDate("fechaInicio"),
							rs.getDate("fechaFin"),rs.getInt("nota")));
				}		
							
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
      }
      	   return cursos;
    }  
}


