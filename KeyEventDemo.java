import java.awt.BorderLayout;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.*;
import javax.swing.*;

 
public class KeyEventDemo extends JFrame {
	
	static double totalScore;
	static JLabel score;
	//static JLabel highScore;

	static Timer timer;

   
     
    public static void main(String[] args) {
        snakeGame();

     }
    
    public static void snakeGame() {
    	JFrame window=new JFrame();
        window.setFocusable(false);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setSize(800,600); 
         
        JButton button=new JButton("Start");
 		button.setSize(50, 30);
 		JButton restart=new JButton("Restart");
  		restart.setSize(50, 30);
  		JButton useClassic= new JButton("Classic");
  		useClassic.setSize(50, 30);
  		JButton useApples= new JButton("Two Apples");
  		useApples.setSize(50, 30);
  		JButton useBlocks= new JButton("Blocks");
  		useBlocks.setSize(50, 30);
  	    JButton useSpeed= new JButton("Speed");
  		useSpeed.setSize(50, 30);
  		JButton useArcade= new JButton("Arcade");
   		useArcade.setSize(50, 30);
   		JButton useEndless= new JButton("Endless");
   		useEndless.setSize(50, 30);
   	
  	
 		
 		JPanel navPanel=new JPanel();
 		navPanel.setPreferredSize(new Dimension(100, 500));
 		navPanel.setBackground(Color.GRAY);

 		
 		DrawingComponent2 panel ;
 		panel = new DrawingComponent2();
 		panel.setPreferredSize(new Dimension(500, 500));
 		panel.setBackground(Color.white);
 		
 		
 		JPanel outerPanel=new JPanel();
 		outerPanel.setSize(800,800);
 		outerPanel.setBackground(Color.BLUE);
 		totalScore= panel.totalLength-2;
		score=new JLabel("Score=" + totalScore);
		//highScore=new JLabel("Score=" );

		

        useClassic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<panel.totalLength;i++) {
					panel.squares[i].squareColor=Color.GREEN;
				}
				    panel.useApple=false;
                    panel.useBlock=false;
                    panel.useClassic=true;;
                    panel.useEndless=false;

                    panel.useSpeed=false;
                    panel.useArcade=false;

                    useApples.setEnabled(false);
                    useBlocks.setEnabled(false);
                    useSpeed.setEnabled(false);
                    useArcade.setEnabled(false);
                    useEndless.setEnabled(false);


                    if(panel.dead) {
    					panel.totalLength=2;
    					panel.squares[0].x=200;
    					panel.squares[0].y=200;
    					panel.apple=new Apple();
    					panel.totalBlocks=0;
    					panel.speed=150;
    					panel.dead=false;
    				    DrawingComponent2.timer.restart();
    					
    					System.out.println("I am clicked");
    					button.setFocusable(false);
    					panel.requestFocusInWindow();
    					
                     }
			
			  }
			

		  });
        

        useApples.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<panel.totalLength;i++) {
					panel.squares[i].squareColor=Color.RED;
				}

				
				    panel.useApple=true;
                    panel.useBlock=false;
                    panel.useClassic=false;
                    panel.useEndless=false;

                    panel.useSpeed=false;
                    panel.useArcade=false;


                    useClassic.setEnabled(false);
                    useBlocks.setEnabled(false);
                    useSpeed.setEnabled(false);
                    useEndless.setEnabled(false);

                    useArcade.setEnabled(false);


                    if(panel.dead) {
    					panel.totalLength=2;
    					panel.squares[0].x=200;
    					panel.squares[0].y=200;
    					panel.apple=new Apple();
    					panel.totalBlocks=0;
    					panel.speed=150;

    					panel.dead=false;
    				    DrawingComponent2.timer.restart();
    					
    					System.out.println("I am clicked");
    					button.setFocusable(false);
    					panel.requestFocusInWindow();
    				
                   }
			
			 }
			
		 });
        

        useBlocks.addActionListener(new ActionListener() {
			
			@Override
			   public void actionPerformed(ActionEvent e) {
				for(int i=0;i<panel.totalLength;i++) {
					panel.squares[i].squareColor=Color.MAGENTA;
				}
				    panel.useApple=false;
                    panel.useBlock=true;
                    panel.useClassic=false;
                    panel.useEndless=false;

                    panel.useSpeed=false;
                    panel.useArcade=false;


                    useApples.setEnabled(false);
                    useClassic.setEnabled(false);
                    useSpeed.setEnabled(false);
                    useEndless.setEnabled(false);

                    useArcade.setEnabled(false);


                    if(panel.dead) {
    					panel.totalLength=2;
    					panel.squares[0].x=200;
    					panel.squares[0].y=200;
    					panel.apple=new Apple();
    					panel.totalBlocks=0;
    					panel.speed=150;

    					panel.dead=false;
    				    DrawingComponent2.timer.restart();
    					
    					System.out.println("I am clicked");
    					button.setFocusable(false);
    					panel.requestFocusInWindow();
    					

                    }
  					
			   }
	     });
        
         useSpeed.addActionListener(new ActionListener() {
			
			@Override
			   public void actionPerformed(ActionEvent e) {
				
				for(int i=0;i<panel.totalLength;i++) {
					panel.squares[i].squareColor=Color.BLUE;
				}
				
				    panel.useApple=false;
                    panel.useBlock=false;
                    panel.useClassic=false;
                    panel.useEndless=false;

                    panel.useSpeed=true;
                    panel.useArcade=false;


                    useApples.setEnabled(false);
                    useClassic.setEnabled(false);
                    useBlocks.setEnabled(false);
                    useArcade.setEnabled(false);
                    useEndless.setEnabled(false);


                    
                    if(panel.dead) {
    					panel.totalLength=2;
    					panel.squares[0].x=200;
    					panel.squares[0].y=200;
    					panel.apple=new Apple();
    					panel.totalBlocks=0;
    					panel.speed=150;

    					panel.dead=false;

    					DrawingComponent2.timer.stop();
						DrawingComponent2.timer1.restart();

    					button.setFocusable(false);
    					panel.requestFocusInWindow();
    					

                    }
  					
			    }
	       });
         
         useArcade.addActionListener(new ActionListener() {
 			
 			@Override
 			   public void actionPerformed(ActionEvent e) {
 				
 				for(int i=0;i<panel.totalLength;i++) {
 					panel.squares[i].squareColor=Color.ORANGE;
 				}
 				
 				     panel.useApple=false;
                     panel.useBlock=false;
                     panel.useClassic=false;
                     panel.useSpeed=false;
                     panel.useEndless=false;

                     panel.useArcade=true;


                     useApples.setEnabled(false);
                     useClassic.setEnabled(false);
                     useBlocks.setEnabled(false);
                     useSpeed.setEnabled(false);
                     useEndless.setEnabled(false);


                     
                     if(panel.dead) {
     					panel.totalLength=2;
     					panel.squares[0].x=200;
     					panel.squares[0].y=200;
     					panel.apple=new Apple();
     					panel.totalBlocks=0;
     					panel.dead=false;

     					DrawingComponent2.timer.stop();
 						DrawingComponent2.timer.restart();

     					button.setFocusable(false);
     					panel.requestFocusInWindow();
     					

                     }
                     
                   
                     
   					
 			    }
 	       });
         
         useEndless.addActionListener(new ActionListener() {
 			
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				for(int i=0;i<panel.totalLength;i++) {
 					panel.squares[i].squareColor=Color.GREEN;
 				}
 				    panel.useApple=false;
                     panel.useBlock=false;
                     panel.useClassic=false;;
                     panel.useSpeed=false;
                     panel.useArcade=false;
                     panel.useEndless=true;

                     useApples.setEnabled(false);
                     useBlocks.setEnabled(false);
                     useSpeed.setEnabled(false);
                     useArcade.setEnabled(false);

                     if(panel.dead) {
     					panel.totalLength=2;
     					panel.squares[0].x=200;
     					panel.squares[0].y=200;
     					panel.apple=new Apple();
     					panel.totalBlocks=0;
     					panel.speed=150;
     					panel.dead=false;
     				    DrawingComponent2.timer.restart();
     					
     					System.out.println("I am clicked");
     					button.setFocusable(false);
     					panel.requestFocusInWindow();
     					
                      }
 			
 			  }
 			

 		  });
         
         
         
        
        
        


 		
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				    timer.start();
					DrawingComponent2.timer.start();
				    
				    panel.useApple=false;
                    panel.useBlock=false;
                    panel.useArcade=false;
                    panel.useSpeed=false;
                    panel.useClassic=true;
                    panel.useEndless=false;
                    
                    useApples.setEnabled(false);
                    useBlocks.setEnabled(false);
                    useSpeed.setEnabled(false); 
                    useArcade.setEnabled(false);
                    useEndless.setEnabled(false);


                    
					System.out.println("I am clicked");
					button.setFocusable(false);
					panel.requestFocusInWindow();
					
				}
			

		 });
        
         restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    
				  if(panel.dead) {
					useApples.setEnabled(true);
                    useBlocks.setEnabled(true);
                    useClassic.setEnabled(true);
                    useSpeed.setEnabled(true);
                    useArcade.setEnabled(true);
                    useEndless.setEnabled(true);
                   
                  }
					
		     }
			

		 });
            
           
         
         navPanel.add(button);
         navPanel.add(restart);
         navPanel.add(score);
         //navPanel.add(highScore);
         navPanel.add(useClassic);
         navPanel.add(useApples);
         navPanel.add(useBlocks);
         navPanel.add(useSpeed);
         navPanel.add(useArcade);
         navPanel.add(useEndless);



         

 		 outerPanel.add(navPanel);
 		 outerPanel.add(panel);
 		 window.add(outerPanel);
         window.setVisible(true);
 		 window.pack();
 		
 		 timer=new Timer(100, new ActionListener() {

			@Override
			// Method to call the animation method and to repaint and the component.
			public void actionPerformed(ActionEvent e) {
				
				   score.setText("Score= "+String.valueOf(panel.totalLength-2));
				   //highScore.setText("Highscore= "+String.valueOf(panel.totalLength-2));
				   
				   

		     };



		 });
 		
    	
   }
    
  
         
 }


   
        
       
 
   
     
   
     
  