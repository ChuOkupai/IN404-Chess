# Projet IN404 - Chess

## La Dev Team
- Chabé Paul Victor 
- Dankou Mathis
- Naze Franck
- Soursou Adrien
## Objectifs
- [x] Base du projet
	- jeu d'échec fonctionnelle avec une interface textuelle 
- [ ] Détection des situations d'échec et d'échec et mat
- [x] Promotion des pions
- [x] Commande Undo
- [x] (Optionnel) Affichage du plateau
- [x] (Optionnel) Ajout d'un timer
- [ ] (En plus) Option Dragon !
- [ ] (En plus) Option mode Blitz !

## Manuel Utilisateur
### Lancement :
```shell
./Chess -[option] -j1 [J1] -j2 [J2]
```
Pour lancer une simple partie contre l'AI :
```shell
./Chess -j1 player -j2 ia
ou
./Chess 
```
### Commandes :
- **undo** : reviens au coup précédent
- **exit** :  quitte la partie
- **effectuer un coup** : position de départ vers position d'arrivé
	Par exemple la commande "b5d7" déplace la pièce de b5 vers d7 si le déplacement est jugé valide 

## Manuel Technique
![diagramme de classe](Classes.png)
### Option :
TO BE STARTED ...
## Difficulté(?)
