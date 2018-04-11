package game;

/**
 * 結束例外方法的class
 * @author Takus
 *
 */
public class EndException extends Exception {
	
	/**
	 * GameOver的例外方法處理
	 */
	public static void GameOver(){
		WindowsUI.state=WindowsUI.STATE.GAMEOVER;
	}
	
	/**
	 * 勝利的例外方法處理
	 */
	public static void Win(){
		WindowsUI.state=WindowsUI.STATE.WIN;
	}
}
