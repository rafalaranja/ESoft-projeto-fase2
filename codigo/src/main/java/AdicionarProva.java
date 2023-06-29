import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdicionarProva extends JFrame{
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
    private JTextField nomeTextField;
    private JButton adicionarButton;
    private JComboBox generoComboBox;
    private JComboBox catPesoComboBox;
    private JComboBox eventoComboBox;

    // Restringir as opções na JComboBox
    private DefaultComboBoxModel<String> generoComboBoxModel = new DefaultComboBoxModel<>(
            new String[]{"Masculino", "Feminino"});

    private DefaultComboBoxModel<String> catPesoComboBoxModel = new DefaultComboBoxModel<>(
            new String[]{"50", "65", "80", "95", "110"});

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

    private int guardarProva() {
        String nome = nomeTextField.getText();
        String genero = (String) generoComboBox.getSelectedItem();
        String catPeso = (String) catPesoComboBox.getSelectedItem();
        String evento = (String) eventoComboBox.getSelectedItem();
        Prova prova = new Prova(nome, genero, catPeso, evento, ",");


            Prova.guardarProva(prova);
            return 1; // Evento guardado com sucesso

    }

    public AdicionarProva(){
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

        generoComboBox.setModel(generoComboBoxModel);
        catPesoComboBox.setModel(catPesoComboBoxModel);
        carregarEventos();

        adicionarButton.addActionListener(e -> {

            //Adicionar evento
            int result = guardarProva();
            if (result == 1) {
                JOptionPane.showMessageDialog(null, "Prova adicionado com sucesso!");
                PaginaProvas paginaProvas = new PaginaProvas();
                paginaProvas.setVisible(true);
                dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao adicionar prova!");
            }
        });
    }
}
