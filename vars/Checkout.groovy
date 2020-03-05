#!Groovy
/*	
	1.This is a Generic script which will update the WorkArea in respective slave machines.
	2.This script will take the branch as parameter.

*/


def call(String Branch,String repo){
	
		checkout(

			[$class: 'GitSCM', branches: [[name: Branch]],
			browser: [$class: 'Stash', repoUrl: 'http://BUILD@blrvslalm0006:7990/scm/opics/'+repo+'.git'],
			doGenerateSubmoduleConfigurations: false,
			extensions: [[$class: 'CleanBeforeCheckout']],
			submoduleCfg: [], userRemoteConfigs: [[credentialsId: '628e04bc-0b21-449f-8c81-937814bfdb83',
			url: 'http://BUILD@blrvslalm0006:7990/scm/opics/'+repo+'.git']]

			]
		)
}
