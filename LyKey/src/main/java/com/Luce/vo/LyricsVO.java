package com.Luce.vo;

import java.util.Date;

public class LyricsVO {
	private int idx;
	private String email;
	private String songTitle;
	private String singer;
	private String lyric;
	private Date saved_date;
	@Override
	public String toString() {
		return "LyricsVO [idx=" + idx + ", email=" + email + ", songTitle=" + songTitle + ", singer=" + singer
				+ ", lyric=" + lyric + ", saved_date=" + saved_date + "]";
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSongTitle() {
		return songTitle;
	}
	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getLyric() {
		return lyric;
	}
	public void setLyric(String lyric) {
		this.lyric = lyric;
	}
	public Date getSaved_date() {
		return saved_date;
	}
	public void setSaved_date(Date saved_date) {
		this.saved_date = saved_date;
	}
	
	
	
	
}
