package main.java;

import java.util.ArrayList;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.event.KeyEvent;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "./main/resources/";
	private String file = "starwars-episode-1-interactions.json"; //初始為第一個檔案
	JSONObject data;
	JSONArray nodes, links;
	public int bigCircleX = 650; //大圓的X
	public int bigCircleY = 325; //大圓的Y
	private int bigCircleStroke = 3; //大圓的粗度 
	public boolean smallInBig; ////小圓在大圓裡面
	private ArrayList<Character> characters;
	private Character nowNode = null; //目前被點到的小圓
	private ControlP5 con; //兩個button
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		size(width, height);
		characters = new ArrayList<Character>();
		this.data = loadJSONObject(path + file);
		loadData();
		smooth();
		con = new ControlP5(this);
		con.addButton("button1").setLabel("ADD ALL") //初始ADD ALL的值
	    		                .setPosition(930, 60)
								.setSize(100, 60)
								.setColorBackground(0xFF3881e5)
								.setColorForeground(0xFFff9400)
								.setColorActive(0xFF7ACB95);
								
		con.addButton("button2").setLabel("CLEAR") //初始CLEAR的值
								.setPosition(930, 180)
								.setSize(100, 60)
								.setColorBackground(0xFF3881e5)
								.setColorForeground(0xFFff9400)
								.setColorActive(0xFF7ACB95);
	}
	public void button1() {
		for(Character c : characters){
			c.reseting = false;
			c.smallInBig = true;
		}
	}
	
	public void button2() {
		for(Character c : characters) {
			c.smallInBig = false;
		}
	}
	public void draw() {
		background(255);
		stroke(100,100,100); //右邊大圓的外圍顏色
		if(nowNode != null && nowNode.smallInBig == true)
			strokeWeight(8); //右邊大圓的粗度
		else
			strokeWeight(2);
		fill(255, 255, 255); //右邊大圓填滿白色
		ellipse(bigCircleX, bigCircleY, 500, 500); //右邊大圓的位置及大小
		strokeWeight(0); //左邊小圓的粗度
		smallInCircle();
		for(Character character: characters) { //若有移到小圓上面，小圓會變大
			if(dist(character.getNowX() , character.getNowY() , mouseX , mouseY) < 20){
				character.setRad(50);
			} else {
				character.setRad(40);
			}
			if(character.reseting == true) 
				this.resetSmall(character);
			character.display();
		}
		for(Character character: this.characters){ //用來加名字，因為要覆蓋在所有小圓上，所以不能寫在上面的for迴圈，當滑鼠移到小圓上或是按小圓會顯示名字
			if ((nowNode == null && dist(character.getNowX() , character.getNowY() , mouseX , mouseY) < 20) || character == nowNode)  {
				this.fill(0, 255, 255);;
				this.rect(mouseX, mouseY- 15, character.name.length()*15, 30, 15);
				this.fill(0);
				this.textSize(16);
				this.text(character.name, mouseX + 10, mouseY + 5);
			}
		}
	}
	public void mousePressed() {
		for(Character character: characters){
			if(dist(character.getNowX() , character.getNowY() , mouseX , mouseY) < 25){
				nowNode = character;
			}
		}
	}
	public void mouseReleased() {
		for(Character character: this.characters){
			 character.setRad(40);
			 if(character.smallInBig == false){ //放開時若在大圓裡則不用reset，不在大圓裡要reset 
				character.reseting = true;
			 }else{
				 character.reseting = false;
			 }
		}
		nowNode = null;
	}
	public void mouseDragged() {
		if (nowNode != null) {
			nowNode.setNowX(this.mouseX); //小圓的位置會跟著滑鼠的位置跑，達到拖曳的效果
			nowNode.setNowY(this.mouseY);	
			if (dist(nowNode.getNowX(), nowNode.getNowY(), bigCircleX, bigCircleY) < 250) //若移到大圓裡則smallInBig變true
				nowNode.smallInBig = true;
			else 
				nowNode.smallInBig = false;
		}
	}
	
	private void loadData(){ //讀Json檔
		this.nodes = data.getJSONArray("nodes");
		int x = 30, y = 30;
		for(int i=0; i<this.nodes.size(); i++) {
			JSONObject json = nodes.getJSONObject(i);
			String name = json.getString("name");
			String color = json.getString("colour");
			characters.add(new Character(this, name, color, x, y));
			y += 60;
			if(y >= 610) {
				y = 30;
				x += 60;
			}
			//System.out.println(color);
		}
		this.links = data.getJSONArray("links");
		for(int i=0; i<links.size(); i++){
			JSONObject link = links.getJSONObject(i);
			characters.get(link.getInt("source")).addTarget(characters.get(link.getInt("target")));
			characters.get(link.getInt("source")).addValue(link.getInt("value"));
		}
	}
	
	public void keyPressed(KeyEvent ev){ //用來變換檔案，當按下1~7時對應到該顯示的檔案
		if(this.keyCode == '1') {
			this.clear();
			file = "starwars-episode-1-interactions.json";
			this.setup();
		}else if(this.keyCode == '2') {
			this.clear();
			file = "starwars-episode-2-interactions.json";
			this.setup();
		}
		else if(this.keyCode == '3') {
			this.clear();
			file = "starwars-episode-3-interactions.json";
			this.setup();
		}else if(this.keyCode == '4') {
			this.clear();
			file = "starwars-episode-4-interactions.json";
			this.setup();
		}else if(this.keyCode == '5') {
			this.clear();
			file = "starwars-episode-5-interactions.json";
			this.setup();
		}else if(this.keyCode == '6') {
			this.clear();
			file = "starwars-episode-6-interactions.json";
			this.setup();
		}else if(this.keyCode == '7') {
			this.clear();
			file = "starwars-episode-7-interactions.json";
			this.setup();
		}
	}
	private void resetSmall(Character ch){ //重設小圓
		float tmpx = ch.getNowX() - ch.startX;
		float tmpy = ch.getNowY() - ch.startY;
		ch.setNowX(ch.startX + tmpx * (float)0.9); //讓小圓可以以滑動的動畫呈現
		ch.setNowY(ch.startY + tmpy * (float)0.9);
		if(Math.abs(tmpx) < 0.01 || Math.abs(tmpy) < 0.01){ //當距離原本的位置很小時歸原位，因為現在的位置可能在原本的上下左右都有可能，所以意思是tmpX、tmpY有可能是負的或是正的，所以要用絕對值abs
			ch.setNowX(ch.startX);
			ch.setNowY(ch.startY);
			ch.reseting = false;
		}
	}
	private void smallInCircle(){
		int num = 0;
		for(Character character: this.characters){ //計算有幾個小圓在大圓裡面
			if(character.smallInBig == true) 
				num++;
		}
		int index = 0;
		for(Character character: this.characters){ //使原呈現多邊形的位置，像直角坐標一樣，從正東方開始順時針加圓
			if(character.smallInBig == true && character != nowNode){
				character.setNowX(bigCircleX + 250 * (float)Math.cos(Math.PI*2/num*index));
				character.setNowY(bigCircleY + 250 * (float)Math.sin(Math.PI*2/num*index));
				index++;
			}
		}
	}

}
