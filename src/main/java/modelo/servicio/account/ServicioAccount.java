package modelo.servicio.account;

import java.util.List;

import modelo.Account;
import modelo.dao.account.AccountNeoDatisDao;
import modelo.dao.account.IAccountDao;

public class ServicioAccount implements IServicioAccount {
	
	private IAccountDao accountDao;
	
	public ServicioAccount() {
		accountDao= new AccountNeoDatisDao();
	}

	@Override
	public long createAccount(Account account) {
		return accountDao.create(account);
	}

	@Override
	public List<Account> findAll() {
		return accountDao.findAll();
	}

	@Override
	public List<Account> findByEmpno(Integer empno) {
		return accountDao.getAccountsByEmpno(empno);
	}

	@Override
	public List<Object> getValues() {
		
		return accountDao.getValues();
	}
	
	@Override
	public List<Object[]> getValues2() {
		
		return accountDao.getValues2();
	}

	

	
}
