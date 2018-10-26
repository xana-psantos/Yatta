pipeline {
  agent any
  stages {
    stage('Recuperation des sources') {
      steps {
        git(url: 'https://github.com/xana-psantos/Yatta.git', branch: 'master', credentialsId: 'LoginGitHub')
      }
    }
    stage('Build Maven') {
      steps {
        bat(script: 'runmaven.bat', encoding: 'utf-8')
      }
    }
    stage('Qualimetrie') {
      steps {
		withSonarQubeEnv('Sonar') {
			bat(script: 'runqualimetrie.bat', encoding: 'utf-8')}
      }
    }
	stage("Quality Gate") {
		steps {
			timeout(time: 1, unit: 'HOURS') {
				// Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
				// true = set pipeline to UNSTABLE, false = don't
				// Requires SonarQube Scanner for Jenkins 2.7+
				waitForQualityGate(abortPipeline: true)
			}
		}
	}
    stage('Publication') {
      steps {
        nexusArtifactUploader(artifacts: [
                    			[artifactId: 'yatta', type: 'war', classifier:'debug', file: 'Yatta_Web/target/Yatta_Web.war']
                    			], nexusVersion: 'nexus3', protocol: 'http', nexusUrl: 'localhost:8081/', groupId: 'yatta', version: '1.0-SNAPSHOT', repository: 'maven-snapshots', credentialsId: 'nexus')
        }
      }
    }
  }