pipeline {
    agent any
     environment {
         DISABLE_AUTH = 'true'
     }
    stages {
      stage('Checkout And Clean') {
            steps {
              script {
                    echo 'Pulling...' + env.BRANCH_NAME
                    sh('./script/clean.sh')
                    def mvnHome = tool 'mvn'
                    sh "'${mvnHome}/bin/mvn' clean -DskipTests=true"
                }
             }
            post {
                success {
                    echo 'cleaning succesfully...'
                }
            }
        }
        stage('Build') {
            steps {
                script {
                   def mvnHome = tool 'mvn'
                   sh "'${mvnHome}/bin/mvn' compile package -DskipTests=true"
                   archiveArtifacts 'target*//*.jar'
               }
            }
        }
        stage('Test') {
            steps {
                echo 'Executing test.....'
          }
        }
        stage('Deploy'){
            when {
                 expression { BRANCH_NAME ==~ /(master|develop|release)/ }
                 }
             steps{
                  script{
                       withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
                            sh('./script/deploy.sh')
                        }
                    }
                }
            post {
                success {
                    echo 'mod-catalogin up and running on port 8080'
                }
            }
        }
        stage('Deploy ITNET'){
               when {
                  expression { BRANCH_NAME ==~ /(master|release)/ }
              }
               steps{
                    script{
                         withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
                                  sh('./script/deploy_itnet.sh')
                                }
                            }
                        }
              post {
                   success {
                         echo 'mod-catalogin up and running on port 8080 on ITNET'
                         notifySuccessful(${currentBuild.currentResult}, ${env.JOB_NAME}, ${env.BUILD_NUMBER}, ${env.BUILD_URL})
                   }
              }
         }
         stage('Publish API Docs') {
             when {
               expression { BRANCH_NAME ==~ /(master|develop|release)/ }
             }
             steps {
                echo 'Publishing API Docs....'
             }
         }
          stage('Publish Npm') {
                   when {
                      expression { BRANCH_NAME ==~ /(master|develop|release)/ }
                 }
                   steps {
                     echo 'Publishing on Npm....'
              }
         }
    }
}

def notifySuccessful(currentResult, JOB_NAME, BUILD_NUMBER, BUILD_URL) {
   emailext (
      body: 'currentResult: Job JOB_NAME build BUILD_NUMBER\n More info at: BUILD_URL', compressLog: true, recipientProviders: [upstreamDevelopers(), developers(), brokenBuildSuspects()], subject: 'Jenkins Pipeline mod-cataloging', to: 'christian.chiama@atcult.it', attachLog: true
    )
}
