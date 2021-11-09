pipeline {
 environment {
 registry = "1401199897/devops"
 registryCredential = 'DockerCredentials'
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



		
stage('Docker : Build image') {
steps {
   dir("test_informatique"){
script {
   dockerImage = docker.build registry}}}}
stage('Docker : Push image') {
steps {
   dir("TimesheetProject"){
script {
docker.withRegistry( '', registryCredential ) {
   dockerImage.push()}}}}}
}
post {
failure {
emailext attachLog: true, body: '''There was an error that prevented a Build Success ! 
Do check the attached log or the console output for further details. 
Jenkins Team ''', to: '$DEFAULT_RECIPIENTS' , subject: 'Build Failure on Pipeline'
    }
success {
emailext body: '''Congrats for the successful build! 
Jenkins Team ''', to: '$DEFAULT_RECIPIENTS' , subject: 'Build Success on Pipeline'
    }
}
}