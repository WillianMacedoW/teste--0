import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jogo jogo = new Jogo(2000.00, 20); // Salário inicial e rodadas máximas
        adicionarImoveisPadrao(jogo);

        System.out.println("Bem-vindo ao Jogo de Imóveis!");
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Adicionar Jogador");
            System.out.println("2. Iniciar Jogo");
            System.out.println("3. Exibir Jogadores");
            System.out.println("4. Exibir Tabuleiro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer do teclado

            switch (opcao) {
                case 1:
                    // Passo 1: Adicionar jogador
                    System.out.print("Digite o nome do jogador: ");
                    String nomeJogador = scanner.nextLine();
                    Jogador jogador = new Jogador(nomeJogador, 2000.00, null); // Saldo inicial
                    jogo.adicionarJogador(jogador);
                    System.out.println("Jogador " + nomeJogador + " adicionado!");
                    break;
                case 2:
                    // Passo 2: Iniciar o jogo
                    System.out.println("Iniciando o jogo...");
                    jogo.iniciarJogo();
                    continuar = false; // O jogo foi iniciado, sair do menu
                    break;
                case 3:
                    // Passo 3: Exibir jogadores
                    System.out.println("Jogadores:");
                    for (Jogador j : jogo.getJogadores()) {
                        System.out.println(j);
                    }
                    break;
                case 4:
                    // Passo 4: Exibir tabuleiro
                    System.out.println("Tabuleiro:");
                    exibirTabuleiro(jogo);
                    break;
                case 5:
                    // Passo 5: Sair do jogo
                    System.out.println("Saindo do jogo. Até logo!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para adicionar imóveis padrão ao jogo
    public static void adicionarImoveisPadrao(Jogo jogo) {
        String[][] imoveis = {
                {"Casa do Bosque", "200000", "1100"},
                {"Apartamento Central", "350000", "1800"},
                {"Vila das Flores", "400000", "2200"},
                {"Pousada da Praia", "500000", "2700"},
                {"Mansão da Colina", "600000", "3300"},
                {"Residência do Lago", "450000", "2500"},
                {"Cobertura Diamante", "700000", "3700"},
                {"Edifício Horizonte", "550000", "2900"},
                {"Chácara do Sol", "300000", "1600"},
                {"Fazenda Boa Vista", "250000", "1300"}
        };

        // Inicializando casas no tabuleiro
        for (String[] imovel : imoveis) {
            String nome = imovel[0];
            double precoCompra = Double.parseDouble(imovel[1]);
            double aluguel = Double.parseDouble(imovel[2]);
            Casa casa = new Casa(nome, "Imóvel", precoCompra, aluguel);
            jogo.getTabuleiro().adicionarCasa(casa);
        }
    }

    // Método para exibir o tabuleiro
    public static void exibirTabuleiro(Jogo jogo) {
        Casa atual = jogo.getTabuleiro().getInicio();
        if (atual == null) {
            System.out.println("O tabuleiro está vazio.");
            return;
        }

        // Exibir todas as casas do tabuleiro
        do {
            System.out.println(atual);
            atual = atual.getProxima();
        } while (atual != null); // Agora percorre até a última casa
    }
}
