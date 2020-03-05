#!Groovy


def call(String Branch,String BuildMachine){

			String GIT_WA;
			String Version, Version1;
			String ClientMsi
			String noBranch = ['Rel4.0','Rel4.1','Rel4.2']
			String SCRIPT_PATH="\\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\40CI\\API_Testing\\BAT\\xcludefilelist.txt"
			String SCRIPT_PATH1="\\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\40CI\\API_Testing\\BAT\\FS_excludefilelist.txt"
			Boolean ExclToolPrp;
			
			if(Branch == 'trunk'){
				Version = '4.0.0.0'
				Version1 = '4.6.0.0'
				ClientMsi = 'FusionOpicsClient.msi'
			}else{
				Version =Branch[3..5].concat(".0.0")
				Version1 = Version
				ClientMsi = 'OpicsPlusClient.msi'
			}
			
		
		
		echo 'Setup creation of latest nightly build for trunk has been started!!!'
		
		bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel3.x\\buildfoldertolatest.pl \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+" "+Version+"_"+Branch+"_Daily"
		
		if(Branch == 'trunk'){
			bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel3.x\\buildfoldertolatest.pl \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\4.0.0.0_FOGC 4.0.0.0_FOGC_Daily'
		} else if (Branch == 'Rel4.4'){ 
			bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel3.x\\buildfoldertolatest.pl \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\4.4.0.0_FOGC 4.4.0.0_FOGC_Daily'
		}
		
		if(Branch == 'trunk'){
				GIT_WA = 'Rel4.0';
		}else{GIT_WA = Branch;}
		
		if(Branch == 'Rel4.0'){
			ExclToolPrp = 'FALSE';
		}
		else{
			ExclToolPrp = 'TRUE';
		}
					
		bat 'perl \\\\blrwinopfilsrv01\\OpicsCMRE_Work\\CMRE-Tools\\36.NightlySetup_4X\\OpicsSetup4.0.pl'+" "+Version+" "+Branch+" "+GIT_WA+" "+BuildMachine+" "+ExclToolPrp		
		
		if(!(noBranch.contains(Branch))) {			
			echo 'Copying the FusionOpics Client and Server installers to Docker location for Codeline ' +Branch
			echo '**********************************************************************************************************************************************'
			bat 'copy \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+"\\"+Version+"_"+Branch+'_Daily_latest\\OPXCLIENT\\*.msi \\\\blrwinopfilsrv01\\OpicsBUILDS\\Docker\\'+Version1+'\\OPXCLIENT\\. '
			bat 'xcopy \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+"\\"+Version+"_"+Branch+'_Daily_latest\\OPXSVR\\Setup \\\\blrwinopfilsrv01\\OpicsBUILDS\\Docker\\'+Version1+'\\OPXSVR\\Setup  /I /E /D /Y /EXCLUDE:'+SCRIPT_PATH			
			echo '**********************************************************************************************************************************************'			
		}
}