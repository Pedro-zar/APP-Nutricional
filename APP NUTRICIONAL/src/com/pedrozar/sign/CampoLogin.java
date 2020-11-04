package com.pedrozar.sign;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

import com.pedrozar.main.Main;
import com.pedrozar.main.Save;

public class CampoLogin extends JFrame {

	private static final long serialVersionUID = 1L;

	void logar() {
		Container janela = getContentPane();
        setLayout(null);
        JLabel labelUser = new JLabel("Usuário: " + Main.accountSelecter.getSlot());
        JLabel labelPassword = new JLabel("Senha: ");
        JButton buttonEntrar = new JButton("Entrar");
        JButton buttonCancelar = new JButton("Cancelar");
        MaskFormatter mascaraPassword = null;
        labelUser.setBounds(30,40,100,(Main.getWIDTH()/16/4));
        labelPassword.setBounds(30,(int)(Main.getWIDTH()/16),100,(Main.getWIDTH()/16/4));
        buttonEntrar.setBounds(150, 120, 100, (Main.getWIDTH()/16/4));
        buttonCancelar.setBounds(30, 120, 100, (Main.getWIDTH()/16/4));
        
        try{
        	mascaraPassword = new MaskFormatter("####");
       	 	mascaraPassword.setPlaceholderCharacter('_');
        }
        catch(ParseException excp) {
        	System.err.println("Erro na formatação: " + excp.getMessage());
        	System.exit(-1);
        }
        
        JFormattedTextField jFormattedPassword = new JFormattedTextField(mascaraPassword);
        jFormattedPassword.setBounds(150,80,100,(Main.getWIDTH()/16/4));
        
        janela.add(labelPassword);
        janela.add(jFormattedPassword);
        janela.add(buttonEntrar);
        janela.add(buttonCancelar);
        janela.add(labelUser);
        
        buttonCancelar.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) {
        		AccountSelecter.setSTATE(AccountSelecter.getSTATE() - 1);
				dispose();
			}
        });
        
        buttonEntrar.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		if(validation()) {
        			integrarDados();
        			Main.setState("FUNC_SELEC");
        			dispose();
        		}
        	}
        	
        	private void integrarDados() {
				Save.applySave(
						Save.loadRegister(13, Main.accountSelecter.getSlot(),
								"contas"));
			}

			public boolean validation() {
				return Save.getSave(Save.loadRegister(13, 
						Main.accountSelecter.getSlot(), "contas")) 
						== Integer.parseInt(jFormattedPassword.getText());
			}
        });
        
        setSize(300, 240);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Login");
	}
}
