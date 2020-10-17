package com.Integrador2020.functionalities;

public class Menu {

	public String[] options = { "DIARY","UPDATE", "LOGOUT"};
	public int currentOption = 0, maxOption = options.length-1;
	public boolean down = false, up = false, enter = false;
	
	public void tick() {
		if(up) {
			currentOption--;
			up = false;
			if(currentOption < 0) {
				currentOption = maxOption;
			}
		}else if(down){
			currentOption++;
			down = false;
			if(currentOption > maxOption) {
				currentOption = 0;
			}
		}else if(enter) {
			if(options[currentOption] == "DIARY") {
				
			}else if(options[currentOption] == "UPDATE") {
				
			}else if(options[currentOption] == "LOGOUT") {
				
			}
			enter = false;
		}
	}
	
	public void render() {
		
	}
}
