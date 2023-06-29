import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PaginaCombates extends JFrame {
    public String prova;
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
    private JLabel adicionarEventosButton;
    private JButton registarButton;
    private JComboBox combateComboBox;
    private JLabel atleta2Label;
    private JLabel atleta1Label;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JTextField textField2;

    private void carregarCombatesDropbox() {
        // Ler os combates do arquivo "combates.txt" e atualizar o modelo da ComboBox
        try {
            BufferedReader reader = new BufferedReader(new FileReader("combates.txt"));
            String linha;
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith(prova + ":")) {            //os combates que começam com a prova selecionada
                    String[] dados = linha.split(":");
                    modelo.addElement(dados[1].trim());
                }
            }
            reader.close();
            combateComboBox.setModel(modelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public PaginaCombates(String prova) {
        this.prova = prova;

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

        carregarCombatesDropbox();

        
    }
}
