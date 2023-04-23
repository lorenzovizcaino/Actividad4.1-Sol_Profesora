/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao.account;

import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.oid.OIDFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

import modelo.Account;
import modelo.dao.AbstractGenericDao;
import modelo.exceptions.InstanceNotFoundException;
import util.ConnectionFactory;
import util.Utils;

/**
 *
 * @author mfernandez
 */
public class AccountNeoDatisDao extends AbstractGenericDao<Account> implements IAccountDao {

	private ODB dataSource;

	public AccountNeoDatisDao() {
		this.dataSource = ConnectionFactory.getConnection();
	}

	@Override
	public long create(Account entity) {
		OID oid = null;
		long oidlong = -1 ;
		try {

			oid = this.dataSource.store(entity);
			this.dataSource.commit();
			System.out.println("Creado un objeto "+
			getEntityClass()+ " con oid: " + oid.getObjectId());

		} catch (Exception ex) {

			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
			this.dataSource.rollback();
			oid = null;
		}
		if (oid != null) {
			oidlong = oid.getObjectId();
		}
		return oidlong;
	}

	@Override
	public Account read(long id) throws InstanceNotFoundException {
		Account account = null;
		try {
			OID oid = OIDFactory.buildObjectOID(id);
			account = (Account) this.dataSource.getObjectFromId(oid);
		} catch (ODBRuntimeException ex) {

			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
//Suponemos que no lo encuentra
			throw new InstanceNotFoundException(id, getEntityClass());
		} catch (Exception ex) {

			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

		}
		return account;
	}

	@Override
	public boolean update(Account entity) {
		boolean exito = false;
		try {
			this.dataSource.store(entity);
			this.dataSource.commit();
			exito = true;
		} catch (Exception ex) {
			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
			this.dataSource.rollback();

		}
		return exito; // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}



	@Override
	public boolean delete(Account entity) {
		boolean exito = false;
		try {
			OID oid = this.dataSource.delete(entity);
			System.out.println("El oid del objeto eliminado es: " + oid.getObjectId());
			this.dataSource.commit();
			exito = true;
		} catch (Exception ex) {
			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
			this.dataSource.rollback();

		}
		return exito;
	}

	@Override
	public List<Account> findAll() {

		CriteriaQuery query = new CriteriaQuery(Account.class);
		IQuery iquery = query.orderByAsc("accountno");
		Objects<Account> accounts = dataSource.getObjects(iquery);
		return Utils.toList(accounts);
	}

	@Override
	public List<Account> getAccountsByEmpno(Integer empno) {
		CriteriaQuery query = new CriteriaQuery(Account.class, 
				Where.equal("emp.empno", empno));
		Objects<Account> accounts = dataSource.getObjects(query);
		return Utils.toList(accounts);
	}

	@Override
	public List<Object> getValues() {
		List<Object> objList = new ArrayList<>();
		Object object = null;
		Values values = this.dataSource.getValues(new 
				ValuesCriteriaQuery(Account.class)
				.field("emp.empno")
				.field("emp.ename")
				.field("amount")
				.field("accountno"));
		
		int counter=0;
		for (ObjectValues objectValue : values) {
			counter=0;
			//comentar
//			objList.add(objectValue.getByIndex(counter++));
//			objList.add(objectValue.getByIndex(counter++));
//			objList.add(objectValue.getByIndex(counter++));
//			objList.add(objectValue.getByIndex(counter++));
			
			objList.add(objectValue);
		
		}


		return objList;
	}
	

	public List<Object[]> getValues2() {
		List<Object[]> objList = new ArrayList<>();
		Object[] datosCuenta =new Object[4];
		
		Values values = this.dataSource.getValues(new 
				ValuesCriteriaQuery(Account.class)
				.field("emp.empno")
				.field("emp.ename")
				.field("amount")
				.field("accountno"));
		
		int counter=0;
		for (ObjectValues objectValue : values) {
			counter=0;
	
			datosCuenta[counter]= objectValue.getByIndex(counter++);
			datosCuenta[counter]= objectValue.getByIndex(counter++);
			datosCuenta[counter]= objectValue.getByIndex(counter++);
			datosCuenta[counter]= objectValue.getByIndex(counter++);			

			objList.add(datosCuenta);
		
		}


		return objList;
	}
	
	
	
	
	
}
