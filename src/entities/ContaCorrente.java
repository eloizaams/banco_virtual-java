package entities;

public final class ContaCorrente extends ContaBancaria {
	
	private static int createdAccounts;

	public ContaCorrente(Cliente cliente, int agency, String password) {
		super(cliente, agency, password);
		createdAccounts ++;
	}

	public static final int getCreatedAccounts() {
		return createdAccounts;
	}
	
	


}//class ContaCorrente
