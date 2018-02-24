package utilidades;

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
import beans.Respuesta;

public class Tools {
	
	private final static int PREGUNTAS=5;
	
	public static Curso buscarCurso(String nombreCurso) {
		Curso c=null;
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			 Statement st=cn.createStatement();
			String sql="select * from Curso where nombreCurso='"+ nombreCurso +"'";
			ResultSet rs=st.executeQuery(sql);
			//No necesitamos converison de fechas sql a util.date ya que hay asignacion directa por herdar sql de date.
			while (rs.next()) {
				c=new Curso(rs.getInt("idCurso"),rs.getString("nombreCurso"),rs.getDate("fechaInicio"),rs.getDate("fechaFin"));
			}	
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
			return c;
	}
	
	public static Curso buscarCurso(int idCurso) {
		Curso c=null;
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			 Statement st=cn.createStatement();
			String sql="select * from Cursos where idCurso='"+ idCurso +"'";
			ResultSet rs=st.executeQuery(sql);
			//No necesitamos converison de fechas sql a util.date ya que hay asignacion directa por herdar sql de date.
			while (rs.next()) {
				c=new Curso(rs.getInt("idCurso"),rs.getString("nombreCurso"),rs.getDate("fechaInicio"),rs.getDate("fechaFin"));
			}
			System.out.println("buscarCurso nombreCurso"+c.getNombreCurso());
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
			return c;
	}
	
	public static Examen buscarExamen(String nombreCurso) {
		Curso c=buscarCurso(nombreCurso);
		Examen e=null;
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			 Statement st=cn.createStatement();
			String sql="select * from Examen where idCurso='"+ c.getIdCurso() +"'";
			ResultSet rs=st.executeQuery(sql);
			//No necesitamos converison de fechas sql a util.date ya que hay asignacion directa por herdar sql de date.
			String preguntas[]=new String[5];
			while (rs.next()) {
				for (int i=1;i<PREGUNTAS;i++) {preguntas[i-1]=rs.getString(i+1);}
				e=new Examen(rs.getInt("idCurso"),preguntas);
			}			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
			return e;
	}
	
	public static Respuesta buscarRespuestas(String nombreCurso) {
		Curso c=buscarCurso(nombreCurso);
		Respuesta  r=null;
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			 Statement st=cn.createStatement();
			String sql="select * from Respuestas where idCurso='"+ c.getIdCurso() +"'";
			ResultSet rs=st.executeQuery(sql);
			//No necesitamos converison de fechas sql a util.date ya que hay asignacion directa por herdar sql de date.
			String resp[]=new String[5];
			while (rs.next()) {
				for (int i=0;i<PREGUNTAS;i++) {resp[i]=rs.getString(i+2);}
				r=new Respuesta(rs.getInt("idCurso"),resp);
			}			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
			return r;
	}
	
	public static  Alumno buscarAlumno(int dni) {
		Alumno a=null;
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			Statement st=cn.createStatement();
			String sql="select * from Alumnos where dni='"+ dni +"'";
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				a=new Alumno(dni,rs.getString("nombreAlumno"),rs.getInt("telefono"));
			}			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
			return a;
	}
	
	public static List<Curso> buscarCursosPendientes(int dni) {
		ArrayList <Curso> cursos=null;
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			
			String sql="select * from curso as c where c.idCurso not in (select idCurso from evaluacion where dni=? ) ";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1,dni);
			ResultSet rs=ps.executeQuery();
			 cursos=new ArrayList<>();
			while (rs.next()) {
				Curso c=new Curso(rs.getInt("idCurso"),rs.getString("nombreCurso"),rs.getDate("fechaInicio"),rs.getDate("fechaFin"));
				cursos.add(c);
			}
						
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
			return cursos;
	}
		
	

}
