package com.Luce.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.Luce.vo.LyKeyVO;
import com.Luce.vo.LyricsVO;

public interface LyKeyDAO {

	void insertMember(LyKeyVO lyKeyVO);

	String emailChk(String email);

	void insertLyric(LyricsVO lyricsVO);

	String signChk(LyKeyVO lyKeyVO);

	ArrayList<LyricsVO> selectList(String email);

	String selectLyrics(int idx);

	void delete(int idx);

	

	

	

}
