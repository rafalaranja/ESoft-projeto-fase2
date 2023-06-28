import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ApagarEventos extends JFrame{
    private JPanel painelPrincipal;
    private JButton atletasButtonSide;
    private JButton estatisticasButtonSide;
    private JButton sobreButtonSide;
    private JButton eventosButtonSide;
    private JButton loginButtonSide;
    private JButton menuInicialButtonSide;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JLabel adicionarEventosButton;
    private JComboBox eventoComboBox;
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
            eventoComboBox.setModel(modelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean confirmarRemocaoEvento(String evento) {
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente remover o evento selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }

    private void removerEvento(String evento) {
        // Implemente o código para remover o evento do arquivo "eventos.txt"
        try {
            BufferedReader reader = new BufferedReader(new FileReader("eventos.txt"));
            StringBuilder conteudoArquivo = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.startsWith(evento + ":")) {
                    conteudoArquivo.append(linha).append("\n");
                }
            }
            reader.close();

            // Escrever o conteúdo atualizado no arquivo
            FileWriter writer = new FileWriter("eventos.txt");
            writer.write(conteudoArquivo.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ApagarEventos(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        //////////////////////////// SIDEBAR ////////////////////////////


        // colocar a foto e nome do user logado
        if (Login.nomeUser != null){
            nomeUser.setText(Login.nomeUser); //mostrar o nome do user logado
        } else {
            nomeUser.setText("Guest");
        }if (!nomeUser.getText().equals("Guest")){
            loginButtonSide.setVisible(false);
        }

        ImageIcon imagemIcon = new ImageIcon("fotos/profile.png"); // Caminho para a imagem
        Image imagem = imagemIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT); // Definir tamanho da imagem
        ImageIcon icon = new ImageIcon(imagem); // Criar o ImageIcon com a imagem
        fotoUser.setIcon(icon);


        // Listeners dos botões

        eventosButtonSide.addActionListener(e -> {
            PaginaEventos paginaEventos = new PaginaEventos();
            paginaEventos.setVisible(true);
            dispose();
        });

        atletasButtonSide.addActionListener(e -> {
            PaginaAtletas paginaAtletas = new PaginaAtletas();
            paginaAtletas.setVisible(true);
            dispose();
        });

        estatisticasButtonSide.addActionListener(e -> {
            PaginaEstatisticas paginaEstatisticas = new PaginaEstatisticas();
            paginaEstatisticas.setVisible(true);
            dispose();
        });

        loginButtonSide.addActionListener(e -> {
            Login login = new Login();
            login.setVisible(true);
            dispose();
        });

        sobreButtonSide.addActionListener(e -> {
            Sobre sobre = new Sobre();
            sobre.setVisible(true);
            dispose();
        });

        menuInicialButtonSide.addActionListener(e -> {
            PaginaInicial paginaInicial = new PaginaInicial();
            paginaInicial.setVisible(true);
            dispose();
        });

        //////////////////////////// FIM DA SIDEBAR ////////////////////////////



        carregarEventos();

        apagarButton.addActionListener(e -> {
            String eventoSelecionado = (String) eventoComboBox.getSelectedItem();
            if (eventoSelecionado != null) {
                if (confirmarRemocaoEvento(eventoSelecionado)) {
                    removerEvento(eventoSelecionado);
                    JOptionPane.showMessageDialog(this, "Evento removido com sucesso!");
                    carregarEventos();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum evento selecionado.");
            }
        });
    }
}
