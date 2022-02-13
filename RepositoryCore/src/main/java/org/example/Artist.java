package org.example;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "artist")
@XmlAccessorType(XmlAccessType.FIELD)
public class Artist {
    @XmlElement
    private String nickName; // uniquely string, main key
    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
    private String shortBio;

    public Artist(String nickName, String firstName, String lastName, String shortBio) {
        this.nickName = nickName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shortBio = shortBio;
    }
    public Artist(){

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


    public int compareTo(Artist o) {
        return this.nickName.compareTo(o.nickName);
    }
}
