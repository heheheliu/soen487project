import org.example.Artist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.io.*;
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
                out.println(a.getNickName()+". full name: "+a.getFirstName()+" "+a.getLastName());
            }
        }
        else {
            if(this.artistsCrud.existArtist(nickName)) {
                Artist a1 = this.artistsCrud.readArtist(nickName);
                out.println(a1);
            } else {
                out.println("No Artist who has nick name: " + nickName);
            }
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req,resp);
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
        for(String arg : args) {
            String[] part = arg.split("=");
            if(part[0].equals("nickName")) nickName = part[1];
            if(part[0].equals("firstName")) firstName = part[1];
            if(part[0].equals("lastName")) lastName = part[1];
            if(part[0].equals("shortBio")) shortBio = part[1];
        }

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