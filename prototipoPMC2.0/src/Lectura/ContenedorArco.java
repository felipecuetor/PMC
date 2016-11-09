package Lectura;

import estructuras.IArco;
import estructuras.IVertice;

public class ContenedorArco implements IArco {

	private String elem;

	public ContenedorArco(String elem) {
		this.elem = elem;
	}

	public String darId() {
		return elem;
	}

	public int darPeso() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		return elem;

	}

}
