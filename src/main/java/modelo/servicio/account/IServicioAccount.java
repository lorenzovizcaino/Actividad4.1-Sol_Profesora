package modelo.servicio.account;


import java.util.List;

import modelo.Account;

public interface IServicioAccount {

	public long createAccount(Account account) ;
	
//	public boolean deleteEmpleado(Empleado empleado);
//	public boolean updateEmpleado(Empleado empleado);
//	public Empleado findByOID(long longOid) throws InstanceNotFoundException ;
//	
	public List<Account> findAll();
//
	public List<Account> findByEmpno(Integer empno);
	
	public List<Object> getValues();
	public List<Object[]> getValues2();
//	
//
//	float findAvgSalary();
//	
//	List<Empleado> findEmployeesByHireDate(int yearFrom, int monthFrom, int dayFrom, int yearTo, int monthTo, int dayTo);
//	
//	boolean exists(Integer empno);
	
}


