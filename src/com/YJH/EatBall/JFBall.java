package com.YJH.EatBall;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import javax.swing.JFrame;

public class JFBall extends JFrame {  
    public JFBall(){  
        this.setTitle("大球吃小球");         
        this.setBounds(100,100,800,600);        //窗体大小  
        setResizable(false);//设置窗体是否可以调整大小，参数为布尔值
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭窗体停止运行 
        setLocationRelativeTo(null);//设置窗口相对于指定组件的位置。
        //如果组件当前未显示，或者 c 为 null，则此窗口将置于屏幕的中央。
        
        JPBall jp=new JPBall();  
        
        
        // 绑定窗口事件监听器
		addWindowListener(new WindowAdapter() {
			// 窗口被激活时要刷新窗口
			@Override
			public void windowActivated(WindowEvent e) {
				repaint();
			}	
			// 在关闭窗口时先存档关闭JVM结束程序
			@Override
			public void windowClosing(WindowEvent e) {
				// 游戏存档
				BallContext context = new BallContext();
				context.setBalls(jp.balls);
				context.setGameover(jp.isGameover);
				context.setMb(jp.mb);
				context.setScore(jp.score);
				// 向文件中写入context对象就可以实现存档
				ObjectOutputStream oos = null;
				// TWR - Try With Resources - Java 7+
				try (OutputStream out = new FileOutputStream("EatBall.sav")) {
					oos = new ObjectOutputStream(out);
					oos.writeObject(context);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
        jp.run_run();       //开始运行多线程  
        this.add(jp);       //画布添加到窗体      
        this.setVisible(true);  
    }  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        new JFBall();  
    }  
}  