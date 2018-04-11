package game;
import javax.swing.*;

/**
 * 取得Icon的Class
 * @author Takus
 *
 */
public class GetIcon implements GameConfig {
	
	/**
	 * 取得地圖圖檔
	 * @param n 編號
	 * @return 圖片Icon
	 */
	static ImageIcon int2icon(int n){
		if(n==0)	return none;
		else if(n==1)	return blocks;
		
		return null;
	}
	
	/**
	 * 取得背景圖檔
	 * @param n 編號
	 * @return 圖片Icon
	 */
	static ImageIcon background2icon(int n){
		if(n==0)	return BGicon01;
		else if(n==1)	return BGicon02;
		else if(n==2)	return BGicon03;
		else if(n==3)	return BGicon04;
		
		return null;
	}
	
	/**
	 * 取得武器圖檔
	 * @param n 編號
	 * @return 圖片Icon
	 */
	static ImageIcon weapon2Icon(int n){
		if(n==0)	return DGicon1;
		else if(n==1)	return DGicon2;
		else if(n==2)	return DGicon3;
		else if(n==3)	return DGicon4;
		else if(n==4)	return DGicon5;
		else if(n==5)	return DGicon6;
		else if(n==6)	return DGicon7;
		else if(n==7)	return DGicon8;
		
		else if(n==100)	return GNicon1;
		else if(n==101)	return GNicon2;
		
		return null;
	}
}
