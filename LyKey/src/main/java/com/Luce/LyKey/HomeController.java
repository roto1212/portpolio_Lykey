package com.Luce.LyKey;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Luce.dao.LyKeyDAO;
import com.Luce.vo.LyKeyVO;
import com.Luce.vo.LyricsList;
import com.Luce.vo.LyricsVO;

@Controller
public class HomeController {
	@Autowired
	public SqlSession sqlSession;
	@RequestMapping("/")
	public String home(Locale locale, Model model) {
		System.out.println("컨트롤러 home()");
		return "main";
	}
	@RequestMapping("/main")
	public String main(Locale locale, HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("컨트롤러 main()");
		
//		session.setAttribute("email", session.getAttribute("email"));
		model.addAttribute("msg_lyrics", request.getParameter("msg_lyrics"));
		model.addAttribute("msg_lylics", request.getParameter("msg_lyrics"));
		model.addAttribute("lyric", request.getParameter("lyric"));
		model.addAttribute("searchWord", request.getParameter("searchWord"));
		model.addAttribute("lyricsVO", request.getParameter("lyricsVO"));
		model.addAttribute("msg_session", request.getParameter("msg_session"));
		model.addAttribute("lyricsList", request.getParameter("lyricsList"));
		model.addAttribute("msg_why", request.getParameter("msg_why"));
		
		
		return "main";
	}
	@RequestMapping("/signUp")
	public String signUp(HttpServletRequest request, Model model, LyKeyVO lyKeyVO) {
		System.out.println("컨트롤러 signUp()");
		return "signUp";
	}
	@RequestMapping(value = "id_check", produces = "application/text; charset=utf8")
	public @ResponseBody String id_check(String email, Model model) {
		System.out.println("컨트롤러 insertMember()");
		LyKeyDAO mapper = sqlSession.getMapper(LyKeyDAO.class);
		String result = "";
		String confirm = mapper.emailChk(email);
		if (confirm != null && confirm.length() != 0) {
			result = confirm + "은(는) 이미 등록된 이메일입니다.";
		} else {
			result = email + "은(는) 등록 가능한 이메일입니다.";
		}
		
		model.addAttribute("result", result);
		return result;
		
	} 
	@RequestMapping("/insertMember")
	public String insertMember(HttpServletRequest request, Model model, LyKeyVO lyKeyVO) {
		System.out.println("컨트롤러 insertMember()");
		LyKeyDAO mapper = sqlSession.getMapper(LyKeyDAO.class);
		lyKeyVO.setEmail(request.getParameter("email"));
		lyKeyVO.setPw(request.getParameter("pw"));
		String email = lyKeyVO.getEmail();
		String pw = lyKeyVO.getPw();
//		이메일이 널이거나 공백이면  
		if (email == null || email.equals("")) {
			
			return "fail";
		} else {
//		입력받은 이메일과 테이블에 저장된 이메일을 비교해 이메일이 중복됬는지 검사한다.
			String result = (String) mapper.emailChk(email);
//			중복되지 않았다면
			if (result == null) {
//				이메일을 저장한다.
				mapper.insertMember(lyKeyVO);
				model.addAttribute("email", email);
				return "cong";
			} else { // 중복되었다면
				model.addAttribute("email", email);
//				실패창으로 간다.
				return "fail_signUp";
			}
		}
//		System.out.println(email + pw + pwChk);
		
//		System.out.println(result);
		
	}
	@RequestMapping("/signIn.do")
	public String signIn(HttpServletRequest request, Model model, LyKeyVO lyKeyVO, HttpSession session) {
		System.out.println("컨트롤러 signIn()");
		LyKeyDAO mapper = sqlSession.getMapper(LyKeyDAO.class);
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
//		System.out.println(id + pw);
//		테이블에 입력된 이메일이 있고, 패스워드가 같으면 로그인 한다.
//		String selcEmail = (String) mapper.emailChk(email);
		lyKeyVO.setEmail(email);
		lyKeyVO.setPw(pw);
		
		String signChk = mapper.signChk(lyKeyVO);
		if (signChk != null) {
			session.setAttribute("email", email);
//			session.setAttribute("signIn", "yes");
//			model.addAttribute("email", email);
			
			return "redirect:main";
		} else {
			model.addAttribute("email", email);
			return "fail_signIn";
		}
	}
	@RequestMapping("/signOut")
	public String signOut(HttpSession session) {
		session.invalidate();
		return "redirect:main";
	}
	
	@RequestMapping("/searching")
	public String searching(HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("컨트롤러 searching()");
		String searchWord = request.getParameter("searchWord"); 
		String msg_lyrics = "";
//		String msg_youtube = "";
		String url_lyrics = "https://search.daum.net/search?w=tot&DA=YZR&t__nil_searchbox=btn&sug=&sugo=&sq=&o=&q=" + searchWord + "가사";
//		String url_youtube = "https://www.youtube.com/results?search_query=" + searchWord;
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url_lyrics).get();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(doc);
//		Elements lylics = doc.select("div.PZPZlf span>div.ujudUb>span");
//		doc.attr("aria-expanded", "true");
//		Elements lyrics = doc.select("div.PZPZlf g-expandable-content[jsname='WbKHeb'] div.ujudUb>span");
//	가사 크롤링
		Elements lyrics = doc.select("li.fst p.txt_lyrics");
//		System.out.println(lyrics);
		String lyric = "";
		for (Element e:lyrics) {
			lyric += e.html() + "\n" ;
		}
//		System.out.println(lyric);
		if (lyric == null || lyric.trim().equals("")) {
			msg_lyrics = "해당 곡을 찾을 수 없습니다.";
		} else {
			msg_lyrics = "해당 곡을 찾았습니다.";
		}
//		System.out.println(msg_lyrics);
		lyric = lyric.replaceAll("<br>", "\n");
		model.addAttribute("lyric", lyric);

//		try {
//			doc = Jsoup.connect(url_youtube).get();
//			msg_youtube = "해당 영상을 찾았습니다.";
//		} catch (IOException e) {
//			msg_youtube = "해당 영상을 찾을 수 없습니다.";
//			e.printStackTrace();
//		}
//		System.out.println(doc);
		
		
//		Elements youtube_id = doc.select("div#contents a#video-title[title]");
//		System.out.println("youtube_id" + youtube_id);
//		String youtube_embed = "https://www.youtube.com/embed/" + youtube_id ;
		
//		System.out.println(youtube_embed);
//		model.addAttribute("doc", doc);
//		model.addAttribute("enter", "\r\n");
		
		model.addAttribute("msg_lyrics", msg_lyrics);
		model.addAttribute("searchWord", searchWord);
//		return "searching";
	
	return "redirect:main";
	}
	@RequestMapping("/save")
	public String save(HttpServletRequest request, Model model, HttpSession session, @ModelAttribute LyricsVO lyricsVO) {
		System.out.println("컨트롤러 save()");
		LyKeyDAO mapper = sqlSession.getMapper(LyKeyDAO.class);
//		String searchWord = request.getParameter("searchWord"); 
	
//		System.out.println("노래제목: " + lyricsVO.getSongTitle() );
//		System.out.println("가수: " + lyricsVO.getSinger());
//		System.out.println("가사: " + lyricsVO.getLyric());
//		System.out.println(searchWord);
		
//		----------------------------------------
		String msg_session = "";
		String email = "";
		if (session.getAttribute("email") == null) {
			//세센에 로그인 정보가 없을 경우 
			msg_session += "로그인을 해주세요~";
			model.addAttribute("msg_session", msg_session);
			return "redirect:main";
//			return "searching";
		} else {
			//세센에 로그인 정보가 있을 경우 
			email = (String) session.getAttribute("email");
			lyricsVO.setEmail(email);
			mapper.insertLyric(lyricsVO);					// 가사를 이메일을 기준으로 테이블에 저장한다.
			model.addAttribute("lyricsVO", lyricsVO);
			return "redirect:lyric_list";
//			return "searching";
		}
//		----------------------------------------
		
//		model.addAttribute("searchWord", searchWord);
	}
//	저장된 가사 지우기
	@RequestMapping("del_ly")
	public String del_ly(int idx, Model model ) {
		System.out.println("컨트롤러 lyric_list()");
		LyKeyDAO mapper = sqlSession.getMapper(LyKeyDAO.class);
//		System.out.println(idx);
		mapper.delete(idx);
		
		return "redirect:lyric_list";
	}
//	저장된 가사 리스트 보여주기, 로그인한 이메일로 저장된 리스트만 보여줌
	@RequestMapping("lyric_list")
	public String lyric_list(HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("컨트롤러 lyric_list()");
		LyKeyDAO mapper = sqlSession.getMapper(LyKeyDAO.class);
		
		String email = (String) session.getAttribute("email");
		LyricsList lyricsList = new LyricsList();
		lyricsList.setList(mapper.selectList(email));
//		for (LyricsVO vo : lyricsList.getList()) {
//			System.out.println(vo);
//		}
		model.addAttribute("lyricsList", lyricsList);
		model.addAttribute("lyric", request.getParameter("lyric"));
	
//		return "redirect:main";
		return "lyric_list";
	}
	@RequestMapping("search_saved")
	public String search_saved(HttpServletRequest req, int idx, String songTitle, String singer, Model model, HttpSession session) {
		System.out.println("컨트롤러 search_saved()");
		LyKeyDAO mapper = sqlSession.getMapper(LyKeyDAO.class);
//		System.out.println("index: " + idx);
		String lyric = mapper.selectLyrics(idx);
		model.addAttribute("lyric", lyric);
		
//		System.out.println(req.getParameter("songTitle"));
//		System.out.println(singer);
		model.addAttribute("songTitle", req.getParameter("songTitle"));
		model.addAttribute("singer", singer);
		
		return "redirect:lyric_list";
		
	}
	
	@RequestMapping("toLatin")
	public String toLatin(Model model, String lyric) {
		System.out.println("컨트롤러 toLatin()");
////		String toLatin_url = "https://www.lexilogos.com/keyboard/korean_conversion.htm";
////		String toLatin_url = "https://www.google.com/search?hl=ko&sxsrf=ALeKk00gOZk55edal24Tr51WmGKJSrFB0g%3A1608608520244&source=hp&ei=CGvhX42sDOLt-QaZ8LiQAg&q=convert+korean+to+latin+alphabet&oq=con&gs_lcp=CgZwc3ktYWIQAxgAMgQIIxAnMgQIIxAnMgQIIxAnMgIIADIFCAAQsQMyBQgAELEDMggIABCxAxCDATIICAAQsQMQgwEyAggAMgUIABCxAzoHCCMQ6gIQJzoECAAQQ1CBHljjIWCYLWgBcAB4AIABmwGIAcwDkgEDMC4zmAEAoAEBqgEHZ3dzLXdperABCg&sclient=psy-ab";
//		String toLatin_url = "https://sori.org/hangul/conv2kr.cgi";
//		Document doc = null;
//		
//		
//		try {
//			doc = Jsoup.connect(toLatin_url).get();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Elements input = doc.select("td.menu+td center tr:first-child input:first-child");
//		System.out.println("#bar1: " + input);
//		String[] ly = lyric.trim().split("\n");
//		String lyrics = "";
//		for (String str : ly) {
////			System.out.println("개행: " + str);
//			lyrics += input.attr("value", str);
//		}
////		System.out.println("변환할 가사: " + lyric);
////		lyrics.attr("value", lyric);
//		toLatin_url = "https://sori.org/hangul/conv2kr.cgi?q=%BE%EE%B5%D2+%BC%D3%BF%A1+%C0%CC+%BE%F3%B1%BC+%BA%B8%B4%D9%B0%A1&m=1";
//		doc = null;
//		try {
//			doc = Jsoup.connect(toLatin_url).get();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Elements output = doc.select("td.menu+td tbody tr:first-child font.result2");
//		System.out.println(output);
//		System.out.println("변환된 가사: " + output.text());
		
		
		return "redirect:lyric_list";
		
	}
	
	
}
