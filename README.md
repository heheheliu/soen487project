## soen487project
### Artist Servlet
---
+ **GET**  
    - **all** &&curl localhost:8181/ArtistWebServer_war/Artist
    - **one** &&curl localhost:8181/ArtistWebServer_war/Artist?nickName=x
+ **POST**  
    - **all** &&curl --request POST --data "" localhost:8181/ArtistWebServer_war/Artist
    - **one** &&curl --request POST --data "nickName=x" localhost:8181/ArtistWebServer_war/Artist
+ **PUT**  
    - **one** &&curl --request PUT --data "nickName=a&firstName=b&lastName=c&shortBio=d" localhost:8181/ArtistWebServer_war/Artist  
when Artist nickName is exist already, then update its data
+ **DELETE**  
    - **one** &&curl --request DELETE --data "nickName=x"
---

