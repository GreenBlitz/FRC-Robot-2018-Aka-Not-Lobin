package org.usfirst.frc.team4590.utils;

public class GBGameData {
	
	private static GBGameData instance = null;
	
	private String m_data = "";	
	public static GBGameData getInstance(){
		return (instance == null ? newInstance() : instance);
	}
	
	private static GBGameData newInstance(){
		return instance = new GBGameData();
	}
	
	private GBGameData(){
		
	}

	public boolean hasData(int index){
		return m_data.length() > index;
	}
	
	public boolean hasData(GameEntity index){
		return hasData(index.ordinal());
	}
	
	public char charAt(int index){
		return m_data.charAt(index);
	}
	
	public char charAt(GameEntity index){
		return charAt(index.ordinal());
	}
	
	public GBGameData insertData(char c){
		m_data += c;
		return this;
	}
	
	public static enum GameEntity{
		SWITCH,
		SCALE,
		ENEMY_SWITCH
	}

	public GBGameData insertData(String plates) {
		m_data += plates;
		return this;
	}
}
