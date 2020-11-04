package com.pedrozar.functionalities;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.pedrozar.main.Main;

public class CampoAlimento extends JFrame {

	private static final long serialVersionUID = 1L;

	void criarLista() {
		Container janela = getContentPane();
		setLayout(null);
		
		JButton buttonFechar = new JButton("Fechar");
		JButton buttonAdicionar = new JButton("Adicionar");
		
		buttonAdicionar.setBounds((int)(Main.getWIDTH()/5.8),(int)(Main.getHEIGHT()/2.57), 
				Main.getHEIGHT()/7, Main.getHEIGHT()/7/5);
        buttonFechar.setBounds((int)(Main.getWIDTH()/16),(int)(Main.getHEIGHT()/2.57), 
        		Main.getHEIGHT()/7, Main.getHEIGHT()/7/5);
        
        janela.add(buttonFechar);
        janela.add(buttonAdicionar);
		
        buttonFechar.addActionListener(new ActionListener() {
			
        	public void actionPerformed(ActionEvent arg0) {
        		Diary.enabled = true;
				dispose();
			}
        	
        });
        
        buttonAdicionar.addActionListener(new ActionListener() {
			
        	public void actionPerformed(ActionEvent arg0) {

				
			}
        	
        });
        
		setSize(Main.getWIDTH()/3, Main.getHEIGHT()/2);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Alimentos");
	}
}
