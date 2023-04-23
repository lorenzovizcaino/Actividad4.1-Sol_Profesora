package main;

import java.util.ArrayList;
import java.util.List;

import modelo.Account;
import modelo.Empleado;
import modelo.servicio.account.IServicioAccount;
import modelo.servicio.account.ServicioAccount;
import modelo.servicio.empleado.IServicioEmpleado;
import modelo.servicio.empleado.ServicioEmpleado;

public class MainAccount {
	

	private static IServicioEmpleado empleadoServicio = new ServicioEmpleado();
	
	private static IServicioAccount accountServicio = new ServicioAccount();
	
	
	public static void main(String[] args) {
		
//		MainEmpleado.listar();
//		
//		crearCuentas();
		
//		listarCuentas();
//		
//		Integer empno= 2;
//		findByEmpno(empno);
		
		getValues();
		
		
	}
	
	private static void getValues() {
		System.out.println("Se han recuperado los siguientes valores:");
		
		for(Object o :accountServicio.getValues()) {
			System.out.println("Se ha recuperado: " +o);
			//System.out.println("Se ha recuperado: " +Arrays.toString(o));
		}
	}
	
	private static void findByEmpno(Integer empno) {
		List<Account> accounts = accountServicio.findByEmpno(empno);
		System.out.println("Se han encontrado: " + accounts.size() + " cuentas para el empno: " + empno);
		for (Account account : accounts) {
			System.out.println("Account: " + account);
		}
	}
	
	private static void crearCuentas() {
		
		
		List<Empleado> empleados = empleadoServicio.findAll();
		int counter= 0;
		for (Empleado empleado : empleados) {
			addAccountToEmpleado(empleado, ++counter);
			addAccountToEmpleado(empleado, ++counter);
		}
	}
	
	private static void addAccountToEmpleado(Empleado emp, int accountNo) {
		Account account=null;
		account = new Account(emp, (float)(Math.random()*1000));
		emp.getAccounts().add(account);
		account.setAccountno(accountNo);
		accountServicio.createAccount(account);
		
	}
	
	private static void listarCuentas() {
		List<Account> accounts = new ArrayList<>();
		

		accounts = accountServicio.findAll();

		System.out.println("Se han encontrado: " + accounts.size() + " cuentas");

		for (Account acc : accounts) {
			System.out.println("La cuenta recuperada es: " + acc);
		}
	}

	
}
