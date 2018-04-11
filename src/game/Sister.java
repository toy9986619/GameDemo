package game;
import java.util.concurrent.TimeUnit;


/**
 * 定義角色-姊姊的Class
 * @author Takus
 *
 */
public class Sister extends Character implements GameConfig {
	
	/**
	 * 建構子
	 * @param map 所在地圖
	 */
	public Sister(Map map){
		super(map);
		HP=100;
		wp=new Dagger(map);
		
		Thread fight = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					if(attack){
						wp.attack=true;
						wp.shoot(map);
						try{
							TimeUnit.MILLISECONDS.sleep(700);
						}catch(InterruptedException e){
							e.printStackTrace();
						}
					}
					
					try{
						Thread.sleep(15);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}	
    	});
		
    	fight.start();
	}
	

}
