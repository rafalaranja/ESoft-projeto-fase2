import java.io.FileWriter;
import java.io.IOException;

public class Evento {
    private String nome;
    private String arteMarcial;
    private String dataInicial;
    private String dataFinal;
    private String descricao;

    public Evento(String nome, String arteMarcial, String dataInicial, String dataFinal, String descricao) {
        this.nome = nome;
        this.arteMarcial = arteMarcial;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.descricao = descricao;
    }

    public static void guardarEvento(Evento evento) {
        try {
            FileWriter writer = new FileWriter("eventos.txt", true);
            writer.write(evento.nome + ":" + evento.arteMarcial + ":" + evento.dataInicial + ":" + evento.dataFinal + ":" + evento.descricao + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
