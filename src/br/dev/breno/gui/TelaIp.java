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
	private JList<String>  listIp;
	private DefaultListModel<String> modeloSubRedes;
	private JScrollPane scrollIp;
	private JLabel labelClasseIp;
	private JLabel labelBinario;
	private JLabel labelDecimal;
	private JLabel labelHost;
	private JLabel labelErro;
	private JLabel labelSubRede;

	public void CriarTelaIp(String tituloDaTela) {
		this.tituloDaTela = tituloDaTela;

		// Configuração da janela principal
		JFrame tela = new JFrame();
		tela.setTitle(this.tituloDaTela);
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
		labelErro.setBounds(250, 100, 300, 30);
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
		labelSubRede.setBounds(50, 195, 300, 30);
		labelSubRede.setFont(new Font("Arial", Font.BOLD, 17));
		labelSubRede.setVisible(false);
		
		
		modeloSubRedes = new DefaultListModel<>();
        listIp = new JList<>(modeloSubRedes);
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

					if (primeiroOcteto < 1 || primeiroOcteto > 255) {
						labelClasseIp.setText("Verifique se o valor do primeiro octeto é válido");
						labelClasseIp.setForeground(Color.RED);
						labelBinario.setVisible(false);
						labelDecimal.setVisible(false);
						labelHost.setVisible(false);
						labelClasseIp.setVisible(true);
					} else {
						labelClasseIp.setText("Classe do IP: " + addIP.getCp());
						labelClasseIp.setForeground(Color.BLACK);
						int rede = addIP.getIpRede();
						labelSubRede.setText("Total de sub-redes:" + rede);
						labelSubRede.setVisible(true);
						labelClasseIp.setVisible(true);
						labelErro.setVisible(false);

					}

					if (cidr <= 0) {
						labelBinario.setText("Cidr está inválido");
						labelBinario.setForeground(Color.RED);
						labelBinario.setVisible(true);
						labelDecimal.setVisible(false);
						labelHost.setVisible(false);

					}

					else if (cidr > 32) {
						labelBinario.setText("Cidr está inválido");
						labelBinario.setForeground(Color.RED);
						labelBinario.setVisible(true);
						labelDecimal.setVisible(false);
						labelHost.setVisible(false);

					} else {
						// Binário
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

						// Hosts
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

					}

				} catch (Exception erro) {
					labelErro.setText("Erro");
					labelErro.setVisible(true);
					labelClasseIp.setVisible(false);
					labelBinario.setVisible(false);
					labelDecimal.setVisible(false);
					labelHost.setVisible(false);
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