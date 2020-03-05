#!Groovy
/*

This script helps us to build Every day CI build on codeline basis

*/



def call(String Branch){

	String Version;
	if(Branch == 'trunk' || Branch == 'FOGC'){
		Version = '4.x'
		WA = 'Rel4.1'
	}else{
		Version =Branch[3..5]
		WA =Branch
	}

	print('CI build for codeline ' +Branch+' has been started!!!\n')
	
	if(Branch == 'trunk' || Branch == 'Rel4.4' || Branch == 'Rel4.5' || Branch == '47Proj' || Branch == 'FOGC'|| Branch == 'Rel4.6'){
		/*	Vijay - Commented below mv because we now have only one build for FOGC against master
		if(Branch == 'FOGC'){
				bat 'move /Y %WORKSPACE%\\.nuget\\packagesv1.config %WORKSPACE%\\.nuget\\packages.config'
		}	*/  
		bat '%WORKSPACE%\\.nuget\\nuget.exe restore -ConfigFile %WORKSPACE%\\.nuget\\Nuget.config %WORKSPACE%\\.nuget\\packages.config'
	}
								
    bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\40CI\\40CIBuild_Generic.pl '+Version+'_'+Branch+' '+WA
	
}

