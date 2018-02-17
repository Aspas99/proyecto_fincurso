package modelo;


import java.sql.Date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GeneraFechas {
	List<java.util.Date> fechas;
	
	public GeneraFechas(int year) {
		LocalDate fecha=null;
		//SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy"); 
		java.sql.Date d;
		String date;
		fechas=new ArrayList<>();
		
		for (int i=1;i<=12;i++) {
			date=year +"-" +(i<10?"0"+i:i)+"-01";
			fecha=LocalDate.parse(date);
			fechas.add(Date.valueOf(fecha));
			System.out.println("La fecha generada para el alta de cursos es:" + fecha);
		}
	}
	
	public List<java.util.Date> fechas(){
		return fechas;
	}
	
	public static  int getAno(java.util.Date d) {
		if (null == d){

			return 0;

		}else{

			String formato="yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
			return Integer.parseInt(dateFormat.format(d));

		}

	}
}


