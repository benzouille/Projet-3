package main.java.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

public abstract class PanelModel extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4494688574702011068L;
	protected Font comics30 = new Font("Comics Sans MS", Font.BOLD, 30);
	protected Font comics40 = new Font("Comics Sans MS", Font.BOLD, 40);
	protected Font comics20 = new Font("Comics Sans MS", Font.BOLD, 20);
	protected Font arial = new Font("Arial", Font.BOLD, 15);
	protected Font dialog = new Font("Dialog", Font.BOLD + Font.ITALIC, 15);
	
	public PanelModel(Dimension dim){
		this.setPreferredSize(dim);
		this.setBackground(Color.white);
		
	}
	
	protected abstract void initPanel();	
	
}

