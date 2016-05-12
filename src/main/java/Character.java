package main.java;

import java.util.ArrayList;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	public String name;
	private MainApplet parent;
	private float nowX, nowY; //現在的X跟Y
	public float startX, startY; //起始的X跟Y
	public boolean mouseOnMe = false; //滑鼠有沒有移到小圓上面
	private int rad; //小圓半徑
	public boolean smallInBig = false; //小圓在大圓裡面
	public boolean reseting; //判斷該不該跑回原來的位置
	private int color; //小圓的顏色
	private ArrayList<Character> targets;
	private ArrayList<Integer> values;

	public Character(MainApplet parent, String name, String color, int x, int y){

		this.parent = parent;
		this.name = name;
		this.color = (int) Long.parseLong(color.replace("#", ""), 16); //用來存取顏色
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
			for(Character c : targets){ //此部分為連線的部分，跟Lab8相似
				parent.noFill();
				parent.stroke(100);
				if(values.get(targets.indexOf(c)) > 10) //利用value來畫線的粗度
					parent.strokeWeight((float)values.get(targets.indexOf(c)) / 6);
				else 
					parent.strokeWeight((float)values.get(targets.indexOf(c)) / 2);
				float a1 = (this.nowX + 600) / 2;
				float b1 = (this.nowY + 325) / 2;
				float a2 = (c.nowX + 600) / 2;
				float b2 = (c.nowY + 325)  / 2;
				if (c.smallInBig == true) //畫曲線
					parent.bezier(nowX, nowY, a1, b1, a2, b2, c.nowX, c.nowY);
			}
		}
		this.parent.noStroke(); //小圓無外框
		this.parent.fill(color); //填滿小圓的顏色
		this.parent.ellipse(this.nowX, this.nowY, this.rad, this.rad); //畫小圓
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
	public void addTarget(Character target){ 
		this.targets.add(target); 
	}
	public void addValue(Integer value){ 
		this.values.add(value); 
	}
	public ArrayList<Character> getTargets(){ 
		return this.targets; 
	}
	public ArrayList<Integer> getValues(){ 
		return this.values; 
	}
	
}
