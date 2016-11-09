package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;




import Lectura.ArchivosTxt;

public class PaginaPrincipal extends JFrame {
	
	
	private JTextPane txtRespuesta;
	private JMenuBar  menuBar;
	private  PanelHistorial pnlHistorial;
	
	private ArchivosTxt p; 

	public PaginaPrincipal(ArchivosTxt p)
	{
		this.setTitle("The origin of species Q&A");;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 500);
		this.p = p;
		
		
		
	
		this.setLayout(new BorderLayout());
		
		
		txtRespuesta = new JTextPane();
		
	
        
		txtRespuesta.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(txtRespuesta); 
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		pnlHistorial= new PanelHistorial(this);
		
		this.add(scrollPane,BorderLayout.CENTER);
		this.add(pnlHistorial,BorderLayout.EAST);
		this.setVisible(true);
		
	
		
		
		
		menuBar = new JMenuBar(); 
	    JMenu fileMenu = new JMenu("File");
		 fileMenu.setMnemonic(KeyEvent.VK_F);

		    // File->New, N - Mnemonic
		    JMenuItem newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
		    fileMenu.add(newMenuItem);
		    menuBar.add(fileMenu);
		    setJMenuBar(menuBar);
		
		
	}

	public void darRespuesta(String txtt)
	{
		String rta =p.responder(txtt);
		txtRespuesta.setText("");
	
		
		String txt= txtt.split("about")[1];
		txt = txt.replace("?", "");
		txt = txt.toLowerCase();
		String[] holi=rta.split(txt);
		int t= holi.length;
		if (t==0)
		{
			txtRespuesta.setText(rta);
		}
		for (int i = 0; i <t; i++) 
		{
			
			 appendToPane(txtRespuesta,holi[i], Color.black);
			 if(i==t-1)
			 {
				 
			 }
			 else
			 {
				 appendToPane(txtRespuesta,txt, Color.RED);
			 }
			 
			
		}
		
		
	      
	      repaint();
		
	}
	private void appendToPane(JTextPane tp, String msg, Color c)
    {
		StyledDocument doc = tp.getStyledDocument();
		Style style = tp.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style,c);
        try {
			doc.insertString(doc.getLength(), msg,style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	
	
	
	
}
