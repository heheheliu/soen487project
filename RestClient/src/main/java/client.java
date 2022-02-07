


import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.example.Album;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;


import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class client {

    public static void doGet(String url) throws Exception{

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {

            response = httpclient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {

                String content = EntityUtils.toString(response.getEntity(), "UTF-8");

                System.out.println(content);
            }
        } finally {
            if (response != null) {
                response.close();
            }

            httpclient.close();
        }

    }

    public static void doPost(String url, String data) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity("nickName=bbb&firstName=b&lastName=c"));
        CloseableHttpResponse response = null;
        try {

            response = httpclient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
            }
        } finally {
            if (response != null) {
                response.close();
            }

            httpclient.close();
        }
    }

    public static void doPut(String url, String data) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPut httpPut = new HttpPut(url + "/" + data);
        CloseableHttpResponse response = null;
        try {

            response = httpclient.execute(httpPut);

            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
            }
        } finally {
            if (response != null) {
                response.close();
            }

            httpclient.close();
        }
    }

    public static void doDelete(String url, String data) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpDelete httpDelete = new HttpDelete(url + "/" + data);
        CloseableHttpResponse response = null;
        try {

            response = httpclient.execute(httpDelete);

            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
            }
        } finally {
            if (response != null) {
                response.close();
            }

            httpclient.close();
        }
    }
//    public Album getJsonEmployee(int id) {
//        return client
//                .target(REST_URI)
//                .path(String.valueOf(id))
//                .request(MediaType.APPLICATION_JSON)
//                .get(Employee.class);
//    }

    public static void addAlbum() throws IOException {
        Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ));
        WebTarget webTarget = ((javax.ws.rs.client.Client) client).target("http://localhost:8080/myapp").path("albums/code123/title/description/1990/name");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.TEXT_PLAIN);
        Response response = invocationBuilder.post(Entity.entity("code123/title/description/1990/name",MediaType.TEXT_PLAIN));

//                                        Album albums = response.readEntity(Album.class);
//                                        List<Album> listOfEmployees = albums.getEmployeeList();
        String s = IOUtils.readStringFromStream((InputStream) response.getEntity());
        System.out.println(response.getStatus());
        System.out.println(response);
        System.out.println(response.toString());
        System.out.println(s);
    }

    public static void main(String[] args) throws Exception {
        Scanner keyIn = new Scanner(System.in);
        int menuOption = 0;
        int submenuOption = 0;
        final String artistUrl = "http://localhost:8181/ArtistWebServer_war/Artist";
        final String albumUrl = "http://localhost:8080/myapp/albums";
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
                                Scanner sc = new Scanner(System.in);
                                String info = "";
                                switch(submenuOption) {
                                    case 1:
                                        String data = "";
                                        doGet(artistUrl);
                                        break;
                                    case 2:
                                        System.out.println("enter artist nickname:");
                                        String nickName = sc.next();
                                        doGet(artistUrl + "/" + nickName);
                                        break;
                                    case 3:
                                        System.out.println("enter new artist info:");
                                        info = sc.next();
                                        doPost(artistUrl, info);
                                        break;
                                    case 4:
                                        System.out.println("enter artist new info:");
                                        info = sc.next();
                                        doPut(artistUrl, info);
                                        break;
                                    case 5:
                                        System.out.println("enter artist nickname:");
                                        nickName = sc.next();
                                        doDelete(artistUrl, nickName);
                                        break;
                                    case 6:
                                        exit = 1;
                                        sc.close();
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
//                                        Client client = ClientBuilder.newBuilder().newClient();
//                                        WebTarget target = client.target("http://localhost:8080/myapp/albums");
////                                        target = target.path("");
////                                        target = target.path("").queryParam("a", "avalue");
//                                        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON);
//                                        Response response = builder.get();
//                                        Album albums = client
//                                                .target(albumUrl)
//                                                .path("/isbn123")
//                                                .request(MediaType.APPLICATION_JSON)
//                                                .get(Album.class);
//                                        System.out.println(albums.toString());
                                        Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ));
                                        WebTarget webTarget = ((javax.ws.rs.client.Client) client).target("http://localhost:8080/myapp").path("albums");

                                                Invocation.Builder invocationBuilder =  webTarget.request(MediaType.TEXT_PLAIN);
                                        Response response = invocationBuilder.get();

//                                        Album albums = response.readEntity(Album.class);
//                                        List<Album> listOfEmployees = albums.getEmployeeList();
                                        String s = IOUtils.readStringFromStream((InputStream) response.getEntity());
                                        System.out.println(response.getStatus());
                                        System.out.println(response);
                                        System.out.println(response.toString());
                                        System.out.println(s);


                                        break;
                                    case 2:
                                        System.out.println("2");
                                        break;
                                    case 3:
                                        addAlbum();
                                        break;
                                    case 4:
                                        System.out.println("4");
                                        break;
                                    case 5:
                                        System.out.println("5");
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
                        System.exit(0);
                }
            }
        } while(true);
    }
}
