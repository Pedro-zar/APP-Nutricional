package com.Pedrozar.functionalities;

import com.Pedrozar.entities.User;
import com.Pedrozar.main.Main;
import com.Pedrozar.main.Save;
import com.Pedrozar.sign.CampoRegistro;

public class CampoAlteracao extends CampoRegistro {

	private static final long serialVersionUID = 1L;

	public void criarAlterator() {
		criarRegistro();
		setTitle("Alteração de dados");
		this.jFormattedPassword.setText(Integer.toString(User.getPassword()));
		this.jFormattedTextWeight.setText(Integer.toString(User.getWeight()));
		this.jFormattedTextHeight.setText(Integer.toString(User.getHeight()));
		this.jFormattedTextAge.setText(Integer.toString(User.getAge()));
		if(User.getGender() == 1)
			jrbFem.setSelected(true);
		else
			jrbMas.setSelected(true);	
	}

	@Override
	public void finalizarRegistro() {
		super.finalizarRegistro();
		Save.applySave(Save.loadRegister(13, User.getUser()));
	}
	
	@Override
	public void retornar() {
		Main.setState("FUNC_SELEC");
		dispose();
	}
	
}