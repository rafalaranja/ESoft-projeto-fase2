import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GerirInscricoes extends JFrame{
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
    private JComboBox atletasComboBox;
    private JButton efetuarAcao;
    private JComboBox acaoComboBox;
    private JComboBox provasComboBox;

    private void carregarAtletas() {
        // Ler os eventos do arquivo "atletas.txt" e atualizar o modelo da ComboBox
        try {
            BufferedReader reader = new BufferedReader(new FileReader("atletas.txt"));
            String linha;
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");
                modelo.addElement(dados[0].trim());
            }
            reader.close();
            atletasComboBox.setModel(modelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarProvas(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("provas.txt"));
            String linha;
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");
                modelo.addElement(dados[0].trim());
            }
            reader.close();
            provasComboBox.setModel(modelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean confirmarAcao() {
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente remover o atleta selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }

    public GerirInscricoes() {
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

        carregarAtletas();
        carregarProvas();

        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Adicionar");
        modelo.addElement("Remover");
        acaoComboBox.setModel(modelo);



        efetuarAcao.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String acaoSelecionada = (String) acaoComboBox.getSelectedItem();
                if (acaoSelecionada.equals("Adicionar")) {
                    String atletaSelecionado = (String) atletasComboBox.getSelectedItem();
                    String provaSelecionada = (String) provasComboBox.getSelectedItem();
                    try {
                        File arquivoAtletas = new File("provas.txt");
                        File arquivoTemp = new File("temp.txt");

                        BufferedReader reader = new BufferedReader(new FileReader(arquivoAtletas));
                        PrintWriter writer = new PrintWriter(new FileWriter(arquivoTemp));

                        String linha;
                        while ((linha = reader.readLine()) != null) {
                            String[] dados = linha.split(":");
                            String nomeProva = dados[0].trim();
                            if (nomeProva.equals(provaSelecionada)) {
                                // Editar a linha correspondente ao evento encontrado
                                String[] atletas = dados[4].split(",");
                                StringBuilder atletasString = new StringBuilder();
                                int arrayComAtleta = 0;
                                for (String atleta : atletas) {
                                    if (!atleta.equals(atletaSelecionado)) {
                                        atletasString.append(atleta).append(",");
                                    } else {
                                        arrayComAtleta = 1;
                                    }
                                }
                                if (arrayComAtleta == 0) {
                                    atletasString.append(atletaSelecionado).append(",");
                                }
                                dados[4] = atletasString.toString();
                                writer.println(dados[0] + ":" + dados[1] + ":" + dados[2] + ":" + dados[3] + ":" + dados[4]);
                            }
                        }

                        reader.close();
                        writer.close();

                        // Substituir o arquivo original pelo arquivo temporário
                        if (arquivoAtletas.delete() && arquivoTemp.renameTo(arquivoAtletas)) {
                            JOptionPane.showMessageDialog(null, "Atleta adicionado com sucesso!");
                            PaginaProvas paginaProvas = new PaginaProvas();
                            paginaProvas.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao editar o atleta.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao acessar o arquivo eventos.txt");
                    }
                }
            }
        });
        efetuarAcao.addActionListener(e -> {
            String acaoSelecionada = (String) acaoComboBox.getSelectedItem();
            if (acaoSelecionada.equals("Remover")) {
                String atletaSelecionado = (String) atletasComboBox.getSelectedItem();
                String provaSelecionada = (String) provasComboBox.getSelectedItem();
                if (confirmarAcao()) {
                    // Remover o atleta selecionado do arquivo "atletas.txt"
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("provas.txt"));
                        StringBuilder conteudoArquivo = new StringBuilder();
                        String linha;
                        while ((linha = reader.readLine()) != null) {
                            String[] dados = linha.split(":");
                            if (!dados[4].contains(atletaSelecionado)) {
                                conteudoArquivo.append(linha).append("\n");
                            }
                            else {
                                String[] atletas = dados[4].split(",");
                                StringBuilder atletasString = new StringBuilder();
                                for (String atleta : atletas) {
                                    if (!atleta.equals(atletaSelecionado)) {
                                        atletasString.append(atleta).append(",");
                                    }
                                }
                                dados[4] = atletasString.toString();
                                conteudoArquivo.append(dados[0]).append(":").append(dados[1]).append(":").append(dados[2]).append(":").append(dados[3]).append(":").append(dados[4]).append("\n");
                            }
                        }
                        reader.close();
                        FileWriter writer = new FileWriter("provas.txt");
                        writer.write(conteudoArquivo.toString());
                        writer.close();
                        JOptionPane.showMessageDialog(this, "Atleta removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        //carregarAtletas();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
