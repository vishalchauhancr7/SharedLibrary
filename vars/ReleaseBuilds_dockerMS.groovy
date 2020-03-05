#!Groovy


def call(String Branch){

			String Version, Version1;
			String noBranch = ['Rel4.0','Rel4.1','Rel4.2','Rel4.3']
			
			if(Branch == 'trunk'){
				Version = '4.6.0.0'
				Version1 = '4.6'
			}else{
				Version =Branch[3..5].concat(".0.0")
				Version1 = Version
			}
			
		RBPATH = '\\\\blrwinopfilsrv01\\OpicsBUILDS\\ReleaseBuilds\\'+Version1+"\\"+Version1+'_SP1'
		def RB_FOLDER_LATEST =buildFolderLatest(RBPATH)
		Doc_RBPATH = RBPATH+'\\'+RB_FOLDER_LATEST+'\\'+Patch
		print(Doc_RBPATH)
		
		//if(!(noBranch.contains(Branch))) {
		//	echo 'Copying the Daily QA service pack builds docker Release builds location for Codeline ' +Branch 					
		//	echo 
			//'****************************************************************************************************************************//******************'
		//	bat 'xcopy 
		//	\\\\blrwinopfilsrv01\\OpicsBUILDS\\ReleaseBuilds\\'+Version1+"\\"+Version1+'_SP1'+"\\"+Date+'\\Patch \\\\blrwinopfilsrv01\\OpicsBUILDS\\ReleaseBuilds\\DockerReleaseBuilds\\'+Version /I /E /D /Y
				
		//	echo '**********************************************************************************************************************************************'
		//}
}

def buildFolderLatest(fs_path){

    def files=[]

    (fs_path as File).eachFile groovy.io.FileType.DIRECTORIES, {
        files << it
    }
    def result = files.sort{ a,b -> b.lastModified() <=> a.lastModified() }*.name

    def latestBuild =result[0]

    return latestBuild

}