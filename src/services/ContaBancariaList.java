package services;

import static mos.io.InputOutput.*;
import static mos.io.InputOutput.readInt;
import static services.TipoConta.CORRENTE;
import static services.TipoConta.POUPANCA;
import static services.TipoConta.REMUNERADA;
import static services.TipoConta.criaVetorTipoConta;
import static utilities.Constantes.*;

import static utilities.DatesAndTimes.diasUteis;
import static utilities.DatesAndTimes.mesesEntre;
import static utilities.Util.*;
import static utilities.CPF.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import entities.Cliente;
import entities.ContaBancaria;
import entities.ContaCorrente;
import entities.ContaPoupanca;
import entities.ContaRemunerada;
import entities.OperacaoBancaria;
import exception.BancoExceptions;

public class ContaBancariaList{
	
	private List<ContaBancaria> contaBancariaList;
	private List<Object[]> operacaoBancariaList;

	public ContaBancariaList() {
		this.contaBancariaList = new ArrayList<ContaBancaria>();
		this.operacaoBancariaList = new ArrayList<Object[]>();
	}
	
	public final List<ContaBancaria> getContaBancariaList() {
		return contaBancariaList;
	}

	public final List<Object[]> getOperacaoBancariaList() {
		return operacaoBancariaList;
	}

	public void abrirContaBancaria() {
		Cliente cliente = criarCliente();
		String senha = criarSenha();
		int agencia = readInt(MSG_AGENCIA, TITULO_DADOS_CLIENTE);
		ContaBancaria conta = null; 
		TipoConta tipoConta = TipoConta.values()[menu(MSG_CONTA_TIPO, TITULO_ABERTURA_CONTA, criaVetorTipoConta(),CORRENTE.toString())];
		
		switch (tipoConta) {
			case CORRENTE: 
				conta = abrirContaCorrente(cliente,agencia,senha); 
				break;
			case REMUNERADA:
				double valorInicialRemunerada = leDouble(MSG_DEPOSITO_INICIAL_REMUNERADA, TITULO_ABERTURA_CONTA);
				conta = abrirContaRemunerada(cliente,agencia,senha, valorInicialRemunerada, TAXA_REMUNERADA_AO_DIA); 
				break;
			
			case POUPANCA: 
				double valorInicialPoupanca = leDouble(MSG_DEPOSITO_INICIAL_POUPANCA, TITULO_ABERTURA_CONTA);
				conta = abrirContaPoupanca(cliente,agencia,senha, valorInicialPoupanca, TAXA_POUPANCA_AO_MES); 
				break;
			
			default: break;
		}
		if (conta == null) {
			throw new BancoExceptions(ERRO_ABERTURA_CONTA);
		}
		contaBancariaList.add(conta);
	}
	
	private ContaRemunerada abrirContaRemunerada (Cliente cliente, int agencia, String senha, Double valorDeposito,  Double taxaRendimento) {
		if (valorDeposito < VALOR_MINIMO_CONTA_REMUNRADA) {
			throw new BancoExceptions(ERRO_SALDO_INICIAL_REMUNERADA);
		}
		
		return new ContaRemunerada(cliente, agencia, senha, valorDeposito, taxaRendimento);
	}
	
	private ContaPoupanca abrirContaPoupanca(Cliente cliente, int agencia, String senha, Double valorDeposito,  Double taxaRendimento) {
		if (valorDeposito < VALOR_MINIMO_CONTA_POUPANCA) {
			throw new BancoExceptions(ERRO_SALDO_INICIAL_POUPANCA);
		}
		
		return new ContaPoupanca(cliente, agencia, senha, valorDeposito, taxaRendimento);
	}

	private ContaCorrente abrirContaCorrente (Cliente cliente, int agencia, String senha ) {
		return new ContaCorrente(cliente, agencia, senha);
	}
	
	private Cliente criarCliente() {
		return new Cliente(formatarNome(leString(NOME, TITULO_DADOS_CLIENTE)), leCpf(MSG_CPF, TITULO_DADOS_CLIENTE));
	}
	
	private String criarSenha() {
		String senha;
		do {
			senha = readString(SENHA, TITULO_ABERTURA_CONTA);
		}
		while (senha!=null && !validaSenha(senha));
		return senha;
	}

	// Valida a senha de no mínimo 8 caracteres composta por número, letra maiúscula, letra minúscula e caractere especial
    public static boolean validaSenha(String senha) {
        // Verifica se a senha tem no mínimo 8 caracteres
        if (senha.length() < 8) {
            return false;
        }

        // Verifica se a senha contém pelo menos uma letra maiúscula
        if (!Pattern.compile(STRING_MAIUSCULAS).matcher(senha).find()) {
            return false;
        }

        // Verifica se a senha contém pelo menos uma letra minúscula
        if (!Pattern.compile(STRING_MINUSCULAS).matcher(senha).find()) {
            return false;
        }

        // Verifica se a senha contém pelo menos um caractere especial
        if (!Pattern.compile(STRING_CARCACTERES).matcher(senha).find()) {
            return false;
        }

        // Verifica se a senha contém pelo menos um número
        if (!Pattern.compile(STRING_NUMEROS).matcher(senha).find()) {
            return false;
        }

        // Se todas as condições forem atendidas, retorna true
        return true;
    }
    
	public void adicionaOperacaoBancaria(ContaBancaria conta, OperacaoBancaria ob) {
		if (!contaBancariaList.contains(conta)) {
			throw new BancoExceptions(ERRO_CONTA_INEXISTENTE);
		}
		Object[] object = {conta, ob};
		operacaoBancariaList.add(object);
	}
	

	public boolean executarOperacaoBancaria(ContaBancaria conta, OperacaoBancaria ob) {
		double rendimento = calculaRendimento(conta);
		if (rendimento > 0) {
			OperacaoBancaria deposito = new OperacaoBancaria(TipoOperacao.DEPOSITO,rendimento);
			executarOperacaoDeposito (conta, deposito);
			adicionaOperacaoBancaria(conta,deposito);
		}
		switch(ob.getType()) {
			case SALDO: {
				executarOperacaoSaldo (conta, ob); 
				break;
			}
			case DEPOSITO: {
				executarOperacaoDeposito (conta, ob); 
				break;
			}
			case TRANSFERENCIA: {
				if (!(conta instanceof ContaCorrente)) {
					throw new BancoExceptions(ERRO_TIPO_CONTA);
				}
				executarOperacaoTransferencia (conta, ob); 
				break;
			}
			case SAQUE: {
				executarOperacaoSaque(conta, ob); 
				break;
			}
			default: return false;	
		}
		adicionaOperacaoBancaria(conta,ob);
		return true;
	}
	
	private void executarOperacaoSaque(ContaBancaria conta, OperacaoBancaria ob) {
		if (conta.getSaldo() < ob.getAmount()) {
			throw new BancoExceptions(ERRO_SALDO_INSUFICIENTE);
		}
		conta.saque(ob.getAmount());
	}

	private void executarOperacaoTransferencia(ContaBancaria conta, OperacaoBancaria ob) {
		int opcao = menu(MSG_CONTA_TIPO, TITULO_MOVIMENTACAO, new String[]{REMUNERADA.toString(),POUPANCA.toString()},REMUNERADA.toString());
		ContaBancaria destino = null;
		
		for(ContaBancaria cb: contaBancariaList) {
			if (opcao == 0 && cb instanceof ContaRemunerada && conta.getCliente().getCpf().equals(cb.getCliente().getCpf())) {
				destino = cb;
				break;
			}
			if (opcao == 1 && cb instanceof ContaPoupanca && conta.getCliente().getCpf().equals(cb.getCliente().getCpf())) {
				destino = cb;
				break;
			}
		}
		
		if (destino == null) {
			throw new BancoExceptions(ERRO_CONTA_INEXISTENTE);
		}
		
		conta.transferencia(destino,ob.getAmount());
	}

	private void executarOperacaoDeposito(ContaBancaria conta, OperacaoBancaria ob) {
		conta.deposito(ob.getAmount());
	}

	private double executarOperacaoSaldo (ContaBancaria conta, OperacaoBancaria ob) {
		if (!contaBancariaList.contains(conta)) {
			throw new BancoExceptions(ERRO_CONTA_INEXISTENTE);
		}
				
		return conta.getSaldo();
	}

	private List<OperacaoBancaria> obterOperacoesConta(ContaBancaria conta){
		List<OperacaoBancaria> obList = new ArrayList<>();
		for(Object[] obj: operacaoBancariaList) {
			if (obj[0].equals(conta)) {
				obList.add((OperacaoBancaria)obj[1]);
			}
		}
		return obList;
	}
	
	private double calculaRendimento(ContaBancaria conta) {
		if (conta instanceof ContaCorrente) {
			return ZERO;
		}
		List<OperacaoBancaria> obList = obterOperacoesConta(conta);
		LocalDate ultimaAtualizacao;
		//Verifica se lista de operacões bancárias da conta está vazia
		if (obList.size() == ZERO_INT) {
			ultimaAtualizacao = conta.getOpenDate();
		}
		else {
			ultimaAtualizacao = obList.getLast().getDate();
		}
		double valor = conta.getSaldo(); 
		double rate = 0;
		int periodo = 0;
		
		if (conta instanceof ContaPoupanca) {
			rate = ((ContaPoupanca)conta).getMonthlyInterestRate();
			periodo =  mesesEntre(ultimaAtualizacao, LocalDate.now());
		}
		
		if (conta instanceof ContaRemunerada) {
			rate = ((ContaRemunerada)conta).getDailyInterestRate();
			periodo =  diasUteis(ultimaAtualizacao, LocalDate.now());
		}
		for (int i = 0; i < periodo; i++) { 
			valor += valor*rate;
		}
		return valor - conta.getSaldo();
	}
	

	
}//class ContaBancariaList