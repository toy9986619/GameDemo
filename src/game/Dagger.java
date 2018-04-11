package game;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;
import org.magiclen.media.AudioPlayer;
import sun.audio.*;
import org.magiclen.media.*;

/**
 * 武器-飛刀的類別
 * @author Takus
 * 
 */
public class Dagger extends Weapon{
	
	File file = new File("audio/dagger.wav");
	AudioPlayer audioPlayer = new AudioPlayer(file);
	File file2 = new File("audio/attackEnemy.wav");
	AudioPlayer audioPlayer2 = new AudioPlayer(file2);
	
	/**
	 * 建構子
	 * @param map 傳入所在的地圖
	 */
	public Dagger(Map map){
		super(map);
		this.map=map;
		
	}
	
	/**
	 * 射擊方法
	 * @param map 傳入所在的地圖
	 */
	public void shoot(Map map) {
		//audioPlayer.setVolume(10);
		faceCount=Character.faceCount;
		
		if(attack){
			//System.out.println("attack!");
			//System.out.println(faceCount);
			
			if(faceCount==1){
				x=Character.x+50;
				y=Character.y-50;
				max_x=x+100;
				icon=0;
				max_icon=icon+3;
				
			}else if(faceCount==0){
				x=Character.x-50;
				y=Character.y-50;
				max_x=x-100;
				icon=4;
				max_icon=icon+3;
				
			}
			
			
		}
		
		//發射物移動
		Thread thread = new Thread(new Runnable(){
			
			public void run(){
				
				int audioCount=0;
				
				while(faceCount==1&&icon<max_icon&&attack==true&&x<=max_x&&map.map1[y/elesize][x/elesize+1]==0){
					if(audioCount<1){
						audioPlayer.play();
						audioCount++;
					}
					
					icon++;
					try{
						Thread.sleep(70);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				
				while(faceCount==0&&icon<max_icon&&attack==true&&x>=max_x&&map.map1[y/elesize][x/elesize-1]==0){
					if(audioCount<1){
						audioPlayer.play();
						audioCount++;
					}
					
					icon++;
					try{
						Thread.sleep(70);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				
				while(faceCount==1&&x<=max_x&&map.map1[y/elesize][x/elesize+1]==0&&attack==true)	{
					x=x+50;
					attackEnemy();
					try{
						Thread.sleep(50);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				
				while(faceCount==0&&x>=max_x&&map.map1[y/elesize][x/elesize-1]==0&&attack==true)	{
					x=x-50;
					attackEnemy();
					try{
						Thread.sleep(50);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				
				audioCount=0;
				attack=false;
			}
		});
		
		
		thread.start();
		
	}
	
	/**
	 * 判斷有無擊中敵人
	 */
	public void attackEnemy(){
		ArrayList ms = map.getEnemyList();
		for (int i = 0; i < ms.size(); i++) {

            Enemy m = (Enemy) ms.get(i);
            
            if(m.isVisiable() && getJ()==m.getJ() && (m.getI()-this.getI()>=0&&m.getI()-this.getI()<=3) ){
            	
            	try{
            		minusEnemy(ms, m ,i);
            	}catch(EndException e){
            		e.Win();
            	}

            	attack=false;
            	
            }
            
        }
	}
	
	/**
	 * 敵人消除
	 * 
	 * @param ms 敵人的ArrayList
	 * @param m	敵人Enemy
	 * @param i	位於ArrayList的值
	 * @throws EndException	結束例外方法
	 */
	public void minusEnemy(ArrayList ms, Enemy m, int i) throws EndException{
		audioPlayer2.play();
		m.visiable=false;
    	ms.remove(i);
		
		if(ms.size()==0)	throw new EndException();
	}
	
}
