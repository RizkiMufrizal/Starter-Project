# Starter-Project

## Development Stack Back End :

* Hikaricp
* Spring Data JPA
* Spring Boot
* Spring HATEOAS
* Spring OAuth2
* Spring JWT (Asymmetric Key)
* H2 Database
* Kotlin
* Gradle
* Tomcat

## Cara Menjalankan Back End :

* Akses Folder Starter-BackEnd lalu jalankan perintah : `gradle clean bootRun`
* Untuk Mendapatkan Access Token, Silahkan jalankan perintah berikut :

```
curl -X POST -vu clientid:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "client_id=clientid&grant_type=client_credentials"
```

* Akses API dengan Access Token seperti berikut :

```
curl "http://127.0.0.1:8080/api/barangs" \
  -H "Authorization: Bearer <access_token>" \
  -H "Content-Type: application/json"
```
