#!Groovy
/*
This script helps us to build Every day nightly build on codeline basis
*/


def call(String Branch, String RBranch){

/* Branch contains the value of the Branch being built. RBranch contains the value of the Reference Branch. This has been introduced for FOGC builds */

	String Version;	
	String noBranch = ['Rel4.0','Rel4.1','Rel4.2','Rel4.3','Rel4.4']	
	if(Branch == 'trunk' || RBranch == 'trunk'){
		Version = '4.0.0.0'
		// Version1 = '4.5.0.0'
	}else{
		Version =RBranch[3..5].concat(".0.0")
		// Version1 = Version
	}
	
	//if(!(Branch in noBranch)) {
	if (!noBranch.contains(Branch)) {
		echo 'Using MSBuild 14.0 and above'
		bat '\\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\4.0_build_pp2.bat'+" "+Version+" "+Branch+" "+"Nightly"+" "+"Rel4.0"
	}else {
		echo 'Using MSBuild 4.0'
		bat '\\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\4.0_build_pp.bat'+" "+Version+" "+Branch+" "+"Nightly"+" "+"Rel4.0"
	}
	// bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel3.x\\checkBuildFailure_4.X.pl \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch

	 /*if(Branch == 'trunk' || Branch == 'FOGC' ){				
		print('Nightly build for codeline ' +Branch+' has been started!!!')					
		bat '\\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\4.0_build_pp.bat'+" "+Version+" "+Branch+" "+"Nightly"+" "+"Rel4.0"				
	}else{
		print('Nightly build for codeline ' +Branch+' has been started!!!')							
		bat '\\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\4.0_build_Generic.bat'+" "+Version+" "+Branch+" "+"Nightly"+" "+Branch								
		bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel3.x\\checkBuildFailure_4.X.pl \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch
	}
	if(!(Branch in noBranch)) {
		echo 'Updating the DDT Distribution Area for ' +Branch 
		bat 'xcopy \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+"\\"+Version+"_"+Branch+'_Daily_latest\\OPXSVR \\\\blrwinopfilsrv01\\OpicsBUILDS\\OpicsDDT\\'+Version1+'\\OPXSVR /I /E /D /Y /EXCLUDE:'+SCRIPT_PATH
	}*/
}