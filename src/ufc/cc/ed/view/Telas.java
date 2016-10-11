package ufc.cc.ed.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ufc.cc.ed.control.DesenhaArvore;
import ufc.cc.ed.model.Arvore;
import ufc.cc.ed.model.No;

public class Telas extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private static Arvore arvore;
	private DesenhaArvore desenhaArvore;	
	private JPanel painelCRUD;
	private JButton btInserir, btBusca, btExcluir;
	private JTextField tfCampo;
	
	Toolkit dimensoesTela = Toolkit.getDefaultToolkit();  
		private int larguraTela = ((int) dimensoesTela.getScreenSize().getWidth());
		private int alturaTela = ((int) dimensoesTela.getScreenSize().getHeight());

	public Telas(){
		arvore = new Arvore();
		desenhaArvore = new DesenhaArvore();
		painelCRUD = new JPanel();
		
//		INSERIR
//		for (int i = 1; i < 64 ; i++) { arvore.inserir(i); }		
		
		this.setSize(larguraTela,alturaTela);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Árvore AVL - Estrutura de Dados - CC / Ronildo Oliveira - 366763");
		this.setBackground(new Color(255, 255, 255));
		
		this.add(painelCRUD);
		
		Border borda = BorderFactory.createEmptyBorder(5,0,0,0);

		painelCRUD.setBorder(borda);
		painelCRUD.setSize(larguraTela,50);
		painelCRUD.setLocation(-20, 0);
		painelCRUD.setBackground(new Color(154,147,236));
		
		tfCampo = new JTextField(5);
		painelCRUD.add(tfCampo);
		
		btInserir = new JButton("Inserir");
		btInserir.addActionListener(this);
		painelCRUD.add(btInserir);
		
		btBusca = new JButton("Procurar");
		btBusca.addActionListener(this);
		painelCRUD.add(btBusca);
				
		btExcluir = new JButton("Excluir");		
		btExcluir.addActionListener(this);	
		painelCRUD.add(btExcluir);
		
		atualizarArvore();
	}

	public void actionPerformed(ActionEvent e){		
		if (e.getSource() == btInserir){
			try {
				int num = Integer.parseInt(tfCampo.getText());
				arvore.inserir(num);
			} catch (NumberFormatException numForm) {
				
			}finally{
				atualizarArvore();
				limpaCampoTexto(tfCampo);
			}
			
		}else if(e.getSource() == btBusca){
			try {
				int num = Integer.parseInt(tfCampo.getText());				
				if(arvore.procurar(num, arvore.getNoRraiz()) == null)
					JOptionPane.showMessageDialog(null,"Nada Encontrado");
				else{
				No n = arvore.procurar(num, arvore.getNoRraiz());
				JOptionPane.showMessageDialog(null,"Encontrado:" +
							"\nPai..................: "+ n.getPai()+
							"\nFilho Direita.....: " +n.getDireita()+
							"\nFilho Esquerda.: " +n.getEsquerda());
				}
				
			} catch (NumberFormatException numForm) {
				// TODO: handle exception
			}finally{
				limpaCampoTexto(tfCampo);
			}			
						
		}else if(e.getSource() == btExcluir){
			try {
				int num = Integer.parseInt(tfCampo.getText());
				No noExcluir = arvore.procurar(num, arvore.getNoRraiz());
				if(noExcluir == null)
					JOptionPane.showMessageDialog(null,"Nada Encontrado");
				else{
					arvore.remover(num);
				}				
			} catch (NumberFormatException numForm) {
				
			}finally{
				limpaCampoTexto(tfCampo);
				atualizarArvore();
			}			
		}
	}

	private void limpaCampoTexto(JTextField tfCampo2) {
		tfCampo2.setText(null);
		tfCampo2.requestFocus();		
	}

	private void atualizarArvore() {		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { }
		});
		this.add(painelCRUD);
		this.getContentPane().add("Center", desenhaArvore);
		desenhaArvore.iniciaTela(arvore.getNoRraiz(),larguraTela-50);
		this.pack();
		this.setSize(new Dimension(larguraTela,alturaTela));
		this.setVisible(true);	
	}
}