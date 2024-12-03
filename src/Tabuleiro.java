import java.util.ArrayList;

public class Tabuleiro {
    private ArrayList<Casa> casas;
    private Casa inicio;

    public Tabuleiro() {
        this.casas = new ArrayList<>();
        this.inicio = new Casa("Início", "Início", 0, 0);
        casas.add(inicio); // A primeira casa é o início, mas ainda não tem casa seguinte.
    }

    // Método para adicionar casas ao tabuleiro e conectar cada casa à próxima
    public void adicionarCasa(Casa casa) {
        // Se a lista de casas não estiver vazia, a casa que está sendo adicionada será a próxima
        if (!casas.isEmpty()) {
            Casa ultimaCasa = casas.get(casas.size() - 1);
            ultimaCasa.setProxima(casa); // Conecta a última casa à nova casa
        }
        casas.add(casa); // Adiciona a nova casa ao tabuleiro
    }

    // Método para acessar a casa atual
    public ArrayList<Casa> getCasas() {
        return casas;
    }

    public Casa getInicio() {
        return inicio;
    }

    // Método para exibir o tabuleiro
    public void exibirTabuleiro() {
        for (Casa casa : casas) {
            System.out.println(casa.getNome());
            // Verifique se a casa tem uma próxima
            if (casa.getProxima() != null) {
                System.out.println("  Próxima casa: " + casa.getProxima().getNome());
            } else {
                System.out.println("  Esta é a última casa.");
            }
        }
    }
}
