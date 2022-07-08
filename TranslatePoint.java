package com.miniproject;

public class TranslatePoint
{
	private static double scale = 1;
	private static final double zf = 2;

	public static void zoomIn()
	{
		scale*=zf;
	}
	public static void zoomOut()
	{
		scale/=zf;
	}

	public static java.awt.Point translatePoint(Point point3D)
	{
	    double x3d = point3D.y*scale;
        double y3d = point3D.z*scale;
	    double depth = point3D.x*scale;
    	double[] storeNewCoordinate = scale(x3d,y3d,depth);

		int x2d = (int)(Launch.WIDTH/2 + storeNewCoordinate[0]);
		int y2d = (int)(Launch.HEIGHT/2 - storeNewCoordinate[1]);
		java.awt.Point point2d = new java.awt.Point(x2d,y2d);
		return point2d;
	}

	private static double[] scale(double x3d, double y3d, double depth)
	{
		double dist = Math.sqrt(x3d*x3d+y3d*y3d);
		double theta = Math.atan2(y3d, x3d);
		double depth2 = 15 - depth;
		double localScale = Math.abs(1200/(1200+depth2));
		dist *= localScale;
		double[] storeNewCoordinate = new double[2];
		storeNewCoordinate[0] = dist*Math.cos(theta);
		storeNewCoordinate[1] = dist*Math.sin(theta);
		return storeNewCoordinate;
	}

	public static void rotateAxisX(Point p, double degrees)
	{
		double radius = Math.sqrt(p.y*p.y+p.z*p.z);
		double theta = Math.atan2(p.z, p.y);
		theta += 2*Math.PI/360*degrees;
		p.y = radius* Math.cos(theta);
		p.z = radius* Math.sin (theta);
	}

	public static void rotateAxisY(Point p, double degrees)
	{
		double radius = Math.sqrt(p.x*p.x+p.z*p.z);
		double theta = Math.atan2(p.x, p.z);
		theta += 2*Math.PI/360*degrees;
		p.x = radius* Math.sin(theta);
		p.z = radius* Math.cos(theta);
	}

	public static void rotateAxisZ(Point p, double degrees)
	{
		double radius = Math.sqrt(p.y*p.y+p.x*p.x);
		double theta = Math.atan2(p.y, p.x);
		theta += 2*Math.PI/360*degrees;
		p.y = radius* Math.sin(theta);
		p.x = radius* Math.cos(theta);
	}
}
