package entites;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import services.OperacaoBancaria;

public class  ContaBancaria{
	
	private int agency, accountNumber;
	private String password;
	private Double amount;
	private LocalDate openDate;
	private List<OperacaoBancaria> operationList;
	
	public ContaBancaria() {
		this.operationList = new ArrayList<OperacaoBancaria>();
	}

	public ContaBancaria(int agency, int accountNumber, String password) {
		this();
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
	
	public final String getPassword() {
		return password;
	}

	public final int getAccountNumber() {
		return accountNumber;
	}

	public final Double getAmount() {
		return amount;
	}

	public final List<OperacaoBancaria> getOperationList() {
		return operationList;
	}
	
	
	
	
}//class ContaBancaria
