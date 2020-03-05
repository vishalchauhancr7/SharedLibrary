#!Groovy
/*

This script the analyse the sonar status from the sonar server

*/

def call(String Branch){
		String Version;
		if(Branch == 'trunk'){
			Version = '4.0.0.0'
		}else{
			Version =Branch[3..5].concat(".0.0")
		}
	
		bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel3.x\\checkBuildFailure_4.X_pipeline.pl \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch

}