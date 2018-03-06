package com.YJH.EatBall;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * �浵����
 * @author YJH
 *
 */
public class BallContext implements Serializable{
	private List<Ball> balls =new ArrayList<Ball>();         //������е��� 
	private MyBall  mb=new MyBall(100, 100, 20, Color.red);//�ҷ���ʼС��
	private int score=0;//�ҷ���ʼ����
	private boolean isGameover=false;//�ж���Ϸ�Ƿ����
    
    
    
	public List<Ball> getBalls() {
		return balls;
	}
	public void setBalls(List<Ball> balls) {
		this.balls = balls;
	}
	public MyBall getMb() {
		return mb;
	}
	public void setMb(MyBall mb) {
		this.mb = mb;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isGameover() {
		return isGameover;
	}
	public void setGameover(boolean isGameover) {
		this.isGameover = isGameover;
	}
    
}
