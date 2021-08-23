package api;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridUtil implements ActionListener {
	
	private int[][] grid;
	private int[][] locked;
	
	JButton button;
	JTextArea[][] text;
	JFrame frame;
	JPanel panel;
	JPanel subpanel1;
	
	public GridUtil() {
		frame = new JFrame();
        frame.setSize(800, 700);
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        subpanel1 = new JPanel();
        subpanel1.setBackground(new Color(245, 235, 220));
        
        text = new JTextArea[9][9];
        grid = new int[9][9];
        locked = new int[9][9];
	}
	
	public static void main(String[] args) {
        GridUtil test = new GridUtil();
        test.frame();
         
	}
	 
	public JFrame frame() {       
        subpanel1.setPreferredSize(new Dimension(600,600));
        subpanel1.setLayout( new GridLayout( 9, 9, 20, 20 ) );
        
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                text[i][j] = new JTextArea();
                text[i][j].setText("-");
                text[i][j].setEditable(true);
                Font font = new Font("Verdana", Font.BOLD, 40);
                text[i][j].setFont(font);
                subpanel1.add(text[i][j]);
            }
        }
        
        JPanel subpanel2 = new JPanel();
        button = new JButton("Solve");
        button.addActionListener(this);
        
        subpanel2.add(button);
        panel.add(subpanel1, BorderLayout.WEST);
        panel.add(subpanel2, BorderLayout.EAST);
        frame.add(panel);
        frame.setVisible(true);
        return frame;
	}
	
	public void actionPerformed(ActionEvent event) {
	    button.setText("Solved!");
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
            	if (!text[i][j].getText().equals("-")) {
                	grid[i][j] = Integer.parseInt(text[i][j].getText());
                	locked[i][j] = Integer.parseInt(text[i][j].getText());
            	} else {
            		grid[i][j] = -1;
            		text[i][j].setText("-1");
            	}
            }
        }
        solved();
	}
	
	public void solved() {
		solving();
	    for(int i = 0; i < 9; i++) {
	    	for(int j = 0; j < 9; j++) {
	    		if(locked[i][j] != grid[i][j]) {
		    		text[i][j].setText(Integer.toString(grid[i][j]));
		    		text[i][j].setEditable(false);
	    		}
	    	}
	    }
	}
		
	public boolean solving() {
	    for(int row=0;row<9;row++)
	    {
	        for(int col=0;col<9;col++)
	        {
	            if(grid[row][col]==-1)
	            {
	                for(int number=1;number<=9;number++)
	                {
	                    if(checkBlock1(row, col, number))
	                    {
	                        grid[row][col] = number;
	                        if(solving())
	                        {
	                            return true;
	                        }
	                        else
	                        {
	                            grid[row][col] = -1;
	                        }
	                    }
	                }
	                return false;
	            }
	        }
	    }
	    return true;	
	}
	
	
	public boolean checkBlock1(int row, int col, int number) {
		return !(checkRow(row, number) || checkCol(col, number) || checkBlock(row, col, number));
	}
	
	public boolean checkRow(int row, int number) {
		for(int i=0;i<9;i++)
	    {
	        if(grid[row][i]==number)
	        {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean checkCol(int col, int number) {
	    for(int i=0;i<9;i++)
	    {
	        if(grid[i][col]==number)
	        {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean checkBlock(int row, int col, int number) {
	    int r = row - row%3;
	    int c = col - col%3;
	    
	    for(int i = r ; i< r+3 ; i++)
	    {
	        for(int j = c; j < c+3 ; j++)
	        {
	            if(grid[i][j]==number)
	            {
	                return true;
	            }
	        }
	          
	    }
	    return false;
	}
	
	
	
}
	
	