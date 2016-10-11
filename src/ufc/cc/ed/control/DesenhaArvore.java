package ufc.cc.ed.control;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JApplet;

import ufc.cc.ed.model.No;


public class DesenhaArvore extends JApplet {

	private static final long serialVersionUID = 1L;
	
	final Color corPlanoFundo = Color.WHITE;
	
	Graphics2D pintor;
	
	final BasicStroke galhos = new BasicStroke(2.0f);
	int altura, largura;
	No noRaiz = null;
	
	public void iniciaTela(No raiz, int larg) {
		setBackground(corPlanoFundo);
		noRaiz = raiz;
		largura = larg;
	}

	public void paint(Graphics grafico) {
		pintor = (Graphics2D) grafico;
		pintor.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		getSize();
		imprimeValorCirculo(noRaiz, -15, largura, 80);
	}
	
	public void desenhar(int iniTela, int fimTela, int yStringValor, String nomeValor, int ladoPai) {
//	    try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
		pintor.setStroke(galhos);
		pintor.setPaint(Color.GRAY);
		int xStringValor = (iniTela + fimTela) / 2;
//		ladoPai == 1 -> Esquerda
		if (ladoPai == 1){
			System.err.println(nomeValor+ " esquerda do pai");
			pintor.draw(new Line2D.Double(fimTela, yStringValor - 30, xStringValor + 15, yStringValor));
		}
//		ladoPai == 2 -> Direita
		else if (ladoPai == 2){
			System.err.println(nomeValor+ " direita do pai");
			pintor.draw(new Line2D.Double(xStringValor + 15, yStringValor, iniTela + 30, yStringValor - 30));
//		    Documentacao função:
//				x1 the X coordinate of the start point
//				y1 the Y coordinate of the start point
//				x2 the X coordinate of the end point
//				y2 the Y coordinate of the end point
		}
			
		switch (yStringValor%3) {
		case 0:
			pintor.setPaint(new Color(140,101,211));
			break;
		case 1:
			pintor.setPaint(new Color(154,147,236));
			break;
		case 2:
			pintor.setPaint(new Color(202,185,241));
			break;
		default:
			break;
		}
		
		Shape circulo = new Ellipse2D.Double((iniTela + fimTela) / 2, yStringValor, 30, 30);
		pintor.draw(circulo);
		pintor.fill(circulo);
		
		pintor.setPaint(Color.BLACK);
		pintor.drawString(nomeValor, xStringValor + 8, yStringValor + 20);
	}

	void imprimeValorCirculo(No no, int iniTela, int fimTela, int yStringValor) {
		if (no == null)
			return;		
		
		imprimeValorCirculo(no.getEsquerda(), iniTela, (iniTela + fimTela) / 2, yStringValor + 40);
		
		if (no.getPai() == null)
			desenhar(iniTela, fimTela, yStringValor, no.getValor() + "", 0);
		else {
			if (no.getPai().getValor() < no.getValor())
//				desenha valor na direita
				desenhar(iniTela, fimTela, yStringValor, no.getValor() + "", 2);
			else
//				desenha valor na esquerda
				desenhar(iniTela, fimTela, yStringValor, no.getValor() + "", 1);
		}
		imprimeValorCirculo(no.getDireita(), (iniTela + fimTela) / 2, fimTela, yStringValor + 40);
	}
}