import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class lecturaArchivosTest 
{

	private static ArrayList<String> parrafos;
	private File archivo;
	private File parafos;


	public lecturaArchivosTest() 
	{
		archivo = new File("./data/The origin of species.txt");
		parafos = new File("./data/output.txt");
		parrafos = new ArrayList<String>();
		try {
			leer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void leer() throws Exception 
	{
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(parafos)));
		
		//System.out.println(".");
		
		FileInputStream fi = new FileInputStream(archivo);

		BufferedReader br = new BufferedReader(new InputStreamReader(fi));

		String linea = br.readLine();
		String parafo = "";
		int count = 0;

		while (count != 50) 
		{
			if(!linea.equals(""))
			{
			parafo = parafo + linea +"\n";
			}
			linea = br.readLine();
			if(linea.equals(""))
			{
				parrafos.add(parafo);
				parafo = "";
			}
			count++;
		}
		
		br.close();
	}
	
	public static void main (String[] args)
	{
	
		 new lecturaArchivosTest() ;
		 
		 for(int i= 0 ; i< parrafos.size();i++)
		 {
			System.err.println(parrafos.get(i));
		 }
	}
	
	public ArrayList darParrafos()
	{
		return parrafos;
	}
}
