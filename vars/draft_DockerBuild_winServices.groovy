#!Groovy
	
def call(String opService, String FS_path,String I_TAG){
	
	if(opService == 'opics-windows-service'){
	
		PACKAGE_PATH="Services/Messaging"
		CONFIGURATION_PACKAGE_PATH="OriginalConfiguration"
		SERVICE_NAME="OpicsPlusMessaging4.0"
		SERVICE_EXECUTABLE="Misys.OpicsPlus.Framework.Messaging.OpicsPlusMessaging.exe"
		IMAGE_NAME="messaging"
		
		PACKAGE_PATH_1="Services/Revaluation"
		CONFIGURATION_PACKAGE_PATH_1="OriginalConfiguration"
		SERVICE_NAME_1="OpicsPlusRevaluation4.0"
		SERVICE_EXECUTABLE_1="Misys.OpicsPlus.Framework.Revaluation.RevalService.exe"
		IMAGE_NAME_1="reval"
		
		PACKAGE_PATH_2="Services/Logging"
		CONFIGURATION_PACKAGE_PATH_2="OriginalConfiguration"
		SERVICE_NAME_2="OpicsPlusLoggingDistribution4.0"
		SERVICE_EXECUTABLE_2="Misys.OpicsPlus.Framework.Common.ApplicationLogging.LoggingDistributionService.exe"
		IMAGE_NAME_2="logging"
		
			
	}else if(opService == 'opics-pubsub'){
		PACKAGE_PATH="Services"
		CONFIGURATION_PACKAGE_PATH="OriginalConfiguration"
		IMAGE_NAME="pubsub"
		
		
	}else if(opService == 'opics-commonservice'){
		PACKAGE_PATH="Services"
		CONFIGURATION_PACKAGE_PATH="OriginalConfiguration"
		IMAGE_NAME="commonservice"
	
	}
	
	if(opService == 'opics-windows-service'){
	
		bat 'docker build -t registry.misys.global.ad/fcopics/'+IMAGE_NAME+':'+I_TAG+' --build-arg PACKAGE_PATH='+PACKAGE_PATH+' --build-arg CONFIGURATION_PACKAGE_PATH='+CONFIGURATION_PACKAGE_PATH+' --build-arg SERVICE_NAME='+SERVICE_NAME+' --build-arg SERVICE_EXECUTABLE='+SERVICE_EXECUTABLE+' '+FS_path
		
		bat 'docker build -t registry.misys.global.ad/fcopics/'+IMAGE_NAME_1+':'+I_TAG+' --build-arg PACKAGE_PATH='+PACKAGE_PATH_1+' --build-arg CONFIGURATION_PACKAGE_PATH='+CONFIGURATION_PACKAGE_PATH_1+' --build-arg SERVICE_NAME='+SERVICE_NAME_1+' --build-arg SERVICE_EXECUTABLE='+SERVICE_EXECUTABLE_1+' '+FS_path
		
		
		bat 'docker build -t registry.misys.global.ad/fcopics/'+IMAGE_NAME_2+':'+I_TAG+' --build-arg PACKAGE_PATH='+PACKAGE_PATH_2+' --build-arg CONFIGURATION_PACKAGE_PATH='+CONFIGURATION_PACKAGE_PATH_2+' --build-arg SERVICE_NAME='+SERVICE_NAME_2+' --build-arg SERVICE_EXECUTABLE='+SERVICE_EXECUTABLE_2+' '+FS_path
		
		
	
	}else{
		
		bat 'docker build -t registry.misys.global.ad/fcopics/'+IMAGE_NAME+':'+I_TAG+' --build-arg PACKAGE_PATH='+PACKAGE_PATH+' --build-arg CONFIGURATION_PACKAGE_PATH='+CONFIGURATION_PACKAGE_PATH+' '+FS_path
	
	}	
}



				
