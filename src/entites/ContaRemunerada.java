package entites;

public final class ContaRemunerada extends ContaBancaria {
	
	private double dailyIterestRate;

	public ContaRemunerada(int agency, int accountNumber, String password) {
		super(agency, accountNumber, password);
		
	}

	public ContaRemunerada(int agency, int accountNumber, String password, double dailyIterestRate) {
		super(agency, accountNumber, password);
		this.dailyIterestRate = dailyIterestRate;
	}

	public final double getDailyIterestRate() {
		return dailyIterestRate;
	}

	public final void setDailyIterestRate(double dailyIterestRate) {
		this.dailyIterestRate = dailyIterestRate;
	}
	
}//class ContaRemunerada
