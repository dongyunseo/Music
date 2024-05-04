package com.Music.dto;

import java.sql.Date;

public class MusicVo {
    private String day; // 일시
    private int ranking;   // 랭킹
    private String title;   // 제목
    private String singer;  // 가수
    private String albumImgUrls; // 앨범 URL
    private int albumCode;  // 앨범 코드
    private Date releaseDate;    // 발매일
    private String genreText;   // 장르
    private String platform; // 플랫폼
    private int view_Count;
    private String click_Url;
    
	public MusicVo() {}
	
	public MusicVo(String day, int ranking, String title, String singer, String albumImgUrls, int albumCode, Date releaseDate,
			String genreText, String platform, int view_Count, String click_Url) {
		super();
		this.day = day;
		this.ranking = ranking;
		this.title = title;
		this.singer = singer;
		this.albumImgUrls = albumImgUrls;
		this.albumCode = albumCode;
		this.releaseDate = releaseDate;
		this.genreText = genreText;
		this.platform = platform;
		this.view_Count = view_Count;
		this.click_Url = click_Url;
	}
	
	// Getter 메서드
    public String getDay() {
        return day;
    }

    public int getRanking() {
        return ranking;
    }

    public String getTitle() {
        return title;
    }

    public String getSinger() {
        return singer;
    }

    public String getAlbumImgUrls() {
        return albumImgUrls;
    }

    public int getAlbumCode() {
        return albumCode;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getGenreText() {
        return genreText;
    }
    
    public String getPlatform() {
    	return platform;
    }
    public int getView_Count() {
        return view_Count;
    }

    // Setter 메서드
    public void setDay(String day) {
        this.day = day;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setAlbumImgUrls(String albumImgUrls) {
        this.albumImgUrls = albumImgUrls;
    }

    public void setAlbumCode(int albumCode) {
        this.albumCode = albumCode;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenreText(String genreText) {
        this.genreText = genreText;
    }
    
    public void setPlatform(String platform) {
    	this.platform = platform;
    }
    
    public void setView_Count(int view_Count) {
        this.view_Count = view_Count;
    }

	public String getCilck_URL() {
		return click_Url;
	}

	public void setCilck_URL(String cilck_URL) {
		this.click_Url = cilck_URL;
	}

}
