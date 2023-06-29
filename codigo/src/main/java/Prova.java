import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Prova {
    private String atletas;
    private String evento;
    private String nome;
    private String genero;
    private String categoriaPeso;

    public Prova(String nome, String genero, String categoriaPeso, String evento) {
        this.nome = nome;
        this.evento = evento;
        this.genero = genero;
        this.categoriaPeso = categoriaPeso;
    }

    public static void guardarProva(Prova prova) {
        try {
            FileWriter writer = new FileWriter("provas.txt", true);
            writer.write(prova.nome + ":" + prova.evento + ":" + prova.genero + ":" + prova.categoriaPeso + ":" + prova.atletas + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
