pipeline {
  agent any
  stages {
    stage('Recupération des sources') {
      steps {
        git(url: 'https://github.com/xana-psantos/Yatta.git', branch: 'master', credentialsId: 'LoginGitHub')
      }
    }
    stage('Build Maven') {
      steps {
        bat(script: 'runmaven.bat', encoding: 'utf-8')
      }
    }
	stage('Publication') {
      steps {
        nexusArtifactUploader {
        nexusVersion('nexus3')
        protocol('http')
        nexusUrl('localhost:8081/')
        groupId('yatta')
        version('1.0')
        repository('maven-snapshots')
        credentialsId('nexus')
        artifact {
            artifactId('yatta')
            type('war')
            classifier('debug')
            file('Yatta_Web/target/Yatta_Web.war')
        }
      }
    }
  }
}