import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private Tabuleiro tabuleiro;
    private ArrayList<Jogador> jogadores;
    private double salario;
    private int rodadasMaximas;

    public Jogo(double salario, int rodadasMaximas) {
        this.tabuleiro = new Tabuleiro();
        this.jogadores = new ArrayList<>();
        this.salario = salario;
        this.rodadasMaximas = rodadasMaximas;

        // Inicializando imóveis
        inicializarImoveis();  // Agora inicializa os imóveis quando o jogo é criado
    }

    public void adicionarJogador(Jogador jogador) {
        if (tabuleiro.getCasas().isEmpty()) {
            throw new IllegalStateException("O tabuleiro não possui casas. Inicialize o tabuleiro antes de adicionar jogadores.");
        }
        jogador.setPosicaoAtual(tabuleiro.getCasas().get(0)); // Atribuindo a primeira casa ao jogador
        jogadores.add(jogador);
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void iniciarJogo() {
        int rodada = 1;
        while (rodada <= rodadasMaximas && jogadores.size() > 1) {
            System.out.println("\nRodada " + rodada);
            for (Jogador jogador : jogadores) {
                jogarDado(jogador); // Jogar dado para mover o jogador
                interagirCasa(jogador); // Interagir com a casa onde o jogador está
                exibirStatus(jogador); // Exibir o status do jogador
            }
            rodada++;
        }
        exibirVencedor(); // Exibir o vencedor ao fim do jogo
    }

    private void jogarDado(Jogador jogador) {
        Random random = new Random();
        int dado = random.nextInt(6) + 1;
        System.out.println(jogador.getNome() + " jogou o dado e tirou " + dado);

        if (jogador.getPosicaoAtual() != null) {
            for (int i = 0; i < dado; i++) {
                if (jogador.getPosicaoAtual().getProxima() != null) {
                    jogador.setPosicaoAtual(jogador.getPosicaoAtual().getProxima());
                }
            }
            System.out.println(jogador.getNome() + " está na casa: " + jogador.getPosicaoAtual().getNome());
        } else {
            System.out.println("Erro: posição do jogador é nula.");
        }
    }

    private void interagirCasa(Jogador jogador) {
        Casa casa = jogador.getPosicaoAtual();
        if (casa != null) {
            switch (casa.getTipo()) {
                case "Imóvel":
                    if (casa.getProprietario() == null) {
                        System.out.println(jogador.getNome() + ", você está na casa: " + casa.getNome() + ". O preço de compra é R$" + casa.getPrecoCompra());
                        System.out.print("Você deseja comprar este imóvel? (S/N): ");
                        Scanner scanner = new Scanner(System.in);
                        String resposta = scanner.nextLine().toUpperCase();

                        if (resposta.equals("S")) {
                            if (jogador.getSaldo() >= casa.getPrecoCompra()) {
                                jogador.atualizarSaldo(-casa.getPrecoCompra());
                                casa.setProprietario(jogador);
                                jogador.adicionarPropriedade(casa);
                                System.out.println(jogador.getNome() + " comprou o imóvel: " + casa.getNome());
                            } else {
                                System.out.println("Saldo insuficiente para comprar o imóvel!");
                            }
                        } else {
                            System.out.println(jogador.getNome() + " decidiu não comprar o imóvel.");
                        }
                    } else if (casa.getProprietario() != jogador) {
                        double aluguel = casa.getAluguel();
                        jogador.atualizarSaldo(-aluguel);
                        casa.getProprietario().atualizarSaldo(aluguel);
                        System.out.println(jogador.getNome() + " pagou aluguel de R$" + aluguel + " para " + casa.getProprietario().getNome());
                    }
                    break;
                case "Imposto":
                    double imposto = jogador.getSaldo() * 0.05;
                    jogador.atualizarSaldo(-imposto);
                    System.out.println(jogador.getNome() + " pagou R$" + imposto + " de imposto.");
                    break;
                case "Restituição":
                    double restituição = salario * 0.1;
                    jogador.atualizarSaldo(restituição);
                    System.out.println(jogador.getNome() + " recebeu R$" + restituição + " de restituição.");
                    break;
            }
        }
    }

    private void exibirStatus(Jogador jogador) {
        System.out.println(jogador);
    }

    private void exibirVencedor() {
        Jogador vencedor = jogadores.stream().max((a, b) -> Double.compare(a.getSaldo(), b.getSaldo())).orElse(null);
        if (vencedor != null) {
            System.out.println("O vencedor é " + vencedor.getNome() + " com saldo de R$" + vencedor.getSaldo());
        }
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void inicializarImoveis() {
        if (tabuleiro.getCasas().isEmpty()) {
            adicionarImoveisPadrao();
        }
    }

    private void adicionarImoveisPadrao() {
        System.out.println("Adicionando imóveis padrão ao jogo...");

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

        for (String[] imovel : imoveis) {
            String nome = imovel[0];
            double precoCompra = Double.parseDouble(imovel[1]);
            double aluguel = Double.parseDouble(imovel[2]);
            Casa casa = new Casa(nome, "Imóvel", precoCompra, aluguel);
            tabuleiro.adicionarCasa(casa);
        }

        System.out.println("Imóveis padrão adicionados com sucesso!");
    }
}
