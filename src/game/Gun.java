package game;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.magiclen.media.AudioPlayer;

/**
 * 武器-弩槍的Class
 * @author Takus
 *
 */
public class Gun extends Weapon {
	
	File file = new File("audio/gun.wav");
	AudioPlayer audioPlayer = new AudioPlayer(file);
	File file2 = new File("audio/attackCharacter.wav");
	AudioPlayer audioPlayer2 = new AudioPlayer(file2);
	
	/**
	 * 建構子
	 * @param map 傳入所在的地圖
	 */
	public Gun(Map map){
		super(map);
	}
	
	/**
	 * 敵人射擊方法
	 * @param en 敵人物件
	 * @param map 所在地圖
	 */
	public void enemyShoot(Enemy en, Map map){
		faceCount=en.faceCount;
		if(attack){
			audioPlayer.play();
			//System.out.println("attack!");
			if(faceCount==1){
				x=en.x+50;
				y=en.y-50;
				max_x=x+400;
				icon=100;
				
			}else if(faceCount==0){
				x=en.x-50;
				y=en.y-50;
				max_x=x-400;
				icon=101;
				
			}
		}
		
		//發射物移動
		Thread thread = new Thread(new Runnable(){
			
			public void run(){
				while(faceCount==1&&x<=max_x&&map.map1[y/elesize][x/elesize+1]==0&&attack==true)	{
					x=x+50;
					attackCharacter();
					try{
						Thread.sleep(70);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				
				while(faceCount==0&&x>=max_x&&map.map1[y/elesize][x/elesize-1]==0&&attack==true)	{
					x=x-50;
					attackCharacter();
					try{
						Thread.sleep(70);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				
				attack=false;
			}
		});
		
		
		thread.start();	
		
		
	}
	
	/**
	 * 攻擊角色的方法
	 */
	public void attackCharacter(){
		if(this.getJ()==(Character.x/elesize)){
			if(Character.SuperArmor==false){
				audioPlayer2.play();
				try{
					Enemy.minusCharacterHP();
				}catch(EndException e){
					e.GameOver();
				}
				
				if(Character.SuperArmor==true){
					attack=false;
					
					try{
						TimeUnit.SECONDS.sleep(2);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					Character.SuperArmor=false;
				}
			}

			attack=false;
			
		}
	}
}
