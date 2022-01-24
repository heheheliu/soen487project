## soen487project
### Artist Servlet
---
+ **GET**  
    - **all** &nbsp;&nbsp;curl localhost:8181/ArtistWebServer_war/Artist
    - **one** &nbsp;&nbsp;curl localhost:8181/ArtistWebServer_war/Artist?nickName=x
+ **POST**  
    - **all** &nbsp;&nbsp;curl --request POST --data "" localhost:8181/ArtistWebServer_war/Artist
    - **one** &nbsp;&nbsp;curl --request POST --data "nickName=x" localhost:8181/ArtistWebServer_war/Artist
+ **PUT**  
    - **one** &nbsp;&nbsp;curl --request PUT --data "nickName=a&firstName=b&lastName=c&shortBio=d" localhost:8181/ArtistWebServer_war/Artist  
when Artist nickName is exist already, then update its data
+ **DELETE**  
    - **one** &nbsp;&nbsp;curl --request DELETE --data "nickName=x"
---

