package entites;

public final class ContaCorrente extends ContaBancaria {
	
	private static int createdAccounts;

	public ContaCorrente(Cliente cliente, int agency, int accountNumber, String password) {
		super(cliente, agency, accountNumber, password);
		createdAccounts ++;
	}

	public static final int getCreatedAccounts() {
		return createdAccounts;
	}
	
	


}//class ContaCorrente
