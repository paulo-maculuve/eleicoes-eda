package eleicoes.cne;

import java.util.Scanner;

import eleicoes.fila.LinkedQueue;
import eleicoes.fila.Node;

public class CNEElencoes {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		LinkedQueue eleitores = new LinkedQueue();
		String[] candidatos = { "c1", "c2", "c3" };
		String[] provincias = { "p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9", "p10", "p11", "p12" };
		int opcao;

		do {
			System.out.println("\nMenu da CNE");
			System.out.println("1. Recenseamento");
			System.out.println("2. Votação");
			System.out.println("3. Apuramento Nacional");
			System.out.println("4. Apuramento Por Província");
			System.out.println("5. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				// Recenseamento
				System.out.print("Nome do eleitor: ");
				String nome = scanner.next();
				System.out.println("Escolha a província:");
				for (int i = 0; i < provincias.length; i++) {
				    System.out.println(i + 1 + ". " + provincias[i]);
				}
				int escolhaProvincia = scanner.nextInt();
				String provincia = provincias[escolhaProvincia - 1];

				// Gerar o número do eleitor
				String numeroEleitor = gerarNumeroEleitor(eleitores, provincia);

				Eleitor eleitor = new Eleitor(numeroEleitor, nome, provincia);
				eleitores.enqueue(eleitor);

				// Exibir mensagem de sucesso e dados do eleitor
				System.out.println("Eleitor registado com sucesso.\n");
				System.out.println("Dados do eleitor:");
				System.out.println("Número do eleitor: " + eleitor.numeroCartao);
				System.out.println("Nome: " + eleitor.nome);
				System.out.println("Província: " + eleitor.provincia);
				break;

			case 2:
				// Votação
				System.out.print("Digite o número do eleitor: ");
				String numEleitor = scanner.next();
				Eleitor votante = procurarEleitor(eleitores, numEleitor);
				if (votante != null) {
					System.out.println("Escolha o candidato:");
					for (int i = 0; i < candidatos.length; i++) {
						System.out.println(i + 1 + ". " + candidatos[i]);
					}
					int escolhaCandidato = scanner.nextInt();
					votante.setCandidato(candidatos[escolhaCandidato - 1]);
					System.out.println("Voto registado com sucesso.");
				} else {
					System.out.println("Eleitor não encontrado.");
				}
				break;

			case 3:
				// Apuramento Nacional
				apuramentoNacional(eleitores, candidatos);
				break;

			case 4:
				// Apuramento por Província
				apuramentoPorProvincia(eleitores, candidatos, provincias);
				break;

			case 5:
				System.out.println("O programa foi encerrado.");
				break;

			default:
				System.out.println("Opção inválida. Tente novamente.");
				break;
			}
		} while (opcao != 5);
	}

	// Metodo para gerar o número do eleitor
	public static String gerarNumeroEleitor(LinkedQueue eleitores, String provincia) {
		int count = eleitores.getSize() + 1;
		return count + provincia;
	}

	// Metodo para encontrar um eleitor pelo número do eleitor
	public static Eleitor procurarEleitor(LinkedQueue eleitores, String numeroEleitor) {
		Node temp = eleitores.front;
		while (temp != null) {
			Eleitor eleitor = (Eleitor) temp.getItem();
			if (eleitor.numeroCartao.equals(numeroEleitor)) {
				return eleitor;
			}
			temp = temp.getNext();
		}
		return null; // Eleitor não encontrado
	}

	// Metodo para apuramento Nacional
	public static void apuramentoNacional(LinkedQueue eleitores, String[] candidatos) {
		int[] contagemVotos = new int[candidatos.length];

		Node temp = eleitores.front;
		while (temp != null) {
			Eleitor eleitor = (Eleitor) temp.getItem();
			String candidato = eleitor.getCandidato();

			if (candidato != null) {
				// Encontrou um voto válido
				for (int i = 0; i < candidatos.length; i++) {
					if (candidatos[i].equals(candidato)) {
						contagemVotos[i]++;
						break;
					}
				}
			}

			temp = temp.getNext();
		}

		System.out.println("Apuramento Nacional:");
		for (int i = 0; i < candidatos.length; i++) {
			System.out.println("Candidato " + candidatos[i] + ": " + contagemVotos[i] + " votos");
		}
	}

	// Metodo para apuramento provincia
	public static void apuramentoPorProvincia(LinkedQueue eleitores, String[] candidatos, String[] provincias) {
		for (String provincia : provincias) {
			int[] contagemVotos = new int[candidatos.length];

			Node temp = eleitores.front;
			while (temp != null) {
				Eleitor eleitor = (Eleitor) temp.getItem();

				if (eleitor.provincia.equals(provincia)) {
					String candidato = eleitor.getCandidato();

					if (candidato != null) {
						for (int i = 0; i < candidatos.length; i++) {
							if (candidatos[i].equals(candidato)) {
								contagemVotos[i]++;
								break;
							}
						}
					}
				}

				temp = temp.getNext();
			}

			System.out.println("Apuramento por Província " + provincia + ":");
			for (int i = 0; i < candidatos.length; i++) {
				System.out.println("Candidato " + candidatos[i] + ": " + contagemVotos[i] + " votos");
			}
		}
	}

}