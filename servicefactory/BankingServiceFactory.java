package in.ineuron.servicefactory;

import in.ineuron.service.BankingServiceImpl;
import in.ineuron.service.IBankingService;

public class BankingServiceFactory {

	private BankingServiceFactory() {};
	
	public static IBankingService bankingservice=null;
	
	public static IBankingService getBankingService() {
		if(bankingservice==null) {
			return new BankingServiceImpl();
		}
		return bankingservice;
	}
	
}
