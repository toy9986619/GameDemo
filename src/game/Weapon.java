package game;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

/**
 * 定義武器的類別
 * @author Takus
 *
 */
public abstract class Weapon implements GameConfig {
		
	int x;	//位於地圖中的多少x
	int y;	//位於地圖中的多少y
	int max_x;	//最長射程
	int faceCount=1;	//判斷面相位置
	boolean attack = false;	//判斷是否發射
	Map map;
	int icon;
	int max_icon;
	
	/**
	 * 建構子
	 * @param map 所在的地圖
	 */
	public Weapon(Map map){
		this.map=map;
	}
	
	/**
	 * 角色射擊方法
	 * @param map 所在的地圖
	 */
	//角色用發射
	public void shoot(Map map) {
		
	}
	
	/**
	 * 敵人射擊方法
	 * @param en 敵人物件
	 * @param map 所在的地圖
	 */
	//敵人用發射
	public void enemyShoot(Enemy en, Map map){
		
		
	}
	
	/**
	 * 判斷角色是否打中敵人方法
	 */
	//判斷是否打中敵人
	public void attackEnemy(){
		
	}
	
	/**
	 * 判斷敵人是否打中角色方法
	 */
	//判斷是否打中玩家(敵人用)
	public void attackCharacter(){
		
	}
	
	/**
	 * 得到Y座標
	 * @return Y座標
	 */
	//得到武器在地圖中的位置I  
	public int getI(){  
			    
		
		return y/elesize;
	}  
	/**
	 * 得到X座標
	 * @return X座標
	 */
	//得到武器在地圖中的位置J  
	public int getJ(){  
			      
			
		return x/elesize;
	}
	
}
