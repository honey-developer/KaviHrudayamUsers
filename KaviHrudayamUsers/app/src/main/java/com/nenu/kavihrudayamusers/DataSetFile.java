package com.nenu.kavihrudayamusers;

public class DataSetFile {
    private String movie;
    private String song;
    private String lyrics;
    private String writer;


    public DataSetFile() {
    }

    public DataSetFile(String movie, String song, String lyrics, String writer) {
        this.movie = movie;
        this.song = song;
        this.lyrics = lyrics;
        this.writer = writer;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
