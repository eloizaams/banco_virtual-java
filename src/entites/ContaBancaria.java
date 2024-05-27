package entites;

import java.time.LocalDate;

public class  ContaBancaria{
	
	private int agency, accountNumber;
	private String password;
	private Double amount;
	private LocalDate openDate;
	
	public ContaBancaria(int agency, int accountNumber, String password) {
		this.agency = agency;
		this.accountNumber = accountNumber;
		this.password = password;
		this.openDate = LocalDate.now();
	}

	public final LocalDate getOpenDate() {
		return openDate;
	}

	public final void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public final int getAgency() {
		return agency;
	}

	public final int getAccountNumber() {
		return accountNumber;
	}

	public final Double getAmount() {
		return amount;
	}
	
	
	
}//class ContaBancaria
