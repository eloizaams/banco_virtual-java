package utilities;

/**
 * Interface que contém constantes utilizadas no sistema do banco virtual.
 */
public interface Constantes {

    // Constantes de texto
    String
            ERRO_ABERTURA_CONTA = "A conta não pode ser aberta, verifique o valor mínimo.",
            ERRO_CONTA_INEXISTENTE = "Conta inexistente",
            ERRO_DEPOSITO_NEGATIVO = "O valor para depósito deve ser maior que zero.",
            ERRO_ENTRADA_DADOS = "Erro na entrada de dados",
            ERRO_SALDO_INICIAL_POUPANCA = "Saldo inicial para abertura de conta remunerada deve ser maior que 1,00",
            ERRO_SALDO_INICIAL_REMUNERADA = "Saldo inicial para abertura de conta remunerada deve ser maior que 100,00",
            ERRO_SALDO_INSUFICIENTE = "Saldo insuficiente para realizar a operação",
            ERRO_SENHA_ERRADA = "Senha errada",
            ERRO_TIPO_CONTA = "Somente conta corrente pode realizar transferência",
            ERRO_TIPO_CONTA_INEXISTENTE = "Tipo da conta não encontrado",
            FORMATA_DATA = "dd/MM/yyyy",
            FORMATA_HORA = "HH:mm",
            MSG_AGENCIA = "Número da agência",
            MSG_ABERTURA_OK = "Conta aberta com sucesso!\nAgencia: %d\nConta: %s",
            MSG_CPF = "Digite um CPF válido",
            MSG_CONFIRMA_DATA = "Data de abertura da conta: %s. Deseja alterar?",
            MSG_CONTA_TIPO = "Escolha o tipo da conta",
            MSG_DATA = "Nova data: (dd/mm/aaaa)",
            MSG_DEPOSITO = "Valor a ser depositado",
            MSG_DEPOSITO_INICIAL_POUPANCA = "Valor a ser depositado: (Conta Poupança: mínimo R$1,00)",
            MSG_DEPOSITO_INICIAL_REMUNERADA = "Valor a ser depositado: (Conta Remunerada: mínimo R$100,00)",
            MSG_DEPOSTIO_OK = "Deposito realizado com sucesso!",
            MSG_LER_NUMERO_AGENCIA = "Agencia:",
            MSG_LER_NUMERO_CONTA = "Número da conta:",
            MSG_MENU = "Bem-vindo ao BANCO VIRTUAL X",
            MSG_SAQUE = "Valor a ser sacado",
            MSG_SAQUE_OK = "Saque realizado com sucesso!",
            MSG_SALDO = "Saldo: %s em %s",
            MSG_SENHA = "Senha:",
            MSG_TRANSFERENCIA_OK = "Transferência realizada com sucesso!",
            MSG_VALOR_TRANSFERENCIA = "Valor a ser transferido",
            NOME = "Nome",
            SENHA = "Digite uma senha de no mínimo 8 caracteres composta por número, letra maiúscula, letra minúscula "
                    + "e caractere especial ",
            STRING_CABECALHO = "Data: %s\nHora: %s",
            STRING_CARCACTERES = "[!@#$%^&*()\\-+./&?]",
            STRING_CONTA = "%d-%04d",
            STRING_MAIUSCULAS = "[a-z]",
            STRING_MINUSCULAS = "[A-Z]", 
            STRING_NAO_NUMEROS = "[^0-9]",
            STRING_NUMEROS = "[0-9]",
            STRING_VAZIA = "",
            TITULO_ABERTURA_CONTA = "Abertura de conta",
            TITULO_DADOS_CLIENTE = "Cadastro de cliente",
            TITULO_ERRO = "ERRO",
            TITULO_MOVIMENTACAO = "Movimentação Bancária",
            TITULO_MENU = "Banco Virtual X",
            TO_STRING_VALIDA_CPF = "%s.%s.%s-%s",
            VIRGULA = ",";

    // Constantes numéricas
    double  VALOR_MINIMO_CONTA_POUPANCA = 1.00,
            VALOR_MINIMO_CONTA_REMUNRADA = 100.00,
            ZERO = 0.00,
            TAXA_POUPANCA_AO_MES = 0.005,
            TAXA_REMUNERADA_AO_DIA = 0.0002;
    

    // Constantes inteiras
    int ZERO_INT = 0;
    
    Object SAIR = null;
    		
} //interface Constantes 
