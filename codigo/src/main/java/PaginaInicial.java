import javax.swing.*;
import java.awt.*;

public class PaginaInicial extends JFrame {
    private JPanel painelPrincipal;
    private JButton eventosButton;
    private JButton atletasButton;
    private JButton eventosButtonSide;
    private JButton atletasButtonSide;
    private JButton estatisticasButtonSide;
    private JButton sobreButtonSide;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JButton loginButtonSide;


    public static void main(String[] args) {
        PaginaInicial paginaInicial = new PaginaInicial();
        paginaInicial.setVisible(true);
    }


    public PaginaInicial() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        String username = "Guest";

        // colocar a foto e nome do user logado

        if (Login.nomeUser != null){
            nomeUser.setText(Login.nomeUser); //mostrar o nome do user logado
        } else {
            nomeUser.setText(username);
        }

        if (!nomeUser.getText().equals("Guest")){
            loginButtonSide.setVisible(false);
        }

        ImageIcon imagemIcon = new ImageIcon("fotos/profile.png"); // Caminho para a imagem
        Image imagem = imagemIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT); // Definir tamanho da imagem
        ImageIcon icon = new ImageIcon(imagem); // Criar o ImageIcon com a imagem
        fotoUser.setIcon(icon);


        // Listeners dos botões


        eventosButton.addActionListener(e -> {
            PaginaEventos paginaEventos = new PaginaEventos();
            paginaEventos.setVisible(true);
            dispose();
        });
        /*
        atletasButton.addActionListener(e -> {
            Atletas atletas = new Atletas();
            atletas.setVisible(true);
            dispose();
        });

        estatisticasButtonSide.addActionListener(e -> {
            Estatisticas estatisticas = new Estatisticas();
            estatisticas.setVisible(true);
            dispose();
        });
        */

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
    }

}
