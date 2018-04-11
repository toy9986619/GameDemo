package game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * 撰寫角色的類別
 * @author Takus
 * 
 * 
 */
public abstract class Character extends Thread implements GameConfig{
	
    //角色中點相對遊戲畫面的位置(在遊戲中不變)  
    int px = panelX/2;  
    int py = 50*11;  
    //角色中點在整張地圖中的位置
    static int x = 3000;  
    static int y = 1000;  
    //角色的步長
    int step = 10;  
    //角色是否移動 
    boolean up = false;  
    boolean down = true;  
    boolean left = false;  
    boolean right = false;   
    //判斷角色是否攻擊
    boolean attack = false;
    
    //判斷角色是否霸體
    static boolean SuperArmor=false;
    
    //角色血量
    static int HP;
    //載入地圖
    Map map;
    //載入武器
    Weapon wp;
    
    //繪圖用
    int rightCount = 27;
    int leftCount = 27;
    static int faceCount = 1;
    
    /**
     * 建構子的方法說明
     */
    public Character(){
    	x = 3000; 
    	y = 1000;
    }
    
    /**
     * 建構子的方法說明
     * @param map 傳入所在的地圖
     */
    public Character(Map map){
    	x = 3000; 
    	y = 1000;
    	this.map=map;
    	Jump jp = new Jump();
    	Thread t1 = new Thread(jp);
    	t1.start();
    	
    }
    
    /**
     * 移動用的執行緒
     */
	@Override  //移動與掉落
	public void run() {  
	    while(true){  
	    	move();  
	    	if(down)	fall();
	        try {  
	            Thread.sleep(20);  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }
	        
	    }  
	}  
	 
	/**
	 * 移動方法
	 */
	//角色移動的方法  
	public void move(){  
		
	    if(left){  
	    	//System.out.printf("%d %d\n", this.getI(), this.getJ());
	    	if(faceCount==1)	x=x-20;
	    	
	    	faceCount=0;
            leftCount++;
            if(leftCount==28)leftCount=0;
            
	    	if(map.map1[this.getI()][this.getJ()-1]!=0){  }
	    	else if(map.map1[this.getI()][this.getJ()-1]==0){  
                x=x-step; 
                
            }    
	    }  
	    if(right){  
	    	//System.out.printf("%d %d\n", this.getI(), this.getJ());
	    	if(faceCount==0)	x=x+20;
	    	
	    	faceCount=1;
            rightCount++;
            if(rightCount==28)rightCount=0;
	    	
	    	if(map.map1[this.getI()][this.getJ()+1]!=0){ }
	    	else if(map.map1[this.getI()][this.getJ()+1]==0){  
                x=x+step;  
                
            } 
	    }
	}  
	
	/**
	 * 跳躍方法
	 */
	//角色跳躍的方法
	public void jump(){
		
		if(up==true&&down==false){
			
			down=true;
			fall();
			up=false;
			
		}
		
		if(up==true&&down==true){
			
			down=false;
			//System.out.printf("%d %d\n", this.getI(), this.getJ());
			if(map.map1[this.getI()-1][this.getJ()]!=0){  }
			else if(map.map1[this.getI()-1][this.getJ()]==0){//上方没物体，可以继续向上移动  
				this.y=this.y-100;  
			}

			System.out.printf("%d %d\n", this.getI(), this.getJ());	
		}

		try{
			TimeUnit.MILLISECONDS.sleep(150);
		}catch(InterruptedException e){
			e.printStackTrace();   
		}
		
		
	}
	
	/**
	 * 角色掉落的方法
	 */
	//角色掉落的方法
	public void fall(){
		/*while(down){
			if(map.map1[this.getI()+1][this.getJ()]!=0){  
	            break;
		    }else if(map.map1[this.getI()+1][this.getJ()]==0){  
		           y=y+step;
		    }    
		     
		}*/
		
		if(down&&map.map1[this.getI()+1][this.getJ()]==0){
			y=y+50;
		}
	}
	
	/**
	 * 繪製角色的方法
	 * @param g 系統參數Graphics
	 */
	//畫角色
	public void drawCharacter(Graphics g){
		//g.drawImage(sister.getImage(), this.px-50, this.py-121, null);
		
		//面向右邊
		if((rightCount/7)==0&&faceCount==1){
			g.drawImage(sister_right1.getImage(), this.px-40, this.py-121, null);
		}
		else if((rightCount/7)==1&&faceCount==1){
			g.drawImage(sister_right2.getImage(), this.px-40, this.py-121, null);
		}
		else if((rightCount/7)==2&&faceCount==1){
			g.drawImage(sister_right3.getImage(), this.px-40, this.py-121, null);
		}
		else if((rightCount/7)==3&&faceCount==1){
			g.drawImage(sister_right2.getImage(), this.px-40, this.py-121, null);
		}
		
		//面向左邊
		else if((leftCount/7)==0&&faceCount==0){
			g.drawImage(sister_left1.getImage(), this.px-10, this.py-121, null);
		}
		else if((leftCount/7)==1&&faceCount==0){
			g.drawImage(sister_left2.getImage(), this.px-10, this.py-121, null);
		}
		else if((leftCount/7)==2&&faceCount==0){
			g.drawImage(sister_left3.getImage(), this.px-10, this.py-121, null);
		}
		else if((leftCount/7)==3&&faceCount==0){
			g.drawImage(sister_left2.getImage(), this.px-10, this.py-121, null);
		}
	}
	
	/**
	 * 回傳座標Y的方法
	 * @return	Y座標
	 */
	//得到角色在地圖中的位置I  
	public int getI(){  
	    
		
		return y/elesize;
	}  
	/**
	 * 回傳X座標的方法
	 * @return X座標
	 */
	//得到角色在地圖中的位置J  
	public int getJ(){  
	      
		
		return x/elesize;
	}  
	
	/**
	 * 跳躍用的執行緒
	 * @author Takus
	 * 
	 */
	//跳躍用Thread
	class Jump implements Runnable{
		public void run(){
			while(true){
				jump();
				try{
					Thread.sleep(20);
				}catch(InterruptedException e){
					e.printStackTrace();   
				}
				
			}
		}
	}
}

