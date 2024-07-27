import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;

public class Grid {
	
	double x1;
	double y1;
	Rectangle.Double square;
	String name;
	double width=25;
	double height=25;
    int direction,nextDirection;
    Grid next;
	Color gridColor;
	int gridNumber;



	
	public Grid(double x,double y,int gridNumber) {
		
		
		this.x1=x;
		this.y1=y;
		this.gridNumber=gridNumber;
		if (gridNumber%2==0) {
			this.gridColor=new Color(192,192, 192);
		}
		else this.gridColor=new Color(255,255,255);
		

		
	
		
	}
	
	public void sketchGrid(Graphics2D g) {
		
		square=new Rectangle.Double(x1, y1, width,height);
		g.setColor(gridColor);
		g.fill(square);
		g.setColor(Color.BLACK);
		g.draw(square);
	}
	


}