import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ApagarAtletas extends JFrame{
    private JPanel painelPrincipal;
    private JButton atletasButtonSide;
    private JButton estatisticasButtonSide;
    private JButton sobreButtonSide;
    private JButton eventosButtonSide;
    private JButton loginButtonSide;
    private JButton menuInicialButtonSide;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JComboBox atletaComboBox;
    private JButton apagarButton;

    private void carregarEventos() {
        // Ler os eventos do arquivo "eventos.txt" e atualizar o modelo da ComboBox
        try {
            BufferedReader reader = new BufferedReader(new FileReader("eventos.txt"));
            String linha;
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");
                modelo.addElement(dados[0].trim());
            }
            reader.close();
            atletaComboBox.setModel(modelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean confirmarRemocaoAtleta(String atleta) {
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente remover o atleta selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }

    private void removerAtleta(String atleta) {
        // Implemente o código para remover o atleta do arquivo "atletas.txt"
        try {
            BufferedReader reader = new BufferedReader(new FileReader("atletas.txt"));
            StringBuilder conteudoArquivo = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.startsWith(atleta + ":")) {
                    conteudoArquivo.append(linha).append("\n");
                }
            }
            reader.close();

            // Escrever o conteúdo atualizado no arquivo
            FileWriter writer = new FileWriter("atletas.txt");
            writer.write(conteudoArquivo.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
