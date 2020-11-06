package com.pedrozar.sign;

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
import com.pedrozar.main.Save;

public class CampoRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JFormattedTextField jFormattedTextWeight, jFormattedPassword;
	protected JFormattedTextField jFormattedTextAge, jFormattedTextHeight;
    protected JRadioButton jrbFem, jrbMas;
    protected Container janela;
    
	protected void criarRegistro() {
		
		janela = getContentPane();
		setLayout(null);
		
		JLabel labelUser = new JLabel("Usuário: " + Main.accountSelecter.getSlot());
        JLabel labelPassword = new JLabel("Senha: ");
        JLabel labelGenre = new JLabel("Gênero biológico:");
        JLabel labelAge = new JLabel("Idade: ");
        JLabel labelWeight = new JLabel("Peso (KG): ");
        JLabel labelHeight = new JLabel("Altura (CM): ");
       
        JButton buttonRegistrar = new JButton("Registrar");
        JButton buttonCancelar = new JButton("Cancelar");
        ButtonGroup bgGenre = new ButtonGroup();
        
        jrbMas = new JRadioButton("M");
        jrbFem = new JRadioButton("F");
        
        MaskFormatter mascaraAge = null;
        MaskFormatter mascaraHeight = null;
        MaskFormatter mascaraPassword = null;
        MaskFormatter mascaraWeight = null;
	             
        labelUser.setBounds(Main.getHEIGHT()/24,Main.getHEIGHT()/18,
        		(int)(Main.getWIDTH()/7.2),(Main.getWIDTH()/16/4));
        labelPassword.setBounds(Main.getHEIGHT()/24,(Main.getWIDTH()/16),
        		(int)(Main.getWIDTH()/7.2),(Main.getWIDTH()/16/4));
        labelGenre.setBounds(Main.getHEIGHT()/24,Main.getHEIGHT()/6,
        		(int)(Main.getWIDTH()/7.2),(Main.getWIDTH()/16/4));
        labelAge.setBounds(Main.getHEIGHT()/24,(int)(Main.getHEIGHT()/4.5),
        		(int)(Main.getWIDTH()/7.2),(Main.getWIDTH()/16/4));
        labelWeight.setBounds(Main.getHEIGHT()/24,(int)(Main.getWIDTH()/3.6),
        		(int)(Main.getWIDTH()/7.2),(Main.getWIDTH()/16/4));
        labelHeight.setBounds(Main.getHEIGHT()/24,Main.getHEIGHT()/3,
        		(int)(Main.getWIDTH()/7.2),(Main.getWIDTH()/16/4));
        
        buttonRegistrar.setBounds((int)(Main.getHEIGHT()/4.8),(int)(Main.getHEIGHT()/2.57), 
        		(int)(Main.getHEIGHT()/7.2), (Main.getWIDTH()/16/4));
        buttonCancelar.setBounds(Main.getHEIGHT()/24,(int)(Main.getHEIGHT()/2.57), 
        		(int)(Main.getHEIGHT()/7.2), (Main.getWIDTH()/16/4));
	             
        try{
        	mascaraAge = new MaskFormatter("##");
        	mascaraPassword = new MaskFormatter("####");
        	mascaraHeight = new MaskFormatter("###");
        	mascaraWeight = new MaskFormatter("###");
        	mascaraAge.setPlaceholderCharacter('_');
        	mascaraPassword.setPlaceholderCharacter('_');
        	mascaraHeight.setPlaceholderCharacter('_');
        	mascaraWeight.setPlaceholderCharacter('_');
        }
        catch(ParseException excp) {
        	System.err.println("Erro na formatação: " + excp.getMessage());
        	System.exit(-1);
        }
        
        jFormattedTextWeight = new JFormattedTextField(mascaraWeight);
        jFormattedPassword = new JFormattedTextField(mascaraPassword);
        jFormattedTextAge = new JFormattedTextField(mascaraAge);
        jFormattedTextHeight = new JFormattedTextField(mascaraHeight);
        
        
        jFormattedPassword.setBounds((int)(Main.getHEIGHT()/4.8),(Main.getWIDTH()/16),
        		(int)(Main.getHEIGHT()/7.2),(Main.getWIDTH()/16/4));
        jrbFem.setBounds((int)(Main.getHEIGHT()/4.8),Main.getHEIGHT()/6,
        		(int)(Main.getHEIGHT()/14.4),(Main.getWIDTH()/16/4));
        jrbMas.setBounds((int)(Main.getHEIGHT()/3.6),Main.getHEIGHT()/6,
        		(int)(Main.getHEIGHT()/14.4),(Main.getWIDTH()/16/4));
        jFormattedTextAge.setBounds((int)(Main.getHEIGHT()/4.8),(int)(Main.getHEIGHT()/4.5),
        		(int)(Main.getHEIGHT()/7.2), (Main.getWIDTH()/16/4));
        jFormattedTextWeight.setBounds((int)(Main.getHEIGHT()/4.8),(int)(Main.getHEIGHT()/3.6),
        		(int)(Main.getHEIGHT()/7.2),(Main.getWIDTH()/16/4));
        jFormattedTextHeight.setBounds((int)(Main.getHEIGHT()/4.8),Main.getHEIGHT()/3,
        		(int)(Main.getHEIGHT()/7.2),(Main.getWIDTH()/16/4));
        
        janela.add(labelGenre);
        janela.add(labelUser);
        janela.add(labelAge);
        janela.add(labelPassword);
        janela.add(labelWeight);
        janela.add(labelHeight);
        janela.add(jFormattedTextAge);
        janela.add(jFormattedPassword);
        janela.add(jFormattedTextWeight);
        janela.add(jFormattedTextHeight);
        janela.add(jrbFem);
        janela.add(jrbMas);
        janela.add(buttonRegistrar);
        janela.add(buttonCancelar);
        
        bgGenre.add(jrbMas);
        bgGenre.add(jrbFem);
        
        buttonCancelar.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) {
        		retornar();
        	}
            	 	
        });	
        buttonRegistrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (verificarCampos()) { 
        			finalizarRegistro();
        			retornar();
        		}
            		
        	}

        	protected boolean verificarCampos() {
        		if(!(jFormattedPassword.getText().isEmpty()) &&
        				(jrbMas.isSelected() || jrbFem.isSelected()) &&
        				!(jFormattedTextAge.getText().isEmpty()) &&
        				!(jFormattedTextWeight.getText().isEmpty()) &&
        				!(jFormattedTextHeight.getText().isEmpty())) {
						if((jFormattedPassword.getText().length() == 4)) {
							if(Integer.parseInt(jFormattedTextWeight.getText()) <= 600 ) {
								if(Integer.parseInt(jFormattedTextHeight.getText()) 
										<= 300 ) {		
									return true;
								}else {
									JOptionPane.showMessageDialog(null,
											"Insira uma altura válida!",
											"Erro de registro!", 
											JOptionPane.INFORMATION_MESSAGE);
									return false;
								}
							}else {
								JOptionPane.showMessageDialog(null,
										"Insira um peso válido!",
										"Erro de registro!", 
										JOptionPane.INFORMATION_MESSAGE);
								return false;
							}
						}else {
							JOptionPane.showMessageDialog(null,
									"A senha deve conter 4 caracteres!",
									"Erro de registro!", JOptionPane.INFORMATION_MESSAGE);
							return false;
						}
					}else {
						JOptionPane.showMessageDialog(null,
								"Preencha todos os campos, sem deixá-los em branco!",
								"Erro de registro!", JOptionPane.INFORMATION_MESSAGE);
						return false;
					}
        	}
        });
             
        setSize(Main.getWIDTH()/4, Main.getHEIGHT()/2);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Registro");
             
    }

	protected void retornar() {
		AccountSelecter.setSTATE(1);
		Main.setState("MENU");
		dispose();
	}
	
	protected void finalizarRegistro() {
		String[] opt1 = {"user", "password", "gender", "age","weight", 
				"height", "firstLogin"};
		int gender;
		if (jrbFem.isSelected())
			gender = 1; //F
		else
			gender = 0;	//M
	
		int[] opt2 = {Main.accountSelecter.getSlot(),
				Integer.parseInt(jFormattedPassword.getText()), 
				gender, Integer.parseInt(jFormattedTextAge.getText()),
				Integer.parseInt(jFormattedTextWeight.getText()), 
				Integer.parseInt(jFormattedTextHeight.getText()),
				0};
		
		Save.saveRegister(opt1, opt2, 13, Main.accountSelecter.getSlot(), 
				"contas");
	}
}