#!Groovy


def call(String Branch){

			String Version, Version1;
			String noBranch = ['Rel4.0','Rel4.1','Rel4.2']
			String SCRIPT_PATH="\\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\40CI\\API_Testing\\BAT\\xcludefilelist.txt"
			String SCRIPT_PATH1="\\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\40CI\\API_Testing\\BAT\\FS_excludefilelist.txt"
			
			if(Branch == 'trunk'){
				Version = '4.0.0.0'
				Version1 = '4.6.0.0'
			}else{
				Version =Branch[3..5].concat(".0.0")
				Version1 = Version
			}
			
			bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel3.x\\buildfoldertolatest.pl \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+" "+Version+"_"+Branch+"_Daily"
		
		if(Branch == 'trunk'){
			bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel3.x\\buildfoldertolatest.pl \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\4.0.0.0_FOGC 4.0.0.0_FOGC_Daily'
		} else if (Branch == 'Rel4.4'){ 
			bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel3.x\\buildfoldertolatest.pl \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\4.4.0.0_FOGC 4.4.0.0_FOGC_Daily'
		}


		if(!(noBranch.contains(Branch))) {
			echo 'Copying the FusionOpics Client and Server Components to Docker location for Codeline ' +Branch 					
			echo '**********************************************************************************************************************************************'
			bat 'xcopy \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+"\\"+Version+"_"+Branch+'_Daily_latest\\OPXCLIENT \\\\blrwinopfilsrv01\\OpicsBUILDS\\Docker\\'+Version1+'\\OPXCLIENT /I /E /D /Y /EXCLUDE:'+SCRIPT_PATH
			bat 'xcopy \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+"\\"+Version+"_"+Branch+'_Daily_latest\\OPXSVR\\Application\\Bin \\\\blrwinopfilsrv01\\OpicsBUILDS\\Docker\\'+Version1+'\\OPXSVR\\Application\\Bin\\  /I /E /D /Y /EXCLUDE:'+SCRIPT_PATH
			bat 'xcopy \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+"\\"+Version+"_"+Branch+'_Daily_latest\\OPXSVR\\Services \\\\blrwinopfilsrv01\\OpicsBUILDS\\Docker\\'+Version1+'\\OPXSVR\\Services\\ /I /E /D /Y /EXCLUDE:'+SCRIPT_PATH	
			bat 'copy /Y \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\4.0.0.0_trunk\\4.0.0.0_trunk_Daily_latest\\OPXTEST\\OPXSVR\\ApplicationServiceRest\\bin \\\\blrwinopfilsrv01\\OpicsBUILDS\\Docker\\'+Version1+'\\OPXTEST\\OPXSVR\\ApplicationServiceRest\\bin'	
			echo '**********************************************************************************************************************************************'
			echo 'Updating the DDT Distribution Area for ' +Branch 
			echo '**********************************************************************************************************************************************'
			echo 'Skipping DDT Distibution Update ...... '
			// bat 'xcopy \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+"\\"+Version+"_"+Branch+'_Daily_latest\\OPXSVR \\\\blrwinopfilsrv01\\OpicsBUILDS\\OpicsDDT\\'+Version1+'\\OPXSVR /I /E /D /Y /EXCLUDE:'+SCRIPT_PATH1
			// bat 'copy /Y \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+"\\"+Version+"_"+Branch+'_Daily_latest\\OPXTEST\\OPXSVR\\ApplicationServiceRest\\bin \\\\blrwinopfilsrv01\\OpicsBUILDS\\OpicsDDT\\'+Version1+'\\OPXTEST\\OPXSVR\\ApplicationServiceRest\\bin'	
			echo '**********************************************************************************************************************************************'
		}
}