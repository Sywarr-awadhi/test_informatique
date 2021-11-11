pipeline {
 environment {
 registry = "1401199897/timesheet"
 registryCredential = 'dockerHub'
 dockerImage = '' }
 


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
stage('pulling from git') {
 steps {
mail bcc: '', body: 'Test success', cc: '', from: '', replyTo: '', subject: 'test Timesheet', to: 'awadhi.sywar1@gmail.com'
}}
    }

