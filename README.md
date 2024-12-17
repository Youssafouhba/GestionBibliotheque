📚 Gestion Bibliothèque
Une solution complète de gestion de bibliothèque universitaire

🌟 Présentation du Projet
Gestion Bibliothèque est une application web moderne conçue pour simplifier la gestion des ressources documentaires dans un environnement académique. Développée avec passion et rigueur, elle offre une expérience utilisateur intuitive et performante.

✨ Fonctionnalités Principales
📖 Gestion des Livres

Catalogue complet et dynamique
Suivi précis des stocks
Recherche rapide et efficace
👥 Gestion des Étudiants

Profils détaillés
Historique des emprunts
Gestion des adhésions
🔄 Gestion des Emprunts

Processus de prêt/retour simplifié
Alertes automatiques
Traçabilité complète
🛠 Technologies Utilisées


🚀 Installation Rapide
Prérequis
Java 17+
Maven
Base de données MySQL/PostgreSQL
Étapes d'Installation
bash
Copier le code
# Cloner le repository
git clone https://github.com/Youssafouhba/GestionBibliotheque.git

# Accéder au dossier du projet
cd gestion-bibliotheque

# Installer les dépendances
mvn clean install

# Lancer l'application
mvn spring-boot:run
📂 Structure du Projet
bash
Copier le code
gestion-bibliotheque/
│
├── src/
│   ├── main/
│   │   ├── java/           # Code source Java
│   │   └── resources/      # Ressources de l'application
│   └── test/               # Tests unitaires
│
├── pom.xml                 # Configuration Maven
├── Jenkinsfile             # Fichier de configuration CI/CD pour Jenkins
└── README.md               # Documentation du projet
🔐 Configuration
Configurez votre fichier application.properties :

properties
Copier le code
# Paramètres de base de données
spring.datasource.url=jdbc:mysql://localhost:3306/bibliotheque
spring.datasource.username=votre_username
spring.datasource.password=votre_mot_de_passe

# Configuration Hibernate
spring.jpa.hibernate.ddl-auto=update
🛠 CI/CD Configuration
Le projet est configuré avec un pipeline CI/CD via Jenkins pour automatiser les tests, la construction et le déploiement. Le fichier Jenkinsfile se trouve à la racine du projet et contient les étapes nécessaires pour configurer le pipeline de manière efficace et fluide.

Étapes du Pipeline Jenkins :
Build - Construction de l'application.
Tests - Exécution des tests unitaires.
Docker Build - Construction de l'image Docker pour le déploiement.
Push Docker Image - Poussée de l'image Docker vers Docker Hub ou un autre registre.
🤝 Contribution
Forker le projet
Créer une branche feature (git checkout -b feature/MaFeature)
Committer les modifications (git commit -m 'Ajout de ma feature')
Pousser la branche (git push origin feature/MaFeature)
Ouvrir une Pull Request
📜 Licence
Distribué sous licence MIT. Voir LICENSE pour plus de détails.

📞 Contact
youssefouhba@gmail.com
+2127 05359817

