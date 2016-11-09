package Lectura;

import estructuras.IVertice;

public class ContenedorVertice implements IVertice<String>{

	
	private String elem;
	public ContenedorVertice(String elem) {
		this.elem = elem;
	}

	public String darId() {
		return elem;
	}
	
	public String toString()
	{
		return elem;
	}



	
}
