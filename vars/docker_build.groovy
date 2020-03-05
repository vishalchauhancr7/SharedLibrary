#!Groovy

//call docker_build(String codeline){
		
	//bat 'docker build -t registry.misys.global.ad/balasuna/explore-data:image_450_DBfiles D:\stores\opics_fs\Opics450'
		
//}

//docker build -t registry.misys.global.ad/balasuna/explore-data:image_450_DBfiles D:\stores\opics_fs\Opics450

//docker build -t registry.misys.global.ad\balasuna\explore-data:image_450_DBfiles D:\stores\opics_fs\Opics450




@Library('OpicsDevOps')_
pipeline{
    
	 agent none;
	 define {
				def BUILD_TYPE ='Nightly';
				def RELEASE=450;
				def DBTYPE='sql'
				def DOC_HOSTNAME='opics'+RELEASE+DBTYPE
				def CONTAINER_NAME='OPICS'+RELEASE+DBTYPE+BUILD_TYPE
				def DTR_REGISTRY=registry.misys.global.ad/balasuna/explore-data

				Date date = new Date()
				String TEMP1 = date.format("yyyyMMdd")
				String TEMP2 = date.format("hhmm")
				String TEMP3 =TEMP1+TEMP2
				echo TEMP3
				
				def IMAGE_TAG='opics'+RELEASE+DBTYPE+TEMP3
				def IMAGE_NAME = DTR_REGISTRY+':'+IMAGE_TAG
				
				
				echo 'Docker Host Name:'+DOC_HOSTNAME
				echo 'Container Name:'+CONTAINER_NAME
     }
 
    stages {

		stage('BLRCSWOPICS0117 \u27A1 Docker build') {
		
			agent {
					label{
					 label 'BLRCSWOPICS0117.misys.global.ad'
					 //customWorkspace "D:\\Stores\\GIT_WA\\opics4plus\\master\\"
					}
			}

            steps {
				  // bat 'D:\\stores\\scripts\\builds\\start_build.bat %CODELINE% %BUILD_TYPE% %RELEASE%' 
				  //bat 'D:\\stores\\scripts\\Images\\create_images.bat'
				  //docker_build('master')
				  
				  bat 'docker build -t DTR_REGISTRY:image_450_DBfiles D:\\stores\\opics_fs\\Opics450'
            }

        }
        
        
    }
}