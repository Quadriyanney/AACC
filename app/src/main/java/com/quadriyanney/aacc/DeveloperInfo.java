package com.quadriyanney.aacc;



public class DeveloperInfo {

    String username, user_photo, git_url;

    public DeveloperInfo(String username, String user_photo, String git_url){

        this.setUsername(username);
        this.setUser_photo(user_photo);
        this.setGit_url(git_url);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }
}
