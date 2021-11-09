pipeline {
 environment {
 registry = "1401199897/devops"
 registryCredential = '1401199897'
 dockerImage = ''
}

agent any
stages {
stage('pulling from git') {
steps { 	        git branch: 'main', url: 'https://github.com/Sywarr-awadhi/test_informatique.git'
}
}
stage("Build") {
       steps {
       
       bat "mvn clean install"
             }}

      stage("Unit tests") {
       steps {
       
       bat "mvn test"
             }}
    
	  stage("test statique") {
       steps{
		bat "mvn clean verify sonar:sonar -Dsonar.projectKey=timesheet -Dsonar.host.url=http://localhost:9000 -Dsonar.login=8af1af23dc5d142d684ea96aaa8c7d1b5e22c580"		
			}}
	  stage ("clean et packaging") {    
       steps {
         bat "mvn clean package "
			}}

      stage("DEPLOY with Nexus") {
          steps {
       bat "mvn deploy"
			}}



		
stage('Build image with Docker') {
steps {
   dir("test_informatique"){
script {
   dockerImage = docker.build registry + ":$BUILD_NUMBER"}}}}
stage('Push image with Docker') {
steps {
   dir("test_informatique"){
script {
docker.withRegistry( '', registryCredential ) {
 bat "docker push $registry:$BUILD_NUMBER"


}}}}}

stage ('Remove unused docker imager'){
    steps {
        bat "docker rmi $registry:$BUILD_NUMBER"
    }
}
    }



      post {

         success {
         mail bcc: '', body: '''success Jenkins pipline .
             Jenkins.''', cc: '', from: '', replyTo: '', subject: 'Build succed', to: 'siwar.awadhi1@esprit.tn'
         }
         failure {
             mail bcc: '', body: '''failed Jenkins pipline .
             Jenkins.''', cc: '', from: '', replyTo: '', subject: 'Build failed', to: 'siwar.awadhi1@esprit.tn'
         }
             always {
            cleanWs()
        }
      }

}