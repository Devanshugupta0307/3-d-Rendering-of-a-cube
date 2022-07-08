package com.miniproject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Launch extends Canvas implements Runnable
{

    public Launch()
    {
    	this.screen= new JFrame();
		screen.setExtendedState(screen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		screen.setMinimumSize(new Dimension(800,800));
    	this.mouse= new Mouse();
    	this.addMouseListener(this.mouse);
    	this.addMouseMotionListener(this.mouse);
    	this.addMouseWheelListener(this.mouse);
    }

    public synchronized void start()
	{
    	isRunning = true;
    	this.thread=new Thread(this,"Display");
    	this.thread.start();
    }
    
    public synchronized void stop()
    {
    	isRunning = false;
    	try
		{
    		this.thread.join();
		}
    	catch(InterruptedException e)
		{
			e.printStackTrace();
		}
    }

    public static void main(String[] args)
    {
    	Launch display=new Launch();
    	display.screen.setTitle((title));
    	display.screen.add(display);
    	display.screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	display.screen.setLocationRelativeTo(null);
		display.screen.setVisible(true);
		display.start();
    }
    
	@Override
	public void run()
	{
	    init();
		while(isRunning)
		{
			update();
			render();
			//this.cube.rotate(true, 0, 0.3, 0);
		}
		stop();
	}
	
	public void init()
	{
		int s = 150;
		Point p1 = new Point(s/2,-s/2,-s/2);
		Point p2 = new Point(s/2,s/2,-s/2);
		Point p3 = new Point(s/2,s/2,s/2);
		Point p4 = new Point(s/2,-s/2,s/2);
		Point p5 = new Point(-s/2,-s/2,-s/2);
		Point p6 = new Point(-s/2,s/2,-s/2);
		Point p7 = new Point(-s/2,s/2,s/2);
		Point p8 = new Point(-s/2,-s/2,s/2);
		this.cube = new Object(
				new Polygon(Color.RED,p5,p6,p7,p8),
				new Polygon(Color.WHITE,p1,p2,p6,p5 ),
				new Polygon(Color.YELLOW,p1,p5,p8,p4),
				new Polygon(Color.GREEN,p2,p6,p7,p3),
				new Polygon(Color.MAGENTA,p4,p3,p7,p8),
				new Polygon(Color.getHSBColor(284,58,66),p1,p2,p3,p4));
	}
	
	private void render()
	{
		BufferStrategy strategy = this.getBufferStrategy();
		if(strategy==null)
		{
			this.createBufferStrategy(2);
			return;
		}
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Graphics g = strategy.getDrawGraphics();
		g.setColor(Color.getHSBColor(101,67,33));
		g.fillRect(0, 0, screenSize.width, screenSize.height);
		
		cube.render(g);
		g.dispose();
		strategy.show();
	}

	int initialX,initialY;
	double mouseSensitivity=2;
	public void update()
	{
		int x=this.mouse.getX();
		int y=this.mouse.getY();
		if(this.mouse.getButton() == MouseClick.LeftClick)
		{
			int xDif=x-initialX;
			int yDif=y-initialY;
			this.cube.rotate(
					xDif/mouseSensitivity,
					yDif/mouseSensitivity,
					0);
		}
			
		else if(this.mouse.getButton() == MouseClick.RightClick)
		{
			int xDif=x-initialX;
			
			this.cube.rotate(
					0,
					0,
					xDif/mouseSensitivity);
		}
			
		if(this.mouse.scrollUp())
		{
			TranslatePoint.zoomIn();
		}
		else if(this.mouse.scrollDown())
		{
			TranslatePoint.zoomOut();
		}
		this.mouse.resetScroll();
		initialX=x;
		initialY=y;

		Rectangle r = screen.getBounds();
		WIDTH = r.width;
		HEIGHT = r.height;
	}

	private static final long serialVersionUID = 1L;
	private Thread thread;
	private JFrame screen;
	private static String title ="3D Cube Rendering";

	public static int WIDTH = 1000;
	public static int HEIGHT= 800;
	private static boolean isRunning =false;

	private Object cube;
	private Mouse mouse;
}
