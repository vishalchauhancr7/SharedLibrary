
def call(String Branch){

		String Version;
		String BType;
		if(Branch == 'trunk'){
			Version = '4.0.0.0'
		}else{
			Version =Branch[3..5].concat(".0.0")
		}
		BType='Nightly'
				
		bat 'perl \\\\blrcswopics0028\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\4xDB\\OpicsDBScriptGenerator.pl'+" "+BType+" "+Version+" "+Branch
				
}