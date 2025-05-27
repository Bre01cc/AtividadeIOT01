package br.dev.breno.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.dev.breno.model.ClassificarEnderecoIP;

public class TelaIp {
	private JLabel labelIpAddress;
	private JTextField textIpAddres;
	private String tituloDaTela;
	private JButton buttonResultado;
	private JButton buttonLimpar;
	private JLabel labelClasseIp;
	private JLabel labelBinario;
	private JLabel labelDecimal;
	private JLabel labelHost;

	private JLabel labelErro;

	public void CriarTelaIp(String tituloDaTela) {
		this.tituloDaTela = tituloDaTela;
		JFrame tela = new JFrame();
		tela.setTitle(this.tituloDaTela);
		tela.setSize(500, 400);
		tela.setResizable(false);
		tela.setLayout(null);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = tela.getContentPane();

		// LABEL DO IP
		labelIpAddress = new JLabel();
		labelIpAddress.setBounds(50, 20, 100, 20);
		labelIpAddress.setFont(new Font("Arial", 1, 14));
		labelIpAddress.setText("INSIRA O IP");

		// LABEL MENSAGEM DE ERRO
		labelErro = new JLabel();
		labelErro.setFont(new Font("Arial", 1, 20));
		labelErro.setForeground(Color.RED);
		labelErro.setBounds(50, 90, 100, 200);
		labelErro.setVisible(false);

		// LABEL ClasseIp
		labelClasseIp = new JLabel();
		labelClasseIp.setBounds(50, 90, 200, 200);
		labelClasseIp.setFont(new Font("Arial", 1, 15));
		labelClasseIp.setVisible(false);

		// LABEL Binario
		labelBinario = new JLabel();
		labelBinario.setBounds(50, 70, 400, 200);
		labelBinario.setFont(new Font("Arial", 1, 13));
		labelBinario.setVisible(false);

		// LABEL Decimal
		labelDecimal = new JLabel();
		labelDecimal.setBounds(50, 60, 200, 200);
		labelDecimal.setFont(new Font("Arial", 1, 15));
		labelDecimal.setVisible(false);

		// LABEL Host
		labelHost = new JLabel();
		labelHost.setBounds(50, 110, 200, 200);
		labelHost.setFont(new Font("Arial", 1, 15));
		labelHost.setVisible(false);

		// CAIXA DE IP
		textIpAddres = new JTextField();
		textIpAddres.setBounds(50, 40, 300, 30);

		// Button resultado
		buttonResultado = new JButton();
		buttonResultado.setText("Exibir");
		buttonResultado.setBounds(50, 275, 140, 35);

		// Button limpar
		buttonLimpar = new JButton();
		buttonLimpar.setText("Limpar");
		buttonLimpar.setBounds(210, 275, 140, 35);

		buttonLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textIpAddres.setText(null);

			}

		});

		buttonResultado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					ClassificarEnderecoIP addIP = new ClassificarEnderecoIP();

					// Classe
					String caixaText = textIpAddres.getText();
					addIP.setIp(caixaText);
					String ip = addIP.getIp();
					addIP.SeparadorDeIPClasse(ip);
					String classe = addIP.classificarIp();
					labelClasseIp.setText("Classe do IP: " + classe);
					labelClasseIp.setVisible(true);

					// Binario
					addIP.SeparaIPCidr(ip);
					int cidr = addIP.getCidr();
					addIP.converterBinario(cidr);
					String binario = addIP.getMascaraBinario();
					labelBinario.setText("Máscara em binario" + binario);
					labelBinario.setVisible(true);

					// Decimal

					// Host

				

				} catch (Exception erro) {
					labelErro.setText("Erro");
					labelErro.setVisible(true);

				}

			}

		});

		container.add(labelIpAddress);
		container.add(textIpAddres);
		container.add(buttonLimpar);
		container.add(labelErro);
		container.add(labelClasseIp);
		container.add(labelHost);
		container.add(labelDecimal);
		container.add(labelBinario);
		container.add(buttonResultado);

		tela.setVisible(true);
	}

}
