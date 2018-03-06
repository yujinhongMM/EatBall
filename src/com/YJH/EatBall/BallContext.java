package com.YJH.EatBall;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 存档对象
 * @author YJH
 *
 */
public class BallContext implements Serializable{
	private List<Ball> balls =new ArrayList<Ball>();         //存放所有的球 
	private MyBall  mb=new MyBall(100, 100, 20, Color.red);//我方初始小球
	private int score=0;//我方初始分数
	private boolean isGameover=false;//判断游戏是否结束
    
    
    
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
