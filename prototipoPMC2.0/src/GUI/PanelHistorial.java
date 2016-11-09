package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelHistorial extends JPanel implements ActionListener
{
	private final String PREGUNTAR="preguntar";
	private JTextField pre;
	private PaginaPrincipal main;
	private JButton btnPregunt;
	private JList<String> lista;
	private DefaultListModel<String> model;
	
	public PanelHistorial(PaginaPrincipal prin)
	{
		main=prin;
		model = new DefaultListModel<String>();
		lista= new JList<String>(model);
		pre= new JTextField();
		pre.setPreferredSize(new Dimension(50,50));
		btnPregunt = new JButton("Ask");
		btnPregunt.setActionCommand(PREGUNTAR);
		btnPregunt.addActionListener(this);
		 
	
		  setLayout(new BorderLayout());
		  distribuir();
	}

	public void actionPerformed(ActionEvent e) 
	{
			String cmd = e.getActionCommand();
		
		if(cmd.equals(PREGUNTAR))
		{
			if (pre.getText()==null ||pre.getText().equals(""))
			{
				 JOptionPane.showMessageDialog(null, "You have to ask a question", "Error: " + "No question was asked", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				String h=pre.getText();
				 model.addElement( h );
				 main.darRespuesta(h);
				actualizarLista();
				
			}
		}
		
	}
	
	public void distribuir()
	{
		
		JScrollPane LISTA= new JScrollPane(lista);
		add(LISTA, BorderLayout.CENTER);
		JPanel subPaenl= new JPanel();
		subPaenl.setLayout(new BorderLayout());
		subPaenl.add(pre,BorderLayout.CENTER);
		subPaenl.add(btnPregunt,BorderLayout.SOUTH);
		add(subPaenl,BorderLayout.SOUTH);
		
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {

		        		int[] index =lista.getSelectedIndices();
		        		main.darRespuesta(lista.getSelectedValue());
		         }
		    }
		};
		lista.addMouseListener(mouseListener);
	}
	
	
	
	private void actualizarLista()
	{
		repaint();
	}

}
