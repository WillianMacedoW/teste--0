import java.util.ArrayList;

public class Jogador {
    private String nome;
    private double saldo;
    private Casa posicaoAtual;
    private ArrayList<Casa> propriedades;

    public Jogador(String nome, double saldoInicial, Casa posicaoInicial) {
        this.nome = nome;
        this.saldo = saldoInicial; // Use o saldo inicial passado no construtor
        this.posicaoAtual = posicaoInicial;
        this.propriedades = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void atualizarSaldo(double valor) {
        this.saldo += valor;
    }

    public Casa getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Casa novaPosicao) {
        this.posicaoAtual = novaPosicao;
    }

    public void adicionarPropriedade(Casa propriedade) {
        this.propriedades.add(propriedade);
    }

    public ArrayList<Casa> getPropriedades() {
        return propriedades;
    }

    @Override
    public String toString() {
        return "Jogador: " + nome +
                " | Saldo: R$" + saldo +
                " | Posição Atual: " + posicaoAtual.getNome() +
                " | Propriedades: " + propriedades.size();
    }
}
