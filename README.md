Tondeuse à gazon automatique
=====

## Dépendences

### Construit avec Maven


Tout d’abord, assurez-vous que votre environnement de développement est configuré. Sinon téléchargez et installez le [Java Development Kit][jdk] et [Maven][maven].  
Notez que ce projet nécessite le JDK 8.

[jdk]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[maven]: http://maven.apache.org/

#### Pour compiler faire :

```bash
$ mvn clean package
```

#### Pour exécuter faire :

```bash
$ java -jar target/automatic-lawnmower-0.0.1-SNAPSHOT.jar --i input.example.txt
```
