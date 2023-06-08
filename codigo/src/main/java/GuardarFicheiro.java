import java.io.FileWriter;
import java.io.IOException;

public class GuardarFicheiro {
    public static void guardarNomePass(String nome, String pass) {
        try {
            FileWriter writer = new FileWriter("utilizadores.txt", true);
            writer.write(nome + ":" + pass + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
