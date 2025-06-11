package winmk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings({ "serial" })
public class telaInicial extends JFrame 
{
	private JButton[][] assentos = new JButton[5][10];
	
	public telaInicial() 
    {
        telaLogin(); // Exibe a tela inicial assim que a janela é criada
    }
    
	public void telaLogin() 
    {
        setTitle("🎬 CineFoz - Login");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel principal
        JPanel telaLogin = new JPanel(new BorderLayout());
        telaLogin.setBackground(new Color(40, 40, 40)); 

        // Logo no topo
        JLabel logo = new JLabel("🎬 CineFoz");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 26));
        logo.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
        telaLogin.add(logo, BorderLayout.NORTH);
        		  add(telaLogin, BorderLayout.WEST);

        // Painel central com layout manual
        JPanel body = new JPanel(null); // Layout absoluto
        body.setBackground(new Color(20, 20, 20)); 

        // Botão "Entrar"
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Arial", Font.PLAIN, 20));
        btnEntrar.setBounds(870, 60, 120, 40);
        body.add(btnEntrar);
        
        JTextField txtNome = new JTextField();
        txtNome.setBounds(825, 50, 200, 40);
        txtNome.setFont(new Font("Arial", Font.PLAIN, 22));
        txtNome.setVisible(false);
        body.add(txtNome);
        
        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(825, 110, 200, 40);
        txtSenha.setFont(new Font("Arial", Font.PLAIN, 22));
        txtSenha.setVisible(false);
        body.add(txtSenha);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBounds(875, 180, 100, 40);
        btnLogin.setVisible(false);
        body.add(btnLogin);

        // Ação do botão "Entrar"
        btnEntrar.addActionListener(e -> 
        {
            btnEntrar.setVisible(false);
            txtNome.setVisible(true);
            txtSenha.setVisible(true);
            btnLogin.setVisible(true);
            body.repaint();
        });

        // Ação do botão "Login"
        btnLogin.addActionListener(e -> 
        {
            String nome = txtNome.getText();
            String senha = new String(txtSenha.getPassword());

            if (nome.equals("admin") && senha.equals("1234")) 
            {
                getContentPane().removeAll();
                Inicio(); // Chamada da tela principal
                revalidate();
                repaint();
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Nome ou senha incorretos.");
            }
        });
        telaLogin.add(body, BorderLayout.CENTER);
        add(telaLogin, BorderLayout.CENTER);
        setVisible(true);
    }
	public void selecionarFilme(String caminhoImagem, String titulo) 
	{
	    setTitle("🎬 CineFoz - Selecionar Horário");
	    setLayout(new BorderLayout());
	    getContentPane().setBackground(new Color(20, 20, 20)); // Cor geral

	    // Painel central que organiza tudo verticalmente
	    JPanel painelCentral = new JPanel();
	    painelCentral.setBackground(new Color(20, 20, 20));
	    painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
	    painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // margem

	    // Imagem do filme centralizada
	    URL recurso = getClass().getResource(caminhoImagem);
	    if (recurso == null) {
	        System.err.println("Imagem não encontrada: " + caminhoImagem);
	        return;
	    }
	    ImageIcon ogIcon = new ImageIcon(recurso);
	    Image imgP = ogIcon.getImage().getScaledInstance(350, 450, Image.SCALE_SMOOTH);
	    JLabel lblImagem = new JLabel(new ImageIcon(imgP));
	    lblImagem.setAlignmentX(CENTER_ALIGNMENT); // centraliza horizontalmente no BoxLayout
	    painelCentral.add(lblImagem);

	    // Espaço vertical entre imagem e horários
	    painelCentral.add(Box.createRigidArea(new Dimension(0, 30)));

	    // Painel de horários com FlowLayout centralizado
	    JPanel painelHorarios = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
	    painelHorarios.setBackground(new Color(20, 20, 20));
	    painelHorarios.setMaximumSize(new Dimension(700, 70));

	    String[] horarios = { "12:00", "15:00", "18:00", "21:00" };

	    for (String horario : horarios) 
	    {
	        JButton btnHorario = new JButton(horario);
	        btnHorario.setPreferredSize(new Dimension(120, 50));
	        btnHorario.setFont(new Font("Arial", Font.BOLD, 18));
	        btnHorario.setBackground(new Color(40, 40, 40));
	        btnHorario.setForeground(Color.WHITE);
	        btnHorario.setFocusPainted(false);
	        btnHorario.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
	        btnHorario.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        btnHorario.setOpaque(true);
	        btnHorario.setContentAreaFilled(true);

	        final Timer[] timer = new Timer[1];
	        final int[] size = {0};
	        final int maxGrow = 10;

	        btnHorario.addMouseListener(new MouseAdapter() {
	            public void mouseEntered(MouseEvent e) {
	                if (timer[0] != null && timer[0].isRunning()) timer[0].stop();
	                size[0] = 0;

	                timer[0] = new Timer(10, ev -> {
	                    if (size[0] < maxGrow) {
	                        size[0]++;
	                        btnHorario.setPreferredSize(new Dimension(120 + size[0], 50 + size[0]));
	                        painelHorarios.revalidate();
	                    } else {
	                        timer[0].stop();
	                    }
	                });
	                timer[0].start();
	            }

	            public void mouseExited(MouseEvent e) {
	                if (timer[0] != null && timer[0].isRunning()) timer[0].stop();

	                timer[0] = new Timer(10, ev -> {
	                    if (size[0] > 0) {
	                        size[0]--;
	                        btnHorario.setPreferredSize(new Dimension(120 + size[0], 50 + size[0]));
	                        painelHorarios.revalidate();
	                    } else {
	                        timer[0].stop();
	                    }
	                });
	                timer[0].start();
	            }
	        });

	        btnHorario.addActionListener(ev -> {
	            getContentPane().removeAll();
	            reservas();
	            revalidate();
	            repaint();
	        });

	        painelHorarios.add(btnHorario);
	    }

	    painelHorarios.setAlignmentX(CENTER_ALIGNMENT);
	    painelCentral.add(painelHorarios);

	    // Espaço entre horários e informações do filme
	    painelCentral.add(Box.createRigidArea(new Dimension(0, 30)));

	    // Painel de informações (Duração, Gêneros, Diretor)
	    JPanel painelInfo = new JPanel();
	    painelInfo.setBackground(new Color(20, 20, 20));
	    painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
	    painelInfo.setAlignmentX(CENTER_ALIGNMENT);

	    // Labels com as informações (exemplo fixo, pode adaptar para variáveis)
	    JLabel lblSinopse = new JLabel("Sinopse: Paul Atreides é um jovem brilhante, dono de um destino além de sua compreensão. Ele deve viajar para o planeta mais perigoso do universo para garantir o futuro de seu povo.");
	    JLabel lblDuracao = new JLabel("Duração: 2h 15min");
	    JLabel lblGeneros = new JLabel("Gêneros: Ação, Ficção Científica");
	    JLabel lblDiretor = new JLabel("Diretor: Denis Villeneuve");
	    
	    

	    // Estilo dos textos
	    Font fontInfo = new Font("Arial", Font.PLAIN, 16);
	    Color corTexto = Color.WHITE;
	    
	    lblSinopse.setFont(fontInfo);
	    lblSinopse.setForeground(corTexto);
	    lblDuracao.setFont(fontInfo);
	    lblDuracao.setForeground(corTexto);
	    lblGeneros.setFont(fontInfo);
	    lblGeneros.setForeground(corTexto);
	    lblDiretor.setFont(fontInfo);
	    lblDiretor.setForeground(corTexto);

	    // Margem entre linhas
	    lblSinopse.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
	    lblDuracao.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
	    lblGeneros.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
	    lblDiretor.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
	    
	    painelInfo.add(lblSinopse);
	    painelInfo.add(lblDuracao);
	    painelInfo.add(lblGeneros);
	    painelInfo.add(lblDiretor);

	    painelCentral.add(painelInfo);

	    add(painelCentral, BorderLayout.CENTER);

	    setVisible(true);
	}
    public void Inicio() 
    {
        //Nome do painel
        setTitle("CineFoz");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Topo com logotipo
        JPanel body = new JPanel(new BorderLayout());
        body.setBackground(new Color(40, 40, 40));
        body.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
        JLabel logo = new JLabel("🎬 CineFoz");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 26));
        body.add(logo, BorderLayout.WEST);
        add(body, BorderLayout.NORTH);

        // Painel com FlowLayout para os pôsteres (sem scroll)
        JPanel corpo = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        corpo.setBackground(new Color(22, 22, 22));

        String[][] filmes = 
        {
            { "/imgs/duna.jpg", "Duna" },
            { "/imgs/avengers.jpg", "Vingadores" },
            { "/imgs/matrix.jpg", "Matrix" },
            { "/imgs/origem.jpg", "Origem" }
        };

        for (String[] filme : filmes) 
        {
            String imgCaminho = filme[0];
            String titulo = filme[1];

            URL recurso = getClass().getResource(imgCaminho);
            if (recurso == null) 
            {
                System.err.println("Imagem não encontrada: " + imgCaminho);
                continue;
            }

            ImageIcon ogIcon = new ImageIcon(recurso);
            Image imgP = ogIcon.getImage().getScaledInstance(300, 450, Image.SCALE_SMOOTH);
            ImageIcon iconP = new ImageIcon(imgP);

            Image imgG = ogIcon.getImage().getScaledInstance(320, 480, Image.SCALE_SMOOTH);
            ImageIcon iconG = new ImageIcon(imgG);

            JLabel poster = new JLabel(iconP);
            poster.setToolTipText(titulo);
            poster.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            poster.setPreferredSize(new Dimension(320, 480));

            poster.addMouseListener(new java.awt.event.MouseAdapter() 
            {
                public void mouseEntered(java.awt.event.MouseEvent evt) 
                {
                    poster.setIcon(iconG);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) 
                {
                    poster.setIcon(iconP);
                }
                public void mouseClicked(java.awt.event.MouseEvent evt) 
                {
                    getContentPane().removeAll();
					selecionarFilme(imgCaminho, titulo);
                    revalidate();
                    repaint();
                }
            });

            corpo.add(poster);
        }

        // Container visível que "corta" o painel corpo para mostrar só parte das imagens
        JPanel containerVisivel = new JPanel(null);
        containerVisivel.setPreferredSize(new Dimension(850, 520));
        containerVisivel.setBackground(new Color(20, 20, 20));
        containerVisivel.setOpaque(true);

        // Posiciona corpo dentro do container (tamanho natural)
        Dimension corpoPref = corpo.getPreferredSize();
        corpo.setBounds(0, 0, corpoPref.width, corpoPref.height);
        containerVisivel.add(corpo);

        // Botões de navegação
        JButton btnLeft = new JButton("<");
        JButton btnRight = new JButton(">");
        Dimension btnSize = new Dimension(40, 140);

        JButton[] botoes = {btnLeft, btnRight};
        for (JButton btn : botoes) {
            btn.setPreferredSize(btnSize);
            btn.setFont(new Font("Arial", Font.BOLD, 30));
            btn.setMargin(new Insets(0, 0, 0, 0));
            btn.setOpaque(true);
            btn.setBackground(new Color(40, 40, 40));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        }

        final int[] posX = {0}; 
        int deslocamento = 370;

        class Animador 
        {
            Timer timer;
            Animador(int destino) 
            {
                timer = new Timer(10, e -> 
                {
                    int passo = 20;
                    if (posX[0] > destino) 
                    {
                        posX[0] = Math.max(posX[0] - passo, destino);
                    } 
                    else if (posX[0] < destino) 
                    {
                        posX[0] = Math.min(posX[0] + passo, destino);
                    }
                    corpo.setLocation(posX[0], 0);
                    containerVisivel.repaint();
                    if (posX[0] == destino) 
                    {
                        timer.stop();
                    }
                });
                timer.start();
            }
        }

        btnLeft.addActionListener(e -> 
        {
            int destino = Math.min(posX[0] + deslocamento, 0);
            new Animador(destino);
        });

        btnRight.addActionListener(e -> 
        {
            int limiteDireito = containerVisivel.getWidth() - corpo.getWidth();
            int destino = Math.max(posX[0] - deslocamento, limiteDireito);
            new Animador(destino);
        });

        JPanel painelScroll = new JPanel();
        painelScroll.setBackground(new Color(20, 20, 20));
        painelScroll.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        painelScroll.add(btnLeft);
        painelScroll.add(containerVisivel);
        painelScroll.add(btnRight);

        add(painelScroll, BorderLayout.CENTER);

        getContentPane().setBackground(new Color(20, 20, 20));
        setVisible(true);
    }
    public void reservas() 
    {
        setTitle("CineFoz - Escolha de Assentos");
        setLayout(new BorderLayout());

        JPanel mainWin = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("Escolha seus assentos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        mainWin.add(titulo, BorderLayout.NORTH);

        JPanel painelAssentos = new JPanel(new GridLayout(5, 10, 5, 5));
        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                JButton botao = new JButton((char)('A' + i) + String.valueOf(j + 1));
                botao.setBackground(Color.LIGHT_GRAY);
                botao.addActionListener(e -> 
                {
                    if (botao.getBackground() == Color.GREEN) 
                    {
                    	botao.setBackground(Color.LIGHT_GRAY);
                    }
                    else 
                    {
                        botao.setBackground(Color.GREEN);
                    }
                });
                assentos[i][j] = botao;
                painelAssentos.add(botao);
            }
        }

        mainWin.add(painelAssentos, BorderLayout.CENTER);

        JButton confirmar = new JButton("Confirmar Reserva");
        confirmar.setFont(new Font("Arial", Font.BOLD, 16));
        confirmar.addActionListener(e -> 
        {
            StringBuilder selecionados = new StringBuilder("Assentos selecionados:\n");
            for (int i = 0; i < 5; i++) 
            {
                for (int j = 0; j < 10; j++) 
                {
                    if (assentos[i][j].getBackground() == Color.GREEN) 
                    {
                        selecionados.append(assentos[i][j].getText()).append(" ");
                    }
                }
            }

            JOptionPane.showMessageDialog(this, selecionados.toString());

            // Volta para a tela inicial após confirmar
            getContentPane().removeAll();
            Inicio();
            revalidate();
            repaint();
        });

        mainWin.add(confirmar, BorderLayout.SOUTH);
        add(mainWin, BorderLayout.CENTER);
        revalidate();
        repaint();
    }   
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        {
            telaInicial tela = new telaInicial();
            tela.setExtendedState(JFrame.MAXIMIZED_BOTH); // Janela maximizada (mantém barra)
            tela.setVisible(true); // Exibe a janela
        });
    }
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            URL resource = getClass().getResource(imagePath);
            if (resource != null) {
                backgroundImage = new ImageIcon(resource).getImage();
            } else {
                System.err.println("Imagem de fundo não encontrada: " + imagePath);
            }
        }
    }
    
}
