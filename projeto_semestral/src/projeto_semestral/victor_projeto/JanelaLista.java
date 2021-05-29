package projeto_semestral.victor_projeto;

import java.awt.GridLayout;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JanelaLista extends JPanel {

	private static final long serialVersionUID = 1L;
	private FilmeDao filmeDao = new FilmeDao();

	public JanelaLista() {
		init();
	}

	private void init() {

		setLayout(new GridLayout(2, 1));
		List<Filme> filmes = filmeDao.getList();

		@SuppressWarnings("serial")
		DefaultTableModel modelo = new DefaultTableModel() {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		modelo.addColumn("ID");
		modelo.addColumn("TÍTULO");
		modelo.addColumn("SINOPSE");
		modelo.addColumn("GÊNERO");
		modelo.addColumn("ONDE ASSISTIR");
		modelo.addColumn("ASSISTIDO");
		modelo.addColumn("AVALIAÇÃO");

		JTable tabela = new JTable(modelo);

		filmes.forEach(filme -> {
			modelo.addRow(new Object[] { filme.getId(), filme.getTitulo(), filme.getSinopse(), filme.getGenero(),
					filme.getOndeAssistir(), filme.isAssistido(), filme.getAvaliacao() });
		});

		JScrollPane scroll = new JScrollPane(tabela);
		add(scroll);
		JPanel painel = new JPanel();
		JButton botaoDeletar = new JButton("deletar");
		botaoDeletar.addActionListener(acaodeletar -> {
			if (tabela.getSelectedRow() >= 0) {
				filmeDao.remover(Long.parseLong(tabela.getValueAt(tabela.getSelectedRow(), 0).toString()));
				Main.tabs.setSelectedIndex(0);
				Main.tabs.setSelectedIndex(1);
			} else {
				System.out.println("selecione uma linha");
			}
		});

		JButton botaoEditar = new JButton("Editar");
		botaoEditar.addActionListener(acaoeditar -> {
			if (tabela.getSelectedRow() >= 0) {
				Long id = Long.parseLong(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
				String titulo = tabela.getValueAt(tabela.getSelectedRow(), 1).toString();
				String sinopse = tabela.getValueAt(tabela.getSelectedRow(), 2).toString();
				String genero = tabela.getValueAt(tabela.getSelectedRow(), 3).toString();

				String ondeAssistir = tabela.getValueAt(tabela.getSelectedRow(), 4).toString();

				boolean assistido = Boolean.parseBoolean(tabela.getValueAt(tabela.getSelectedRow(), 5).toString());
				String avaliacao = tabela.getValueAt(tabela.getSelectedRow(), 6).toString();

				Main.tabs.setSelectedIndex(0);
				JanelaCadastro janelaCadastro = (JanelaCadastro) Main.tabs.getComponent(0);

				JTextField campoid = janelaCadastro.getId();
				campoid.setText(String.valueOf(id));
				
				JTextField campotitulo = janelaCadastro.getTitulo();
				campotitulo.setText(titulo);
				
				JComboBox<String> campogenero = janelaCadastro.getGenero();
				campogenero.setSelectedItem(genero);
				
				JTextField camposinopse = janelaCadastro.getSinopse();
				camposinopse.setText(sinopse);
				
				JCheckBox campoassistido = janelaCadastro.getAssistido();
				campoassistido.setSelected(assistido);
				
				StarRater campoStarRater = janelaCadastro.getStarRater();
				campoStarRater.setRating(Float.valueOf(avaliacao));
				
				for (Enumeration<AbstractButton> buttons = janelaCadastro.getOndeAssistir().getElements(); buttons.hasMoreElements();) {
		            AbstractButton button = buttons.nextElement();

		           if(button.getActionCommand().equalsIgnoreCase(ondeAssistir)) {
		        	   button.setSelected(true);
		           }
		        }
				
				
				
				

			} else {
				System.out.println("selecione uma linha");
			}
		});
		painel.add(botaoDeletar);
		painel.add(botaoEditar);
		add(painel);
	}

}