#!Groovy
/*

This script helps us to build Every day nightly build on codeline basis

*/


def call(String repo){

	String Version;
	
	if(repo == 'trunk' || repo == 'FOGC'){
		Version = '4.0.0.0'
		Workarea_path = 'C:\\GIT_WA\\'+Version+'_'+repo+'\\Rel4.0\\Integration\\FFC'
		buildFodler_path ='\\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+'_'+repo
	}else{
		Version =RBranch[3..5].concat(".0.0")
		buildFodler_path ='\\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+'_'+repo
	}

	bat '\\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\maven_build.bat'+' '+Workarea_path+' '+buildFodler_path
	
	
}