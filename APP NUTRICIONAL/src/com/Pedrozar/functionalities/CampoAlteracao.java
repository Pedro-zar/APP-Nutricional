package com.Pedrozar.functionalities;

import com.Pedrozar.main.Main;
import com.Pedrozar.sign.CampoRegistro;

public class CampoAlteracao extends CampoRegistro {

	private static final long serialVersionUID = 1L;

	public void criarAlterator() {
		criarRegistro();
		setTitle("Alteração de dados");
	}

	@Override
	public void retornar() {
		Main.State ="FUNC_SELEC";
		dispose();
	}
	
}