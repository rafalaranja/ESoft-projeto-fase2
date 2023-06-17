import javax.swing.*;
import java.awt.*;

public class AdicionarEventos extends JFrame{
    private JButton atletasButton1;
    private JButton estatisticasButton;
    private JButton sobreButton;
    private JButton eventosButton1;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JPanel painelPrincipal;
    private JTextField nomeTextField;
    private JButton adicionarButton;
    private JTextField arteMarcialTextField;
    private JTextField descricaoTextField;
    private JTextField dataFinalTextField;
    private JTextArea textArea1;

    public static void main(String[] args) {
        AdicionarEventos adicionarEventos = new AdicionarEventos();
        adicionarEventos.setVisible(true);
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

        adicionarButton.addActionListener(e -> {
            // Adicionar evento
        });
    }
}


