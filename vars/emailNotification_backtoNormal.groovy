#!Groovy
/*
	1.This script helps us to send notification to developers,
		culprits(whose changes caused for build failure) on every Build failure
	2.Opics DevOps team and Build requester

*/


def call(String Status) {
	
		def to_list = "balasundaram.A@misys.com mcnagendra.prasad@misys.com BV.VijayKumar@misys.com"
		/*echo "CurrentResult:${currentBuild.CurrentResult}"*/
		print(Status)
		
		if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {
		if (currentBuild.previousBuild != null && currentBuild.previousBuild.result != 'SUCCESS') {
				emailext (subject: "JENKINS JOB BACK TO NORMAL'${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
							body: "See ${env.BUILD_URL} for details.",
							mimeType: 'text/html',
							recipientProviders: [[$class: 'RequesterRecipientProvider']],
							to:to_list)
							}
				}						  
}