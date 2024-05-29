package application.gui;

import static mos.io.InputOutput.*;
import static utilities.Constantes.*;
import static utilities.Util.*;
import static services.TipoOperacao.*;
import static services.TipoConta.*;
import static utilities.CPF.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import entities.Cliente;
import entities.ContaCorrente;
import entities.ContaPoupanca;
import entities.ContaRemunerada;
import static entities.OperacaoBancaria.*;
import exception.BancoExceptions;
import entities.ContaBancaria;
import services.ContaBancariaList;
import services.Menu;

import static services.Menu.*;

import static services.Menu.*;
import services.TipoConta;


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
		Menu opcao;
		while (true) {
			opcao = criaMenu();
			switch(opcao) {
				case ABRIR_CONTA: abrirConta(); break;
				case DEPOSITO: depositar(); break;
				case SALDO:consultarSaldo(); break;
				case TRANSFERENCIA: transferir(); break;
				case SAQUE: realizarSaque(); break;
				case RELATORIO: exibirRelatorio(); break;
				default: System.exit(0); break;
			}
		} 
	}
	
	private void exibirRelatorio() {
		// TODO Auto-generated method stub
		
	}

	private void realizarSaque() {
		// TODO Auto-generated method stub
		
	}

	private void transferir() {
		// TODO Auto-generated method stub
		
	}

	private void consultarSaldo() {
		// TODO Auto-generated method stub
		
	}

	private void depositar() {
		
		
	}

	private void abrirConta() {
		cbl.abrirContaBancaria();
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
    public static String formataNumeroConta(ContaBancaria conta) {
    	int numeroTipoConta = 0;
    	if (conta instanceof ContaCorrente) {
    		numeroTipoConta = 1;
    	}
    	else if (conta instanceof ContaPoupanca) {
    		numeroTipoConta = 2;
    	}
    	else if (conta instanceof ContaRemunerada) {
    		numeroTipoConta = 3;
    	}
    	else {
    		throw new BancoExceptions(ERRO_TIPO_CONTA_INEXISTENTE);
    	}
        return String.format(STRING_CONTA, numeroTipoConta , conta.getAccountNumber());
    }
    
    
}//class BancoVirtual
