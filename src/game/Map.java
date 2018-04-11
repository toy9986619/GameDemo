package game;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import java.io.*;
import java.util.ArrayList;

import javax.imageio.*;

/**
 * 地圖Class
 * @author Takus
 *
 */

public class Map extends JPanel {
	String mapName; 
	ArrayList enemy;
	
	int[] background;
	int[][] map1;
	int enemyCount;
	
	/**
	 * 建構子，執行init方法
	 */
	public Map(){
		init();
	}
	
	/**
	 * 設定地圖
	 */
	public void init(){
		
	}
	
	/**
	 * 將敵人加入地圖
	 * @param x 敵人的X位置
	 * @param y 敵人的Y位置
	 * @param iconNum 敵人的圖檔編號
	 */
	public void enemyAdd(int x, int y, int iconNum){
		enemy.add(new Enemy(this, x, y, iconNum));
	}
	
	/**
	 * 取得地圖的敵人ArrayList
	 * @return 敵人ArrayList
	 */
	public ArrayList getEnemyList(){
		return enemy;
	}
	/**
	 * 取得地圖的敵人人數
	 * @return 敵人人數
	 */
	public int getEnemyCount(){
		return enemy.size();
	}
	
}
