# IKUBProject
Rest API per krijimin/modifikimin/fshirjen e lementeve ne dy entite (Employee, Department)


### Dependencies

Duhet te keni te instaluar nje version te JAVA (8 ose me te larte)

### Instalimi

Shkarkoni folderin e projektit si ZIP ose Klonojeni ate dhe hapeni projektin ne ide tuaj (Eclipse/IntelliJ).
Krijoni ne databasen MySQL nje schema me emrin 'ikub_project' dhe ndryshoni ne file-in /resources/application.properties te dhenat 

```
spring.datasource.username=root
spring.datasource.password=root
```

### Ekzekutimi i programit

Per te ekzekutuar projektin klikoni run ne IDE ose ne terminal me komanden 

```
mvn spring-boot:run
```

Navigoni ne http://localhost:8080/login .Kredencialet e perdoruesit: 
  * username - user
  * password - user
  
