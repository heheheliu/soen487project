import org.example.Artist;

import java.util.*;
import java.util.stream.Collectors;

public class ArtistsCrud {
    private List<Artist> artistList;

    public ArtistsCrud() {
        this.artistList = Collections.synchronizedList(new ArrayList<>());
    }

    public boolean isArtistsListExist() {
        return this.artistList.isEmpty();
    }

    public void createArtist(Artist a) {
        this.artistList.add(a);
    }

    public Artist readArtist(String nickname) {
        for(Artist a: this.artistList) {
            if(a.getNickName().equals(nickname)) {
                return a;
            }
        }
        return null;
    }

    public List<Artist> getAllArtists() {
        return this.artistList;
    }

    public boolean updateArtist(Artist artist) {
        boolean hasArtist = false;
        for(Artist a : this.artistList) {
            if(a.getNickName().equals(artist.getNickName())) {
                hasArtist = true;
                a.setFirstName(artist.getFirstName());
                a.setLastName(artist.getLastName());
                a.setShortBio(artist.getShortBio());
                break;
            }
        }
        return hasArtist;
    }

    public void deleteArtist(String nickname) {
        this.artistList = this.artistList.stream().filter(a -> !a.getNickName().equals(nickname)).collect(Collectors.toList());
    }

    public boolean existArtist(String nickname) {
        for(Artist a: this.artistList) {
            if(a.getNickName().equals(nickname)) {
                return true;
            }
        }
        return false;
    }





}
