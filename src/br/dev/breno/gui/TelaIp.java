package br.dev.breno.gui;

import br.dev.breno.model.ClassificarEnderecoIP;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import br.dev.breno.model.ClassificarEnderecoIP;

public class TelaIp {
	private JLabel labelIpAddress;
	private JTextField textIpAddres;
	private String tituloDaTela;
	private JButton buttonResultado;
	private JButton buttonLimpar;
	private JList<String> listIp;
	private DefaultListModel<String> modeloSubRedes;
	private JScrollPane scrollIp;
	private JLabel labelClasseIp;
	private JLabel labelBinario;
	private JLabel labelDecimal;
	private JLabel labelHost;
	private JLabel labelErro;
	private JLabel labelSubRede;

	public TelaIp() {
		CriarTelaIp();
	}

	public void CriarTelaIp() {

		// Configuração da janela principal
		JFrame tela = new JFrame("Calculador de ip");
		tela.setSize(570, 440);
		tela.setResizable(false);
		tela.setLayout(null);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = tela.getContentPane();

		// Campo de entrada do IP
		labelIpAddress = new JLabel("INSIRA O IP");
		labelIpAddress.setFont(new Font("Arial", Font.BOLD, 16));
		labelIpAddress.setBounds(50, 10, 100, 20);

		// Campo de entrada de ip
		textIpAddres = new JTextField();
		textIpAddres.setFont(new Font("Arial", Font.BOLD, 15));
		textIpAddres.setBounds(50, 30, 400, 30);

		// Botão "Exibir"
		buttonResultado = new JButton("Exibir");
		buttonResultado.setBounds(80, 350, 160, 35);

		// Botão "Limpar"
		buttonLimpar = new JButton("Limpar");
		buttonLimpar.setBounds(260, 350, 160, 35);

		// Mensagem de erro
		labelErro = new JLabel();
		labelErro.setFont(new Font("Arial", Font.BOLD, 20));
		labelErro.setForeground(Color.RED);
		labelErro.setBounds(50, 100, 500, 30);
		labelErro.setVisible(false);

		// Labels de resultado
		labelClasseIp = new JLabel();
		labelClasseIp.setFont(new Font("Arial", Font.BOLD, 17));
		labelClasseIp.setBounds(50, 75, 400, 30);
		labelClasseIp.setVisible(false);

		labelDecimal = new JLabel();
		labelDecimal.setFont(new Font("Arial", Font.BOLD, 17));
		labelDecimal.setBounds(50, 105, 400, 30);
		labelDecimal.setVisible(false);

		labelBinario = new JLabel();
		labelBinario.setFont(new Font("Arial", Font.BOLD, 16));
		labelBinario.setBounds(50, 135, 500, 30);
		labelBinario.setVisible(false);

		labelHost = new JLabel();
		labelHost.setFont(new Font("Arial", Font.BOLD, 17));
		labelHost.setBounds(50, 165, 400, 30);
		labelHost.setVisible(false);

		// Label ip de rede
		labelSubRede = new JLabel();
		labelSubRede.setBounds(50, 190, 300, 30);
		labelSubRede.setFont(new Font("Arial", Font.BOLD, 17));
		labelSubRede.setVisible(false);

		// Lista de sub-redes
		// Criando um novo DefaultListModel com <> vazio, pois o tipo (String) já foi definido na declaração da variável modeloSubRedes. 
		modeloSubRedes = new DefaultListModel<>();
		// Criando um JList que recebe modeloSubRedes no construtor, ou seja, o componente será criado já com esse modelo de dados.
		listIp = new JList<>(modeloSubRedes);
		// Adicionando listIp em um JScrollPane para permitir rolagem caso o conteúdo fique grande.
		scrollIp = new JScrollPane(listIp);
		scrollIp.setBounds(30, 220, 500, 120);

		// Ação do botão "Limpar"
		buttonLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textIpAddres.setText("");
				labelClasseIp.setText("");
				labelBinario.setText("");
				labelDecimal.setText("");
				labelHost.setText("");
				labelErro.setText("");
				labelSubRede.setText("");
				modeloSubRedes.clear();

			}
		});

		// Ação do botão "Exibir"
		buttonResultado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ClassificarEnderecoIP addIP = new ClassificarEnderecoIP();
					String caixaText = textIpAddres.getText();

					addIP.setIp(caixaText);
					String ip = addIP.getIp();

					addIP.SeparadorDeIPClasse(ip);
					int primeiroOcteto = addIP.getPrimeiroOcteto();
					addIP.classificarIp(primeiroOcteto);

					addIP.SeparaIPCidr(ip);
					int cidr = addIP.getCidr();
					addIP.calculoDeSubRede();

					// verificando se o primeiro octeto e cidr é valido
					// & significa E, no caso só vai classificar se ambas as codições forem
					// verdadeiras
					if ((primeiroOcteto >= 1 && primeiroOcteto <= 254) && (cidr > 0 && cidr <= 32)) {
						labelClasseIp.setText("Classe do IP: " + addIP.getClasseIP());
						labelClasseIp.setForeground(Color.BLACK);
						labelClasseIp.setVisible(true);
						int rede = addIP.getIpRede();
						labelSubRede.setText("Total de sub-redes:" + rede);
						labelSubRede.setVisible(true);

						if ((cidr > 24 && cidr < 31) && (addIP.getClasseIP().equals("C"))) {
						  //Quando o botão for pressionado vai limpar a lista, pois, se tiver uma lista previamnte criada ela sera limpa e substituida por outra
							modeloSubRedes.clear();
							//ModeloSubRede está recebendo o gerarListaSubRedes, no caso armazenando esses
							modeloSubRedes = addIP.gerarListaSubRedes();
							listIp.setModel(modeloSubRedes);
						}
						if ((!addIP.getClasseIP().equals("C"))||(cidr<25)) {
							modeloSubRedes.clear();
						}
						// Binario
						addIP.converterBinario(cidr);
						String binario = addIP.getMascaraBinario();
						labelBinario.setText("Máscara em binário: " + binario);
						labelBinario.setForeground(Color.BLACK);
						labelBinario.setVisible(true);

						// Decimal
						addIP.converterCidrDecimal(binario);
						String decimal = addIP.getMascaraDecimal();
						labelDecimal.setText("Máscara decimal: " + decimal);
						labelDecimal.setForeground(Color.BLACK);
						labelDecimal.setVisible(true);

						// Host
						if (cidr <= 30) {
							addIP.IpHost(cidr);
							Double host = addIP.getIpDisponiveis();
							DecimalFormat df = new DecimalFormat("#,###");
							labelHost.setText("IPs disponíveis para host: " + df.format(host));
							labelHost.setForeground(Color.BLACK);
							labelHost.setVisible(true);

						} else if (cidr >= 31) {
							labelHost.setText("Ips para host indisponiveis");
							labelHost.setForeground(Color.red);
							labelHost.setVisible(true);
						}

						labelErro.setVisible(false);
//						} 
					}

					else {
						labelClasseIp.setText("Verifique se o valor do cidr é válido");
						labelClasseIp.setForeground(Color.RED);
						labelBinario.setVisible(false);
						labelDecimal.setVisible(false);
						labelHost.setVisible(false);
						labelClasseIp.setVisible(true);
						labelSubRede.setVisible(false);

					}
				} catch (Exception erro) {
					labelErro.setText("Verifique se o IP foi digitado corretamente");
					labelErro.setVisible(true);
					labelClasseIp.setVisible(false);
					labelBinario.setVisible(false);
					labelDecimal.setVisible(false);
					labelHost.setVisible(false);
					labelSubRede.setVisible(false);
					modeloSubRedes.clear();
				}
			}
		});

		// Adicionando componentes ao container
		container.add(labelIpAddress);
		container.add(textIpAddres);
		container.add(buttonResultado);
		container.add(buttonLimpar);
		container.add(labelErro);
		container.add(labelClasseIp);
		container.add(labelBinario);
		container.add(labelDecimal);
		container.add(labelHost);
		container.add(scrollIp);
		container.add(labelSubRede);

		tela.setVisible(true);
	}
}