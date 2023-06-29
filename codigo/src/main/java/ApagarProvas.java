import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ApagarProvas extends JFrame {
    private JPanel painelPrincipal;
    private JButton atletasButtonSide;
    private JButton estatisticasButtonSide;
    private JButton sobreButtonSide;
    private JButton eventosButtonSide;
    private JButton loginButtonSide;
    private JButton menuInicialButtonSide;
    private JButton provasButtonSide;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JComboBox provasComboBox;
    private JButton apagarButton;

    private void carregarProvas() {
        // Ler as provas do arquivo "provas.txt" e atualizar o modelo da ComboBox
        try {
            BufferedReader reader = new BufferedReader(new FileReader("provas.txt"));
            String linha;
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");
                modelo.addElement(dados[0].trim());
            }
            reader.close();
            provasComboBox.setModel(modelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean confirmarRemocaoProva(String evento) {
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente remover a prova selecionada?", "Confirmação", JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }

    private void removerProva(String evento) {
        //Código para remover o evento do arquivo "provas.txt"
        try {
            BufferedReader reader = new BufferedReader(new FileReader("provas.txt"));
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


    public ApagarProvas(){
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

        provasButtonSide.addActionListener(e ->  {
            PaginaProvas paginaProvas = new PaginaProvas();
            paginaProvas.setVisible(true);
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

        carregarProvas();

        apagarButton.addActionListener(e -> {
            String eventoSelecionado = (String) provasComboBox.getSelectedItem();
            if (eventoSelecionado != null) {
                if (confirmarRemocaoProva(eventoSelecionado)) {
                    removerProva(eventoSelecionado);
                    JOptionPane.showMessageDialog(this, "Prova removida com sucesso!");
                    carregarProvas();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma prova selecionado.");
            }
        });
    }
}
