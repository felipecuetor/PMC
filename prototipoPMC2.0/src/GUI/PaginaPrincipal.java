package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Lectura.ArchivosTxt;

public class PaginaPrincipal extends JFrame implements ActionListener{
	
	private JTextField txtPregunta;
	private JButton btnResponder;
	private JTextArea txtRespuesta;
	
	private ArchivosTxt p; 

	public PaginaPrincipal(ArchivosTxt p)
	{
		this.setTitle("The origin of species Q&A");;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.p = p;
		
		GridLayout grid = new GridLayout(2, 1);
		GridLayout subg = new GridLayout(6, 1);
		
		JPanel sub = new JPanel();
		sub.setLayout(subg);
		this.setLayout(grid);
		
		txtPregunta = new JTextField();
		btnResponder = new JButton("Answer");
		txtRespuesta = new JTextArea("");
		
		txtRespuesta.setLineWrap(true);
		txtRespuesta.setWrapStyleWord(true);
        
		txtRespuesta.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(txtRespuesta); 
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		sub.add(new JLabel("Question:"));
		sub.add(txtPregunta);
		sub.add(btnResponder);
		sub.add(new JLabel(""));
		sub.add(new JLabel(""));
		sub.add(new JLabel("Response:"));
		this.add(sub);
		this.add(scrollPane);
		this.setVisible(true);
		
		btnResponder.setActionCommand("responder");
		btnResponder.addActionListener(this);
		
		
		
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("responder"))
		{
			txtRespuesta.setText(p.responder(txtPregunta.getText()));
		}
		
	}
	
	
}
