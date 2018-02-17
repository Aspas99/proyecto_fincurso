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

public class Tools {
	

	public static Curso buscarCurso(String nombreCurso) {
		Curso c=null;
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			 Statement st=cn.createStatement();
			String sql="select * from Curso where nombreCurso='"+ nombreCurso +"'";
			ResultSet rs=st.executeQuery(sql);
			//No necesitamos converison de fechas sql a util.date ya que hay asignacion directa por herdar sql de date.
			if (rs.next())
				c=new Curso(rs.getInt("idCurso"),rs.getString("nombreCurso"),rs.getDate("fechaInicio"),rs.getDate("fechaFin"));
						
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
			if (rs.next())
				c=new Curso(rs.getInt("idCurso"),rs.getString("nombreCurso"),rs.getDate("fechaInicio"),rs.getDate("fechaFin"));
						
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
			if (rs.next())
				e=new Examen(rs.getString("nombreCurso"),
						(String []) rs.getObject("preguntas"),
						(String []) rs.getObject("respuestas"));
						
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
			return e;
	}
	
	public static  Alumno buscarAlumno(int dni) {
		Alumno a=null;
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			Statement st=cn.createStatement();
			String sql="select * from Alumnos where dni='"+ dni +"'";
			ResultSet rs=st.executeQuery(sql);
			if (rs.next())
				a=new Alumno(dni,rs.getString("nombreAlumno"),rs.getInt("telefono"));
						
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
