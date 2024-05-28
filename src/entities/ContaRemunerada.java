package entites;

public final class ContaRemunerada extends ContaBancaria {
	
	private double dailyIterestRate;

	public ContaRemunerada(Cliente cliente, int agency, int accountNumber, String password) {
		super(cliente, agency, accountNumber, password);
		
	}

	public ContaRemunerada(Cliente cliente, int agency, int accountNumber, String password, double dailyIterestRate) {
		super(cliente, agency, accountNumber, password);
		this.dailyIterestRate = dailyIterestRate;
	}

	public final double getDailyIterestRate() {
		return dailyIterestRate;
	}

	public final void setDailyIterestRate(double dailyIterestRate) {
		this.dailyIterestRate = dailyIterestRate;
	}
	
}//class ContaRemunerada
