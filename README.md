# Projet IN404 - Chess

## La Dev Team
- Chabé Paul Victor 
- Dankou Mathis
- Naze Franck
- Soursou Adrien
## Objectifs
- [x] Base du projet
	- jeu d'échec fonctionnel avec une interface textuelle
	- ia (coups aléatoires)
- [x] Détection des situations d'échecs et d'échecs et mat
- [x] Promotion des pions
- [x] Commande Undo
- [x] (Optionnel) Affichage du plateau
- [x] (Optionnel) Ajout d'un timer
- [ ] (En plus) Option Dragon ! (RIP)
- [x] (En plus) Option mode Blitz !

## Manuel Utilisateur
### Lancement :
```shell
./chess [OPTION]...
```
Pour lancer une simple partie contre l'AI :
```shell
./chess
```
ou
```shell
./chess -p1 human -p2 ia

```

### Liste des options :
- **-d, --delay [instant|slow|normal|fast]** définit un délai d'attente après le tour de l'ia
- **-b, --bank [entier]** définit une banque de temps pour chaque joueur (0 pour désactiver)
- **-s, --seconds [entier]** définit le nombre maximum de secondes par tour (0 pour désactiver)
- **-t, --seconds [entier]** définit le nombre maximum de tours (0 pour désactiver)
- **-p1, --player1 [ai|human]** permet de sélectionner si le joueur est humain
- **-p2, --player2 [ai|human]** même fonction que pour le joueur 1
- **--help** affiche la liste des options
- **--version** affiche les informations de version

La commande exécutée par défaut est:
```shell
./chess -d normal -b 0 -s 0 -t 0 -p1 human -p2 ia
```
*Remarque: Eviter de lancer des batailles d'IAs sans limiter le nombre de tours.*

### Commandes :
- **undo** : reviens au coup précédent
- **exit** : quitte la partie
- **[a-h][1-8][a-h][1-8]** : position de départ vers position d'arrivée. Ex: b5d7 déplace la pièce de b5 vers d7 si le déplacement est valide

## Manuel Technique
![diagramme de classe](Classes.png)
### Option :
TO BE STARTED ...
## Difficulté(?)
