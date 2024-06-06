pipeline {
    agent any

    stages {
        stage('Clone repository') {
            steps {
                // Клонирование репозитория из Git
                git 'https://github.com/nlc1/MyBot.git'
            }
        }
        stage('Build') {
            steps {
                // Сборка проекта с помощью Maven
                sh 'mvn clean package'
            }
        }
        stage('Copy JAR file') {
            steps {
                // Копирование .jar файла в указанную директорию
                sh 'cp target/*.jar /home/nik'
            }
        }
    }

    post {
        success {
            // Уведомление об успешной сборке
            emailext body: 'Build successful', subject: 'Build Successful', to: 'nlc@mail.ru'
        }
        failure {
            // Уведомление о неуспешной сборке
            emailext body: 'Build failed', subject: 'Build Failed', to: 'nlc@mail.ru'
        }
    }
}
