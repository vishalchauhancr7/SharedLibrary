#!Groovy
/*

This script helps us to do code signing on nightly build on codeline basis

*/


def call(String status,String repo){
	
	if(repo == 'opics4plus'){
		
		 bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\40CI\\API_Testing\\script\\codelineLock_master_CI.pl '+status
		
	}else{
	
		 bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\40CI\\API_Testing\\script\\codelineLock_master_CI_FOGC.pl '+status
	}

}