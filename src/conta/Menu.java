package conta;

import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
import conta.util.Cores;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.model.ContaCorrente;
import conta.controller.ContaController;

public class Menu {

	public static void main(String[] args) {
		
		ContaController contas = new ContaController();
		
		Scanner leia = new Scanner(System.in);
		
		int opcao, numero, agencia, tipo, aniversario,numeroDestino;
		String titular;
		float saldo, limite, valor;

		System.out.println("\nCriar Contas\n");
		ContaCorrente cc1 =new ContaCorrente(contas.gerarNumero(), 123, 1 , "Tais Salis",1000f, 100.0f );
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 =new ContaCorrente(contas.gerarNumero(), 124, 1 , "Maria da Silva", 2000f, 100.0f );
		contas.cadastrar(cc2);
		
		ContaCorrente cp1 =new ContaCorrente(contas.gerarNumero(), 125, 2 , "Mariana dos Santos", 4000f, 12 );
		contas.cadastrar(cp1);
		
		ContaCorrente cp2 =new ContaCorrente(contas.gerarNumero(), 126, 2 , "Juliana Souza", 8000f, 15 );
		contas.cadastrar(cp2);
		
		contas.listarTodas();
		
		
		while (true) {

			System.out.println(Cores.TEXT_PURPLE + Cores.ANSI_WHITE_BACKGROUND);
			System.out.println("****************************************************");
			System.out.println("                                                    ");
			System.out.println("                 BANCO DO BRAZIL                    ");
			System.out.println("                                                    ");
			System.out.println("****************************************************");
			System.out.println("                                                    ");
			System.out.println("                  1 - Criar conta.                  ");
			System.out.println("                  2 - Listar todas as contas.       ");
			System.out.println("                  3 -Buscar conta por Numero.       ");
			System.out.println("                  4 - Atualizar dados da conta.     ");
			System.out.println("                  5 -Apagar conta.                  ");
			System.out.println("                  6 - Sacar.                        ");
			System.out.println("                  7 - Depositar.                    ");
			System.out.println("                  8 - Transferir valor entre contas.");
			System.out.println("                  9 - Sair.                         ");
			System.out.println("                                                    ");
			System.out.println("****************************************************");
			System.out.println("Entre com a opção desejada:                         ");
			System.out.println("                                                    " + Cores.TEXT_RESET);

			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros !");
				leia.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brasil - O seu Futuro começa aqui!");
				leia.close();
				System.exit(0);
			}
			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Criar conta \n\n");

				System.out.println("Digite o Numero da Agência: ");
				agencia = leia.nextInt();
				System.out.println("Digite o Nome do Titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();

				do {
					System.out.println("Digite o tipo da Conta (1-CC ou 2-CP): ");
					tipo = leia.nextInt();
				} while (tipo < 1 && tipo > 2);
				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = leia.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o Limite de Crédito (R$): ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversario da conta: ");
					aniversario = leia.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));

				}
				}
				keyPress();
				break;

			case 2:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Listar todas as contas \n\n");
				contas.listarTodas();

				keyPress();
				break;

			case 3:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Consultar dados de conta - por numero \n\n");

				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				contas.procurarPorNumero(numero);
				
				keyPress();
				break;

			case 4:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Atualizar dados de conta \n\n");
				
				System.out.println("Digite o numero da cota: ");
				numero = leia.nextInt();
				
				if(contas.buscarNaCollection(numero) != null) {
					
				
					System.out.println("Digite o número da Agência: ");
					agencia = leia.nextInt();
					
					System.out.println("Digite o nome do Titular: ");
					leia.skip("\\R?");
					titular =leia.nextLine();
					
					System.out.println("Digite o saldo da conta (R$): ");
					saldo =leia.nextFloat();
					
					tipo = contas.retornaTipo(numero);
				
					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o Limite (R$): ");
						limite = leia.nextFloat();
						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));	
					}
					
					case 2 -> {
						System.out.println("Digite o dia do Aniversario da Conta: ");
						aniversario = leia.nextInt();
						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					
					default -> {
						System.out.println("Tipo de conta invalido!");
					}
					}
					
				}else 
				 System.out.println("\nConta não encontrada!");
					
				keyPress();
				break;
				
			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar a conta \n\n");

				System.out.println("Digite o número da conta: ");
				numero =leia.nextInt();
				
				contas.deletar(numero);
				
				keyPress();
				break;

			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Saque \n\n");

				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o Valor do saque (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.sacar(numero, valor);
				keyPress();
				break;

			case 7:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Deposito \n\n");

				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o valoer do Deposito (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.depositar(numero, valor);
				 
				keyPress();
				break;

			case 8:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Transferência entre contas \n\n");

				System.out.println("Digite o numero da conta de Origem: ");
				numero = leia.nextInt();
				System.out.println("Digite o numero da conta de Destino: ");
				numeroDestino = leia.nextInt();
				
				do {
					System.out.println("Digite o valor da Transferência (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				contas.transferir(numero, numeroDestino, valor);
				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "Opção Inválida! \n\n");

				keyPress();
				break;
				
	}
	}	
	}

	public static void keyPress() {

		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione ENTER para continuar!");
			System.in.read();
		} catch (IOException e) {
			System.out.println("Você pressionou uma tecla diferente do ENTER");
		}
	}
}
