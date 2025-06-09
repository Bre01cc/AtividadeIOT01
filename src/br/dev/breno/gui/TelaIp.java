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

		// Configuração da janela principal da aplicação
		JFrame tela = new JFrame("Calculador de ip");
		tela.setSize(570, 440);
		tela.setResizable(false);
		tela.setLayout(null);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = tela.getContentPane();

		// Label para indicar o campo de entrada do IP
		labelIpAddress = new JLabel("INSIRA O IP");
		labelIpAddress.setFont(new Font("Arial", Font.BOLD, 16));
		labelIpAddress.setBounds(50, 10, 100, 20);

		// Campo de texto para o usuário digitar o IP com máscara CIDR
		textIpAddres = new JTextField();
		textIpAddres.setFont(new Font("Arial", Font.BOLD, 15));
		textIpAddres.setBounds(50, 30, 400, 30);

		// Botão que, ao ser clicado, executa o cálculo e exibe os resultados
		buttonResultado = new JButton("Exibir");
		buttonResultado.setBounds(80, 350, 160, 35);

		// Botão para limpar os campos e resultados exibidos
		buttonLimpar = new JButton("Limpar");
		buttonLimpar.setBounds(260, 350, 160, 35);

		// Label para exibir mensagens de erro ao usuário
		labelErro = new JLabel();
		labelErro.setFont(new Font("Arial", Font.BOLD, 20));
		labelErro.setForeground(Color.RED);
		labelErro.setBounds(50, 100, 500, 30);
		labelErro.setVisible(false);

		// Labels que exibem os resultados do cálculo de IP e máscara
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

		// Label para mostrar o total de sub-redes calculadas
		labelSubRede = new JLabel();
		labelSubRede.setBounds(50, 190, 300, 30);
		labelSubRede.setFont(new Font("Arial", Font.BOLD, 17));
		labelSubRede.setVisible(false);

		// Modelo e lista para exibir as sub-redes geradas (quando aplicável)
		modeloSubRedes = new DefaultListModel<>();
		listIp = new JList<>(modeloSubRedes);
		scrollIp = new JScrollPane(listIp);
		scrollIp.setBounds(30, 220, 500, 120);

		// Evento do botão "Limpar" que reseta todos os campos e resultados na tela
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

				// Esconde os labels de resultado e erro
				labelClasseIp.setVisible(false);
				labelBinario.setVisible(false);
				labelDecimal.setVisible(false);
				labelHost.setVisible(false);
				labelErro.setVisible(false);
				labelSubRede.setVisible(false);
			}
		});

		// Evento do botão "Exibir" que realiza a análise do IP e atualiza a interface
		buttonResultado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ClassificarEnderecoIP addIP = new ClassificarEnderecoIP();
					String caixaText = textIpAddres.getText();

					addIP.setIp(caixaText);
					String ip = addIP.getIp();

					// Extrai e classifica o IP pelo primeiro octeto
					addIP.SeparadorDeIPClasse(ip);
					int primeiroOcteto = addIP.getPrimeiroOcteto();
					addIP.classificarIp(primeiroOcteto);

					// Extrai o CIDR e calcula as sub-redes
					addIP.SeparaIPCidr(ip);
					int cidr = addIP.getCidr();
					addIP.calculoDeSubRede();

					// Verifica se o primeiro octeto e o CIDR são válidos antes de mostrar resultados
					if ((primeiroOcteto >= 1 && primeiroOcteto <= 254) && (cidr > 0 && cidr <= 32)) {
						// Exibe a classe do IP
						labelClasseIp.setText("Classe do IP: " + addIP.getClasseIP());
						labelClasseIp.setForeground(Color.BLACK);
						labelClasseIp.setVisible(true);

						// Exibe o total de sub-redes calculadas
						int rede = addIP.getIpRede();
						labelSubRede.setText("Total de sub-redes: " + rede);
						labelSubRede.setVisible(true);

						// Caso IP classe C e máscara entre /25 e /30, exibe lista de sub-redes
						if ((cidr > 24 && cidr < 31) && (addIP.getClasseIP().equals("C"))) {
							modeloSubRedes.clear();
							modeloSubRedes = addIP.gerarListaSubRedes();
							listIp.setModel(modeloSubRedes);
						}

						// Se não for classe C ou máscara for menor que /25, limpa a lista de sub-redes
						if ((!addIP.getClasseIP().equals("C")) || (cidr < 25)) {
							modeloSubRedes.clear();
						}

						// Converte e exibe a máscara em binário
						addIP.converterBinario(cidr);
						String binario = addIP.getMascaraBinario();
						labelBinario.setText("Máscara em binário: " + binario);
						labelBinario.setForeground(Color.BLACK);
						labelBinario.setVisible(true);

						// Converte e exibe a máscara em decimal
						addIP.converterCidrDecimal(binario);
						String decimal = addIP.getMascaraDecimal();
						labelDecimal.setText("Máscara decimal: " + decimal);
						labelDecimal.setForeground(Color.BLACK);
						labelDecimal.setVisible(true);

						// Calcula e exibe o número de IPs disponíveis para host (quando aplicável)
						if (cidr <= 30) {
							addIP.IpHost(cidr);
							Double host = addIP.getIpDisponiveis();
							DecimalFormat df = new DecimalFormat("#,###");
							labelHost.setText("IPs disponíveis para host: " + df.format(host));
							labelHost.setForeground(Color.BLACK);
							labelHost.setVisible(true);

						} else if (cidr >= 31) {
							labelHost.setText("IPs para host indisponíveis");
							labelHost.setForeground(Color.RED);
							labelHost.setVisible(true);
						}

						// Esconde a mensagem de erro
						labelErro.setVisible(false);

					} else {
						// Mensagem para indicar que o CIDR ou o primeiro octeto são inválidos
						labelClasseIp.setText("Verifique se o valor do CIDR é válido");
						labelClasseIp.setForeground(Color.RED);
						labelBinario.setVisible(false);
						labelDecimal.setVisible(false);
						labelHost.setVisible(false);
						labelClasseIp.setVisible(true);
						labelSubRede.setVisible(false);
						modeloSubRedes.clear();
					}
				} catch (Exception erro) {
					// Exibe mensagem de erro caso o IP seja inválido ou ocorra exceção
					labelErro.setText("Verifique se o IP foi digitado corretamente");
					labelErro.setVisible(true);

					// Esconde os labels de resultado
					labelClasseIp.setVisible(false);
					labelBinario.setVisible(false);
					labelDecimal.setVisible(false);
					labelHost.setVisible(false);
					labelSubRede.setVisible(false);

					modeloSubRedes.clear();
				}
			}
		});

		// Adiciona todos os componentes configurados ao container da janela
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

		// Exibe a janela principal
		tela.setVisible(true);
	}
}
