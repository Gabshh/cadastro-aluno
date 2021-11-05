package br.senai.sp.jandira.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import br.senai.sp.jandira.model.Periodo;

public class FrameCadastroAlunos extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtNome;

	public FrameCadastroAlunos() {
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
		txtMatricula.setBounds(105, 39, 114, 33);
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
		
		JComboBox comboBoxPeriodo = new JComboBox();
		comboBoxPeriodo.setModel(new DefaultComboBoxModel(Periodo.values()));
		comboBoxPeriodo.setBounds(105, 125, 114, 32);
		contentPane.add(comboBoxPeriodo);
		
		JButton btnSalvar = new JButton("Salvar Aluno");
		btnSalvar.setBackground(new Color(153, 204, 0));
		btnSalvar.setForeground(new Color(255, 255, 255));
		btnSalvar.setBounds(105, 189, 148, 45);
		contentPane.add(btnSalvar);
		
		JLabel lblNewLabel_1 = new JLabel("Lista de Alunos:");
		lblNewLabel_1.setBounds(311, 48, 130, 14);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 73, 240, 279);
		contentPane.add(scrollPane);
		
		JList listAlunos = new JList();
		scrollPane.setViewportView(listAlunos);
	}
}
