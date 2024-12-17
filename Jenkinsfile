pipeline {
    agent any
    tools {
       jdk 'jdk 17'
       maven 'maven'
       git  'Default'
    }

    triggers {
        // GitHub webhook trigger with ngrok URL
        githubPush()
    }
    environment {
        SONAR_SCANNER_HOME = tool name: 'sonar-scanner'
        SONAR_HOST_URL = 'http://172.17.0.3:9000'
        SONAR_LOGIN = 'sqa_418217049b9695fab5663978365d0cc82a4ba338'
    }

    stages {
        stage('Checkout') {
            steps {
                sh 'java -version' // Verify JDK 21 is being used
                sh 'javac -version'
                git branch: 'dev',changelog: false ,url: 'https://github.com/Youssafouhba/GestionBibliotheque.git'
            }
        }

        stage('Build') {
            steps {
                echo "build"
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                echo "test"
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo "SonarQube Analysis"
                sh ''' ${SONAR_SCANNER_HOME}/bin/sonar-scanner \\
                    -Dsonar.host.url=${SONAR_HOST_URL} \\
                    -Dsonar.login=${SONAR_LOGIN} \\
                    -Dsonar.projectname=GestionBibliotheque \\
                    -Dsonar.java.binaries=. \\
                    -Dsonar.projectKey=GestionBibliotheque '''
            }
        }


        stage('Build and Push Docker Image') {
               steps {
                   script {
                       withDockerRegistry([credentialsId: 'docker-hub-credentials', url: 'https://index.docker.io/v1/']) {
                           sh '''
                               docker build -t gestionbibliotheque:latest -f ./Dockerfile .
                               docker tag gestionbibliotheque:latest youssefdev950/gestionbibliotheque:latest
                               docker push youssefdev950/gestionbibliotheque:latest
                           '''
                       }
                   }
               }
        }

        stage('trigger CD Pipieline') {
            steps {
               build job: 'CD_Gestion_Bibliotheque' ,wait: true
            }
        }

    }

    post {
        success {
            echo 'Pipeline succeeded! The application has been built, tested, and deployed.'
            emailext to: 'youssefouhba@gmail.com',
                subject: 'Build Success',
                body: 'Le build a été complété avec succès.'
        }
        failure {
            echo 'Pipeline failed. Please check the logs for details.'
            emailext to: 'youssefouhba@gmail.com',
                subject: 'Build Failed',
                body: 'Le build a échoué.'
        }
    }
}
