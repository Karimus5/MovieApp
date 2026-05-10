package com.example.movieapp;

public class MyMovieData {
    private int movieid;
    private String moviename;
    private String moviedate;
    private String movieimage; // Integer → String
    private String moviedescription;

    public MyMovieData(int movieid, String moviename, String moviedate, String movieimage) {
        this.movieid = movieid;
        this.moviename = moviename;
        this.moviedate = moviedate;
        this.movieimage = movieimage;
    }

    public String getMoviename() {
        return moviename;
    }

    public String getMovieimage() { // Integer → String
        return movieimage;
    }

    public String getMoviedate() {
        return moviedate;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public void setMoviedate(String moviedate) {
        this.moviedate = moviedate;
    }

    public void setMovieimage(String movieimage) { // Integer → String
        this.movieimage = movieimage;
    }

    public int getMovieid() {
        return movieid;
    }

    public String getMoviedescription() {
        return moviedescription;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public void setMoviedescription(String moviedescription) {
        this.moviedescription = moviedescription;
    }
}