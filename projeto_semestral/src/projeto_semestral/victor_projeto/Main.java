package projeto_semestral.victor_projeto;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Main {

	public static void main(String[] args) {
		JFrame janela = new JFrame("projeto semestral Victor Barradas RM:81920");
		JTabbedPane tabs = new JTabbedPane();
		tabs.add("Cadastro",new JanelaCadastro());
		tabs.add("Lista",new JanelaLista());

		janela.add(tabs);
		janela.setSize(800, 350);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		janela.setResizable(false);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
