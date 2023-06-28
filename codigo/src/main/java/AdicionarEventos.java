import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AdicionarEventos extends JFrame {
    private JButton atletasButtonSide;
    private JButton estatisticasButtonSide;
    private JButton sobreButtonSide;
    private JButton eventosButtonSide;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JPanel painelPrincipal;
    private JTextField nomeTextField;
    private JButton adicionarButton;
    private JComboBox<String> arteMarcialComboBox; // Define o tipo genérico da JComboBox
    private JFormattedTextField dataInicialTextField;
    private JFormattedTextField dataFinalTextField;
    private JTextArea textArea1;
    private JButton loginButtonSide;
    private JButton menuInicialButtonSide;

    // Restringir as opções na JComboBox
    private DefaultComboBoxModel<String> arteMarcialComboBoxModel = new DefaultComboBoxModel<>(
            new String[]{"BJJ", "Judo", "Luta Greco-Romana", "Luta Livre-Olímpica", "Submission Grappling"});

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

    //Verificar se descricao é válida
    private boolean isValidDescription(String description) {
        return description.length() <= 255;
    }


    private int guardarEvento() {
        String nome = nomeTextField.getText();
        String arteMarcial = (String) arteMarcialComboBox.getSelectedItem();
        String dataInicial = dataInicialTextField.getText();
        String dataFinal = dataFinalTextField.getText();
        String descricao = textArea1.getText();
        Evento evento = new Evento(nome, arteMarcial, dataInicial, dataFinal, descricao);

        //verifica se a data é válida
        if (!tryParseDate(dataInicial) || !tryParseDate(dataFinal)) {
            return 0; // Abortar o processo de salvar o evento
        }

        // Verifica se a descrição excede o tamanho máximo permitido
        else if (!isValidDescription(descricao)) {
            JOptionPane.showMessageDialog(null, "A descrição deve ter no máximo 255 caracteres.");
            return 0; // Abortar o processo de salvar o evento
        }
        else {
            Evento.guardarEvento(evento);
            return 1; // Evento guardado com sucesso
        }


    }

    public AdicionarEventos() {
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



        // Definir o modelo personalizado para a JComboBox arteMarcialComboBox
        arteMarcialComboBox.setModel(arteMarcialComboBoxModel);

        // Criar um MaskFormatter para o formato de data (dd/MM/yyyy)
        MaskFormatter formatter;
        try {
            formatter = new MaskFormatter("##/##/####");
            formatter.setPlaceholderCharacter('_'); // Caractere de espaço reservado para a máscara
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }



        dataInicialTextField.setFormatterFactory(new DefaultFormatterFactory(formatter));
        dataFinalTextField.setFormatterFactory(new DefaultFormatterFactory(formatter));

        adicionarButton.addActionListener(e -> {

            //Adicionar evento
            int result = guardarEvento();
            if (result == 1) {
                JOptionPane.showMessageDialog(null, "Evento adicionado com sucesso!");
            } else { JOptionPane.showMessageDialog(null, "Por favor insira uma data válida!"); }

            PaginaEventos paginaEventos = new PaginaEventos();
            paginaEventos.setVisible(true);
            dispose();
        });
    }
}
