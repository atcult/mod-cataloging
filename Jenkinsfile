pipeline {
    agent any
    stages {
      stage('Clean') {
                steps {
                   sh('./script/clean.sh')
                }
                post {
                     success {
                         echo 'cleaning succesfully...'
                    }
                 }
            }
        stage('Build') {
          when {
              expression { BRANCH_NAME ==~ /(master|develop|ci-test)/ }
            }
            steps {
             script {
               echo 'Pulling...' + env.BRANCH_NAME
               def mvnHome = tool 'mvn'
               sh "'${mvnHome}/bin/mvn' clean compile package -DskipTests"
               archive 'target*//*.jar'
               }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                sh "ps | grep 8888 | awk '{print $1}' | xargs kill -9 || true  env SERVER.PORT=8888 nohup java -Dserver.port=8888 -jar ./target/mod-cataloging-1.0.jar &"
            }
        }
         stage('Npm') {
             steps {
                echo 'Publishing on Npm....'
             }
         }
    }
}
