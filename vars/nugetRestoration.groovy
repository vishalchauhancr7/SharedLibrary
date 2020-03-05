#!Groovy 			    
		
def call(String message){

		bat '%WORKSPACE%\\.nuget\\nuget.exe restore -ConfigFile %WORKSPACE%\\.nuget\\Nuget.config %WORKSPACE%\\.nuget\\packages.config'

        bat 'perl \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\ThirdParty.pl'
		echo "Nuget restoration is:${message}";
}