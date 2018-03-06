package com.YJH.EatBall;
/**
 * 将所有判断两个球相关的函数合并在一起单独抽象为一个类
 * @author YJH
 *
 */
public class BallandBall {
	  public int balldestroy(Ball a,Ball b){         //判断敌方两球是否相碰  
	        int ret=0;  
	        int x1=a.x+a.d/2;  
	        int y1=a.y+a.d/2;  
	        int x2=b.x+b.d/2;  
	        int y2=b.y+b.d/2;  
	        double dis= Math.sqrt( (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));  
	        if(dis<=a.d/2+b.d/2)ret=1;  
	        return ret;  
	    }
	  public int balldestroy(Ball a,MyBall b){         //判断我和敌两球是否相碰  
	        int ret=0;  
	        int x1=a.x+a.d/2;  
	        int y1=a.y+a.d/2;  
	        int x2=b.x+b.d/2;  
	        int y2=b.y+b.d/2;  
	        double dis= Math.sqrt( (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));  
	        if(dis<=a.d/2+b.d/2)ret=1;  
	        return ret;  
	    }  
}
