package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private boolean showPlayerMouseIndicator = false;
	private String playerIndicator = "P1";
	private Point mousePos = new Point();
	private int[] boardState = new int[9]; // 0 is nothing, 1 is x, 2 is o
	private int drawStrike = -1;
	private int winner = -1;
	
	public TicTacToeBoard() {
		for (int i = 0; i < 9; i++) {
			boardState[i] = 0;
		}
		setForeground(Color.white);
		mousePos = getMousePosition();
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
						}
					}
				}
				if (showHover)
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				else
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
				mousePos = arg0.getPoint();
				repaint();
			}
			
		});
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				showPlayerMouseIndicator = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				showPlayerMouseIndicator = false;
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
	}
	
	public Point getXYFromCell(int cell) {
		if (cell > -1) {
			int x = 0;
			int y = 0;
			x = cell;
			if ((cell >= 3) && (cell < 6)) {
				x = cell - 3;
				y = 1;
			} else if ((cell >= 6)) {
				x = cell - 6;
				y = 2;
			}
			return new Point(x, y);
		}
		return new Point(-1, -1);
	}
	
	public void setBoardState(int[] state) {
		boardState = state;
		repaint();
	}
	
	public void setPlayerIndicator(String indicator) {
		playerIndicator = indicator;
	}
	
	public void setStrikeThrough(int winner, int strike) {
		// -1 is no winner
		// 0 is player 1 win
		// 1 is player 2 win
		this.winner = winner;
		// -1 is don't draw
		// 0 is row 1
		// 1 is row 2
		// 2 is row 3
		// 3 is col 1
		// 4 is col 2
		// 5 is col 3
		// 6 is top-left to bottom-right
		// 7 is top-right to bottom-left
		this.drawStrike = strike;
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
	
	public int getCellFromCoords(int x, int y) {
		Dimension d = getSize();
		int cell = -1;
		
		int _x = x / ((d.width / 3) + padding / 3);
		int _y = y / ((d.height / 3) + padding / 3);
		
		if (getCell(_x, _y).contains(x, y)) // got lazy
		{
			cell = _x;
			if (_y == 1)
				cell = _x + 3;
			if (_y == 2)
				cell = _x + 6;
		}
		
		return cell;
	}
	
	private void drawX(Graphics2D g, int x, int y) {
		g.setPaint(xColor);
		Rectangle cell = getCell(x, y);
		int aspect = 0;
		if (cell.width > cell.height)
			aspect = cell.height;
		else
			aspect = cell.width;
		Rectangle r = new Rectangle(cell.x + (cell.width - aspect) / 2, cell.y + (cell.height - aspect) / 2, aspect, aspect);
		g.drawLine(r.x, r.y, r.x + r.width, r.y + r.height);
		g.drawLine(r.x + r.width, r.y, r.x, r.y + r.height);
	}
	
	private void drawO(Graphics2D g, int x, int y) {
		g.setColor(oColor);
		Rectangle cell = getCell(x, y);
		int aspect = 0;
		if (cell.width > cell.height)
			aspect = cell.height;
		else
			aspect = cell.width;
		Rectangle r = new Rectangle(cell.x + (cell.width - aspect) / 2, cell.y + (cell.height - aspect) / 2, aspect, aspect);
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
			g.fill3DRect(hoverRegion.x, hoverRegion.y, hoverRegion.width, hoverRegion.height, true);
		
		// draw numbers
		if (showNumbers) {
			g.setPaint(numberColor);
			drawNumber(g, 0, 0, 7);
			drawNumber(g, 1, 0, 8);
			drawNumber(g, 2, 0, 9);
			drawNumber(g, 0, 1, 4);
			drawNumber(g, 1, 1, 5);
			drawNumber(g, 2, 1, 6);
			drawNumber(g, 0, 2, 1);
			drawNumber(g, 1, 2, 2);
			drawNumber(g, 2, 2, 3);
		}
		
		// draw X's,O's
		g.setStroke(stroke2);
		Point p = new Point();
		for(int i = 0; i < 9; i++) {
			if (boardState[i] == 1) {
				p = getXYFromCell(i);
				drawX(g, p.x, p.y);
			} else if (boardState[i] == 2) {
				p = getXYFromCell(i);
				drawO(g, p.x, p.y);
			}
		}
		
		// set strike-through color
		if (winner == 0)
			g.setColor(xColor);
		if (winner == 1)
			g.setColor(oColor);
		
		// draw strike-through for win
		if (drawStrike >= 0) {
			switch(drawStrike) {
			case 0:
				// row 1
				break;
			case 1:
				// row 2
				break;
			case 2:
				// row 3
				break;
			case 3:
				// col 1
				break;
			case 4:
				// col 2
				break;
			case 5:
				// col 3
				break;
			case 6:
				// top-left -> bottom-right
				break;
			case 7:
				// top-right -> bottom-left
				break;
			}
		}
		
		// draw player indicator
		if (playerIndicator == "P1")
			g.setColor(xColor);
		else 
			g.setColor(oColor);
		g.setFont(new Font("Helvetica", Font.PLAIN, 30));
		setForeground(Color.white);
		if (mousePos != null) {
			if (showPlayerMouseIndicator)
				g.drawString(playerIndicator, mousePos.x + 15, mousePos.y + 40);
		}
	}
}
