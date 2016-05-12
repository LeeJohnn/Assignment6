package main.java;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	public String name;
	private MainApplet parent;
	private float nowX, nowY;
	public float startX, startY;
	private int r, g, b, a;
	public boolean mouseOnMe = false;
	private boolean mousePress;
	private int rad;
	public boolean smallInBig = false;
	public boolean reseting;
	private int color;
	private ArrayList<Character> targets;
	private ArrayList<Integer> values;

	public Character(MainApplet parent, String name, String color, int x, int y){

		this.parent = parent;
		this.name = name;
		this.color = (int) Long.parseLong(color.replace("#", ""), 16);
		this.startX = x;
		this.startY = y;
		this.nowX = x;
		this.nowY = y;
		this.setRad(40);
		this.targets = new ArrayList<Character>();
		this.values =  new ArrayList<Integer>();
		//int intValue = Integer.valueOf(color);
		//System.out.println(g);
	}
	public void display(){
		if(this.smallInBig){
			for(Character c : targets){
				parent.noFill();
				parent.stroke(200);
				if(values.get(targets.indexOf(c)) > 10)
					parent.strokeWeight((float)values.get(targets.indexOf(c)) / 6);
				else 
					parent.strokeWeight((float)values.get(targets.indexOf(c)) / 2);
				// draw the curve with two line(half of the length of two dots to the Circle center)
				float a1 = (this.nowX + 600) / 2;
				float b1 = (this.nowY + 325) / 2;
				float a2 = (c.nowX + 600) / 2;
				float b2 = (c.nowY + 325)  / 2;
				// if the target also in circle , draw it
				if (c.smallInBig == true)
					parent.bezier(nowX, nowY, a1, b1, a2, b2, c.nowX, c.nowY); // 550 340
			}
		}
		this.parent.noStroke();
		this.parent.fill(color);
		this.parent.ellipse(this.nowX, this.nowY, this.rad, this.rad);
//		if(mouseOnMe){
//			this.parent.fill(0, 255, 255);;
//			this.parent.rect(this.nowX, this.nowY, 70, 20);
//			this.parent.fill(0);
//			this.parent.text(name, this.nowX+10, this.nowY+12);
//		}
		
	}

	public void setNowX(float x){
		this.nowX = x;
	}
	public void setNowY(float y){
		this.nowY = y;
	}
	public void setRad(int r){
		this.rad = r;
	}
	public float getNowX(){
		return this.nowX;
	}
	public float getNowY(){
		return this.nowY;
	}
	public int getRad(){
		return this.rad;
	}
	public void addTarget(Character target){ this.targets.add(target); }
	public void addValue(Integer value){ this.values.add(value); }
	public ArrayList<Character> getTargets(){ return this.targets; }
	public ArrayList<Integer> getValues(){ return this.values; }
	
}
