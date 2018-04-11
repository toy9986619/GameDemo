package game;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import org.magiclen.media.AudioPlayer;

/**
 * 敵人的Class
 * @author Takus
 *
 */
public class Enemy extends Character {
	File file = new File("audio/attackCharacter.wav");
	AudioPlayer audioPlayer = new AudioPlayer(file);
	
	
    //角色中點在整張地圖中的位置
    int x;  
    int y;  
    //角色的步長
    int step = 10;  
    //角色是否移動  
    boolean up = false;  
    boolean down = true;  
    boolean left = false;  
    boolean right = false;   
    
    
    //載入地圖
    Map map;
    //載入武器
    Weapon enemyWp;
    
    //敵人HP
    int HP;
    
    //是否可見
    boolean visiable = true;
    
    //角色圖
    int Eicon=0;
    
    //繪圖用
    int rightCount = 27;
    int leftCount = 27;
    int faceCount = 1;
    
    //無敵
    boolean SuperArmor=false;
    
    /**
     * 敵人的建構子
     * @param map 所在的地圖
     * @param x X位置
     * @param y Y位置
     * @param iconNum 使用的外觀編號
     */
    public Enemy(Map map, int x, int y, int iconNum){
    	this.map=map;
    	this.x=x;
    	this.y=y;
    	this.Eicon=iconNum;
    	this.enemyWp=new Gun(map);
    	
    	this.start();
    	Collision cl = new Collision();
    	Thread t1 = new Thread(cl);
    	t1.start();
    	
    	Thread iconChange = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(visiable){
					Eicon++;
					if(Eicon==21)	Eicon=0;
					try{
						Thread.sleep(50);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
    		
    	});
    	
    	iconChange.start();
    }
    
    /**
     * 繪製敵人
     * @param g 系統參數Graphics
     */
    public void drawEnemy(Graphics g){
    	//面向右邊
    	if((Eicon/7)==0&&faceCount==1){
    		g.drawImage(ENicon_right1.getImage(), (this.getJ()-(Character.x/elesize)+13)*elesize-50, (this.getI()-(Character.y/elesize)+11)*elesize-150 , null);
    	}
    	else if((Eicon/7)==1&&faceCount==1){
    		g.drawImage(ENicon_right2.getImage(), (this.getJ()-(Character.x/elesize)+13)*elesize-50, (this.getI()-(Character.y/elesize)+11)*elesize-150 , null);
    	}
    	else if((Eicon/7)==2&&faceCount==1){
    		g.drawImage(ENicon_right3.getImage(), (this.getJ()-(Character.x/elesize)+13)*elesize-50, (this.getI()-(Character.y/elesize)+11)*elesize-150 , null);
    	}
    	
    	else if((Eicon/7)==0&&faceCount==0){
    		g.drawImage(ENicon_left1.getImage(), (this.getJ()-(Character.x/elesize)+13)*elesize, (this.getI()-(Character.y/elesize)+11)*elesize-150 , null);
    	}
    	else if((Eicon/7)==1&&faceCount==0){
    		g.drawImage(ENicon_left2.getImage(), (this.getJ()-(Character.x/elesize)+13)*elesize, (this.getI()-(Character.y/elesize)+11)*elesize-150 , null);
    	}
    	else if((Eicon/7)==2&&faceCount==0){
    		g.drawImage(ENicon_left3.getImage(), (this.getJ()-(Character.x/elesize)+13)*elesize, (this.getI()-(Character.y/elesize)+11)*elesize-150 , null);
    	}
    }
    
    /**
     * 敵人移動的執行緒動作
     */
	@Override  //移動與掉落
	public void run() {  
	    while(visiable){  
	    	if(this.x>=Character.x&&Math.abs((this.getJ()-(Character.x/elesize)))<=9&&this.getI()==(Character.y/elesize)){
	    		if(faceCount==1)	x=x-50;
	    		
	    		faceCount=0;
	    		enemyWp.attack=true;
	    		enemyWp.enemyShoot(this, map);
	    	}
	    	else if(this.x<=Character.x&&Math.abs((this.getJ()-(Character.x/elesize)))<=9&&this.getI()==(Character.y/elesize)){
	    		if(faceCount==0)	x=x+50;
	    		
	    		faceCount=1;
	    		enemyWp.attack=true;
	    		enemyWp.enemyShoot(this, map);
	    	}
	    	else{
	    		move(); 
	    	}
	    	 
	        //jump();
	    	//if(down)	fall();
	        try {  
	            Thread.sleep(2000);  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }
	        
	    }  
	}  
	 
	/**
	 * 敵人移動方法
	 */
	//角色移動的方法  
	public void move(){  
		
		int mv = (int) (Math.random()*10%6);
		//System.out.println(mv);
	    if(mv<=2){  
	    	//System.out.printf("%d %d\n", this.getI(), this.getJ());
	    	if(faceCount==1)	x=x-50;
	    	
	    	faceCount=0;
	    	if(map.map1[this.getI()][this.getJ()-1]!=0){  }
	    	else if(map.map1[this.getI()][this.getJ()-1]==0&&map.map1[this.getI()+1][this.getJ()-1]!=0){  
                x=x-50;   
            }    
	    }  
	    else if(mv>3){  
	    	//System.out.printf("%d %d\n", this.getI(), this.getJ());
	    	if(faceCount==0)	x=x+50;
	    	
	    	faceCount=1;
	    	if(map.map1[this.getI()][this.getJ()+1]!=0){ }
	    	else if(map.map1[this.getI()][this.getJ()+1]==0&&map.map1[this.getI()+1][this.getJ()+1]!=0){  
                x=x+50;  
            } 
	    }
	}
	
	/**
	 * 取得敵人的Y座標
	 * @return Y座標
	 */
	//得到角色在地圖中的位置I  
	public int getI(){  
		    
			
		return y/elesize;
	} 
	
	/**
	 * 取得敵人的X座標
	 * @return X座標
	 */
	//得到角色在地圖中的位置J  
	public int getJ(){  
		      
			
		return x/elesize;
	}  
	
	/**
	 * 得到敵人是否可見
	 * @return 布林值
	 */
	//得到敵人是否可見
	public boolean isVisiable(){
			
		return visiable;
	}
	
	/**
	 * 跟玩家對撞的方法
	 */
	//跟角色對撞
	public void collision(){
		if(this.getJ()==(Character.x/elesize)&&this.getI()==(Character.y/elesize)&&Character.SuperArmor==false){
			audioPlayer.play();
			
			try{
				minusCharacterHP();
				if(Character.faceCount==0)	Character.x+=150;
				else if(Character.faceCount==1)	Character.x-=150;
			}catch(EndException e){
				e.GameOver();
			}

			try{
				TimeUnit.SECONDS.sleep(2);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			Character.SuperArmor=false;
		}
		
		
	}
	
	/**
	 * 減少玩家HP的方法
	 * @throws EndException 結束例外
	 */
	public static void minusCharacterHP() throws EndException{
		Character.HP-=10;
		Character.SuperArmor=true;
		
		if(Character.HP==0)	throw new EndException();
	}
	
	/**
	 * 處理碰撞的執行緒Class
	 * @author Takus
	 *
	 */
	class Collision implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(visiable){
				collision();
				try{
					Thread.sleep(20);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		
	}
    
    
}
