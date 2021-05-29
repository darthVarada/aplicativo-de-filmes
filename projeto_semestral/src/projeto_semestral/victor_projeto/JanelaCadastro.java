package projeto_semestral.victor_projeto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class JanelaCadastro extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private FilmeDao filmeDao = new FilmeDao();
	private JTextField id;
	private JTextField titulo;
    private JTextField sinopse;
    private JComboBox<String> genero;
    private ButtonGroup ondeAssistir;
    private JCheckBox assistido;
    private StarRater starRater;
	
	public JanelaCadastro() {
		init();
	}
	
	private void init() {
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(30);
		setLayout(borderLayout);
        add(campoImagem(),BorderLayout.WEST);
        add(campos(), BorderLayout.CENTER);
        add(botoesExtras(), BorderLayout.EAST);
        add(botoesDeAcao(), BorderLayout.SOUTH);
	}
	
	private JPanel campos() {
		JPanel campos = new JPanel(new GridLayout(8, 1));
		
		JLabel idTexto = new JLabel("ID");
		id = new JTextField();
		id.setEditable(false);
		campos.add(idTexto);
		campos.add(id);
		
		JLabel tituloTexto = new JLabel("Título");
		titulo = new JTextField();
		campos.add(tituloTexto);
		campos.add(titulo);
		
		JLabel sinopseTexto = new JLabel("Sinopse");
		sinopse = new JTextField();
		sinopse.setBorder(new LineBorder(Color.WHITE, 0));
		campos.add(sinopseTexto);
		campos.add(sinopse);
		
		JLabel generoTexto = new JLabel("Gênero");
		genero = new JComboBox<>(new String[] {"Ação", "Romance", "Animes", "Comedia", "Terror"});
		campos.add(generoTexto);
		campos.add(genero);
		
		return campos;
	}
	
	private JPanel botoesExtras() {
		JPanel outrosBotoes = new JPanel(new GridLayout(7, 1));
		
		ondeAssistir = new ButtonGroup();
		
		JLabel  ondeAssistirTexto = new JLabel("Onde assistir");
		outrosBotoes.add(ondeAssistirTexto);
		
		JRadioButton netflix = new JRadioButton("Netflix");
		netflix.setActionCommand("Netflix");
		ondeAssistir.add(netflix);
		outrosBotoes.add(netflix);
		
		JRadioButton primeVideo = new JRadioButton("Prime Video");
		primeVideo.setActionCommand("Prime Video");
		ondeAssistir.add(primeVideo);
		outrosBotoes.add(primeVideo);
		
		JRadioButton pirateBay = new JRadioButton("Pirate Bay");
		pirateBay.setActionCommand("Pirate Bay");
		ondeAssistir.add(pirateBay);
		outrosBotoes.add(pirateBay);
		
		assistido = new JCheckBox("Assistido");
		outrosBotoes.add(assistido);
		
		JLabel avaliacao = new JLabel("Avaliação");
		outrosBotoes.add(avaliacao);
		starRater = new StarRater(5);
		outrosBotoes.add(starRater);
		return outrosBotoes;
	}
	
	private JPanel botoesDeAcao() {
		JPanel botoesDeAcao = new JPanel();
		
		JButton botaoSalvar = new JButton("Salvar");
		JButton botaoCancelar = new JButton("Cancelar");
		botaoSalvar.addActionListener(acaosalvar -> {
			Filme filme = new Filme();
			if(titulo.getText().trim().equals("") || titulo.getText()== null) {
				System.out.println("erro, coloque algo no titulo");			
			}else {
			
				filme.setAssistido(assistido.isSelected());
				filme.setAvaliacao(starRater.getSelection());
				filme.setGenero((String)genero.getSelectedItem());
				filme.setOndeAssistir(ondeAssistir.getSelection().getActionCommand());
				filme.setSinopse(sinopse.getText());
				filme.setTitulo(titulo.getText());
				System.out.println(filme);
				if(id.getText().trim().equals("") || id.getText()== null) {
					filmeDao.salvar(filme);
				}else {
					filme.setId(Long.parseLong(id.getText()));
					filmeDao.atualizar(filme);
				}
				System.out.println(filmeDao.getList());
			}
		});
		botaoCancelar.addActionListener(acaocancelar ->{
			titulo.setText("");
			sinopse.setText("");
			id.setText("");
			ondeAssistir.clearSelection();
	        assistido.setSelected(false);
	        starRater.setSelection(0);
		});
		botoesDeAcao.add(botaoSalvar);		
		botoesDeAcao.add(botaoCancelar);
		
		
		return botoesDeAcao;
	}
	private JPanel campoImagem() {
		JPanel dimencoes = new JPanel();
		ImageIcon img = new ImageIcon(new ImageIcon("src/projeto_semestral/victor_projeto/imagens/Interstellar_Filme.png").getImage().getScaledInstance(120, 200, Image.SCALE_DEFAULT));
		JLabel imagem = new JLabel(img);
		dimencoes.add(imagem);
		return dimencoes;
	}
	

	public JTextField getTitulo() {
		return titulo;
	}

	public JTextField getSinopse() {
		return sinopse;
	}

	public JComboBox<String> getGenero() {
		return genero;
	}

	public ButtonGroup getOndeAssistir() {
		return ondeAssistir;
	}

	public JCheckBox getAssistido() {
		return assistido;
	}

	public StarRater getStarRater() {
		return starRater;
	}
	public JTextField getId() {
		return id;
	}
	
}