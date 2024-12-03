public class Casa {
    private String nome;
    private String tipo; // Imóvel, Imposto, Restituição, etc.
    private double precoCompra;
    private double aluguel;
    private Jogador proprietario;
    private Casa proxima; // Referência para a próxima casa

    public Casa(String nome, String tipo, double precoCompra, double aluguel) {
        this.nome = nome;
        this.tipo = tipo;
        this.precoCompra = precoCompra;
        this.aluguel = aluguel;
        this.proprietario = null; // Inicialmente a casa não tem proprietário
        this.proxima = null; // Inicialmente, a casa não aponta para outra casa
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public double getAluguel() {
        return aluguel;
    }

    public Jogador getProprietario() {
        return proprietario;
    }

    public void setProprietario(Jogador proprietario) {
        this.proprietario = proprietario;
    }

    public Casa getProxima() {
        return proxima;
    }

    public void setProxima(Casa proxima) {
        this.proxima = proxima;
    }

    @Override
    public String toString() {
        return "Casa{" +
                "nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precoCompra=" + precoCompra +
                ", aluguel=" + aluguel +
                ", proprietario=" + (proprietario != null ? proprietario.getNome() : "Nenhum") +
                '}';
    }
}
