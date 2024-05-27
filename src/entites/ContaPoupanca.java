package entites;

public final class ContaPoupanca extends ContaBancaria {
	
	private double monthlyInterestRate;

	public ContaPoupanca(Cliente cliente, int agency, int accountNumber, String password) {
		super(cliente, agency, accountNumber, password);
	}

	public ContaPoupanca(Cliente cliente, int agency, int accountNumber, String password, double monthlyInterestRate) {
		super(cliente, agency, accountNumber, password);
		this.monthlyInterestRate = monthlyInterestRate;
	}

	public final double getMonthlyInterestRate() {
		return monthlyInterestRate;
	}

	public final void setMonthlyInterestRate(double monthlyInterestRate) {
		this.monthlyInterestRate = monthlyInterestRate;
	}
	
	

}
