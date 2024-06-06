pipeline {
    agent any

    stages {
        stage('Clone repository') {
            steps {
                git 'https://github.com/nlc1/MyBot.git'
            }
        }
        stage('Build') {
                    steps {
                        dir('myBot/pom.xml') {
                            sh 'mvn clean package'
                        }
                    }
                }
        stage('Copy JAR file') {
            steps {
                sh 'cp target/*.jar /home/nik'
            }
        }
    }

    post {
        success {
            emailext body: 'Build successful', subject: 'Build Successful', to: 'nlc@mail.ru'
        }
        failure {
            emailext body: 'Build failed', subject: 'Build Failed', to: 'nlc@mail.ru'
        }
    }
}
