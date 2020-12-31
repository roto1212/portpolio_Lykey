package com.Luce.vo;

import java.util.ArrayList;

public class LyricsList {
	private ArrayList<LyricsVO> list = new ArrayList<LyricsVO>();

	public LyricsList() {	}
	
	
	public LyricsList(ArrayList<LyricsVO> list) {
		super();
		this.list = list;
	}


	public ArrayList<LyricsVO> getList() {
		return list;
	}


	public void setList(ArrayList<LyricsVO> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "LyricsList [list=" + list + "]";
	}
	
	
}
