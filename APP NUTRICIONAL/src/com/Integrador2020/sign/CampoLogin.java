package com.Integrador2020.sign;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;
import com.Integrador2020.main.Main;
import com.Integrador2020.main.Save;

public class CampoLogin extends JFrame {

	private static final long serialVersionUID = 1L;

	void logar() {
		Container janela = getContentPane();
        setLayout(null);
        JLabel labelUser = new JLabel("Usuário: " + Main.accountSelecter.slot);
        JLabel labelPassword = new JLabel("Senha: ");
        JButton buttonEntrar = new JButton("Entrar");
        JButton buttonCancelar = new JButton("Cancelar");
        MaskFormatter mascaraPassword = null;
        labelUser.setBounds(30,40,100,20);
        labelPassword.setBounds(30,80,100,20);
        buttonEntrar.setBounds(150, 120, 100, 20);
        buttonCancelar.setBounds(30, 120, 100, 20);
        
        try{
       	 mascaraPassword = new MaskFormatter("####");
       	 mascaraPassword.setPlaceholderCharacter('_');
        }
        catch(ParseException excp) {
               System.err.println("Erro na formatação: " + excp.getMessage());
               System.exit(-1);
        }
        
        JFormattedTextField jFormattedPassword = new JFormattedTextField(mascaraPassword);
        jFormattedPassword.setBounds(150,80,100,20);
        
        janela.add(labelPassword);
        janela.add(jFormattedPassword);
        janela.add(buttonEntrar);
        janela.add(buttonCancelar);
        janela.add(labelUser);
        
        buttonCancelar.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent e) {
				AccountSelecter.STATE--;
				dispose();
			}
        });
        
        buttonEntrar.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		if(validation()) {
        			integrarDados();
        			Main.State = "FUNC_SELEC";
        			dispose();
        		}
        	}
        	
        	private void integrarDados() {
				Save.applySave(Save.loadRegister(13, Main.accountSelecter.slot));
			}

			public boolean validation() {
				return Save.getSave(Save.loadRegister(13, Main.accountSelecter.slot)) == Integer.parseInt(jFormattedPassword.getText());
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
