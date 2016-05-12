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
	private float nowX, nowY; //�{�b��X��Y
	public float startX, startY; //�_�l��X��Y
	public boolean mouseOnMe = false; //�ƹ����S������p��W��
	private int rad; //�p��b�|
	public boolean smallInBig = false; //�p��b�j��̭�
	public boolean reseting; //�P�_�Ӥ��Ӷ]�^��Ӫ���m
	private int color; //�p�ꪺ�C��
	private ArrayList<Character> targets;
	private ArrayList<Integer> values;

	public Character(MainApplet parent, String name, String color, int x, int y){

		this.parent = parent;
		this.name = name;
		this.color = (int) Long.parseLong(color.replace("#", ""), 16); //�ΨӦs���C��
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
			for(Character c : targets){ //���������s�u�������A��Lab8�ۦ�
				parent.noFill();
				parent.stroke(100);
				if(values.get(targets.indexOf(c)) > 10) //�Q��value�ӵe�u���ʫ�
					parent.strokeWeight((float)values.get(targets.indexOf(c)) / 6);
				else 
					parent.strokeWeight((float)values.get(targets.indexOf(c)) / 2);
				float a1 = (this.nowX + 600) / 2;
				float b1 = (this.nowY + 325) / 2;
				float a2 = (c.nowX + 600) / 2;
				float b2 = (c.nowY + 325)  / 2;
				if (c.smallInBig == true) //�e���u
					parent.bezier(nowX, nowY, a1, b1, a2, b2, c.nowX, c.nowY);
			}
		}
		this.parent.noStroke(); //�p��L�~��
		this.parent.fill(color); //�񺡤p�ꪺ�C��
		this.parent.ellipse(this.nowX, this.nowY, this.rad, this.rad); //�e�p��
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
