package org.example;

public class Artist {
    private String nickName;

    public Artist(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "nickName='" + nickName + '\'' +
                '}';
    }
}
