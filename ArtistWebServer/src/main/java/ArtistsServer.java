import org.example.Artist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/Artist")
public class ArtistsServer extends HttpServlet {
    private ArtistsCrud artistsCrud;

    @Override
    public void init() throws ServletException {
        this.artistsCrud = new ArtistsCrud();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickName = req.getParameter("nickName");
        PrintWriter out = resp.getWriter();
        if(nickName == null) {
            List<Artist> la = this.artistsCrud.getAllArtists();
            for(Artist a: la) {
                out.println(a.toString());
            }
        }
        else {
            if(this.artistsCrud.existArtist(nickName)) {
                Artist a1 = this.artistsCrud.readArtist(nickName);
                out.println(this.artistsCrud.getAllArtists());
            } else {
                out.println("No Artist who has nick name: " + nickName);
            }
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickName = null;
        String firstName = null;
        String lastName = null;
        String shortBio = null;
        PrintWriter out = resp.getWriter();

        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String data = br.readLine();
        String[] args = data.split("&"); // id in args[0], rest in args[1]
        String[] parts1 = args[0].split("="); // id = parts1[1]
        nickName = parts1[1];
        String[] parts2 = args[1].split("="); // parts2[0] is key
        firstName = parts2[1];
        String[] parts3 = args[2].split("="); // parts2[0] is key
        lastName = parts3[1];
        String[] parts4 = args[3].split("="); // parts2[0] is key
        shortBio = parts4[1];

        if(nickName!=null&&firstName!=null&&lastName!=null) {
            if (shortBio == null) {
                shortBio = "No bio";
            }
            Artist newArtist = new Artist(nickName, firstName, lastName, shortBio);
            if(!this.artistsCrud.existArtist(nickName)) {
                this.artistsCrud.createArtist(newArtist);
                out.println("New Artist is added into the list");
            } else {
                this.artistsCrud.updateArtist(newArtist);
                out.println("Artist exist and new info get updated");
            }
        } else {
            out.println("Artist is invalid");
        }
        out.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickName = req.getParameter("nickName");
        PrintWriter out = resp.getWriter();
        if(nickName == null) {
            out.println("invalid nickname");
        } else {
            if(this.artistsCrud.existArtist(nickName)) {
                this.artistsCrud.deleteArtist(nickName);
                out.println("Artist is deleted successfully");
            } else {
                out.println("Artist does not exist in the list, delete failed");
            }
        }
        out.close();
    }

}