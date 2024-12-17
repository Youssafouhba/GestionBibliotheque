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
        SONAR_LOGIN = 'squ_41308bd2e53731e4479f866f565334093819fd3c'
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

        stage('OWASP SCAN') {
           steps {
                echo "OWASP SCAN"
                dependencyCheck additionalArguments: ' --scan ./',odcInstallation: 'DP'
                 dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
            }
        }

        stage('Build and Push Docker Image') {
               steps {
                   script {
                       withDockerRegistry([credentialsId: 'docker-hub-credentials', url: 'https://index.docker.io/v1/']) {
                           sh '''
                               docker build -t GestionBibliotheque:latest -f docker/Dockerfile .
                               docker tag GestionBibliotheque:latest youssefdev950/GestionBibliotheque:latest
                               docker push youssefdev950/GestionBibliotheque:latest
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
