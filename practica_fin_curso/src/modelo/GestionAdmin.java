package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.Alumno;
import beans.Curso;
import utilidades.Tools;

public class GestionAdmin {
	
	Statement st;

	
	public Curso altaCurso(String nombreCurso, Date fechaInicio, Date fechaFin){
		Curso c=null;
		if ( fechaInicio.before(fechaFin) && !nombreCurso.equals("")){
		
			 c=new Curso(nombreCurso,fechaInicio,fechaFin);
			
			try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
			{
				//consultas preparadas
				java.sql.Date fechaIni=new java.sql.Date(fechaInicio.getTime());
				java.sql.Date fechafin=new java.sql.Date(fechaFin.getTime());
				String sql="Insert into curso(nombrecurso,fechainicio,fechafin) values(?,?,?)";
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setString(1,nombreCurso);
				ps.setDate(2,fechaIni);
				ps.setDate(3, fechafin);
				ps.execute();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}

        }
		return c;
	}
	
	
	public Alumno matricularAlumno(int dni, String nombreAlumno, int telefono, String nombreCurso) {
		Alumno a=null;
		Curso c=Tools.buscarCurso(nombreCurso);
		if (validarDni(dni) && !nombreAlumno.equals("") && !nombreCurso.equals("")) {
			a=Tools.buscarAlumno(dni);
			
			if (a==null) {
				a=new Alumno(dni,nombreAlumno,telefono);
				altaAlumno(dni,nombreAlumno,telefono);
			}
				
			try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
			{
				//consultas preparadas
				
				String sql="Insert into matriculas(dniAlumno,nombreCurso) values(?,?)";
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setInt(1,a.getDni());
				ps.setString(2,c.getNombreCurso());
					
				ps.execute();
				
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			
		}
		return a;
	}
	public boolean existeMatricula(int dni, String nombreCurso) {
		boolean res=false;
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			//consultas preparadas
			
			String sql="select * from matriculas where dniAlumno=? and nombreCurso=? ";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1,dni);
			ps.setString(2,nombreCurso);
			ResultSet rs=ps.executeQuery();
			res=rs.next();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return res;
		
	}
	
	public List<Curso> cursosAlumno(int dni) {
		Curso c=null;
		ArrayList<Curso> cursos =null;
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			//consultas preparadas
			
			String sql="select * from matriculas where dniAlumno=? ";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1,dni);
			ResultSet rs=ps.executeQuery();
			cursos = new ArrayList<>();
			while (rs.next()) {
				c=new Curso(rs.getInt("idCurso"),rs.getString("nombreCurso"),rs.getDate("fechaInicio"),rs.getDate("fechaFin"));
				cursos.add(c);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return cursos;
		
	}
	private boolean validarDni(int dni) {
		
		String Dni=String.valueOf(dni);
		
		return Dni.matches("[0-9]{6,9}");
	}
	
	private void altaAlumno(int dni,String nombreAlumno,int telefono) {
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			//consultas preparadas
			
			String sql="Insert into Alumnos(dni,nombreAlumno,telefono) values(?,?,?)";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1,dni);
			ps.setString(2,nombreAlumno);
			ps.setInt(3, telefono);
			ps.execute();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public List<Curso> recuperaCursos(){
		Curso c=null;
		ArrayList<Curso> cursos=null;
		
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			 Statement st=cn.createStatement();
			String sql="select * from Curso ";
			ResultSet rs=st.executeQuery(sql);
			cursos=new ArrayList<>();
			//No necesitamos converison de fechas sql a util.date ya que hay asignacion directa por herdar sql de date.
			while (rs.next()) {
				c=new Curso(rs.getInt("idCurso"),rs.getString("nombreCurso"),rs.getDate("fechaInicio"),rs.getDate("fechaFin"));
				cursos.add(c);
			}
						
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return cursos;
	}
	
	public List<Curso> recuperaCursosActivos(Alumno a){
		Curso c=null;
		ArrayList<Curso> cursos=null;
		
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/academias", "root", "root"))
		{
			
			String sql="select * from  curso c, matriculas m where c.nombreCurso=m.nombreCurso"
					+ " and m.dniAlumno=? and c.fechaFin>=CURDATE() ";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1,a.getDni());
			ResultSet rs=ps.executeQuery();
			cursos=new ArrayList<>();
			while (rs.next()) {
				c=new Curso(rs.getInt("idCurso"),rs.getString("nombreCurso"),rs.getDate("fechaInicio"),rs.getDate("fechaFin"));
				cursos.add(c);
			}
						
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return cursos;
	}
	
}