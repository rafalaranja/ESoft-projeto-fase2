import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AdicionarAtletas extends JFrame{
    private JPanel painelPrincipal;
    private JButton atletasButtonSide;
    private JButton estatisticasButtonSide;
    private JButton sobreButtonSide;
    private JButton eventosButtonSide;
    private JButton loginButtonSide;
    private JButton menuInicialButtonSide;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JTextField nomeTextField;
    private JButton adicionarButton;
    private JComboBox generoComboBox;
    private JFormattedTextField equipaTextField;
    private JFormattedTextField dataNascimentoTextField;
    private JTextField nacionalidadeTextField;
    private JButton provasButtonSide;

    // Restringir as opções na JComboBox
    private DefaultComboBoxModel<String> generoComboBoxModel = new DefaultComboBoxModel<>(
            new String[]{"Masculino", "Feminino"});

    //Impedir que sejam colocados caracteres inválidos na data
    private boolean tryParseDate(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Desativa o modo leniente para restringir datas inválidas
        try {
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private int guardarAtleta() {
        String nome = nomeTextField.getText();
        String genero = (String) generoComboBox.getSelectedItem();
        String dataNascimento = dataNascimentoTextField.getText();
        String equipa = equipaTextField.getText();
        String nacionalidade = nacionalidadeTextField.getText();
        Atleta atleta = new Atleta(nome, genero, dataNascimento, equipa, nacionalidade);

        //verifica se a data é válida
        if (!tryParseDate(dataNascimento)) {
            return 0; // Abortar o processo de salvar o evento
        }
        else {
            Atleta.guardarAtleta(atleta);
            return 1; // Evento guardado com sucesso
        }
    }

    public AdicionarAtletas() {
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

        // Criar um MaskFormatter para o formato de data (dd/MM/yyyy)
        MaskFormatter formatter;
        try {
            formatter = new MaskFormatter("##/##/####");
            formatter.setPlaceholderCharacter('_'); // Caractere de espaço reservado para a máscara
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        dataNascimentoTextField.setFormatterFactory(new DefaultFormatterFactory(formatter));

        adicionarButton.addActionListener(e -> {

            //Adicionar evento
            int result = guardarAtleta();
            if (result == 1) {
                JOptionPane.showMessageDialog(null, "Atleta adicionado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Por favor insira uma data válida!"); }

            if (result == 1) {
                PaginaAtletas paginaAtletas = new PaginaAtletas();
                paginaAtletas.setVisible(true);
                dispose();
            }
        });
    }


}
