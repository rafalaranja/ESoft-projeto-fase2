import java.util.ArrayList;

public class Prova {
    private final ArrayList atletas;
    private String evento;
    private String nome;
    private String genero;
    private String categoriaPeso;
    private Evento evento;

    public Prova(String nome, String genero, String categoriaPeso, String evento, ArrayList<Atleta> atletas) {
        this.nome = nome;
        this.evento = evento;
        this.genero = genero;
        this.categoriaPeso = categoriaPeso;
        this.atletas = new ArrayList<>();
    }
}
