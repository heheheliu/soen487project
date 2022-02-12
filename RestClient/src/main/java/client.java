
import org.apache.cxf.helpers.IOUtils;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class client {

    public static void printResponse(CloseableHttpClient httpClient, HttpUriRequest httpMethod) throws IOException {
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpMethod);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
    }
    public static void getArtist(String url) throws Exception{

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        printResponse(httpClient,httpGet);
    }

    public static void addArtist(String url, String data) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(data));
        printResponse(httpClient, httpPost);
    }

    public static void updateArtist(String url, String data) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(new StringEntity(data));

        printResponse(httpClient, httpPut);
    }

    public static void deleteArtist(String url, String data) throws IOException, URISyntaxException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        URIBuilder builder = new URIBuilder(url);
        builder.setParameter("nickName", data);
        HttpDelete httpDelete = new HttpDelete(builder.build());

        printResponse(httpClient, httpDelete);
    }


    public static void getAlbum(String url, String path) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget =  client.target(url).path(path);

        Invocation.Builder invocationBuilder =  webTarget.request();
        Response response = invocationBuilder.get();
        String content = IOUtils.readStringFromStream((InputStream) response.getEntity());

        System.out.println(content);
    }

    public static void addAlbum(String url, String data) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = ((javax.ws.rs.client.Client) client).target(url).path("albums/" + data);

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.TEXT_PLAIN);
        Response response = invocationBuilder.post(Entity.entity(data,MediaType.TEXT_PLAIN));

        String content = IOUtils.readStringFromStream((InputStream) response.getEntity());
        System.out.println(content);
    }

    public static void updateAlbum(String url, String data) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = ((javax.ws.rs.client.Client) client).target(url).path("albums/" + data);

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.TEXT_PLAIN);
        Response response = invocationBuilder.put(Entity.entity(data,MediaType.TEXT_PLAIN));

        String content = IOUtils.readStringFromStream((InputStream) response.getEntity());
        System.out.println(content);
    }

    public static void deleteAlbum(String url, String code) throws IOException, URISyntaxException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = ((javax.ws.rs.client.Client) client).target(url).path("albums/" + code);

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.TEXT_PLAIN);
        Response response = invocationBuilder.delete();

        String content = IOUtils.readStringFromStream((InputStream) response.getEntity());
        System.out.println(content);
    }

    public static void main(String[] args) throws Exception {
        Scanner keyIn = new Scanner(System.in);
        int menuOption = 0;
        int submenuOption = 0;
        final String artistUrl = "http://localhost:8181/ArtistWebServer_war/Artist";
        final String albumUrl = "http://localhost:8080/myapp";
        do {
            /**
             * display menu options
             */
            System.out.println("\nWhat do you want to do? (choose the menu number)" +
                    "");
            System.out.println("\t1. Artists");
            System.out.println("\t2. Albums");
            System.out.println("\t3. Quit");

            /**
             * accept user choice and make sure it's valid
             */
            boolean isInteger = false;
            try {
                menuOption = keyIn.nextInt();
                isInteger = true;
            } catch (InputMismatchException e) {
                System.out.println("You should enter an integer.");
                keyIn.nextLine();
            }
            if (isInteger == false) {
                continue;
            } else if (menuOption > 3 || menuOption < 1) {
                System.out.println("Invalid option! Please choose again.");
            } else {
                switch(menuOption) {
                    case 1:
                        do {
                            /**
                             * display Artist submenu
                             */
                            System.out.println("\nArtists menu:");
                            System.out.println("\t1. list artists");
                            System.out.println("\t2. get artist details");
                            System.out.println("\t3. add artist");
                            System.out.println("\t4. update artist");
                            System.out.println("\t5. delete artist");
                            System.out.println("\t6. go back");
                            /**
                             * accept user choice and make sure it's valid
                             */
                            boolean isIntSub1 = false;
                            try {
                                submenuOption = keyIn.nextInt();
                                isIntSub1 = true;
                            } catch (InputMismatchException e) {
                                System.out.println("You should enter an integer.");
                                keyIn.nextLine();
                            }
                            if (isIntSub1 == false) {
                                continue;
                            } else if (submenuOption > 6 || submenuOption < 1) {
                                System.out.println("Invalid option! Please choose again.");
                            } else {
                                int exit = 0;
                                String info = "";
                                switch(submenuOption) {
                                    case 1:
                                        String data = "";
                                        getArtist(artistUrl);
                                        break;
                                    case 2:
                                        System.out.println("enter artist nickname:");
                                        String nickName = keyIn.next();
                                        getArtist(artistUrl + "?nickName=" + nickName);
                                        break;
                                    case 3:
                                        System.out.println("enter new artist info:");
                                        info = keyIn.next();
                                        addArtist(artistUrl, info);
                                        break;
                                    case 4:
                                        System.out.println("enter artist new info:");
                                        info = keyIn.next();
                                        updateArtist(artistUrl, info);
                                        break;
                                    case 5:
                                        System.out.println("enter artist nickname:");
                                        nickName = keyIn.next();
                                        deleteArtist(artistUrl, nickName);
                                        break;
                                    case 6:
                                        exit = 1;
                                        break;
                                }
                                if (exit == 1) {
                                    break;
                                }
                            }
                        } while (true);
                        break;
                    case 2:
                        do {
                            /**
                             * display Album submenu
                             */
                            System.out.println("\nAlbum menu");
                            System.out.println("\t1. list albums");
                            System.out.println("\t2. get album details");
                            System.out.println("\t3. add album");
                            System.out.println("\t4. update album info");
                            System.out.println("\t5. delete album");
                            System.out.println("\t6. go back");

                            boolean isIntSub2 = false;
                            try {
                                submenuOption = keyIn.nextInt();
                                isIntSub2 = true;
                            } catch (InputMismatchException e) {
                                System.out.println("You should enter an integer.");
                                keyIn.nextLine();
                            }
                            if (isIntSub2 == false) {
                                continue;
                            } else if (submenuOption > 6 || submenuOption < 1) {
                                System.out.println("Invalid option! Please choose again.");
                            } else {
                                int exit = 0;
                                switch(submenuOption) {
                                    case 1:
                                        getAlbum(albumUrl, "albums");
                                        break;
                                    case 2:
                                        System.out.println("enter album code:");
                                        String code = keyIn.next();
                                        getAlbum(albumUrl, "albums/" + code);
                                        break;
                                    case 3:
                                        System.out.println("enter album info:");
                                        String newAlbum = keyIn.next();
                                        addAlbum(albumUrl, newAlbum);
                                        break;
                                    case 4:
                                        System.out.println("enter album new info:");
                                        String updateAlbum = keyIn.next();
                                        updateAlbum(albumUrl, updateAlbum);
                                        break;
                                    case 5:
                                        System.out.println("enter album code:");
                                        String codeDelete = keyIn.next();
                                        deleteAlbum(albumUrl, codeDelete);
                                        break;
                                    case 6:
                                        exit = 1;
                                        break;
                                }
                                if (exit == 1) {
                                    break;
                                }
                            }
                        } while (true);
                        break;
                    case 3:
                        System.out.println("Program terminated!");
                        keyIn.close();
                        System.exit(0);
                }
            }
        } while(true);
    }
}
