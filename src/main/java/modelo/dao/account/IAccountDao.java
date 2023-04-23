/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo.dao.account;


import java.util.List;

import modelo.Account;
import modelo.dao.IGenericDao;

/**
 *
 * @author mfernandez
 */
public interface IAccountDao extends IGenericDao<Account>{
    
	List<Account> getAccountsByEmpno(Integer empno);
	
	List<Object> getValues();
	
	List<Object[]> getValues2();
	
}
