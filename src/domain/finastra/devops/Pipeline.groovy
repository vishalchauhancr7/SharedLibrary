#!Groovy
package domain.finastra.devops

class Pipeline implements Serializable{

	def steps

     Pipeline(steps) {
         this.steps = steps
     }
	 def log(msg) {
         spit(msg, '\033[34m')
     }
	
	def BuildNightly(String Branch){
			print(Branch)
			print('Nagendra is in buildNightly')
	}
	def SetupCreation(String Branch){
			print(Branch)
			print("Nagendra is in SetupCreation")
	}
}



				
