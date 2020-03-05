@Library('OpicsDevOps')_

Date date = new Date()
String TEMP1 = date.format("yyyyMMdd")
String TEMP2 = date.format("hhmm")
String TEMP3 =TEMP1+TEMP2
echo TEMP3
def BUILD_TYPE ='nightly'
def RELEASE=451
def DBTYPE='sql'
def DOC_HOSTNAME='opics'+RELEASE+DBTYPE
def CONTAINER_NAME='OPICS'+RELEASE+DBTYPE+BUILD_TYPE
def DTR_REGISTRY='registry.misys.global.ad/fusion-capital-market/opicsbuilds'
def IMAGE_TAG='opics'+RELEASE+DBTYPE+TEMP3
def IMAGE_NAME=DTR_REGISTRY+':'+IMAGE_TAG
def OPICS_RELEASE='Opics'+RELEASE
def FS_ROOT='D:\\stores\\opics_fs\\'+OPICS_RELEASE
def DB_IMAGE_TAG='image_'+RELEASE+'_DBfiles'
def IMAGE_SCRIPT='C:\\stores\\scripts\\Images\\'+OPICS_RELEASE
def DockerContCount='\\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\Docker\\ContainerCount.txt'

pipeline{
    
	agent none;
    stages {
	
		stage('\u27A1 CleanContainers') {
		
			agent {
					label{
					    label 'BLRCSWOPICS0117.misys.global.ad'
					    customWorkspace FS_ROOT
					}
			}

            steps {
				  
				 script{ 
							bat 'del /f '+DockerContCount
						   
						  try{
						        
								bat 'docker ps -a | find  "'+CONTAINER_NAME+'"  /c  >> '+DockerContCount
						   }catch(Exception e){
							   
							   echo 'No such Container'
							   //throw e
						   } 
						   
						   
						   //File theInfoFile = new File('D://stores//dockerConCount.txt')
						   //theInfoFile.eachLine{line ->
						   readFile(DockerContCount).eachLine{line ->
						   print line
						   
						   if (line > '0') {
							   
								bat 'docker stop ' + CONTAINER_NAME
								bat 'docker rm ' + CONTAINER_NAME
							} else if(line == '0') {
								
								print('No containers are running')
						   
						   }

                        }
				    }
                
                }
        }

		stage('\u27A1 Add DB files') {
		
			agent {
					label{
					 label 'BLRCSWOPICS0117.misys.global.ad'
					 customWorkspace FS_ROOT
					}
			}

            steps {
				  
				 script{ 
				 bat 'cd '+FS_ROOT
				 bat 'docker rmi -f '+DTR_REGISTRY+':'+DB_IMAGE_TAG
				 bat 'ping localhost -n 10'
				  
				  bat 'docker build -t '+DTR_REGISTRY+':'+DB_IMAGE_TAG+' '+FS_ROOT
				  
            }
                
            }

        }
		
		stage('\u27A1 Create Container') {
		
			agent {
					label{
					 label 'BLRCSWOPICS0117.misys.global.ad'
					  customWorkspace FS_ROOT
					}
			}

            steps {
				  
				 script{ 
				  bat 'docker images'
				  bat 'docker create -i -h '+DOC_HOSTNAME+' --name '+CONTAINER_NAME+' '+'-m 4g -v D:\\stores:c:\\stores -p 80:80 -p 8080:8080 -p 1433:1433'+' '+DTR_REGISTRY+':'+DB_IMAGE_TAG
				  
            }
                
            }

        }
        
        stage('\u27A1 Attach DB'){
		
			agent {
					label{
					 label 'BLRCSWOPICS0117.misys.global.ad'
					  customWorkspace FS_ROOT
					}
			}

            steps {
				  
				 script{ 
				  bat 'docker start '+CONTAINER_NAME
				  
				  
				   bat 'docker exec '+CONTAINER_NAME+' '+IMAGE_SCRIPT+'\\attach_db.bat'
				   bat 'ping localhost -n 10'
				  
            }
                
            }

        }
        
        stage('\u27A1 Install Opics'){
		
			agent {
					label{
					 label 'BLRCSWOPICS0117.misys.global.ad'
					  customWorkspace FS_ROOT
					}
			}

            steps {
				  
				 script{ 
				  //bat 'docker start '+CONTAINER_NAME
				  //bat 'docker build -t'+env.DTR_REGISTRY+':image_450_DBfiles D:\\stores\\opics_fs\\Opics450'
				  
				  //bat 'docker create -i -h '+DOC_HOSTNAME+' --name '+CONTAINER_NAME+' '+'-m 4g -v D:\\stores:c:\\stores -p 80:80 -p 8080:8080 -p 1433:1433'+' '+DTR_REGISTRY+':image_450_DBfiles'
				  
				  bat 'docker exec '+CONTAINER_NAME+' '+IMAGE_SCRIPT+'\\Install_Opics.bat'
				  
            }
                
            }

        }
        
        
        stage('\u27A1 Configure Opics'){
		
			agent {
					label{
					 label 'BLRCSWOPICS0117.misys.global.ad'
					  customWorkspace FS_ROOT
					}
			}

            steps {
				  
				 script{ 
				  
				  bat 'docker exec '+CONTAINER_NAME+' '+IMAGE_SCRIPT+'\\Configure_Opics.bat'
				  
            }
                
            }

        }
        stage('\u27A1 Commit Image'){
		
			agent {
					label{
					 label 'BLRCSWOPICS0117.misys.global.ad'
					  customWorkspace FS_ROOT
					}
			}

            steps {
				  
				 script{ 
				     
				 bat 'docker stop '+CONTAINER_NAME
				 bat 'docker commit '+CONTAINER_NAME+' '+IMAGE_NAME
				 
				 bat 'docker stop '+CONTAINER_NAME
				 bat 'docker rm '+CONTAINER_NAME
				  
            }
                
            }

        }
        
        stage('\u27A1 Run Image'){
		
			agent {
					label{
					 label 'BLRCSWOPICS0117.misys.global.ad'
					 customWorkspace FS_ROOT
					}
			}

            steps {
				  
				 script{ 
				     
				  bat 'docker create -i -h '+DOC_HOSTNAME+' --name '+CONTAINER_NAME+' '+'-m 4g -v D:\\stores:c:\\stores -p 80:80 -p 8080:8080 -p 1433:1433'+' '+IMAGE_NAME
				  bat'docker start '+CONTAINER_NAME
				  
            }
                
            }

        }
        
        
        stage('\u27A1 Test Opics Services'){
		
			agent {
					label{
					 label 'BLRCSWOPICS0117.misys.global.ad'
					  customWorkspace FS_ROOT
					}
			}

            steps {
				  
				 script{ 
				    
				      timeout(time:60, unit:'SECONDS'){
				          
				      }
				        bat 'cd D:\\stores\\scripts'
						bat 'curl http://'+DOC_HOSTNAME
						bat 'curl http://'+DOC_HOSTNAME+':8161'
						bat 'curl http://'+DOC_HOSTNAME+'//ServiceInterfaceV4.0//ServiceInterfaceWCF.svc'
						bat 'curl http://'+DOC_HOSTNAME+'//ApplicationServiceV4.0//MBRE.svc'
				  
				  
            }
                
            }

        }
        
            
        stage('\u27A1 Push Image'){
		
			agent {
					label{
					 label 'BLRCSWOPICS0117.misys.global.ad'
					 customWorkspace FS_ROOT
					}
			}

            steps {
				  
				 script{ 
				 
					  bat 'docker stop '+CONTAINER_NAME
				      bat 'docker rm '+CONTAINER_NAME
				    
				      bat 'docker login registry.misys.global.ad -u opicscmre -p Earth@2012'
				      bat 'docker push '+IMAGE_NAME
				      bat 'docker rmi -f '+IMAGE_NAME
				  
            }
                
            }

        }
        
        stage('Email Community'){
            agent {
					label{
					 label 'BLRCSWOPICS0077'
					 
					}
			}
            
			steps{
			        script{
			            echo 'sending mail'
			            bat 'python \\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\email_docker.py  --Image_Name ' +IMAGE_NAME+' --Docker_hostname '+DOC_HOSTNAME+' --Release '+RELEASE
			        }
			      
			}
            
            
        }
        
        
    }
}