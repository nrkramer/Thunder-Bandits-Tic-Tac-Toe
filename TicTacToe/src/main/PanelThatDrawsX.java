package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class PanelThatDrawsX extends JPanel {
	private static final long serialVersionUID = -8795494833611276618L;

	public PanelThatDrawsX() {
		setForeground(Color.lightGray);
	}
	
	public void paint(Graphics gd) {
		Graphics2D g = (Graphics2D)gd;
		Rectangle b = g.getClipBounds();
		g.setStroke(new BasicStroke(3.0f));
		g.drawLine(10, 10, b.width - 10, b.height - 10);
		g.drawLine(b.width - 10, 10, 10, b.height - 10);		
	}
}
