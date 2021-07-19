WYSIWYM_dev
Ce service est le point d'entrée de l'application WYSIWYM (What You See Is What You Measure) déployée sur le site Heroku. 
Celui utilise les service lds et mesure disponible sur mon profil afin d'accéder au différentes fonctionnalités les commandes sont les suivantes:

1-Pour effectuer une mesures de similarité entre deux concept ou d'utiliser des fichier de benchmark il faut utiliser l'adresse suivante https://https://wysiwym-api.herokuapp.com/similarity en envoyant une requêtes de type POST en fournissant les éléments suivant:
- Le Dataset à utiliser contenant le nom, les préfixes, le lien, le graph par défaut et l'url de base pour chaque ressource.
- Les ressources à comparer et le cas échéant le score de benchmark voulu.
- Diverses options comportant un booléen indiquant si l'on souhaite effectuer une mesure basé sur un benchmark, si le benchmark voulu est un benchmark existant le fournir, le type de mesure de corrélation voulu, le nombre de thread à utiliser pour traiter la demande, indiquer si l'on doit utiliser des index et le type de mesure souhaité afin de comparer vos ressources.

2-Pour récupérer les mesures/micro mesures existante il faut utiliser les liens suivants respectivement:
https://wysiwym-api.herokuapp.com/measures/getMicroMeasures
https://wysiwym-api.herokuapp.com/measures/getMeasures

3-Pour créer votre propre micro mesures il faut envoyer une requêtes de type POST et fournir des éléments semblable à ceux d'une mesure déjà implémenter:
- Le Dataset à utiliser contenant le nom, les préfixes, le lien, le graph par défaut et l'url de base pour chaque ressource.
- Les ressources à comparer et la propriété à comparer.
- Diverses options comportant le poids de votre micro mesure ainsi que le type de mesure à effectuer sur les ressources fournies.
