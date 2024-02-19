package in.ineuron.daofactory;

import in.ineuron.dao.BankingDaoImpl;
import in.ineuron.dao.IBankingDao;

public class BankingDaoFactory {

	private BankingDaoFactory(){};
	
	public static IBankingDao bankingDao=null;
	
	public static IBankingDao getBankingDao() {
		if(bankingDao==null) {
			return new BankingDaoImpl();
		}
		return bankingDao;
	}
}
