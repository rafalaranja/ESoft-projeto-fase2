import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VerificadorLogin {
    public static boolean verificarCredenciais(String nome, String pass) {
        try {
            FileReader reader = new FileReader("utilizadores.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linha;

            while ((linha = bufferedReader.readLine()) != null) {
                String[] partes = linha.split(":");
                String nomeSalvo = partes[0];
                String passSalva = partes[1];

                if (nome.equals(nomeSalvo) && pass.equals(passSalva)) {
                    bufferedReader.close();
                    return true;
                } else {
                    return false;
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
