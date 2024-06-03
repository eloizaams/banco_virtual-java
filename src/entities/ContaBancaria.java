package entities;

import java.time.LocalDate;

import exception.BancoExceptions;

import static mos.io.InputOutput.readString;
import static utilities.Constantes.*;

public class  ContaBancaria{
	
	public final int NUMBER;
	private int agency;
	private String password;
	private Double amount;
	private LocalDate openDate;
	private Cliente cliente;
	
	
	private static int numberGenerator;
	
	public ContaBancaria() {
		this.amount = ZERO;
	
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

	
	public void deposito (double amount) {
		if (amount < 0) {
			throw new BancoExceptions(ERRO_DEPOSITO_NEGATIVO);
		}
		this.amount += amount;
	}
	
	public double getSaldo () {
		/*if (!verificaSenha()) {
			throw new BancoExceptions(ERRO_SENHA_ERRADA);
		}*/
		return amount;
	}
	
	public void saque (double amount) {
		if (!verificaSenha()) {
			throw new BancoExceptions(ERRO_SENHA_ERRADA);
		}
		if (this.amount < amount) {
			throw new BancoExceptions(ERRO_SALDO_INSUFICIENTE);
		}
		this.amount -= amount;
	}
	
	public void transferencia (ContaBancaria target, double amount) {
		saque(amount);
		target.deposito(amount);
	}
	
	private boolean verificaSenha() {
		String senha = readString(MSG_SENHA, TITULO_MOVIMENTACAO);
		if (senha.equals(getPassword())) {
			return true;
		}
		return false;
	}
	
}//class ContaBancaria
