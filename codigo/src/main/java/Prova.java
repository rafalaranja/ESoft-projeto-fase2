import java.util.ArrayList;

public class Prova {
    private String atletas;
    private String evento;
    private String nome;
    private String genero;
    private String categoriaPeso;

    public Prova(String nome, String genero, String categoriaPeso, String evento, String atletas) {
        this.nome = nome;
        this.evento = evento;
        this.genero = genero;
        this.categoriaPeso = categoriaPeso;
        this.atletas = atletas;
    }
}
