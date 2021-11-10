pipeline {
 environment {
 dockerImage = ''
 registry = '1401199897/devops'
 registryCredential = 'dockerhub_id'
 
 
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



		
  stage('Docker : Build image') {
steps {
   
script {
   dockerImage = docker.build registry}}}
stage('Docker : Push image') {
steps {
   
script {
docker.withRegistry( '', registryCredential ) {
   dockerImage.push()}}}}
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
