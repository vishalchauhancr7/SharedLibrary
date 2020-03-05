#!Groovy
/*

This script helps us to do code signing on nightly build on codeline basis

*/


def call(String Branch){

	String Version;
	if(Branch == 'trunk'){
		Version = '4.0.0.0'
	}else{
		Version =Branch[3..5].concat(".0.0")
	}

			bat 'perl \\\\blrwinopfilsrv01\\OpicsCMRE_Work\\CMRE-Tools\\43-Code-Sign\\code_sign_v1.pl "\\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\'+Version+"_"+Branch		

}