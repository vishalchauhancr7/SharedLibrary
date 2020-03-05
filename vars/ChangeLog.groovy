#!Groovy
/*

This script helps us to build Every day CI build on codeline basis

*/



def call(String Branch,int BUILD_ID){

	

	print('We are renaming the Changelog.xml file since it has been created with Zero KB\n')
	
	
	print(bat 'ren \\\\BLRCSWOPXVM0032\\Jenkins\\jobs\\JPL_Rel4.0_CI\\builds\\'+BUILD_ID'+\\changelog0.xml changelog.xml')
	
}

