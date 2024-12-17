# 📚 Gestion Bibliothèque
> Une solution complète de gestion de bibliothèque universitaire

## 🌟 Présentation du Projet

Gestion Bibliothèque est une application web moderne conçue pour simplifier la gestion des ressources documentaires dans un environnement académique. Développée avec passion et rigueur, elle offre une expérience utilisateur intuitive et performante.

## ✨ Fonctionnalités Principales

- **📖 Gestion des Livres**
  - Catalogue complet et dynamique
  - Suivi précis des stocks
  - Recherche rapide et efficace

- **👥 Gestion des Étudiants**
  - Profils détaillés
  - Historique des emprunts
  - Gestion des adhésions

- **🔄 Gestion des Emprunts**
  - Processus de prêt/retour simplifié
  - Alertes automatiques
  - Traçabilité complète

## 🛠 Technologies Utilisées

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)

## 🚀 Installation Rapide

### Prérequis
- Java 17+
- Maven
- Base de données MySQL/PostgreSQL

### Étapes d'Installation

```bash
# Cloner le repository
git clone https://github.com/votre-username/gestion-bibliotheque.git

# Accéder au dossier du projet
cd gestion-bibliotheque

# Installer les dépendances
mvn clean install

# Lancer l'application
mvn spring-boot:run
```

## 📂 Structure du Projet

```
gestion-bibliotheque/
│
├── src/
│   ├── main/
│   │   ├── java/           # Code source Java
│   │   └── resources/      # Ressources de l'application
│   └── test/               # Tests unitaires
│
├── pom.xml                 # Configuration Maven
└── README.md               # Documentation du projet
```

## 🔐 Configuration

Configurez votre fichier `application.properties` :

```properties
# Paramètres de base de données
spring.datasource.url=jdbc:mysql://localhost:3306/bibliotheque
spring.datasource.username=votre_username
spring.datasource.password=votre_mot_de_passe

# Configuration Hibernate
spring.jpa.hibernate.ddl-auto=update
```

## 🤝 Contribution

1. Forker le projet
2. Créer une branche feature (`git checkout -b feature/MaFeature`)
3. Committer les modifications (`git commit -m 'Ajout de ma feature'`)
4. Pousser la branche (`git push origin feature/MaFeature`)
5. Ouvrir une Pull Request

## 📜 Licence

Distribué sous licence MIT. Voir `LICENSE` pour plus de détails.

## 📞 Contact

Votre Nom - votre.email@example.com

[Lien du Projet](https://github.com/votre-username/gestion-bibliotheque)
