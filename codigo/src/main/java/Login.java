import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JPanel painelLogin;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton entrarButton;

    public static String nomeUser;

    public Login(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelLogin);
        pack();

        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textField1.getText();
                String pass = new String(passwordField1.getPassword());

                if (VerificadorLogin.verificarCredenciais(nome, pass)) {
                    nomeUser = nome;
                    PaginaInicial paginaInicial = new PaginaInicial();
                    paginaInicial.setVisible(true);
                    dispose();
                } else if (nome.equals("") || pass.equals("")){ // Verifica se os campos estão vazios
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos");

                }else {
                    JOptionPane.showMessageDialog(null, "Credenciais erradas");
                }
            }
        });
    }
}
