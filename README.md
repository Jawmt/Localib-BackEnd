<br />
<div align="center">


<h3 align="center">Application Localib BackEnd API</h3>

  <p align="center">
    Application permettant la gestion de location de vehicules 
    <br />
    <br />
    <br />
  </p>
</div>

<!-- ABOUT THE PROJECT -->

# Getting Started

### Modifier le fichier env.properties au niveau de la racine pour indiquer les données de connexion Mysql.

#### Creer une base de donnes MySQl et indiquer le chemin au niveau de application.properties

``` 
'application.properties'
spring.datasource.url= l'adresse de la bdd
spring.datasource.username= votre username
spring.datasource.password= votre mot de passe
```


### Installation des dépendances & lancement du serveur

``` 
mvn clean install
mvn spring-boot:run
```