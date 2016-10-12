package General;

import GUI.PaginaPrincipal;
import Lectura.ArchivosTxt;

public class Main {

	public static void main(String[] args) {
		System.out.println("Start up");
		ArchivosTxt txt = new ArchivosTxt();
		
		PaginaPrincipal pagina = new PaginaPrincipal(txt);
	}
}
