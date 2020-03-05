#!Groovy
/*
	1.This script helps us to send notification to developers,
		culprits(whose changes caused for build failure) on every Build failure
	2.Opics DevOps team and Build requester

*/


def call(String Status) {
	
		def to_list = "balasundaram.A@misys.com mcnagendra.prasad@misys.com BV.VijayKumar@misys.com"
		print(Status)
		emailext (subject:Status+": Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
							  body:Status+"""<p>Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
							  <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
                              recipientProviders: [[$class: 'CulpritsRecipientProvider'],[$class: 'RequesterRecipientProvider']],
							  to: to_list,mimeType: "text/html")			
}

