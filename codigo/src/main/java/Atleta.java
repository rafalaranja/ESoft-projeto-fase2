import java.io.FileWriter;
import java.io.IOException;

public class Atleta {
    private String nome;
    private String genero;
    private String dataNascimento;
    private String equipa;
    private String nacionalidade;
    private String arteMarcial;

    public Atleta(String nome, String genero, String dataNascimento, String equipa, String nacionalidade) {
        this.nome = nome;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.equipa = equipa;
        this.nacionalidade = nacionalidade;
        //this.arteMarcial = arteMarcial;
    }

    public static void guardarAtleta(Atleta atleta) {
        try {
            FileWriter writer = new FileWriter("atletas.txt", true);
            writer.write(atleta.nome + ":" + atleta.genero + ":" + atleta.dataNascimento + ":" + atleta.equipa + ":" + atleta.nacionalidade + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
