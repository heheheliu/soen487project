package org.example;

public class Artist {
    private String nickName; // uniquely string, main key
    private String firstName;
    private String lastName;
    private String shortBio;

    public Artist(String nickName, String firstName, String lastName, String shortBio) {
        this.nickName = nickName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shortBio = shortBio;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "nickName='" + nickName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", shortBio='" + shortBio + '\'' +
                '}';
    }
}
