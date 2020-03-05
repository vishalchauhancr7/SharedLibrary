#!Groovy
/*

Microservices

*/
def call(Map params){


			Date date = new Date()
			String TEMP1 = date.format("yyyyMMdd")
			String TEMP2 = date.format("hhmm")
			String TEMP3 =TEMP1+TEMP2
			//String TEMP3 ='201901211128'
			def RELEASE=params.RELEASE
			def DBTYPE='sql'
			def DOC_HOSTNAME='OPICS'+RELEASE+DBTYPE
			def DOCKERCOMPOSE_NAME = 'FCopics_dockerCompose'+RELEASE+'_'+TEMP3+'.yaml'
			def DTR_REGISTRY='registry.misys.global.ad/fcopics/'
			def DTR_REGISTRY_MS='fcopics'
			def CONTAINER_NAME_MS='OPICS'+RELEASE+DBTYPE
			def IMAGE_TAG='OPICS'+RELEASE+DBTYPE+'_'+TEMP3
			def IMAGE_NAME=DTR_REGISTRY+':'+IMAGE_TAG
			def MS_list= ['application','applicationrest','gateway','securetoken']
			def BUILD_LOC = '\\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\4.0.0.0_trunk\\4.0.0.0_trunk_Daily_latest\\OPXSVR'
			def BUILD_LOC_USR = 'bmopics'
			def BUILD_LOC_PWD = 'Kondor_123'
			def IMAGE_SCRIPT_MS = 'D:\\stores\\scripts\\Images\\scripts_MicroS\\BaseImage_v1.0'
			def Env_path=IMAGE_SCRIPT_MS+'\\env_file'
			def use_flag='Nightly'

			node('BLRCSWOPICS0111'){
				dir('D:\\stores\\scripts\\Images\\scripts_MicroS\\BaseImage_v1.0'){
					MS_list.eachWithIndex{
						var ->
							stage('Building  : '+var){
								bat 'docker build -t '+DTR_REGISTRY+var+':'+RELEASE+'_'+TEMP3+' --build-arg BUILD_LOCATION='+BUILD_LOC+' --build-arg BUILD_LOCATION_USER='+BUILD_LOC_USR+' --build-arg BUILD_LOCATION_PASSWORD='+BUILD_LOC_PWD+' '+IMAGE_SCRIPT_MS+'\\'+var
								echo 'skipping'
							}

					}
					stage('AquaScan'){
						MS_list.eachWithIndex{
							var ->
								def TAG_Img=var+':'+RELEASE+'_'+TEMP3
								echo 'Tag :'+TAG_Img
								Aqua_scan('MS',TAG_Img)

						}

					}

					stage('Pushing'){

						MS_list.eachWithIndex{
							var ->
								bat 'docker login -u opicscmre -p Earth@2012 registry.misys.global.ad'
								bat 'docker push '+DTR_REGISTRY+var+':'+RELEASE+'_'+TEMP3
								//echo 'skipping'

						}

					}

				}

			}
			node('BLRCSWOPICS0077'){
				stage('Generating Docker compose'){

					bat 'python \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\email_docker.py  --Image_Name ' +IMAGE_NAME+' --Docker_hostname '+DOC_HOSTNAME+' --Release '+RELEASE+' --DCFILE '+DOCKERCOMPOSE_NAME+' --build_flag '+use_flag+' --Tag_timestamp '+TEMP3
					//echo 'skipping'

				}

			}
			//Copy Docker compose

			node('BLRCSWOPICS0111'){
				stage('Run Services'){
					copy_compose(DOCKERCOMPOSE_NAME)
					bat 'docker-compose -f D:\\stores\\DOCKER_COMPOSE\\'+DOCKERCOMPOSE_NAME+' down'
					bat 'docker-compose -f D:\\stores\\DOCKER_COMPOSE\\'+DOCKERCOMPOSE_NAME+' up -d'
					//echo 'skipping'
				}

				stage('Test Services'){

					services()

				}

				stage('DDT Execution'){
					run_MServices()
				}

			}
			node('BLRCSWOPICS0111'){
				dir('D:\\stores\\GIT_WA\\opicsddt\\'){
					echo 'Updating DDT WorkArea'
					Checkout('master','opicsddt')
					bat 'mkdir D:\\stores\\GIT_WA\\opicsddt\\OUTPUT'
					bat 'copy D:\\temp\\OPX45\\opicsddt\\output\\*.trx D:\\stores\\GIT_WA\\opicsddt\\OUTPUT'
	
				stage('Publishing MSTest Result'){
        
				tep([$class: 'MSTestPublisher', testResultsFile: 'OUTPUT/*.trx'])
        
				}
			}

}
			

}
def copy_compose(comp_name){

	bat 'copy \\\\blrwinopfilsrv01\\OpicsBUILDS\\Docker\\4.5.0.0\\DOCKER_COMPOSE_MS\\'+comp_name+'  D:\\stores\\DOCKER_COMPOSE'


}
def services(){

	sleep(time:3,unit:"MINUTES")
	bat 'D:\\stores\\curl -k  https://blrcswopics0111:8443/SecureTokenServiceRestV4.0/STS.svc'
	bat 'D:\\stores\\curl -k https://blrcswopics0111:8444/ApplicationServiceV4.0/MBRE.svc'
	bat 'D:\\stores\\curl -k https://blrcswopics0111:8446/ApplicationServiceRestV4.0/help'

}

def run_MServices(){

	bat 'del D:\\temp\\OPX45\\opicsddt\\output\\*.trx /s /f /q'
	bat 'docker run -v D:/Temp/OPX45:C:/OPX45 -t  registry.misys.global.ad/fcopics/opicsddt:20192101 C:/OPX45/opicsddt/RunUnitTest.cmd'

}
