package com.pedrozar.functionalities;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.pedrozar.entities.User;
import com.pedrozar.main.Main;
import com.pedrozar.main.Save;

public class CampoAlimento extends JFrame {

	private static final long serialVersionUID = 1L;
	public static JTextField textSearch;
	int addedFoods = 0;
	
	public CampoAlimento(int addedFoods) {
		super();
		this.addedFoods = addedFoods;
	}
	
	void criarLista() {
		Container janela = getContentPane();
		setLayout(null);
		
		JLabel labelProt = new JLabel("Proteínas: 0g");
		JLabel labelCarb = new JLabel("Carboidratos: 0g");
		JLabel labelFats = new JLabel("Gorduras: 0g");
		JLabel labelCal = new JLabel("Calorias: 0g");
		JLabel labelWeig = new JLabel("Peso: 0g");
	
		DefaultListModel<String> list = new DefaultListModel<>();	
		JList<String> listFood = new JList<>(list);
		
		
		textSearch = new JTextField();
		
		JButton buttonSearch = new JButton("Procurar");
		JButton buttonFechar = new JButton("Fechar");
		JButton buttonAdicionar = new JButton("Adicionar");
		double a = 2.1, b = 7.2, x = 25;
		int c = 64;
		labelProt.setBounds((int)(Main.getWIDTH()/a),
				(int) (Main.getWIDTH()/8 + (x * -2)),(int)(Main.getHEIGHT()/b),(Main.getWIDTH()/c));
		labelCarb.setBounds((int)(Main.getWIDTH()/a),
				(int)(Main.getWIDTH()/8 + (x * -1)),(int)(Main.getHEIGHT()/b),(Main.getWIDTH()/c));
		labelFats.setBounds((int)(Main.getWIDTH()/a),
				(int) (Main.getWIDTH()/8),(int)(Main.getHEIGHT()/b),(Main.getWIDTH()/c));
		labelCal.setBounds((int)(Main.getWIDTH()/a),
				(int) (Main.getWIDTH()/8 + (x * 1)),(int)(Main.getHEIGHT()/b),(Main.getWIDTH()/c));
		labelWeig.setBounds((int)(Main.getWIDTH()/a),
				(int) (Main.getWIDTH()/8 + (x * 2)),(int)(Main.getHEIGHT()/b),(Main.getWIDTH()/c));
		
		textSearch.setBounds((int)(Main.getWIDTH()/30),
				(Main.getWIDTH()/32),(int)(Main.getHEIGHT()/3),(Main.getWIDTH()/c));

		listFood.setBounds((int)(Main.getWIDTH()/30), (Main.getWIDTH()/16), 550, 200);

		buttonAdicionar.setBounds((int)(Main.getWIDTH()/5.8),
				(int)(Main.getHEIGHT()/2.57), 
				Main.getHEIGHT()/7, Main.getHEIGHT()/7/5);
		buttonSearch.setBounds((int)(Main.getWIDTH()/4),
				(int)(Main.getWIDTH()/32), 
				Main.getHEIGHT()/8, Main.getHEIGHT()/7/5);
        buttonFechar.setBounds((int)(Main.getWIDTH()/16),
        		(int)(Main.getHEIGHT()/2.57), 
        		Main.getHEIGHT()/7, Main.getHEIGHT()/7/5);

        janela.add(listFood);
        janela.add(buttonFechar);
        janela.add(buttonAdicionar);
        janela.add(buttonSearch);
        janela.add(textSearch);
		janela.add(labelWeig);
		janela.add(labelCal);
		janela.add(labelFats);
		janela.add(labelCarb);
		janela.add(labelProt);
		
		listFood.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {
				int i = listFood.getSelectedIndex(); 
		        labelProt.setText("Proteínas: " + Diary.foodList[i][4] + "g");
		        labelCal.setText("Calorías: " + Diary.foodList[i][1]);
		        labelFats.setText("Gorduras: " + Diary.foodList[i][5] + "g");
		        labelCarb.setText("Carboidratos: " + Diary.foodList[i][3] + "g");
		        labelWeig.setText("Peso: " + Diary.foodList[i][2] + "g");
			}
			
		});
		
        buttonFechar.addActionListener(new ActionListener() {
			
        	public void actionPerformed(ActionEvent arg0) {
        		Diary.enabled = true;
				dispose();
			}
        	
        });
        
        buttonAdicionar.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent arg0) {
        		int i = listFood.getSelectedIndex();
        		Diary.alimentos[Diary.contadorFood] = Diary.foodList[i][0];
        		Diary.carbCon[Diary.contadorFood] = Double.parseDouble(Diary.foodList[i][3]);
        		Diary.fatCon[Diary.contadorFood] = Double.parseDouble(Diary.foodList[i][5]);
        		Diary.protCon[Diary.contadorFood] = Double.parseDouble(Diary.foodList[i][4]);
        		Diary.contadorFood++;
        		User.setDcalories(Integer.parseInt(Diary.foodList[i][1]), true);
        	}
    	
        });
        
		buttonSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				Save.loadRegister(list);   
			}
			
		});
        
		setSize((int)(Main.getWIDTH()/1.7), Main.getHEIGHT()/2);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Alimentos");
	}
}
