package com.YJH.EatBall;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class MyBall implements Serializable{
	int x,y,d;
	Color col;
	public MyBall(int x, int y, int d,Color col) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
		this.col = col;
	}
	//画出这个小球
	public void Draw(Graphics g){
			g.setColor(col);
			g.fillOval(x, y, d, d);
			//要填充椭圆的左上角的 x 坐标。
			//要填充椭圆的左上角的 y 坐标。
			//width(d) - 要填充椭圆的宽度。
			//height(d)- 要填充椭圆的高度。
	}
}
