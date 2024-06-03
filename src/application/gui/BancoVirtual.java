package application.gui;

import static mos.io.InputOutput.*;
import static utilities.Constantes.*;
import static utilities.Util.*;
import static utilities.DatesAndTimes.*;
import static utilities.CPF.*;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import entities.ContaCorrente;
import entities.ContaPoupanca;
import entities.ContaRemunerada;
import entities.OperacaoBancaria;

import exception.BancoExceptions;
import entities.ContaBancaria;
import services.ContaBancariaList;
import services.Menu;

import static services.Menu.*;

import services.TipoOperacao;

public class BancoVirtual {

	private ContaBancariaList cbl;
	
	
	public BancoVirtual() {
		this.cbl = criarContasBancarias();
		menuPrincipal();
		
	}
		
	public static void main(String[] args) {
		new BancoVirtual();
	}
	
	public ContaBancariaList criarContasBancarias() {
		return new ContaBancariaList();
	}
	/**
	 * Método que representa o fluxo do programa, exibindo o menu e executando as opções escolhidas pelo usuário.
	 */
	public void menuPrincipal() {
		try {
			Menu opcao;
			do{
				opcao = criaMenu();
				switch(opcao) {
					case ABRIR_CONTA: abrirConta(); break;
					case DEPOSITO: depositar(); break;
					case SALDO:consultarSaldo(); break;
					case TRANSFERENCIA: transferir(); break;
					case SAQUE: realizarSaque(); break;
					case RELATORIO: exibirRelatorio(); break;
					default:  break;
				}
			} while (opcao != SAIR);
		}
		catch(BancoExceptions e) {
			showInfo(e.getMessage(), TITULO_ERRO);
		}
		finally {
			System.exit(0);
		}
	}
	
	private void exibirRelatorio() {
		getTextArea().setText(STRING_VAZIA);
		
		writeTextArea(String.format(STRING_CABECALHO, toStringData(LocalDate.now()), toStringHora(LocalTime.now())));
		writeTextArea(EMPTY_LINE);
		for (ContaBancaria conta: cbl.getContaBancariaList()) {
			OperacaoBancaria ob = new OperacaoBancaria(TipoOperacao.SALDO);
			cbl.executarOperacaoBancaria(conta, ob);
			writeTextArea(String.format("%s. %s\t\t%s\t%s\t%s\n", formataNumeroConta(conta), conta.getCliente().getName(), 
					toStringCPF(conta.getCliente().getCpf()), formatarReais(conta.getSaldo()), toStringData(conta.getOpenDate())));
			
		}
		showTextArea(getTextArea(), TITULO_MENU);
		
	}

	private void realizarSaque() {
		try {
			ContaBancaria conta = procuraConta();
			double valor = leDouble(MSG_SAQUE, TITULO_MOVIMENTACAO);
			OperacaoBancaria ob = new OperacaoBancaria(TipoOperacao.SAQUE, valor);
			cbl.executarOperacaoBancaria(conta, ob);
			showInfo(MSG_SAQUE_OK, TITULO_MOVIMENTACAO);
		}
		catch (Exception e){
			showInfo(e.getMessage(), TITULO_MOVIMENTACAO);
		}
	}

	private void transferir() {
		try {
			ContaBancaria conta = procuraConta();
			double valor = leDouble(MSG_VALOR_TRANSFERENCIA, TITULO_MOVIMENTACAO);
			OperacaoBancaria ob = new OperacaoBancaria(TipoOperacao.TRANSFERENCIA, valor);
			cbl.executarOperacaoBancaria(conta, ob);
			showInfo(MSG_TRANSFERENCIA_OK, TITULO_MOVIMENTACAO);
		}
		catch (Exception e){
			showInfo(e.getMessage(), TITULO_MOVIMENTACAO);
		}
	}
	
	private void abrirConta() {
		try {
			cbl.abrirContaBancaria();
			alteraDataAbertura(cbl.getContaBancariaList().getLast());
			
			showInfo(String.format(MSG_ABERTURA_OK, cbl.getContaBancariaList().getLast().getAgency(), formataNumeroConta(cbl.getContaBancariaList().
					getLast())), TITULO_MOVIMENTACAO);
		}
		catch (Exception e){
			showInfo(e.getMessage(), TITULO_MOVIMENTACAO);
		}
	}
	
	private void alteraDataAbertura(ContaBancaria conta) {
		if (requestConfirm(String.format(MSG_CONFIRMA_DATA, toStringData(conta.getOpenDate())), TITULO_ABERTURA_CONTA) == JOptionPane.YES_OPTION) {
			LocalDate data = converteData(readString(MSG_DATA, TITULO_ABERTURA_CONTA));
			if (data == null || data.isAfter(LocalDate.now())) {
				throw new BancoExceptions(ERRO_ENTRADA_DADOS);
			}
			conta.setOpenDate(data);
		}
	}
	
	private void depositar() {
		try {
			ContaBancaria conta = procuraConta();
			double valor = leDouble(MSG_DEPOSITO, TITULO_MOVIMENTACAO);
			OperacaoBancaria ob = new OperacaoBancaria(TipoOperacao.DEPOSITO, valor);
			
			if (cbl.executarOperacaoBancaria(conta, ob)) {
				showInfo(MSG_DEPOSTIO_OK, TITULO_MOVIMENTACAO);
			}
		}
		catch (Exception e){
			showInfo(e.getMessage(), TITULO_MOVIMENTACAO);
		}
	}
	
	private void consultarSaldo() {
		try {
			ContaBancaria conta = procuraConta();
			OperacaoBancaria ob = new OperacaoBancaria(TipoOperacao.SALDO);
			cbl.executarOperacaoBancaria(conta, ob);
			showInfo(String.format(MSG_SALDO, formatarReais(conta.getSaldo()),toStringData(LocalDate.now())), TITULO_MOVIMENTACAO);
		}
		catch (Exception e){
			showInfo(e.getMessage(), TITULO_MOVIMENTACAO);
		}
	}
	
	/**
     * Método para encontrar uma conta bancária com base na agência e no número da conta.
     *
     * @return a conta bancária encontrada
     */
	private ContaBancaria procuraConta() {
			Integer numeroAgencia = leInt(MSG_LER_NUMERO_AGENCIA, TITULO_MOVIMENTACAO);
			String numeroConta = leString(MSG_LER_NUMERO_CONTA, TITULO_MOVIMENTACAO);
		if (numeroAgencia == null || numeroConta == null) {
			throw new BancoExceptions(ERRO_ENTRADA_DADOS);
		}
		for (ContaBancaria conta: cbl.getContaBancariaList()) {
			if (formataNumeroConta(conta).equals(numeroConta) && conta.getAgency() ==  numeroAgencia) {
				return conta;
			}
		}
		return null;
	}
	
	/**
	 * Método que cria o menu principal do programa.
	 * @return a opção escolhida pelo usuário
	 */
	private Menu criaMenu() {
		int menu = menu(MSG_MENU, TITULO_MENU, criaVetorMenu(), 
				ABRIR_CONTA.toString());
		if (menu == JOptionPane.CLOSED_OPTION)
			return null;
		return Menu.values()[menu];
	}
	
    
    // Formata o número da conta bancária
    private static String formataNumeroConta(ContaBancaria conta) {
    	int numeroTipoConta = 0;
    	if (conta instanceof ContaCorrente) {
    		numeroTipoConta = 1;
    	}
    	else if (conta instanceof ContaRemunerada) {
    		numeroTipoConta = 2;
    	}
    	else if (conta instanceof ContaPoupanca) {
    		numeroTipoConta = 3;
    	}
    	else {
    		throw new BancoExceptions(ERRO_TIPO_CONTA_INEXISTENTE);
    	}
        return String.format(STRING_CONTA, numeroTipoConta , conta.getAccountNumber());
    }
    
    
	
}//class BancoVirtual
