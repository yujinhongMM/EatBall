package com.YJH.EatBall;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * ����ĸ������Ա�����һ����
 * @author YJH
 *
 */
public class Ball implements Serializable{
	int x,y,dir,d;
	int speed;
	Color col;
	static final int left_up=0,right_up=1,left_down=2,right_down=3;
	
	public Ball(int x, int y, int dir, int d, int speed, Color col) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.d = d;
		this.speed = speed;
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
	
	//С���˶�
	public void move(){
		switch(this.dir){
		case left_up:
			x-=speed;y-=speed;
			if(x<=0)
				dir=right_up;
			else if(y<=0)
				dir=left_down;
			break;
		case right_up:
			x+=speed;y-=speed;
			if(x>=800-d)
				dir=left_up;
			else if(y<=0)
				dir=right_down;
			break;
		 case left_down:  
	         x-=speed;y+=speed;  
	         if(x<=0)dir=right_down;  
	         else if(y>=600-d)dir=left_up;  
	         break;  
	     case right_down:  
	         x+=speed;y+=speed;  
	         if(x>=800-d)dir=left_down;  
	         else if(y>=600-d)dir=right_up;  
	         break;  
		}
		
	}
}
