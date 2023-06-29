import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GerirInscricoes extends JFrame{
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
    private JComboBox atletasComboBox;
    private JButton efetuarAcao;
    private JComboBox acaoComboBox;
    private JComboBox provasComboBox;

    private void carregarAtletas() {
        // Ler os eventos do arquivo "atletas.txt" e atualizar o modelo da ComboBox
        try {
            BufferedReader reader = new BufferedReader(new FileReader("atletas.txt"));
            String linha;
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");
                modelo.addElement(dados[0].trim());
            }
            reader.close();
            atletasComboBox.setModel(modelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarProvas(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("provas.txt"));
            String linha;
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");
                modelo.addElement(dados[3].trim());
            }
            reader.close();
            provasComboBox.setModel(modelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean confirmarAcao() {
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente remover o atleta selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }

    public GerirInscricoes() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        //////////////////////////// SIDEBAR ////////////////////////////

        // colocar a foto e nome do user logado
        if (Login.nomeUser != null) {
            nomeUser.setText(Login.nomeUser); //mostrar o nome do user logado
        } else {
            nomeUser.setText("Guest");
        }
        if (!nomeUser.getText().equals("Guest")) {
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

        acaoComboBox.setSelectedItem(null);
        provasComboBox.setSelectedItem(null);
        atletasComboBox.setSelectedItem(null);

        carregarAtletas();
        carregarProvas();

        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Adicionar");
        modelo.addElement("Remover");
        acaoComboBox.setModel(modelo);

        efetuarAcao.addActionListener(e -> {
            if (acaoComboBox.getSelectedItem().equals("Adicionar")) {
                if(confirmarAcao()){

                }
            } else if (acaoComboBox.getSelectedItem().equals("Remover")) {
                if (confirmarAcao()) {
                    // Remover o atleta selecionado do arquivo "atletas.txt"
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("atletas.txt"));
                        String linha;
                        StringBuilder texto = new StringBuilder();
                        while ((linha = reader.readLine()) != null) {
                            String[] dados = linha.split(":");
                            if (!dados[0].trim().equals(atletasComboBox.getSelectedItem())) {
                                texto.append(linha).append("\n");
                            }
                        }
                        reader.close();
                        Util.escreverArquivo("atletas.txt", texto.toString());
                        JOptionPane.showMessageDialog(this, "Atleta removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        carregarAtletas();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }
}
