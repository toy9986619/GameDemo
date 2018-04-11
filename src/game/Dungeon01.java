package game;
import java.util.ArrayList;

/**
 * Dungeon01的類別
 * @author Takus
 *
 */
public class Dungeon01 extends Map {
	
	/**
	 * 建構子
	 */
	public Dungeon01(){
		super();
	}
	
	/**
	 * 設定地圖
	 */
	public void init(){
		enemy = new ArrayList();
		map1=new int[40][300];
		background = new int[4];
		
		for(int a=0; a<4; a++){
			background[a]=a%4;
		}
		
		//前置作業
		for(int y=0; y<40; y++){
			for(int x=0; x<300; x++){
				map1[y][x]=0;
			}
		}
		
		//前面牆壁
		for(int y=0; y<40; y++){
			for(int x=0; x<53; x++){
				map1[y][x]=1;
			}
		}
		
		//開頭長條(53->163)
		for(int y=21; y<40; y++){
			for(int x=53; x<163; x++){
				map1[y][x]=1;
			}
		}
		
		//第一次上坡第一階(X93->143 Y20)
		for(int y=20; y<21; y++){
			for(int x=93; x<143; x++){
				map1[y][x]=1;
			}
		}
		
		//第一次上坡第二階(X95->141 Y19)
		for(int y=19; y<20; y++){
			for(int x=95; x<141; x++){
				map1[y][x]=1;
			}
		}
		
		//第一次下坡(X163->183 Y22)
		for(int y=22; y<23; y++){
			for(int x=163; x<183; x++){
				map1[y][x]=1;
			}
		}
		
		//第二次下坡(X183->X223 Y23)
		for(int y=23; y<24; y++){
			for(int x=183; x<223; x++){
				map1[y][x]=1;
			}
		}
		
		//第二次上坡(X223->X263 Y22)
		for(int y=22; y<23; y++){
			for(int x=223; x<263; x++){
				map1[y][x]=1;
			}
		}
		
		//最後牆壁
		for(int y=0; y<40;y++){
			for(int x=263; x<300; x++){
				map1[y][x]=1;
			}
		}
		
		//填充地板
		for(int y=23; y<40; y++){
			for(int x=0; x<300; x++){
				map1[y][x]=1;
			}
		}
		
		enemyAdd(3750, 1000, 0);
		enemyAdd(4000, 1000, 0);
		enemyAdd(5550, 900, 0);
		enemyAdd(6300, 900, 0);
		enemyAdd(6500, 900, 0);
		enemyAdd(7500, 1000, 0);
		enemyAdd(8350, 1050, 0);
		enemyAdd(9750, 1100, 0);
		enemyAdd(10250, 1100, 0);
		enemyAdd(10750, 1100, 0);
		enemyAdd(11150, 1050, 0);
		enemyAdd(11400, 1050, 0);
		enemyAdd(11650, 1050, 0);
		enemyAdd(11900, 1050, 0);
		enemyAdd(12150, 1050, 0);
	}
}
