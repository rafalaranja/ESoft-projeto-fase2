import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class AdicionarEventos extends JFrame{
    private JButton atletasButton1;
    private JButton estatisticasButton;
    private JButton sobreButton;
    private JButton eventosButton;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JPanel painelPrincipal;
    private JTextField nomeTextField;
    private JButton adicionarButton;
    private JTextField arteMarcialTextField;
    private JFormattedTextField dataInicialTextField;
    private JFormattedTextField dataFinalTextField;
    private JTextArea textArea1;

    public static void main(String[] args) {
        AdicionarEventos adicionarEventos = new AdicionarEventos();
        adicionarEventos.setVisible(true);
    }

    private void guardarEvento() {
        String nome = nomeTextField.getText();
        String arteMarcial = arteMarcialTextField.getText();
        String dataInicial = dataInicialTextField.getText();
        String dataFinal = dataFinalTextField.getText();
        String descricao = textArea1.getText();
        Evento evento = new Evento(nome, arteMarcial, dataInicial, dataFinal, descricao);
        Evento.guardarEvento(evento);
    }

    public AdicionarEventos() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        // colocar a foto e nome do user logado

        nomeUser.setText(Login.nomeUser); //mostrar o nome do user logado

        ImageIcon imagemIcon = new ImageIcon("fotos/profile.png"); // Caminho para a imagem
        Image imagem = imagemIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT); // Definir tamanho da imagem
        ImageIcon icon = new ImageIcon(imagem); // Criar o ImageIcon com a imagem
        fotoUser.setIcon(icon);

        // Listeners dos botões
        sobreButton.addActionListener(e -> {
            Sobre sobre = new Sobre();
            sobre.setVisible(true);
            dispose();
        });






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
            guardarEvento();
            PaginaEventos paginaEventos = new PaginaEventos();
            paginaEventos.setVisible(true);
            dispose();
        });
    }
}


