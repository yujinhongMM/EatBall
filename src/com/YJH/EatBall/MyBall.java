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
	//�������С��
	public void Draw(Graphics g){
			g.setColor(col);
			g.fillOval(x, y, d, d);
			//Ҫ�����Բ�����Ͻǵ� x ���ꡣ
			//Ҫ�����Բ�����Ͻǵ� y ���ꡣ
			//width(d) - Ҫ�����Բ�Ŀ�ȡ�
			//height(d)- Ҫ�����Բ�ĸ߶ȡ�
	}
}
