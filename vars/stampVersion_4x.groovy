#!Groovy

def call(String Branch){

		String Version;
		if(Branch == 'trunk'){
			Version = '4.0.0.0'
		}else{
			Version =Branch[3..5].concat(".0.0")
		}
	
		bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\4X_Versioning.pl'+" "+Version+"_"+Branch+" "+Branch		

}