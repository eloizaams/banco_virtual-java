package entities;

public final class ContaRemunerada extends ContaBancaria {
	
	private double dailyInterestRate;

	public ContaRemunerada(Cliente cliente, int agency,  String password) {
		super(cliente, agency,  password);
	}

	public ContaRemunerada(Cliente cliente, int agency, String password, double dailyInterestRate) {
		this(cliente, agency,  password);
		this.dailyInterestRate = dailyInterestRate;
	}
	
	public ContaRemunerada(Cliente cliente, int agency, String password, double amount, double dailyInterestRate) {
		super(cliente, agency, password, amount);
		this.dailyInterestRate = dailyInterestRate;
	}
	
	public final double getDailyInterestRate() {
		return dailyInterestRate;
	}

	public final void setDailyInterestRate(double dailyIterestRate) {
		this.dailyInterestRate = dailyIterestRate;
	}

	
	
}//class ContaRemunerada
