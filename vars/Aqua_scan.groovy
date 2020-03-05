#!Groovy
/*

This script helps us to build Every day nightly build on codeline basis

*/


def call(String reg_type,String Image_tag){

		if(reg_type == 'OP'){
			registry ='registry.misys.global.ad/fusion-capital-market/opicsbuilds'
			doc_image =registry+':'+Image_tag
		}else if(reg_type =='TO'){
			 registry ='registry.misys.global.ad/fusion-capital-market/treasury-opics'
			 doc_image =registry+':'+Image_tag
		}else{
			registry ='registry.misys.global.ad/fcopics/'
			doc_image =registry+Image_tag
		}
		def(tag1,tag2)=Image_tag.split(':')
		
		bat '\\\\blrwinopfilsrv01\\Opics_Buildtools\\GitBuildConfig\\Rel4.x\\aqua_scan_docker.bat'+" "+doc_image+" "+tag1
	
}