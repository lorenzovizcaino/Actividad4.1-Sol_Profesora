package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Empleado;

import modelo.exceptions.InstanceNotFoundException;
import modelo.servicio.empleado.ServicioEmpleado;
import modelo.servicio.empleado.IServicioEmpleado;
import util.ConnectionFactory;
import util.Utils;

public class MainEmpleado {

	private static IServicioEmpleado empleadoServicio = new ServicioEmpleado();

	public static void main(String[] args) {

		

		 createEmpleados();

		listar();

		float media = empleadoServicio.findAvgSalary();
		System.out.println("La media del salario es: " + media);

		filtrarPorFechas(2014, 8, 1, 2022, 1, 1);
		filtrarPorEmpleo("Contable");

		long oid = 6;
		Empleado actualizado = updateEmpleado(oid);

		if (actualizado != null) {
			eliminarEmpleado(actualizado);
		}
		
		listar();
		

		ConnectionFactory.closeConnection();

	}
	
	public static void listar() {
		List<Empleado> emps = new ArrayList<>();
		

		emps = empleadoServicio.findAll();

		System.out.println("Se han encontrado: " + emps.size() + " empleados");

		for (Empleado empleado : emps) {
			System.out.println("El empleado recuperado es: " + empleado);
		}
	}

	private static void createEmpleados() {

		List<Empleado> empleados = new ArrayList<>();

		Empleado empleado1 = createEmpleado(1, "Juan", "Contable", 30000, 0.15f, 2020, 2, 2);
		Empleado empleado2 = createEmpleado(2, "Lucía", "Jefatura de contabilidad", 50000, 0.25f, 2015, 5, 5);
		empleado1.setJefe(empleado2);
		empleado2.getSubordinados().add(empleado1);

		Empleado empleado3 = createEmpleado(3, "Gonzalo", "Recursos Humanos", 25000, 0.1f, 2018, 8, 1);

		Empleado empleado4 = createEmpleado(4, "Olga", "Jefatura de RR.HH.", 45000, 0.25f, 2010, 01, 02);
		empleado3.setJefe(empleado4);
		empleado4.getSubordinados().add(empleado3);
		try {
			empleados.add(empleado2);
			empleados.add(empleado1);
			empleados.add(empleado4);
			empleados.add(empleado3);

			for (Empleado empleado : empleados) {
				empleadoServicio.createEmpleado(empleado);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Ha ocurrido una exception " + e.getMessage());
		}

	}

	private static Empleado createEmpleado(Integer empno, String ename, String job, float sal, float com, int year,
			int month, int day) {
		Empleado empleado = new Empleado();
		empleado.setEmpno(empno);
		empleado.setEname(ename);
		empleado.setJob(job);
		empleado.setSal(sal);
		empleado.setComm(com);

		Date date = Utils.toDate(year, month, day);
		empleado.setHiredate(date);

		return empleado;
	}

	private static void filtrarPorFechas(int yearFrom, int monthFrom, int dayFrom, int yearTo, int monthTo, int dayTo) {
		// month 0 is January
		List<Empleado> empleadosFechas = empleadoServicio.findEmployeesByHireDate(yearFrom, monthFrom, dayFrom, yearTo,
				monthTo, dayTo);
		for (Empleado empleado : empleadosFechas) {
			System.out.println("Empleados entre fechas: " + empleado);
		}
	}

	private static void filtrarPorEmpleo(String puesto) {
		List<Empleado> foundList = empleadoServicio.findByJob(puesto);
		for (Empleado empleado : foundList) {
			System.out.println("El empleado recuperado by Job es: " + empleado);

		}
	}

	private static void eliminarEmpleado(Empleado empleado) {

		empleadoServicio.deleteEmpleado(empleado);
		if (empleadoServicio.exists(empleado.getEmpno())) {
			System.out.println("¡ERROR! No se ha eliminado el "
					+ "empleado con empno: " + empleado.getEmpno());
		} else {
			System.out.println("El empleado eliminado es: " + empleado);
		}

	}

	private static Empleado updateEmpleado(long oid) {

		Empleado actualizado = null;
		try {

			Empleado buscado = empleadoServicio.findByOID(oid);

			if (buscado != null) {
				System.out.println("Se ha encontrado el empleado con oid: " + oid + " " + buscado);

				// Update
				buscado.setJob("Secretario");
				empleadoServicio.updateEmpleado(buscado);
				actualizado = empleadoServicio.findByOID(oid);
				System.out.println("Empleado actualizado: " + actualizado);

			}
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.err.println("Ha ocurrido una excepción: No se ha encontrado el empleado con OID: " + oid);

		}

		return actualizado;
	}

}
