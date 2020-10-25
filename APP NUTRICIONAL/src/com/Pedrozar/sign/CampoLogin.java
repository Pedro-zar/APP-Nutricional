package com.Pedrozar.sign;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

import com.Pedrozar.main.Main;
import com.Pedrozar.main.Save;

public class CampoLogin extends JFrame {

	private static final long serialVersionUID = 1L;

	void logar() {
		Container janela = getContentPane();
        setLayout(null);
        JLabel labelUser = new JLabel("Usu�rio: " + Main.accountSelecter.getSlot());
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
               System.err.println("Erro na formata��o: " + excp.getMessage());
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
				Save.applySave(Save.loadRegister(13, Main.accountSelecter.getSlot()));
			}

			public boolean validation() {
				return Save.getSave(Save.loadRegister(13, Main.accountSelecter.getSlot())) == Integer.parseInt(jFormattedPassword.getText());
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