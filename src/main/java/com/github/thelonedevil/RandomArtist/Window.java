package com.github.thelonedevil.RandomArtist;

import javax.swing.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pnl = new JPanel();
	JButton add = new JButton("Add more artists");
	JButton random = new JButton("Select Random Artist");

	public Window() {
		super("Random Artist");
		setSize(400, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(pnl);
		setVisible(true);
		pnl.add(add);
		pnl.add(random);
		add.addActionListener(this);
		random.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == add) {
			String inputValue = JOptionPane.showInputDialog(this, "Type Artist name into box", "Add Artist", JOptionPane.PLAIN_MESSAGE);
			if(!App.artists.contains(inputValue)){
				App.artists.add(inputValue);
				App.yamlSave();
				JOptionPane.showMessageDialog(this,"You just added: "+inputValue+".","Confirmation",JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this,"This Artist is already in the list","Error" , JOptionPane.ERROR_MESSAGE);
			}
		} else if (event.getSource() == random) {
			App.random();		
			JOptionPane.showMessageDialog(this, App.artist,"Artist" , JOptionPane.INFORMATION_MESSAGE);
		}

	}
}
