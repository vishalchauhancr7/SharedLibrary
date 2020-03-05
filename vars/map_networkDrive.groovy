#!Groovy
/*

Map netwrok drive to the buildmachine

*/

def call(String flag){

				bat 'DATE /T'
				bat 'TIME /T'

				if(flag == 'Nightly'){

					bat 'net use S: /delete /yes'
					//bat 'net use S: \\\\blrwinopfilsrv01\\opicsbuilds'
					bat 'net use S: \\\\blrwinopfilsrv01\\opicsbuilds /USER:misysroot\build M!sys08@ /PERSISTENT:NO'

				}else if(flag == 'CI' || flag == 'PR'){

					bat 'net use T: /delete /yes'
					bat 'net use T: \\\\blrwinopfilsrv01\\Opics_Buildtools'
					bat 'CD /D T:\\GitBuildConfig\\Rel4.x\\40CI'
				}else{

					echo 'Parameter is missing !!!!, Pass the Build Type!!!'
					
				}
				
					
}