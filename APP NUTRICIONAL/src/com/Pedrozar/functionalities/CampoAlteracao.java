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
		this.jFormattedPassword.setText(Integer.toString(User.password));
		this.jFormattedTextWeight.setText(Integer.toString(User.weight));
		this.jFormattedTextHeight.setText(Integer.toString(User.height));
		this.jFormattedTextAge.setText(Integer.toString(User.age));
		if(User.gender == 1)
			jrbFem.setSelected(true);
		else
			jrbMas.setSelected(true);	
	}

	@Override
	public void finalizarRegistro() {
		super.finalizarRegistro();
		Save.applySave(Save.loadRegister(13, User.user));
	}
	
	@Override
	public void retornar() {
		Main.State ="FUNC_SELEC";
		dispose();
	}
	
}