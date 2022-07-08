package com.miniproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polygon
{
	private Color polygonColor;
	private Point[] polygonCoordinates;

	public Polygon(Color polygonColor, Point... polygonCoordinates)
	{
		this.polygonColor = polygonColor;
		this.polygonCoordinates = new Point[polygonCoordinates.length];
		for(int i=0;i<polygonCoordinates.length;i++)
		{
			Point p = polygonCoordinates[i];
			this.polygonCoordinates[i]= new Point(p.x,p.y,p.z);
		}
	}
	
	public Polygon(Point... polygonCoordinates)
	{
		this.polygonColor = Color.WHITE;
		this.polygonCoordinates = new Point[polygonCoordinates.length];
		for(int i=0;i<polygonCoordinates.length;i++)
		{
			Point p = polygonCoordinates[i];
			this.polygonCoordinates[i]= new Point(p.x,p.y,p.z);
		}
	}
	
	public void render(Graphics g)
	{
		java.awt.Polygon poly= new java.awt.Polygon();
		for(int i=0;i<this.polygonCoordinates.length;i++)
		{
			java.awt.Point p = TranslatePoint.translatePoint(this.polygonCoordinates[i]);
			poly.addPoint(p.x, p.y);
		}
		g.setColor(this.polygonColor);
		g.fillPolygon(poly);
	}
	
	public void rotate(double xDegrees, double yDegrees, double zDegrees )
	{
		for(Point p: polygonCoordinates)
		{
			TranslatePoint.rotateAxisX(p, xDegrees);
			TranslatePoint.rotateAxisY(p, yDegrees);
     		TranslatePoint.rotateAxisZ(p, zDegrees);
		}
	}
	
	public void setColor(Color polygonColor)
	{
		this.polygonColor=polygonColor;
	}
	
	public double getAvgX()
	{
	    double sum=0;
	    for(Point p: this.polygonCoordinates)
	    {
		    sum+=p.x;
	    }
	    return sum/this.polygonCoordinates.length;
    }
	
   public static Polygon[] sortPolygons(Polygon[] polygon)
	{
		List<Polygon> polygonsList = new ArrayList<Polygon>();
		
		for(Polygon poly: polygon)
		{
			polygonsList.add(poly);
		}

		Collections.sort(polygonsList, (p1, p2) -> p2.getAvgX()-p1.getAvgX()<0?1:-1);

		for(int i=0;i<polygon.length;i++)
		{
			polygon[i]= polygonsList.get(i);
		}
		return polygon;
	}
}
