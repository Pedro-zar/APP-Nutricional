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
	             
        labelUser.setBounds(30,40,100,20);
        labelPassword.setBounds(30,80,100,20);
        labelGenre.setBounds(30,120,100,20);
        labelAge.setBounds(30,160,100,20);
        labelWeight.setBounds(30,200,100,20);
        labelHeight.setBounds(30,240,100,20);
        
        buttonRegistrar.setBounds(150,280, 100, 20);
        buttonCancelar.setBounds(30,280, 100, 20);
	             
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
        
        
        jFormattedPassword.setBounds(150,80,100,20);
        jrbFem.setBounds(150,120,50,20);
        jrbMas.setBounds(200,120,50,20);
        jFormattedTextAge.setBounds(150,160,100,20);
        jFormattedTextWeight.setBounds(150,200,100,20);
        jFormattedTextHeight.setBounds(150,240,100,20);
        
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
             
        setSize(Main.getWIDTH()/3, Main.getHEIGHT()/2);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Registro");
             
    }

	protected void retornar() {
		AccountSelecter.setSTATE(1);
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