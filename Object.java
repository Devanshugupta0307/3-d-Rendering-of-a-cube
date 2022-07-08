package com.miniproject;

import java.awt.Color;
import java.awt.Graphics;

public class Object
{
	private Polygon[] ObjectPolygons;
	private Color ObjectColor;
	
	public Object(Color ObjectColor, Polygon... ObjectPolygons)
	{
		this.ObjectColor = ObjectColor;
		this.ObjectPolygons = ObjectPolygons;
		this.setPolygonColor();
	}
	
	public Object(Polygon... ObjectPolygons)
	{
		this.ObjectColor = Color.WHITE;
		this.ObjectPolygons = ObjectPolygons;
	}
	
	public void render(Graphics g)
	{
		for(Polygon poly: this.ObjectPolygons)
		{
			poly.render(g);
		}
	}
	
	public void rotate(double xDegrees, double yDegrees, double zDegrees)
	{
		for(Polygon p: this.ObjectPolygons)
		{
			p.rotate(xDegrees,yDegrees,zDegrees);
		}
		this.sortPolygons();
	}
	
	public void sortPolygons()
	{
		Polygon.sortPolygons(this.ObjectPolygons);
	}
	
	public void setPolygonColor()
	{
		for(Polygon poly: this.ObjectPolygons)
		{
			poly.setColor(this.ObjectColor);
		}
	}
}
