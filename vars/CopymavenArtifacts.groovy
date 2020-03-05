#!Groovy
/*

This script helps us to copy Maven Artifacts to nightly build location

*/


def call(String repo,String WA){

	String Version;
	
	if(repo == 'trunk'){
		Version = '4.0.0.0'
		Workarea_path = WA+'\\Integration\\FFC'
	}else{
		Version =repo[3..5].concat(".0.0")
		Workarea_path = '\\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\4.0.0.0_FOGC\\4.0.0.0_FOGC_Daily_latest'
		
	}
	
	buildFodler_path ='\\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+'_FOGC'
	bat '\\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\CopyMavenArtifacts.bat'+' '+Workarea_path+' '+buildFodler_path+' '+repo
	
	
}