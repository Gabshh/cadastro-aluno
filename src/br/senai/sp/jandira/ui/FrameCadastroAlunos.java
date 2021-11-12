package br.senai.sp.jandira.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import br.senai.sp.jandira.model.Aluno;
import br.senai.sp.jandira.model.Periodo;
import br.senai.sp.jandira.repository.AlunoRepository;

public class FrameCadastroAlunos extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtNome;

	private int posicao = 0;

	public FrameCadastroAlunos() {

//		 Ex. Vetor
//		// *****************************************
//		
//		String[] diasDaSemana = {
//			"Domingo",
//			"Segunda",
//			"Terça",
//			"Quarta",
//			"Quinta",
//			"Sexta",
//			"Sábado"
//		};
//		
//		
//		for (String dia : diasDaSemana) {
//			System.out.println(dia);
//		
//		}
//		
//		// *****************************************

		setTitle("Cadastro de Alunos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Matr\u00EDcula:");
		lblNewLabel.setBounds(10, 39, 85, 32);
		contentPane.add(lblNewLabel);

		txtMatricula = new JTextField();
		txtMatricula.setBounds(105, 39, 148, 33);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 82, 85, 32);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(105, 82, 148, 33);
		contentPane.add(txtNome);

		JLabel lblNewLabel_2 = new JLabel("Per\u00EDodo");
		lblNewLabel_2.setBounds(10, 125, 85, 32);
		contentPane.add(lblNewLabel_2);

		// DEFINIR DESCRIÇÃO DO ENUM PERIODO

		JComboBox comboBoxPeriodo = new JComboBox();

		DefaultComboBoxModel<String> modelPeriodo = new DefaultComboBoxModel<String>();

		for (Periodo p : Periodo.values()) {

			modelPeriodo.addElement(p.getDescricao());

		}

		comboBoxPeriodo.setModel(modelPeriodo);

		comboBoxPeriodo.setBounds(105, 125, 148, 32);
		contentPane.add(comboBoxPeriodo);

		// **********************************************

		JButton btnSalvar = new JButton("Salvar Aluno");
		btnSalvar.setBackground(new Color(51, 153, 255));
		btnSalvar.setForeground(new Color(255, 255, 255));
		btnSalvar.setBounds(10, 282, 243, 45);
		contentPane.add(btnSalvar);

		JLabel lblNewLabel_1 = new JLabel("Lista de Alunos:");
		lblNewLabel_1.setBounds(311, 48, 130, 14);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 73, 240, 279);
		contentPane.add(scrollPane);

		JList listAlunos = new JList();
		DefaultListModel<String> listaModel = new DefaultListModel<String>();
		listAlunos.setModel(listaModel);

		scrollPane.setViewportView(listAlunos);
		
//		JButton btnExibirDados = new JButton("Exibir Dados");
//		btnExibirDados.setForeground(Color.WHITE);
//		btnExibirDados.setBackground(new Color(153, 204, 0));
//		btnExibirDados.setBounds(105, 268, 148, 45);
//		contentPane.add(btnExibirDados);

		AlunoRepository turma = new AlunoRepository(3);

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Aluno aluno = new Aluno();
				aluno.setMatricula(txtMatricula.getText());
				aluno.setNome(txtNome.getText());

				// Definir o horário que o aluno estuda

//				aluno.setPeriodo(definirPeriodo(comboBoxPeriodo.getSelectedIndex()));

				aluno.setPeriodo(Periodo.values()[comboBoxPeriodo.getSelectedIndex()]);

				turma.gravar(aluno, posicao);
				posicao++;

				// Adicionar o nome do aluno ao model da lista
				listaModel.addElement(aluno.getNome());
				
				if (posicao == turma.getTamanho()) {
					btnSalvar.setEnabled(false);
					
					JOptionPane.showMessageDialog(null, "A turma ja está cheia!!", "Cheio", JOptionPane.WARNING_MESSAGE);
					
		}
			}
		});

//		btnExibirDados.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
////				for (Aluno aluno : turma.listarTodos()) {
////					System.out.println(aluno.getMatricula());
////					System.out.println(aluno.getNome());
////					System.out.println(aluno.getPeriodo().getDescricao());
////					System.out.println("------------------------");
////				}
//
//			}
//		});
		
		listAlunos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				Aluno aluno = turma.listarAluno(listAlunos.getSelectedIndex());
				txtMatricula.setText(aluno.getMatricula());
				txtNome.setText(aluno.getNome());
		
				comboBoxPeriodo.setSelectedIndex(aluno.getPeriodo().ordinal());

			}
		});

	}

	//Método alternativo para pegar enum
//	private Periodo definirPeriodo(int periodoSelecionado) {
//		if (periodoSelecionado == 0) {
//			return Periodo.MANHA;
//		} else if (periodoSelecionado == 1) {
//			return Periodo.TARDE;
//		} else if (periodoSelecionado == 2) {
//			return Periodo.NOITE;
//		} else {
//			return Periodo.SABADO;
//		}
//
//	}
}
