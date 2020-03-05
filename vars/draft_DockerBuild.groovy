#!Groovy
	
def call(String opService, String FS_path,String I_TAG){
	
	if(opService == 'opics-application'){
		PACKAGE_PATH="Application"
		CONFIGURATION_PACKAGE_PATH="OriginalConfiguration"
		APPLICATION_NAME="ApplicationServiceV4.0"
		IMAGE_NAME="application"
		
		PACKAGE_PATH_1="ApplicationServiceRest"
		CONFIGURATION_PACKAGE_PATH="OriginalConfiguration"
		APPLICATION_NAME_1="ApplicationServiceRestV4.0"
		IMAGE_NAME_1="applicationrest"
		
			
	}else if(opService == 'opics-gateway'){
		PACKAGE_PATH="."
		IMAGE_NAME="gateway"
		
	}else if(opService == 'opics-securetoken'){
		PACKAGE_PATH="."
		IMAGE_NAME="securetoken"
	
	}else if(opService == 'opics-frontend'){
		PACKAGE_PATH="Application"
		IMAGE_NAME="application"
	
	}else if(opService == 'opics-backend'){
		PACKAGE_PATH="Application"
		IMAGE_NAME="application"
	
	
	}
	
	if(opService == 'opics-application'){
	
		bat 'docker build -t registry.misys.global.ad/fcopics/'+IMAGE_NAME+':'+I_TAG+' --build-arg PACKAGE_PATH='+PACKAGE_PATH+' --build-arg CONFIGURATION_PACKAGE_PATH='+CONFIGURATION_PACKAGE_PATH+' --build-arg APPLICATION_NAME='+APPLICATION_NAME+' '+FS_path
		
		bat 'docker build -t registry.misys.global.ad/fcopics/'+IMAGE_NAME_1+':'+I_TAG+' --build-arg PACKAGE_PATH='+PACKAGE_PATH_1+' --build-arg CONFIGURATION_PACKAGE_PATH='+CONFIGURATION_PACKAGE_PATH+' --build-arg APPLICATION_NAME='+APPLICATION_NAME_1+' '+FS_path
	
	}else{
		
		bat 'docker build -t registry.misys.global.ad/fcopics/'+IMAGE_NAME+':'+I_TAG+' --build-arg PACKAGE_PATH='+PACKAGE_PATH+' '+FS_path
	
	}	
}



				
