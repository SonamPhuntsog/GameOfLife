
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.*;

import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;
public class Main extends GraphicsProgram {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SIZE = 10;
	public static final double SW = 1275;
	public static final double SH = 738;
	public static final int ROW = (int)SH/SIZE;
	public static final int COL = (int) SW/SIZE;
	
	
	public boolean [][] world;
	public Queue<Integer> q;
	private RandomGenerator rand = new RandomGenerator();
	private boolean stop = true;
	private GRect R; 
	public void run(){
		
		setUp();
		
		showWorld();
		
		System.out.println("Enter any key to start simmulatoin");
		while(getStop())
		{
			System.out.println(getStop());
		}
		
		System.out.println("\tOut of wait:");
		while(true)
		{
		
		updateWorld();
		pause(50);
		}
		
	}
		
	
	
	public void showWorld(){
		removeAll();
		createGun(10 , 10);
		
		for(int i = 0; i< COL; i++){
			for(int j = 0 ; j< ROW; j++){
				R = new GRect(i*SIZE,j*SIZE,SIZE,SIZE);
				R.setFilled(true);
				Color c = !world[i][j] ? Color.WHITE: Color.BLACK;
				
				R.setFillColor(c);
				add(R);
			}
		}
		
		
	}
	public void setUp(){
		q = new LinkedList<Integer>();
		addMouseListeners();
		addKeyListeners();
		world = new boolean[COL][ROW];
		
		
		//putRandomly();
		//createGun1(37,25);
		
		createGun1(120,10);
		
		
		
	}
	public void putRandomly(){
		for(int i = 0; i < COL; i++ )
			for(int j = 0; j < ROW; j++){
				//world[i][j] = j > 60 ? rand.nextBoolean(): false;
				world[i][j] = rand.nextBoolean();
			}
	}
	public void updateWorld()
	{
		for(int i = 0; i < COL ; i++)
		{
			for(int j = 0; j < ROW ; j++)
			{
				int count = countNeighbor(i,j);
				if(world[i][j])
				{
						if(count < 2 || count > 3){
							q.add(i);
							q.add(j);
						}
				}
				else
				{
						if(count == 3){
							q.add(i);
							q.add(j);
							}
				}
			}

				
		}
		
		
		while(!q.isEmpty()){
		  int i = (int)q.poll();
		  int j = (int)q.poll();
		  
		  world[i][j] = !world[i][j];
		  
		  R = new GRect(i*SIZE,j*SIZE,SIZE,SIZE);
			R.setFilled(true);
			Color c = !world[i][j] ? Color.WHITE: Color.BLACK;
			
			R.setFillColor(c);
			
			add(R);
		  
		  }
		  
		
	}
	public int countNeighbor(int a , int b){
		int count = 0;
		for(int i = a-1;i <= a+1 ; i++){
			for(int j = b-1; j <= b+1; j++){
				if(i>=0 && i < COL && j >= 0 && j < ROW && !(i ==a && j == b)){
					if(world[i][j])
						count ++;
				}
			}
		}
		
		return count;
	}
	public void printCount(){
	for(int i = 0; i < COL; i++){
		for(int j = 0; j < ROW; j++){
			System.out.print(countNeighbor(i,j) + " ");	
		}
		System.out.println();
	}
	System.out.println();
	}
	
	public void mouseClicked( MouseEvent e)
	{
		int i = e.getX()/SIZE;
		int j = e.getY()/SIZE;
		world[i][j] = !world[i][j];
		 R = new GRect(i*SIZE,j*SIZE,SIZE,SIZE);
			R.setFilled(true);
			Color c = !world[i][j] ? Color.WHITE: Color.BLACK;
			
			R.setFillColor(c);
			
			add(R);
		
	}
	public void keyTyped( KeyEvent e){
		stop = false;
		//System.out.println("\t"+stop);
		}
	public boolean getStop(){
		return stop;
	}
	public void createGun(int s, int d){
		world[s][d+2]=true;
		world[s][d+3]=true;
		world[s+1][d+2]=true;
		world[s+1][d+3]=true;
		world[s+8][d+3]=true;
		world[s+8][d+4]=true;
		world[s+9][d+2]=true;
		world[s+9][d+4]=true;
		world[s+10][d+2]=true;
		world[s+10][d+3]=true;
		world[s+16][d+4]=true;
		world[s+16][d+5]=true;
		world[s+16][d+6]=true;
		world[s+17][d+4]=true;
		world[s+18][d+5]=true;
		world[s+22][d+1]=true;
		world[s+22][d+2]=true;
		world[s+23][d+0]=true;
		world[s+23][d+2]=true;
		world[s+24][d+0]=true;
		world[s+24][d+1]=true;
		world[s+24][d+12]=true;
		world[s+24][d+13]=true;
		world[s+25][d+12]=true;
		world[s+25][d+14]=true;
		world[s+26][d+12]=true;
		world[s+34][d]=true;
		world[s+34][d+1]=true;
		world[s+35][d]=true;
		world[s+35][d+1]=true;
		world[s+35][d+7]=true;
		world[s+35][d+8]=true;
		world[s+35][d+9]=true;
		world[s+36][d+7]=true;
		world[s+37][d+8]=true;
	}
	
	public void createGun1(int s, int d){
		world[s][d+2]=true;
		world[s][d+3]=true;
		world[s-1][d+2]=true;
		world[s-1][d+3]=true;
		world[s-8][d+3]=true;
		world[s-8][d+4]=true;
		world[s-9][d+2]=true;
		world[s-9][d+4]=true;
		world[s-10][d+2]=true;
		world[s-10][d+3]=true;
		world[s-16][d+4]=true;
		world[s-16][d+5]=true;
		world[s-16][d+6]=true;
		world[s-17][d+4]=true;
		world[s-18][d+5]=true;
		world[s-22][d+1]=true;
		world[s-22][d+2]=true;
		world[s-23][d+0]=true;
		world[s-23][d+2]=true;
		world[s-24][d+0]=true;
		world[s-24][d+1]=true;
		world[s-24][d+12]=true;
		world[s-24][d+13]=true;
		world[s-25][d+12]=true;
		world[s-25][d+14]=true;
		world[s-26][d+12]=true;
		world[s-34][d]=true;
		world[s-34][d+1]=true;
		world[s-35][d]=true;
		world[s-35][d+1]=true;
		world[s-35][d+7]=true;
		world[s-35][d+8]=true;
		world[s-35][d+9]=true;
		world[s-36][d+7]=true;
		world[s-37][d+8]=true;
	}
}
