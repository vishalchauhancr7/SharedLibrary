#!Groovy
/*

This script helps us to build Every day nightly build on codeline basis

*/


def call(String Branch){

	String Version;
	String noBranch = ['Rel4.0','Rel4.1','Rel4.2']
	String SCRIPT_PATH="\\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\40CI\\API_Testing\\BAT\\FS_excludefilelist.txt"
	if(Branch == 'trunk'){
		Version = '4.0.0.0'
		Version1 = '4.5.0.0'
		ClientMsi = 'FusionOpicsClient.msi'
	}else{
		Version =Branch[3..5].concat(".0.0")
		Version1 = Version
		ClientMsi = 'OpicsPlusClient.msi'
	}

	if(!(Branch in noBranch)) {
		echo 'Updating the DDT Distribution Area for ' +Branch 			
/*		echo 'bat xcopy \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+"\\"+Version+"_"+Branch+'_Daily_latest\\OPXSVR \\\\blrwinopfilsrv01\\OpicsBUILDS\\OpicsDDT\\'+Version1+'\\OPXSVR /I /E /D /Y /EXCLUDE:'+SCRIPT_PATH
		bat 'xcopy \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+"\\"+Version+"_"+Branch+'_Daily_latest\\OPXSVR \\\\blrwinopfilsrv01\\OpicsBUILDS\\OpicsDDT\\'+Version1+'\\OPXSVR /I /E /D /Y /EXCLUDE:'+SCRIPT_PATH
		bat 'copy \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+"\\"+Version+"_"+Branch+'_Daily_latest\\OPXCLIENT\\'+ClientMsi+' \\\\blrwinopfilsrv01\\OpicsBUILDS\\Docker\\'+Version1+'\\OPXCLIENT\\.'    */
		// bat 'xcopy \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+"\\"+Version+"_"+Branch+'_Daily_latest\\OPXCLIENT \\\\blrwinopfilsrv01\\OpicsBUILDS\\Docker\\'+Version1+'\\OPXCLIENT /I /E /D /Y /EXCLUDE:'+SCRIPT_PATH
		echo '************************************************************************'
		echo  '                 '
		bat 'xcopy \\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch+"\\"+Version+"_"+Branch+'_Daily_latest\\OPXCLIENT \\\\blrwinopfilsrv01\\OpicsBUILDS\\Docker\\'+Version1+'\\OPXCLIENT /I /E /D /Y /EXCLUDE:'+SCRIPT_PATH
	}
}