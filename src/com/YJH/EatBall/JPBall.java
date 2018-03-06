package com.YJH.EatBall;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;  
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;  
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * 监听鼠标,线程
 * @author YJH
 *
 */
public class JPBall extends JPanel implements MouseMotionListener{      //实现监听鼠标的接口  
	List<Ball> balls =new ArrayList<Ball>();         //存放所有的球 
	MyBall  mb=new MyBall(100, 100, 20, Color.red);//我方初始小球
	int score=0;//我方初始分数
	boolean isGameover=false;//判断游戏是否结束
	private BufferedImage image = new BufferedImage(800, 600, 1);
    
    
    public JPBall(){  
        addMouseMotionListener(this);         //添加监听  
       
        
        for(int i=0;i<50;i++)  
            add_ball();
    }
    
  //游戏重新开始
    public void reGame(){
    	addMouseMotionListener(this);  //添加监听  
    	
    	 balls.clear();
         for(int i=0;i<50;i++)  
         add_ball();
         mb.x=100;
         mb.y=100;
         mb.d=20;
         score=0;
         isGameover=false;   
    }

    
      
    public void add_ball(){                      //随机数产生球的初始坐标,大小,方向，颜色等  
        int x=(int) (Math.random()*700);  
        int y=(int) (Math.random()*500);  
        int dir=(int) (Math.random()*4);  
        int d=(int) (Math.random()*30+10);  
        int r=(int) (Math.random()*255);  
        int g=(int) (Math.random()*255);  
        int b=(int) (Math.random()*255);  
        int sp=(int) (Math.random()*1+1.2);  
        Color col=new Color(r,g,b);  
        balls.add(new Ball(x,y,dir,d,sp,col));
        
    }
    
    public void paint(Graphics g) {
    	
    	Graphics g3 = image.getGraphics();//双缓冲
    	
        super.paint(g3);
        setBackground(Color.gray);                 //隐含this 设置画布的背景颜色  
        ImageIcon overimg= new ImageIcon("..\\EatBall\\src\\com\\YJH\\EatBall\\xingkong.png");  
        g3.drawImage(overimg.getImage(), 0, 0, null);
        mb.Draw(g3);//画出自己的小球
        for(int i=0;i<balls.size();i++){            //把所有的球画出来  
            Ball b=balls.get(i);  
            b.Draw(g3);  
        }
        Graphics2D g2 = (Graphics2D) g3;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 36);
        g2.setFont(font);
        g2.drawString("得分："+String.valueOf(score), 30, 50);//得分情况
     // 使用双缓冲技术消除窗口上的闪烁现象
     // 先通过BufferedImage对象获得内存中的图像的画布
     // 将内存中渲染好的图像直接放到窗口上
     		g.drawImage(image, 0, 0, null);
    }  
      
    public void run_run(){ 
    			
        new Thread(){  
            public void run(){
            	load();//读档
            	if(isGameover){//上次已经赢了或输了重新开始
           			 reGame();
            	}
                while(!isGameover){  
                    for(int i=0;i<balls.size();i++){               //所有球运动一次  
                        Ball b=balls.get(i);  
                        b.move();  
                    }  
                    for(int i=0;i<balls.size();i++){           //两个for循环判断所有小球是否碰撞  
                    	Ball b1=balls.get(i);
                        BallandBall bab=new BallandBall();
                        if(bab.balldestroy(b1, mb)==1){//我和敌方碰到了  
                        	if(b1.d>=mb.d){   
                        		b1.d+=mb.d/6;
                        		mb.d=0;
                        		repaint();
                        		JOptionPane.showMessageDialog(JPBall.this, "你失败了，最终得分"+String.valueOf(score));  						//游戏结束
                        		isGameover=true;
                        		int res=JOptionPane.showConfirmDialog(null, "是否重新开始", "是否重新开始", JOptionPane.YES_NO_OPTION); 
                        		 if(res==JOptionPane.YES_OPTION){ 
                        			 reGame();
                        		 }else{
                        			 System.exit(0);
                        		 }
                        		break;
                            }else if(b1.d<mb.d){
                            	score+=1;//得分
                            	mb.d+=b1.d/6;
                                balls.remove(i);
                                continue;  
                            }
                        }
                        for(int j=0;j<balls.size();j++){
                        	if(j==i){
                        		continue; 
                        	}
                            Ball b2=balls.get(j);  
                            if(bab.balldestroy(b1, b2)==1){        //碰到了  
                                if(b1.d>=b2.d){  
                                    b1.d+=b2.d/6;  
                                    balls.remove(j);  
                                    break;  
                                }else if(b1.d<b2.d){  
                                    b2.d+=b1.d/6;  
                                    balls.remove(i);  
                                    break;  
                                }
                            }  
                              
                        }  
                    }
                    if(isGameover){//被吃掉后不重绘退出循环
                    	break;
                    }
                    while(balls.size()<30){//容器中的敌方小球被吃掉时,增加容器中的小球  
                            add_ball();  
                    }
                    repaint();                        //再次调用paint() 
                    if(score>=100){
                    	JOptionPane.showMessageDialog(JPBall.this, "恭喜你成功了！最终得分"+String.valueOf(score));
                    	isGameover=true;
                    	int res=JOptionPane.showConfirmDialog(null, "是否重新开始", "是否重新开始", JOptionPane.YES_NO_OPTION); 
               		 if(res==JOptionPane.YES_OPTION){ 
               			 reGame();
               		 }else{
            			 System.exit(0);
            		 }
                    }
                    try {  
                        Thread.sleep(30);            //休眠  
                    } catch (InterruptedException e) {  
                    e.printStackTrace();   
                }  
                }  
            }
        }.start(); 
         
    }
    //读出上次保存的游戏
    private void load() {
		try (InputStream in = new FileInputStream("EatBall.sav")) {
			ObjectInputStream ois = new ObjectInputStream(in);
			BallContext context = (BallContext) ois.readObject();
			balls=context.getBalls();
			isGameover=context.isGameover();
			mb=context.getMb();
			score=context.getScore();
			repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    
    @Override  
    public void mouseDragged(MouseEvent e) {  
        // TODO Auto-generated method stub  
          
    }  
  
    @Override  
    public void mouseMoved(MouseEvent e) {    
        mb.x=e.getX()-mb.d/2;              //获得鼠标的坐标 
        mb.y=e.getY()-mb.d/2;
    }
}
