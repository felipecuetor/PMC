package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 150);
		this.p = p;
		
		GridLayout grid = new GridLayout(6, 1);
		this.setLayout(grid);
		
		txtPregunta = new JTextField();
		btnResponder = new JButton("Answer");
		txtRespuesta = new JTextArea("");
		
		txtRespuesta.setLineWrap(true);
		txtRespuesta.setWrapStyleWord(true);
        
		txtRespuesta.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(txtRespuesta); 
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.add(new JLabel("Question:"));
		this.add(txtPregunta);
		this.add(new JLabel(""));
		this.add(btnResponder);
		this.add(new JLabel("Response:"));
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
