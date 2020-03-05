def call(Map param_pipleine){

	pipeline{
		
		 agent none;
	 
		stages {

			stage('\u27A1 Trunk_WorkArea_Update') {
			
				agent {
						label{
						 label param_pipleine.BM_build
						 customWorkspace param_pipleine.GIT_WA
						}
				}

				steps {
							Checkout(param_pipleine.Branch)
						}
						
				

			}
			stage('\u27A1 Compilaton'){
			
			
			agent {
					label{
					 label param_pipleine.BM_build
					 customWorkspace param_pipleine.GIT_WA
					}
			}
			
			steps{
				script{
					switch (param_pipleine.Branch){	
						case 'master':
								if(param_pipleine.Branch =='master'){
									param_pipleine.Branch ='trunk'
								}
								map_networkDrive('done!!')
								Build_GIT_nightly(param_pipleine.Branch)
								break;
						case 'Rel4.0':
								stampVersion_4x(param_pipleine.Branch)
								Build_GIT_nightly(param_pipleine.Branch)
								break;
						case 'Rel4.1':
								Build_GIT_nightly(param_pipleine.Branch)
								break;
						case 'Rel4.2':
								Build_GIT_nightly(param_pipleine.Branch)
								break;
						case 'Rel4.3':
								map_networkDrive('done!!')
								Build_GIT_nightly(param_pipleine.Branch)
								break;
						case 'Rel4.4':
								map_networkDrive('done!!')
								nugetRestoration('done!!')
								Build_GIT_nightly(param_pipleine.Branch)
						default:
								print('The Branch value is Unknown')
								break;
					}
					
				}
				
			}
		
		}
		stage('\u27A1 Sonar Status'){
			
			agent {
					label{
					 label param_pipleine.BM_build
					 customWorkspace param_pipleine.GIT_WA
					}
			}
			
			steps{
				script{
					if(param_pipleine.Branch =='master'){
						SonarAnalysisReport(param_pipleine.Branch)
					}else{
						print("Sonar Analysis has not configured for"+param_pipleine.Branch+"this codeline")
					}
				}
				
			}
		
		}
		stage('\u27A1 Build Status '){
			
			steps{
			    
			    script{
			        echo "currentResult:${currentBuild.currentResult}"
			    }
				
			}
		
		}	
		stage('\u27A1 SetupCreation'){
			agent {
				label{
					 label param_pipleine.BM_setup	
				}
			}
			steps {
				script{
						if(param_pipleine.Branch =='master'){
							param_pipleine.Branch =='trunk'
						}
			            SetupCreation(param_pipleine.Branch,param_pipleine.BM_build);
			            
				}
            }
		
		}
		stage('\u27A1 DBScriptExecution'){
			agent {
				label{
					 label param_pipleine.BM_setup	
				}
			}
			steps {
				script{
					if(param_pipleine.Branch =='master'){
						param_pipleine.Branch =='trunk'
					}
					DBScriptExecution(param_pipleine.Branch)
				}
				
            }
		
			
		}
	}
		
		
	post {
			failure {
				script{
					def BuildStatus =currentBuild.currentResult
					emailNotification_failure(BuildStatus)   
				}
				
			 }
			success {
				script{
					def BuildStatus =currentBuild.currentResult
					emailNotification_backtoNormal(BuildStatus)
				}
				
			}
	  }

	}
}