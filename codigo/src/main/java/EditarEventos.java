import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EditarEventos extends JFrame{
    private JPanel painelPrincipal;
    private JButton atletasButtonSide;
    private JButton estatisticasButtonSide;
    private JButton sobreButtonSide;
    private JButton eventosButtonSide;
    private JButton loginButtonSide;
    private JButton menuInicialButtonSide;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JComboBox eventoComboBox;
    private JLabel adicionarEventosButton;
    private JButton editarButton;
    private JTextField nomeTextField;
    private JComboBox arteMarcialComboBox;
    private JFormattedTextField dataFinalTextField;
    private JFormattedTextField dataInicialTextField;
    private JTextArea textArea1;

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


    public EditarEventos() {
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


        eventoComboBox.setSelectedItem(null);
        carregarEventos();

        DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>();
        modeloComboBox.addElement("BJJ");
        modeloComboBox.addElement("Judo");
        modeloComboBox.addElement("Luta Greco-Romana");
        modeloComboBox.addElement("Luta Livre-Olímpica");
        modeloComboBox.addElement("Submission Grappling");
        arteMarcialComboBox.setModel(modeloComboBox);


        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventoSelecionado = (String) eventoComboBox.getSelectedItem();
                if (eventoSelecionado != null) {
                    // Implemente a lógica para editar o evento com as informações dos campos
                    String nome = nomeTextField.getText();
                    String arteMarcial = (String) arteMarcialComboBox.getSelectedItem();
                    String dataInicial = dataInicialTextField.getText();
                    String dataFinal = dataFinalTextField.getText();
                    String descricao = textArea1.getText();

                    try {
                        File arquivoEventos = new File("eventos.txt");
                        File arquivoTemp = new File("temp.txt");

                        BufferedReader reader = new BufferedReader(new FileReader(arquivoEventos));
                        PrintWriter writer = new PrintWriter(new FileWriter(arquivoTemp));

                        String linha;
                        while ((linha = reader.readLine()) != null) {
                            String[] dados = linha.split(":");
                            String nomeEvento = dados[0].trim();
                            if (nomeEvento.equals(eventoSelecionado)) {
                                // Editar a linha correspondente ao evento encontrado
                                linha = nome + " : " + arteMarcial + " : " + dataInicial + " : " + dataFinal + " : " + descricao;
                            }
                            writer.println(linha);
                        }

                        reader.close();
                        writer.close();

                        // Substituir o arquivo original pelo arquivo temporário
                        if (arquivoEventos.delete() && arquivoTemp.renameTo(arquivoEventos)) {
                            JOptionPane.showMessageDialog(null, "Evento editado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao editar o evento.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao acessar o arquivo eventos.txt");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nenhum evento selecionado.");
                }
            }



        });
        eventoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventoSelecionado = (String) eventoComboBox.getSelectedItem();
                if (eventoSelecionado != null) {
                    //obter as informações do evento selecionado
                    String[] informacoes = obterInformacoesEvento(eventoSelecionado);

                    if (informacoes != null && informacoes.length >= 5) {
                        nomeTextField.setText(informacoes[0]);
                        arteMarcialComboBox.setSelectedItem(informacoes[1]);
                        dataInicialTextField.setText(informacoes[2]);
                        dataFinalTextField.setText(informacoes[3]);
                        textArea1.setText(informacoes[4]);
                    }
                } else {
                    // Limpar os campos se nenhum evento estiver selecionado
                    nomeTextField.setText("");
                    arteMarcialComboBox.setSelectedItem(null);
                    dataInicialTextField.setText("");
                    dataFinalTextField.setText("");
                    textArea1.setText("");
                }
            }

            private String[] obterInformacoesEvento(String evento) {
                // obter as informações do evento com base no nome do evento
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("eventos.txt"));
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        String[] dados = linha.split(":");
                        if (dados.length >= 5 && dados[0].trim().equals(evento)) {
                            reader.close();
                            return dados;
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
