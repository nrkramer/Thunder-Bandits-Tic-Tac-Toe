package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class TicTacToeBoard extends JPanel {
	private static final long serialVersionUID = -26071265982424939L;
	
	private Font font;
	private Stroke stroke1; // draw grid
	private Stroke stroke2; // X's and O's
	private int padding = 10;
	private int gridLineWidth = 4;
	private int letterWidth = 4;
	private Color xColor = new Color(255, 100, 100);
	private Color oColor = new Color(200, 200, 255);
	private Color numberColor = Color.lightGray;
	private boolean showHover = false;
	private boolean showNumbers = false;
	private Rectangle hoverRegion = new Rectangle();
	
	public TicTacToeBoard() {
		setForeground(Color.white);
		font = new Font("Helvetica", Font.BOLD, 80);
		stroke1 = new BasicStroke(gridLineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
		stroke2 = new BasicStroke(letterWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent arg0) {}
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				showHover = false;
				for(int i = 0; i <= 2; i++) {
					for(int j = 0; j <= 2; j++) {
						hoverRegion = getCell(i, j);
						if (hoverRegion.contains(arg0.getPoint())) {
							showHover = true;
							i = 2; j = 2;
							repaint();
						}
					}
				}
				if (showHover)
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				else
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
		});
	}
	
	public Rectangle getCell(int _x, int _y) {
		if ((_x > 2) || (_y > 2))
			return null;
		
		Dimension d = getSize();
		int x = (_x * (d.width) / 3) + padding;
		int y = (_y * (d.height) / 3) + padding;
		int width = ((d.width) / 3) - ((_x + 1) * letterWidth) - padding;
		int height = ((d.height) / 3) - ((_y + 1) * letterWidth) - padding;
		Rectangle r = new Rectangle(x, y, width, height);
		return r;
	}
	
	private void drawX(Graphics2D g, int x, int y) {
		g.setPaint(xColor);
		Rectangle r = getCell(x, y);
		g.drawLine(r.x, r.y, r.x + r.width, r.y + r.height);
		g.drawLine(r.x + r.width, r.y, r.x, r.y + r.height);
	}
	
	private void drawO(Graphics2D g, int x, int y) {
		g.setColor(oColor);
		Rectangle r = getCell(x, y);
		g.drawOval(r.x, r.y, r.width, r.height);
	}
	
	private void drawNumber(Graphics2D g, int x, int y, int number) {
		Rectangle r = getCell(x, y);
		int fontHeight = g.getFontMetrics().getHeight();
		int stringWidth = g.getFontMetrics().stringWidth(String.valueOf(number));
		g.drawString(String.valueOf(number), (r.x + (r.width / 2)) - (stringWidth / 2), (r.y + (r.height / 2)) + fontHeight / 4);
	}
	
	protected void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		Graphics2D g = (Graphics2D)gd;
		
		Rectangle b = g.getClipBounds();
		
		g.setFont(font);
		g.setStroke(stroke1);
		
		// draw grid
		g.drawLine(padding, b.height / 3, b.width - padding, b.height / 3);
		g.drawLine(padding, (int)(b.height / 1.5), b.width - padding, (int)(b.height / 1.5));
		g.drawLine(b.width / 3, padding, b.width / 3, b.height - padding);
		g.drawLine((int)(b.width / 1.5), padding, (int)(b.width / 1.5), b.height - padding);
		
		// draw hover
		g.setPaint(new Color(50, 50, 50));
		if (showHover)
			g.fillRect(hoverRegion.x, hoverRegion.y, hoverRegion.width, hoverRegion.height);
		
		// draw numbers
		if (showNumbers) {
			g.setPaint(numberColor);
			drawNumber(g, 0, 0, 1);
			drawNumber(g, 1, 0, 2);
			drawNumber(g, 2, 0, 3);
			drawNumber(g, 0, 1, 4);
			drawNumber(g, 1, 1, 5);
			drawNumber(g, 2, 1, 6);
			drawNumber(g, 0, 2, 7);
			drawNumber(g, 1, 2, 8);
			drawNumber(g, 2, 2, 9);
		}
		
		// draw X's,O's
		g.setStroke(stroke2);
		drawX(g, 1, 1);
		drawO(g, 2, 0);
	}
}
