#!Groovy
/*
	

*/

import java.io.File

def call(String param) {
	
		//def to_list = "balasundaram.A@misys.com mcnagendra.prasad@misys.com BV.VijayKumar@misys.com"
		def to_list = "mcnagendra.prasad@misys.com"
		
		Date date = new Date()
		String DATE = date.format("yyyy-MM-dd")
		/*echo "CurrentResult:${currentBuild.CurrentResult}"*/
		//String param_ImageTag =param
		echo "Image Tag is: ${param}"
		
		//print(master)
		String subje ="Opics 4.5 Docker Image:"+DATE
		//File file = new File("\\blrwinopfilsrv01\OpicsCMRE_Work\CMRE\NP_Stuff\docker_email.txt")
		
		//def File_text =file.readLines();
		
		
		 def File_text = 
		
		emailext (subject: subje,
							body: File_text,
							mimeType: 'text/html',
							recipientProviders: [[$class: 'RequesterRecipientProvider'], [$class: 'DevelopersRecipientProvider']],
							to:to_list
				)
		
			
							  
}