import org.example.Artist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.io.IOException;
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

                out.println("list");
                List<Artist> la = this.artistsCrud.getAllArtists();
                for(Artist a: la) {
                    out.println(a.toString());
                }
        }
        else {
            if(this.artistsCrud.existArtist(nickName)) {
                Artist a1 = this.artistsCrud.readArtist(nickName);
                out.println(this.artistsCrud.getAllArtists());
            }
            out.println("No Artist who has nick name: "+nickName);
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}