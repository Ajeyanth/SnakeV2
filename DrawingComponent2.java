
import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;







public class DrawingComponent2 extends JPanel implements KeyListener {
	
	double x=200;
	double y=200;
	static Timer timer,timer1,timer2;
	double width=20;
	double height=20;
	Rectangle.Double rect;
	Random rnd=new Random();
	SquareNew square;
	Apple apple;
	Apple apple2;
	int totalLength=2;
	SquareNew[] squares=new SquareNew[500];
	int totalBlocks=0;
	Block[] blocks=new Block[500];
	int moveDirection;
	SquareNew ghost=new SquareNew(300,300);
	Grid[][] grid = new Grid[20][20];
	Timer[] timers= new Timer[500];
	int totalTimers=1;
	boolean right;
	boolean left;
	boolean up;
	boolean down;
	boolean dead=false;
	Polygon polygon;
    double x1=0;
    double y1=0;
    int r;
    boolean useApple=true;
    boolean useBlock=true;
    boolean useClassic=true;
    boolean useSpeed=true;
    boolean useArcade=true;
    boolean useEndless=true;
    int speed=150;

    
	
	

	
	public DrawingComponent2() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		

		//Test Polygon
		int[] polyX= {0,0,0};
		int[] polyY= {0,0,0};
		
		polygon=new Polygon(polyX, polyY, 3);
		
		
		//Calling the method to create the squares.
		createSquares();
		if(useBlock==true) {
			createBlocks();
			apple=new Apple();
			//useApple=false;

		}
		//Calling the method to create the apple.
		if(useApple==true) {
			apple=new Apple();
			apple2= new Apple();
			//useBlock=false;

		}
		
		if(useClassic==true ) {
			apple=new Apple();
		}
		
		if(useSpeed==true || useArcade==true || useEndless==true ) {
			apple=new Apple();
		}
		
		
		createGrid();
		ghost.name="G";
		//Creates a timer which is called every 150 milliseconds.
		
		if(useClassic==true || useApple==true || useBlock==true || useEndless==true) {
			timer=new Timer(150, new ActionListener() {

				@Override
				// Method to call the animation method and to repaint and the component.
				   public void actionPerformed(ActionEvent e) {
					
		             	animation(moveDirection);

		            	repaint();
		             	
			       };
			       
			       



			 });
			
			timer.restart();
			
		}
		
		    
		 if(useSpeed==true) {
			
			timer1=new Timer(75, new ActionListener() {

				@Override
				// Method to call the animation method and to repaint and the component.
				   public void actionPerformed(ActionEvent e) {
					
		             	animation(moveDirection);
		            	repaint();
		            	
		             	
			       };



			    });
			
			
		}
		 	
	}
	
	    
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawGrid(g2);
		//Calling the method to display the squares.	
        drawSquares(g2);
		//Calling the method to display the apples.	
        
            drawApples(g2);

        
       // drawGhost(g2);
        
        
        drawPolygon(g2);
        if(useBlock==true) {
            drawBlocks(g2);

        }
        
    
        
	}
	
	public void drawGhost(Graphics2D g) {
		ghost.draw(g);
	}
	
	
	
	
	public void drawPolygon(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fill(polygon);
		g.draw(polygon);
	}
	
	public void animateGhost() {
		
		int direction=rnd.nextInt(100);
		if (direction%4==0) ghost.y-=20;
		if (direction%4==1) ghost.x+=30;
		if (direction%4==2) ghost.y+=10;
		if (direction%4==3) ghost.x-=15;
		
	}
				
			
	//Method to display the squares on the screen. 
	public void drawSquares(Graphics2D g) {
		for (int i=0;i<totalLength;i++) {
			squares[i].draw(g);
			

			
		}
			
	}
	
	//Method to display the apple on the screen.
	public void drawApples(Graphics2D g) {
            if(useApple==true) {
			apple.drawApple(g);
			apple2.drawApple(g);
        }
            if(useBlock==true || useClassic==true || useSpeed==true || useArcade==true || useEndless==true) {
            	apple.drawApple(g);
            }
	
	}
	
	public void drawGrid(Graphics2D g) {
		for (int i=0;i<20;i++) {
        for (int j=0;j<grid.length;j++) {
			grid[i][j].sketchGrid(g);
			//System.out.println(i);
			//System.out.println(grid[i].x1);
			//System.out.println(grid.length);
			//System.out.println("Square created");
			
		}
		}
	
	}

	
	
	
	public void createGrid() {
		for (int i=0;i<20;i++) {
			x1=0;
		for (int j=0;j<grid.length;j++) {
			grid[i][j]=new Grid(x1, y1,i+j);
			x1+=25;
			//System.out.println(i);
			//System.out.println(x1);
			//System.out.println(y1);	
					
		}
		y1+=25;
		}
		
	}
	
	//Method to create the blocks.
    public void createBlocks() {
			double x2=rnd.nextInt(500);
			double y2=rnd.nextInt(500);
			//double x2=(squares[0].x+apple.a)/2;
			//double y2=(squares[0].y+apple.b)/2;
			int remainder=(int) (x2%25);
			x2-=remainder;
			remainder=(int) (y2%25);
			y2-=remainder;
			blocks[totalBlocks]=new Block(x2, y2);
	
	}
	
	//Method to display the blocks on the screen.
	public void drawBlocks(Graphics2D g) {
       for (int i=0;i<totalBlocks;i++) {
			
               blocks[i].draw(g);
			
		}

   }
	
	
			
	
	// Method to create the squares.	 
	public void createSquares() {
		//Start of for loop.
		//The number of squares is  to the value within the variable 'totalLength'.
		for (int i=0;i<totalLength;i++) {
			// Create a new square with its X position being 40 less than the X position of square in front of it.
			squares[i]=new SquareNew(x, y);
			x-=25;
			squares[i].name=Integer.toString(i);
		}
		//End of for loop.
		polygon.xpoints[0]=(int)squares[0].x+25;
		polygon.xpoints[1]=(int)squares[0].x+35;
		polygon.xpoints[2]=(int)squares[0].x+25;
		polygon.ypoints[0]=(int)squares[0].y;
		polygon.ypoints[1]=(int)squares[0].y+15;
		polygon.ypoints[2]=(int)squares[0].y+25;
	}
	
	public void movePolygon(SquareNew head) {
		int shift=25;
		int sign=1;
		if ((head.direction==2) || (head.direction==4)) {
			if (head.direction==4) {
				shift=0;
				sign=-1;
			}
			polygon.xpoints[0]=(int)head.x+shift*sign;
			polygon.xpoints[1]=(int)head.x+shift+(sign*25/2);
			polygon.xpoints[2]=(int)head.x+shift*sign;
			
			polygon.ypoints[0]=(int)head.y;
			polygon.ypoints[1]=(int)head.y+15;
			polygon.ypoints[2]=(int)head.y+25;
		}
		if ((head.direction==1) || (head.direction==3)) {
			if (head.direction==1) {
				shift=0;
				sign=-1;
			}
			polygon.ypoints[0]=(int)head.y+shift*sign;
			polygon.ypoints[1]=(int)head.y+shift+(sign*20/2);
			polygon.ypoints[2]=(int)head.y+shift*sign;
			
			polygon.xpoints[0]=(int)head.x;
			polygon.xpoints[1]=(int)head.x+15;
			polygon.xpoints[2]=(int)head.x+25;
		}
		
	}
	
	//Method to check if there has been a collision between the head and an apple.
	public void checkCollisionWithAppleV1() {
		    // Checks if the head has intersected with an apple.
			if((squares[0].x==apple.a && squares[0].y==apple.b)  ) {
				if(squares[0].collision=true) {
					totalLength+=1;
					
					squares[totalLength-1]= new SquareNew(0,0);
					
				}
				
				if(useBlock==true) {
					squares[totalLength-1].squareColor=Color.MAGENTA;

				}
				
				if(useSpeed==true) {
					squares[totalLength-1].squareColor=Color.BLUE;
				}
				
				if(useArcade==true) {
					squares[totalLength-1].squareColor=Color.ORANGE;
				}
				
				System.out.println(totalLength);
				totalBlocks+=1;
                apple=new Apple();
                if(useArcade==true) {
                	if(totalLength%2==0) {
                		speed=speed-10;
                   
                    	timer.stop();
                    	timer=new Timer(speed, new ActionListener() {

            				@Override
            				// Method to call the animation method and to repaint and the component.
            				   public void actionPerformed(ActionEvent e) {
            					
            		             	animation(moveDirection);

            		            	repaint();
            		             	
            			       };

            			 });   
                    	
                    	timer.restart();
                    	
                    }
                	
                }
                
                createBlocks();
                
			}
			
	}
	
	public void checkCollisionWithAppleV2() {
		if((squares[0].x==apple.a && squares[0].y==apple.b)  ) {
			totalLength+=1;
			squares[totalLength-1]= new SquareNew(0,0);
			if(useApple==true) {
				squares[totalLength-1].squareColor=Color.RED;

			}
			System.out.println(totalLength);
			totalBlocks+=1;
            squares[0].x=apple2.a;
            squares[0].y=apple2.b;
            apple=new Apple();
			apple2=new Apple();
			createBlocks();
            
	 }
		   if((squares[0].x==apple2.a && squares[0].y==apple2.b)  ) {
			
			totalLength+=1;
			squares[totalLength-1]= new SquareNew(0,0);
			squares[totalLength-1].name=Integer.toString(totalLength-1);
			System.out.println(totalLength);
			totalBlocks+=1;
            squares[0].x=apple.a;
            squares[0].y=apple.b;
            apple=new Apple();
			apple2=new Apple();
			
			   if(useApple==true) {
					squares[totalLength-1].squareColor=Color.RED;

			   }

		    }

	 }
	// Method to check if there has been a collision with a block.
	
	public void checkCollisionWithBlock() {
	    // Checks if the head has intersected with a block.
		//squares[0].checkCollisionWithBlock(blocks);;

		for(int i =0 ; i<totalBlocks;i++) {
		    if(squares[0].x==blocks[i].x2 && squares[0].y ==blocks[i].y2) {
			   timer.stop();
			   dead=true;
		
		     }
		}
		
    }
	
	
	
	//Method to check whether the snake has collided with itself.
	public void checkCollisionWithSnake() {
		//Start of for loop.
		for(int i=totalLength-1;i>2;i--) {
			//Checks if the first square collides with any square except the first and second square.
			if(squares[0].x == squares[i].x && squares[0].y== squares[i].y) {
				System.out.println("Collision with snake");
				timer.stop();
				timer1.stop();
				dead=true;
				
				
			}				
			
		}
		//End of for loop.
		
	}
	
	
	
	
	
	//Method to change the direction in order to make the snake up.
	public void up() {
		squares[0].direction=1;
		//Sets all the variables except 'up' false.
		up=true;
		down=false;
		left=false;
		right=false;
		//Calls the move method in order to make the snake move.
		move();
	}
	
	//Method to change the direction in order to make the snake down.			
	public void down() {
	    squares[0].direction=3;
		//Sets all the variables except 'down' false.
	    up=false;
		down=true;
		left=false;
		right=false;
		//Calls the move method in order to make the snake move.
		move();				
	}
	
	//Method to change the direction in order to make the snake left.
	public void left() {
		
		squares[0].direction=4;
		//Sets all the variables except 'left' false.
		up=false;
		down=false;
		left=true;
		right=false;
		//Calls the move method in order to make the snake move.
		move();	
				
	}
	
	//Method to change the direction in order to make the snake right.
	public void right() {
		 squares[0].direction=2;
		//Sets all the variables except 'right' false.
		 up=false;
		 down=false;
		 left=false;
		 right=true;
		//Calls the move method in order to make the snake move.
		 move();
		 	
	}
	
	
	public void move() {
		//Start of for loop.
		// After the first square moves, the squares after will take the position of the square in front of them.
		for (int i=totalLength-1;i>0;i--) {
			squares[i].x=squares[i-1].x;
			squares[i].y=squares[i-1].y;
		}
		//End of for loop.
		
		if (squares[0].direction==1)
			squares[0].y-=25;
			
		if (squares[0].direction==2)
			squares[0].x+=25;
		
		if (squares[0].direction==3)
			squares[0].y+=25;
		
		if (squares[0].direction==4)
			squares[0].x-=25;
		
		movePolygon(squares[0]);
		
		//Start of boundary conditions for the snake.
	
		if(!useEndless) {
			if ((squares[0].x>=490) ||  (squares[0].x<=-10) ||(squares[0].y>=490)|| (squares[0].y<=-10) ) {
				dead=true;
				timer.stop();
				timer1.stop();
			}
			
		}
		
		if(useEndless) {
				if(squares[0].x>500) {
					squares[0].x=0;
				}
				if(squares[0].x<0) {
					squares[0].x=500;
				}
				if(squares[0].y<0) {
					squares[0].y=500;
				}
				if(squares[0].y>500) {
					squares[0].y=0;
				}
				
		 	
		}
		
		
		//End of boundary conditions for the snake.
	

	}
	
    
	
	//Creating method for the different key inputs.
	public void animation(int code) {
		//If the left arrow clicked, change the snake's direction to make it go to the left unless the snake's current direction is to the right.
		if  (code==37 && right!=true) left();
		if  (code==37 && right==true) right();

		//If the right arrow clicked, change the snake's direction to make it go to the right unless the snake's current direction is to the left.
		if  (code==39 && left!=true) right();
		if  (code==39 && left==true) left();

		//If the up arrow clicked, change the snake's direction to make it go upwards unless the snake's currently moving downwards.
		if  (code==38 && down!=true) up();
		if  (code==38 && down==true) down();

		//If the down arrow clicked, change the snake's direction to make it go downwards unless the snake's currently moving upwards.
		if  (code==40 && up!=true) down();
		if  (code==40 && up==true) up();

 
		
		// Calling the method to check if the snake has collided with an apple.
		if(useApple==true) {
		   checkCollisionWithAppleV2();
		}
		if(useBlock==true) {
			checkCollisionWithBlock();
			checkCollisionWithAppleV1();
			//squares[0].checkCollisionWithApple(apple);

			//squares[0].checkCollisionWithBlock(blocks);

		}
		
		if(useClassic==true || useSpeed==true || useArcade==true || useEndless==true) {
			checkCollisionWithAppleV1();
			//squares[0].checkCollisionWithApple(apple);

		}
		
		//Only call the method to check if the snake has collided with itself when its 'totalLength' is larger than 2.
		if(totalLength>2 )  checkCollisionWithSnake();
		
		
		
	}
			
		
	public void keyPressed(KeyEvent e) {
		Integer[] codes= {32,37,38,39,40};
		List<Integer> codesList=Arrays.asList(codes);
		if (codesList.stream().anyMatch(a-> a.intValue()==e.getKeyCode())) {
			moveDirection=e.getKeyCode();
		}
			
				
		//Start the timer when the space bar is clicked.		
		if (moveDirection==32) {
            timer.start();
			timer1.start();
		}
		
				
	}
			
			
			
			
	 public void keyTyped(KeyEvent e) {}
	 public void keyReleased(KeyEvent e) {}
						
			

}




			


			



