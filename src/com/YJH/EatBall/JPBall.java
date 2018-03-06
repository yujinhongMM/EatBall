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
 * �������,�߳�
 * @author YJH
 *
 */
public class JPBall extends JPanel implements MouseMotionListener{      //ʵ�ּ������Ľӿ�  
	List<Ball> balls =new ArrayList<Ball>();         //������е��� 
	MyBall  mb=new MyBall(100, 100, 20, Color.red);//�ҷ���ʼС��
	int score=0;//�ҷ���ʼ����
	boolean isGameover=false;//�ж���Ϸ�Ƿ����
	private BufferedImage image = new BufferedImage(800, 600, 1);
    
    
    public JPBall(){  
        addMouseMotionListener(this);         //��Ӽ���  
       
        
        for(int i=0;i<50;i++)  
            add_ball();
    }
    
  //��Ϸ���¿�ʼ
    public void reGame(){
    	addMouseMotionListener(this);  //��Ӽ���  
    	
    	 balls.clear();
         for(int i=0;i<50;i++)  
         add_ball();
         mb.x=100;
         mb.y=100;
         mb.d=20;
         score=0;
         isGameover=false;   
    }

    
      
    public void add_ball(){                      //�����������ĳ�ʼ����,��С,������ɫ��  
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
    	
    	Graphics g3 = image.getGraphics();//˫����
    	
        super.paint(g3);
        setBackground(Color.gray);                 //����this ���û����ı�����ɫ  
        ImageIcon overimg= new ImageIcon("..\\EatBall\\src\\com\\YJH\\EatBall\\xingkong.png");  
        g3.drawImage(overimg.getImage(), 0, 0, null);
        mb.Draw(g3);//�����Լ���С��
        for(int i=0;i<balls.size();i++){            //�����е��򻭳���  
            Ball b=balls.get(i);  
            b.Draw(g3);  
        }
        Graphics2D g2 = (Graphics2D) g3;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 36);
        g2.setFont(font);
        g2.drawString("�÷֣�"+String.valueOf(score), 30, 50);//�÷����
     // ʹ��˫���弼�����������ϵ���˸����
     // ��ͨ��BufferedImage�������ڴ��е�ͼ��Ļ���
     // ���ڴ�����Ⱦ�õ�ͼ��ֱ�ӷŵ�������
     		g.drawImage(image, 0, 0, null);
    }  
      
    public void run_run(){ 
    			
        new Thread(){  
            public void run(){
            	load();//����
            	if(isGameover){//�ϴ��Ѿ�Ӯ�˻��������¿�ʼ
           			 reGame();
            	}
                while(!isGameover){  
                    for(int i=0;i<balls.size();i++){               //�������˶�һ��  
                        Ball b=balls.get(i);  
                        b.move();  
                    }  
                    for(int i=0;i<balls.size();i++){           //����forѭ���ж�����С���Ƿ���ײ  
                    	Ball b1=balls.get(i);
                        BallandBall bab=new BallandBall();
                        if(bab.balldestroy(b1, mb)==1){//�Һ͵з�������  
                        	if(b1.d>=mb.d){   
                        		b1.d+=mb.d/6;
                        		mb.d=0;
                        		repaint();
                        		JOptionPane.showMessageDialog(JPBall.this, "��ʧ���ˣ����յ÷�"+String.valueOf(score));  						//��Ϸ����
                        		isGameover=true;
                        		int res=JOptionPane.showConfirmDialog(null, "�Ƿ����¿�ʼ", "�Ƿ����¿�ʼ", JOptionPane.YES_NO_OPTION); 
                        		 if(res==JOptionPane.YES_OPTION){ 
                        			 reGame();
                        		 }else{
                        			 System.exit(0);
                        		 }
                        		break;
                            }else if(b1.d<mb.d){
                            	score+=1;//�÷�
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
                            if(bab.balldestroy(b1, b2)==1){        //������  
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
                    if(isGameover){//���Ե����ػ��˳�ѭ��
                    	break;
                    }
                    while(balls.size()<30){//�����еĵз�С�򱻳Ե�ʱ,���������е�С��  
                            add_ball();  
                    }
                    repaint();                        //�ٴε���paint() 
                    if(score>=100){
                    	JOptionPane.showMessageDialog(JPBall.this, "��ϲ��ɹ��ˣ����յ÷�"+String.valueOf(score));
                    	isGameover=true;
                    	int res=JOptionPane.showConfirmDialog(null, "�Ƿ����¿�ʼ", "�Ƿ����¿�ʼ", JOptionPane.YES_NO_OPTION); 
               		 if(res==JOptionPane.YES_OPTION){ 
               			 reGame();
               		 }else{
            			 System.exit(0);
            		 }
                    }
                    try {  
                        Thread.sleep(30);            //����  
                    } catch (InterruptedException e) {  
                    e.printStackTrace();   
                }  
                }  
            }
        }.start(); 
         
    }
    //�����ϴα������Ϸ
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
        mb.x=e.getX()-mb.d/2;              //����������� 
        mb.y=e.getY()-mb.d/2;
    }
}
