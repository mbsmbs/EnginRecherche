Mode d'utilisation :
    1. Il faut d'abord cliquer sur le menu « Indexer - Fichiers »
    2. Vous pouvez vérifier toutes les informations de ces fichiers en cliquant « Afficher - Liste des documents »
    3. Puis cliquer sur le menu « Indexer - Inverser »
    4. Vous pouvez vérifier toutes les informations de ces fichiers en cliquant « Afficher - Liste des mots »
    5. Vous pouvez enfin entrer les mots à rechercher en cliquant sur le menu « Recherche - Mots »


Description de notre programme :

Ce programme réalise un engin de recherche des mots saisis par l'utilisateur.

Il permet de construire les structures d'index où on peut enregistrer les informations des mots qui existent dans chaque document. 
Il permet aussi à l'utilisateur de rechercher un ou plusieurs mots dans tous les documents choisis et 
d'afficher les résultats de recherche en montrant les fréquences d'occurrence des mots dans chaque document sur l’interface graphique.

Sur le menu en haut de la fenêtre, il y a « Indexer» « Afficher » et « Rechercher » :

- Menu « Indexer » :
	- « Fichier » qui permet de choisir un ou plusieurs fichiers à indexer
	- « Inverser » qui crée une structure d'index de tous les mots des documents choisis.

- Menu « Afficher » :
	- « Liste de document » qui affiche la liste des documents choisis avec les informations de ses mots.
	- « Liste de mots » qui affiche la liste de tous les mots qui existent dans les documents choisis.

- Menu « Rechercher » :
	- « Mot » qui permet à l'utilisateur d'entrer les mots à rechercher et d'afficher les résultats trouvés.

Voici les détails pour la structure de notre programme :

Notre programme est composé par « Modèle » « Contrôle » et « Vue ».

- « Vue »      : Gestion de toutes les affichages d'interface graphique.

- « Modele »   : Gestion de toutes les données du programme.
	- « EnginModele »    : Classe pour contrôler les données et communiquer avec Vue et Controle
	- « DocIndex »       : Classe pour enregistrer les informations d'un document
	- « DocIndexListe »  : Classe pour enregistrer une liste des informations des documents choisis
	- « MotIndex »       : Classe pour enregistrer les informations d'un mot
	- « MotIndexListe »  : Classe pour enregistrer une liste des mots dans les documents choisis
    - « ReponseListe »   : Classe pour enregistrer tous les résultats des mots de recherche

- « Contrôle » : Gestion de tous les événements crées sur les menus.

- « EngieMain» : Exécution de notre programme.