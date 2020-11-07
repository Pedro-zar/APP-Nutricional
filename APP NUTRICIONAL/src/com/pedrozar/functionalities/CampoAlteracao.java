package com.pedrozar.functionalities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.pedrozar.entities.User;
import com.pedrozar.main.Main;
import com.pedrozar.main.Save;
import com.pedrozar.sign.CampoRegistro;

public class CampoAlteracao extends CampoRegistro {

	private static final long serialVersionUID = 1L;

	public void criarAlterator() {
		criarRegistro();
		setTitle("Alteração de dados");
		setSize(Main.getWIDTH()/3, Main.getHEIGHT()/2);
		JButton buttonDeletar = new JButton("Deletar");
		buttonDeletar.setBounds(270,280, 100, 20);
		janela.add(buttonDeletar);
		this.jFormattedPassword.setText(Integer.toString(User.getPassword()));
		if(User.getWeight() < 100) 
			this.jFormattedTextWeight.setText("0" + Integer.toString(User.getWeight()));
		else
			this.jFormattedTextWeight.setText(Integer.toString(User.getWeight()));
		
		if(User.getHeight() < 100) 
			this.jFormattedTextHeight.setText("0" + Integer.toString(User.getHeight()));
		else
			this.jFormattedTextHeight.setText(Integer.toString(User.getHeight()));
		
		if(User.getAge() < 10) 
			this.jFormattedTextAge.setText("0" + Integer.toString(User.getAge()));
		else
			this.jFormattedTextAge.setText(Integer.toString(User.getAge()));

		if(User.getGender() == 1)
			jrbFem.setSelected(true);
		else
			jrbMas.setSelected(true);	
		
		buttonDeletar.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent e){
				Save.deleteAccount(User.getUser());
				dispose();
			}
        });
	}

	@Override
	public void finalizarRegistro() {
		super.finalizarRegistro();
		Save.applySave(Save.loadRegister(13, User.getUser(), "contas"));
	}
	
	@Override
	public void retornar() {
		Main.setState("FUNC_SELEC");
		dispose();
	}
	
}