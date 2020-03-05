#!Groovy
/*	
	1.This is a Generic script which will update the WorkArea in respective slave machines.
	2.This script will take the branch as parameter.

*/


def call(String repo){
	
		if(repo == 'opics-product'){
			checkout(
				[$class: 'GitSCM', branches: [[name: '*/master']],
				doGenerateSubmoduleConfigurations: false,
				extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'D:\\GIT_WA\\opics-product']],
				submoduleCfg: [], userRemoteConfigs: [[credentialsId: '628e04bc-0b21-449f-8c81-937814bfdb83',
				url: 'https://build@scm-git-eur.misys.global.ad/scm/opics/'+repo+'.git']]]
			)
			
		} else if (repo == 'opics-product-ui'){
			 checkout(
				[$class: 'GitSCM', branches: [[name: '*/master']],
				doGenerateSubmoduleConfigurations: false,
				extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'D:\\GIT_WA\\opics-product-ui']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '628e04bc-0b21-449f-8c81-937814bfdb83',
				url: 'https://build@scm-git-eur.misys.global.ad/scm/opics/'+repo+'.git']]]
				
			)
			
		}else {
				echo 'Do Nothing'
		}
}
