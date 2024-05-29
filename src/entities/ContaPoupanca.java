package entities;

public final class ContaPoupanca extends ContaBancaria {
	
	private double monthlyInterestRate;

	public ContaPoupanca(Cliente cliente, int agency,  String password) {
		super(cliente, agency,  password);
	}

	public ContaPoupanca(Cliente cliente, int agency, String password, double amount, double monthlyInterestRate) {
		super(cliente, agency, password, amount);
		this.monthlyInterestRate = monthlyInterestRate;
	}

	public ContaPoupanca(Cliente cliente, int agency, String password, double monthlyInterestRate) {
		this(cliente, agency, password);
		this.monthlyInterestRate = monthlyInterestRate;
	}

	public final double getMonthlyInterestRate() {
		return monthlyInterestRate;
	}

	public final void setMonthlyInterestRate(double monthlyInterestRate) {
		this.monthlyInterestRate = monthlyInterestRate;
	}
	


}
