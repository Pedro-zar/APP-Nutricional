package com.pedrozar.functionalities;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.text.MaskFormatter;

import com.pedrozar.main.Main;

public class CampoFinalizarRegistro extends JFrame {

	private static final long serialVersionUID = 1L;

	public void criarRegistro() {
		
		Container janela = getContentPane();
		setLayout(null);
		
		JLabel labelOpt = new JLabel("Você quer: ");
		JLabel labelDiv = new JLabel("Escolha seu tipo de dieta:");
		JLabel labelAtivFis = new JLabel("Qual seu nível de atividade física?");
		JLabel labelPeso = new JLabel("Qual sua meta de peso?");
		
		MaskFormatter mascaraWeight = null;
		
		JButton buttonCancelar = new JButton("Cancelar");
		JButton buttonRegistrar = new JButton("Registrar");

		JRadioButton jrbLow = new JRadioButton("Low Carb");
		JRadioButton jrbMed = new JRadioButton("Medium Carb");
		JRadioButton jrbHigh = new JRadioButton("High Carb");
		ButtonGroup bgNutDiv = new ButtonGroup();
		JRadioButton jrbLoss = new JRadioButton("Perder peso");
		JRadioButton jrbMain = new JRadioButton("Manter peso");
		JRadioButton jrbGain = new JRadioButton("Ganhar massa");
		ButtonGroup bgWeightOpt = new ButtonGroup();

		JRadioButton jrbSedentary = new JRadioButton("Sedentário");
		JRadioButton jrbLeve = new JRadioButton("Exercício leve (1-2 vezes por semana)");
		JRadioButton jrbModerado = new JRadioButton("Exercício moderado (3-5 vezes por semana)");
		JRadioButton jrbPesado = new JRadioButton("Exercício pesado (6-7 vezes por semana)");
		JRadioButton jrbAthlete = new JRadioButton("Atleta (2x por dia)");
		ButtonGroup bgAtivFis = new ButtonGroup();
		
		labelOpt.setBounds(Main.getHEIGHT()/36,Main.getHEIGHT()/36 * 6,
				(int)(Main.getWIDTH()/7.2),(Main.getWIDTH()/16/4));
		labelAtivFis.setBounds(Main.getHEIGHT()/36,Main.getHEIGHT()/36 * 11,
        		(int)(Main.getWIDTH()/5),(Main.getWIDTH()/16/4));
		labelDiv.setBounds(Main.getHEIGHT()/36,Main.getHEIGHT()/36,
				(int)(Main.getWIDTH()/7.2),(Main.getWIDTH()/16/4));
		labelPeso.setBounds(Main.getHEIGHT()/36,Main.getHEIGHT()/36 * 18,
        		(int)(Main.getWIDTH()/5),(Main.getWIDTH()/16/4));
		
		jrbLow.setBounds((int)(Main.getHEIGHT()/40),Main.getHEIGHT()/36 * 2,
        		(int)(Main.getHEIGHT()/8),(Main.getWIDTH()/16/4));
        jrbMed.setBounds((int)(Main.getHEIGHT()/40),Main.getHEIGHT()/36 * 3,
        		(int)(Main.getHEIGHT()/6),(Main.getWIDTH()/16/4));
		jrbHigh.setBounds((int)(Main.getHEIGHT()/40),Main.getHEIGHT()/36 * 4,
        		(int)(Main.getHEIGHT()/8),(Main.getWIDTH()/16/4));
		
		jrbLoss.setBounds((int)(Main.getHEIGHT()/40),Main.getHEIGHT()/36 * 7,
        		(int)(Main.getHEIGHT()/5),(Main.getWIDTH()/16/4));
        jrbMain.setBounds((int)(Main.getHEIGHT()/40),Main.getHEIGHT()/36 * 8,
        		(int)(Main.getHEIGHT()/5),(Main.getWIDTH()/16/4));
		jrbGain.setBounds((int)(Main.getHEIGHT()/40),Main.getHEIGHT()/36 * 9,
        		(int)(Main.getHEIGHT()/5),(Main.getWIDTH()/16/4));
		
		jrbSedentary.setBounds((int)(Main.getHEIGHT()/40),Main.getHEIGHT()/36 * 12,
        		(int)(Main.getHEIGHT()/5),(Main.getWIDTH()/16/4));
        jrbLeve.setBounds((int)(Main.getHEIGHT()/40),Main.getHEIGHT()/36 * 13,
        		(int)(Main.getHEIGHT()/2),(Main.getWIDTH()/16/4));
		jrbModerado.setBounds((int)(Main.getHEIGHT()/40),Main.getHEIGHT()/36 * 14,
        		(int)(Main.getHEIGHT()/2),(Main.getWIDTH()/16/4));
		jrbPesado.setBounds((int)(Main.getHEIGHT()/40),Main.getHEIGHT()/36 * 15,
        		(int)(Main.getHEIGHT()/2),(Main.getWIDTH()/16/4));
        jrbAthlete.setBounds((int)(Main.getHEIGHT()/40),Main.getHEIGHT()/36 * 16,
        		(int)(Main.getHEIGHT()/5.4),(Main.getWIDTH()/16/4));
		
		buttonRegistrar.setBounds((int)(Main.getHEIGHT()/4.8),(int)(Main.getHEIGHT()/1.7), 
        		(int)(Main.getHEIGHT()/7.2), (Main.getWIDTH()/16/4));
		buttonCancelar.setBounds((int)(Main.getHEIGHT()/16),(int)(Main.getHEIGHT()/1.7), 
        		(int)(Main.getHEIGHT()/7.2), (Main.getWIDTH()/16/4));
        
		try{
        	mascaraWeight = new MaskFormatter("###");
        	mascaraWeight.setPlaceholderCharacter('_');
        }
        catch(ParseException excp) {
        	System.err.println("Erro na formatação: " + excp.getMessage());
        	System.exit(-1);
        }
		
		JFormattedTextField jFormattedTextWeight = new JFormattedTextField(mascaraWeight);
		jFormattedTextWeight.setBounds(Main.getHEIGHT() / 36,Main.getHEIGHT()/36 * 19,
				(int)(Main.getHEIGHT()*0.04),(Main.getWIDTH()/16/4));
		
		janela.add(labelPeso);
		janela.add(jFormattedTextWeight);
		janela.add(buttonCancelar);
		janela.add(labelAtivFis);
		janela.add(labelDiv);
		janela.add(buttonRegistrar);
		janela.add(jrbLow);
		janela.add(jrbMed);
		janela.add(jrbHigh);
		janela.add(jrbLoss);
		janela.add(jrbGain);
		janela.add(jrbMain);
		janela.add(labelOpt);
		janela.add(jrbAthlete);
		janela.add(jrbLeve);
		janela.add(jrbModerado);
		janela.add(jrbPesado);
		janela.add(jrbSedentary);

		bgAtivFis.add(jrbSedentary);
		bgAtivFis.add(jrbLeve);
		bgAtivFis.add(jrbModerado);
		bgAtivFis.add(jrbPesado);
		bgAtivFis.add(jrbAthlete);
		bgNutDiv.add(jrbLow);
		bgNutDiv.add(jrbMed);
		bgNutDiv.add(jrbHigh);
		bgWeightOpt.add(jrbLoss);
		bgWeightOpt.add(jrbMain);
		bgWeightOpt.add(jrbGain);
	
		buttonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				FunctionalitySelector.running = true;
			}
			
		});
		
		buttonRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(verificarCampos()) {
					if(jrbLow.isSelected())
						Diary.nutrientsDivision = 1;
					else if(jrbMed.isSelected())
						Diary.nutrientsDivision = 2;
					else
						Diary.nutrientsDivision = 3;
					if(jrbLoss.isSelected())
						Diary.dietOption = -1;
					else if(jrbMain.isSelected())
						Diary.dietOption = 0;
					else
						Diary.dietOption = 1;
					if(jrbSedentary.isSelected())
						Diary.exerciseRate = 0;
					else if(jrbLeve.isSelected())
						Diary.exerciseRate = 1;
					else if(jrbModerado.isSelected())
						Diary.exerciseRate = 2;
					else if(jrbPesado.isSelected())
						Diary.exerciseRate = 3;
					else
						Diary.exerciseRate = 4;
					Diary.wWeight = Integer.parseInt(jFormattedTextWeight.getText());
					Main.setState("DIARY");
					FunctionalitySelector.running = true;
					dispose();
				}
			}
			
			private boolean verificarCampos() {
				if((jrbLow.isSelected() || jrbMed.isSelected() 
						|| jrbHigh.isSelected()) && (jrbLoss.isSelected() 
								|| jrbMain.isSelected() || jrbGain.isSelected()) 
						&& (jrbSedentary.isSelected() || jrbLeve.isSelected()
								|| jrbModerado.isSelected() || jrbPesado.isSelected()
								|| jrbAthlete.isSelected()) 
						&& !jFormattedTextWeight.getText().isEmpty())
					if(Integer.parseInt(jFormattedTextWeight.getText()) <= 600 ) {
						return true;
					}else {
						JOptionPane.showMessageDialog(null,
								"Insira um peso válido!",
								"Erro de registro!", 
								JOptionPane.INFORMATION_MESSAGE);
						return false;
					}
				else {
					JOptionPane.showMessageDialog(null,
							"Preencha todos os campos, sem deixá-los em branco!",
							"Erro de registro!", JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}
			
		});
		
		setSize(Main.getWIDTH()/4, (int)(Main.getHEIGHT()/1.5));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Finalizar Registro");
	}
	
	
}
