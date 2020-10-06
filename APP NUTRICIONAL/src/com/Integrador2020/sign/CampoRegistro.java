package com.Integrador2020.sign;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.Integrador2020.entities.User;

public class CampoRegistro extends JFrame {

	private static final long serialVersionUID = 1L;

	void criarRegistro() {
             Container janela = getContentPane();
             setLayout(null);

             JLabel labelUser = new JLabel("Usuário: ");
             JLabel labelPassword = new JLabel("Senha: ");
             JLabel labelGenre = new JLabel("Gênero biológico:");
             JLabel labelAge = new JLabel("Idade: ");
             JLabel labelWeight = new JLabel("Peso (KG): ");
             JLabel labelHeight = new JLabel("Altura (CM): ");
             JButton buttonRegistrar = new JButton("Registrar");
             ButtonGroup bgGenre = new ButtonGroup();
             JRadioButton jrbMas = new JRadioButton("M");
             JRadioButton jrbFem = new JRadioButton("F");
             MaskFormatter mascaraAge = null;
             MaskFormatter mascaraHeight = null;
             MaskFormatter mascaraWeight = null;
             labelUser.setBounds(30,40,100,20);
             labelPassword.setBounds(30,80,100,20);
             labelGenre.setBounds(30,120,100,20);
             labelAge.setBounds(30,160,100,20);
             labelWeight.setBounds(30,200,100,20);
             labelHeight.setBounds(30,240,100,20);
             buttonRegistrar.setBounds(150,280, 100, 20);
             
             try{
                    mascaraAge = new MaskFormatter("##");
                    mascaraHeight = new MaskFormatter("###");
                    mascaraWeight = new MaskFormatter("###");
                    mascaraAge.setPlaceholderCharacter('_');
                    mascaraHeight.setPlaceholderCharacter('_');
                    mascaraWeight.setPlaceholderCharacter('_');
             }
             catch(ParseException excp) {
                    System.err.println("Erro na formatação: " + excp.getMessage());
                    System.exit(-1);
             }

             JTextField jTextUser = new JTextField();
             JFormattedTextField jFormattedTextWeight = new JFormattedTextField(mascaraWeight);
             JPasswordField jPassword = new JPasswordField();
             JFormattedTextField jFormattedTextAge = new JFormattedTextField(mascaraAge);
             JFormattedTextField jFormattedTextHeight = new JFormattedTextField(mascaraHeight);
             
             jTextUser.setBounds(150,40,100,20);
             jPassword.setBounds(150,80,100,20);
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
             janela.add(jTextUser);
             janela.add(jPassword);
             janela.add(jFormattedTextWeight);
             janela.add(jFormattedTextHeight);
             bgGenre.add(jrbMas);
             bgGenre.add(jrbFem);
             janela.add(jrbFem);
             janela.add(jrbMas);
             janela.add(buttonRegistrar);
             
             buttonRegistrar.addActionListener(new ActionListener() {
            	 public void actionPerformed(ActionEvent e) {
            		if (verificarCampos()) { 
	            		pegarDados();
	            		finalizarRegistro();
	            	}
            		
            	 }

				private void finalizarRegistro() {
					
					
				}

				private void pegarDados() {
					User.user = jTextUser.getText();
					User.password = jPassword.getPassword().toString();
					if (jrbFem.isSelected())
						User.gender = "F";
					else
						User.gender = "M";	
					User.age = Integer.parseInt(jTextUser.getText());
					User.weight = Integer.parseInt(jFormattedTextWeight.getText());
					User.height = Integer.parseInt(jFormattedTextHeight.getText());
					
				}

				private boolean verificarCampos() {
					if(!(jTextUser.getText().isEmpty()) && 
					!(jPassword.getPassword().toString().isEmpty()) &&
					(jrbMas.isSelected() || jrbFem.isSelected()) &&
					!(jFormattedTextAge.getText().isEmpty()) &&
					!(jFormattedTextWeight.getText().isEmpty()) &&
					!(jFormattedTextHeight.getText().isEmpty())) {
						if((jPassword.getPassword().length <= 16) &&
						(jPassword.getPassword().length >= 4)) {
							if(jTextUser.getText().length() >= 4) {
								if(Integer.parseInt(jFormattedTextWeight.getText()) <= 600 ) {
									if(Integer.parseInt(jFormattedTextHeight.getText()) <= 300 ) {
										return true;
									}else {
										//mostrar msg box sobre preencher altura válida
										return false;
									}
								}else {
									//mostrar msg box sobre preencher peso válido
									return false;
								}
							}else {
								//mostrar msg box sobre preencher usuario com mais de 4 caracteres
								return false;
							}
						}else {
							//mostrar msg box sobre preencher senha entre 4-16 caracteres
							return false;
						}
					}else {
						//mostrar msg box sobre preencher todos campos
						return false;
					}
				}
             });
             
             setSize(400, 360);
             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             setVisible(true);
             setResizable(false);
             setLocationRelativeTo(null);
             setTitle("Registro");
    }

}