package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exception.BancoExceptions;
import static utilities.Constantes.*;

public class  ContaBancaria{
	
	public final int NUMBER;
	private int agency;
	private String password;
	private Double amount;
	private LocalDate openDate;
	private Cliente cliente;
	private List<OperacaoBancaria> operationList;
	
	private static int numberGenerator;
	
	public ContaBancaria() {
		this.operationList = new ArrayList<OperacaoBancaria>();
		this.NUMBER  = ++numberGenerator;
	}

	public ContaBancaria(Cliente cliente, int agency, String password) {
		this();
		this.cliente = cliente;
		this.agency = agency;
		this.password = password;
		this.openDate = LocalDate.now();
	}
	
	public ContaBancaria(Cliente cliente, int agency, String password, double amount) {
		this(cliente, agency, password);
		this.amount = amount;
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
		return NUMBER;
	}

	public final Cliente getCliente() {
		return cliente;
	}

	public final List<OperacaoBancaria> getOperationList() {
		return operationList;
	}
	
	public void deposito (double amount) {
		if (amount < 0) {
			throw new BancoExceptions(ERRO_DEPOSITO_NEGATIVO);
		}
		this.amount += amount;
	}
	
	public double getSaldo () {
		return amount;
	}
	
	public void saque (double amount) {
		if (this.amount < amount) {
			throw new BancoExceptions(ERRO_SALDO_INSUFICIENTE);
		}
		this.amount -= amount;
	}
	
	public void transferencia (ContaBancaria target, double amount) {
		saque(amount);
		target.deposito(amount);
	}
	
	
	
}//class ContaBancaria
