import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PaginaProvas extends JFrame{
    private JPanel painelPrincipal;
    private JButton atletasButtonSide;
    private JButton estatisticasButtonSide;
    private JButton sobreButtonSide;
    private JButton eventosButtonSide;
    private JButton loginButtonSide;
    private JButton menuInicialButtonSide;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JTable table1;
    private JButton adicionarProvasButton;
    private JButton editarProvasButton;
    private JButton importarProvasButton;
    private JButton eliminarProvasButton;
    private JButton provasButtonSide;
    private JButton adicAtletaÀProvaButton;
    private JComboBox comboBox1;

    private void carregarProvas(String[] colunas) {
        // Ler os eventos do arquivo "provas.txt" e atualizar o modelo da tabela
        try {
            BufferedReader reader = new BufferedReader(new FileReader("provas.txt"));
            String linha;
            DefaultTableModel modelo = (DefaultTableModel) table1.getModel();
            modelo.setRowCount(0); // Limpar os dados existentes na tabela
            modelo.addRow(colunas);
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");
                modelo.addRow(dados);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PaginaProvas() {
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

        // Criar uma matriz de eventos (supondo que você já tenha os eventos em uma matriz/lista)
        String[][] eventos = {{"Prova 1", "Data 1"}, {"Prova 2", "Data 2"}, {"Prova 3", "Data 3"}};

        // Criar um array de nomes das colunas
        String[] colunas = {"Nome", "Género", "Categoria de Peso"};


        // Criar um DefaultTableModel com os eventos e colunas
        DefaultTableModel modelo = new DefaultTableModel(eventos, colunas){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede a tabela de ser editada pelo utilizador
            }
        };

        table1.setModel(modelo);
        carregarProvas(colunas); // Carregar os eventos do arquivo "provas.txt"

        //ocultar os botoões caso o user não esteja logado
        if (nomeUser.getText().equals("Guest")) {
            importarProvasButton.setVisible(false);
            adicionarProvasButton.setVisible(false);
            editarProvasButton.setVisible(false);
            eliminarProvasButton.setVisible(false);
            adicAtletaÀProvaButton.setVisible(false);
        }

        // Listeners dos botões
        /*adicionarProvasButton.addActionListener(e -> {
            AdicionarProva adicionarProva = new AdicionarProva();
            adicionarProva.setVisible(true);
            dispose();
        });

        editarProvasButton.addActionListener(e -> {
            EditarProva editarProva = new EditarProva();
            editarProva.setVisible(true);
            dispose();
        });

        eliminarProvasButton.addActionListener(e -> {
            EliminarProva eliminarProva = new EliminarProva();
            eliminarProva.setVisible(true);
            dispose();
        });

        importarProvasButton.addActionListener(e -> {
            ImportarProva importarProva = new ImportarProva();
            importarProva.setVisible(true);
            dispose();
        });

        adicAtletaÀProvaButton.addActionListener(e -> {
            AdicionarAtletaÀProva adicionarAtletaÀProva = new AdicionarAtletaÀProva();
            adicionarAtletaÀProva.setVisible(true);
            dispose();
        });*/
    }
}
