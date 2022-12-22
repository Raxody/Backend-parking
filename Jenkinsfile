@Library('ceiba-jenkins-library@master') _
pipeline{
	// any -> tomaria slave 5 u 8
	// Para mobile se debe especificar el slave -> {label 'Slave_Mac'}
	// Para proyectos de arus se debe tomar el slave 6 o 7 -> {label 'Slave6'} o {label 'Slave7'}
    agent {
        label 'Slave_Induccion'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
        disableConcurrentBuilds()
        gitLabConnection('GitCeiba')
    }

    environment {
        PROJECT_PATH = './microservicio'
    }

    triggers {
        // @yearly, @annually, @monthly, @weekly, @daily, @midnight, and @hourly o definir un intervalo ejemplo H */4 * * 1-5
        pollSCM('@daily') //define un intervalo regular en el que Jenkins debería verificar los cambios de fuente nuevos
    }

    tools {
        jdk 'JDK11_Centos'
    }

    stages{
        stage('checkout'){
            steps{
                echo '-----------------checkout-----------------'
                checkout([
                            $class: 'GitSCM',
                            branches: [[name : '*/main']],
                            doGenerateSubmoduleConfigurations: false,
                            extensions : [],
                            gitTool: 'Default',
                            submoduleCfg: [],
                            userRemoteConfig:[[
                            credentialsId: 'bd42f4d1-1ad4-4210-90d7-06a0ca789d8f',
                            url: 'https://git.ceiba.com.co/adnandreslopez/andres.lopez/adn_andres_lopez.git'
                          ]]
                        ])
                dir(PROJECT_PATH){
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean'
                }
            }

        }
            stage('Compile & Unit Tests') {
              steps{
                echo "------------>Compile & Unit Tests<------------"
                sh "chmod +x ./microservicio/gradlew"
                sh "./microservicio/gradlew --b ./microservicio/build.gradle clean"
        		    sh "./microservicio/gradlew --b ./microservicio/build.gradle test"
              }
            }


        stage('Analisis de codigo estatico'){
            steps{
              echo '-----------------Analisis de codigo estatico-----------------'
                withSonarQubeEnv('Sonar'){
                    //Coge las propiedades del sonarQube y las ejecuta
                    sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=./sonar-project.properties"
                }
            }
        }

        stage('Build'){
            parallel {
                stage('construcción Backend'){
                    steps{
                        echo "------------>Compilación backend<------------"
                        dir(PROJECT_PATH){
                            sh './gradlew --b build.gradle clean '
                            sh './gradlew build -x test '
                        }
                    }
                }
            }
         }
    }
    post {
        always {
          echo 'This will always run'
        }
        success {
          echo 'This will run only if successful'
          //junit 'build/test-results/test/*.xml'
        }
        failure {
          echo 'This will run only if failed'
          mail (to: 'andres.lopez@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",body: "Something is wrong with ${env.BUILD_URL}")
        }
        unstable {
          echo 'This will run only if the run was marked as unstable'
        }
        changed {
          echo 'This will run only if the state of the Pipeline has changed'
          echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }

}