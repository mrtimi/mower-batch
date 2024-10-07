# mowItNow batch
## 1 - Spécification fonctionnelle:
C'est un batch qui prend en paramètre un fichier texte qui contient:

- la taille de pelouse
- la définition des plusieurs tondeuses. chaque tondeuse est définie par sa position, orientation ainsi qu'une liste des instruction

qui donne en output: un fichier texte qui contient la position finale de chaque tendeuse

## 2 - Prérequis

Ce projet utilise java 17 et spring boot 3.3.4


## 3 – RUN

pour exécuter les tests unitaires:

``mvn test``

pour runner ce projet il faut tout d’abord exécuter la commande ``mvn clean install``

ensuite on peut lancer le batch soit :

- en exécutant le main principale du projet à partir de l'IDE

ou à en exécutant le script mower-batch-launcher.sh (généré dans le dossier target/Deploy) qui prend en paramètre un fichier configuration qui contient:
- path vers le jar file
- path vers le fichier de logs
- le path vers le fichier properties de l’application

voir un exemple de fichier config.properties dans le dossier ressources
``./mower-batch-launcher.sh config.properties``

## 4 – Proposition de  packaging

Pour ma proposition de packaging:

J’ai ajouté des configuration dans le pom.xml pour que  lors de l'exécution de goal install: il y aura un nouveau dossier deploy qui contient:

- le script mower-batch-launcher.sh.sh
- le application.properties (qui contient le path vers les fichiers input et output) du batch
- le ficheir config.properties qui contient des paramètres pour lancer le batch (jar file, log file, fichier properties applicatif)
- les fichiers input.txt et output.txt
- le fichier application.log dans laquelle nous allons écrire les logs application

pour lancer à partir de ce dossier deploy il suffit de lancer le script.sh avenc le fichier deafult.cong en parametre

Pour ce kata, je me suis arrêté à ce point mais nous pouvons :
ajouter une plugin pour créer un deploy.zip à partir  de dossier deploy dans la target afin de faciliter le transfert de fichiers