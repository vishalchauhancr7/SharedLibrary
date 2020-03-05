#!Groovy

def remove(def cont){

		docker stop cont
			
		docker rm cont
		#!Groovy

def call(String product,String Image_tag){
		
		if(product == 'opics-product'){
			
			//sh 'mvn install'
            //sh '/home/micloud/linux-amd64/draft create --pack win-cargo'
            //sh 'docker build -t registry.misys.global.ad/fcopics/backend --build-arg PACKAGE_PATH="./app/target/opics-cargo-server-4.3.3-SNAPSHOT" .'
			
			bat 'cd D:\\GIT_WA\\opics-product'
			bat 'mvn install'
			bat 'draft create --pack opics-backend D:\\GIT_WA\\opics-product '
			bat 'docker build -t registry.misys.global.ad/fcopics/win_backend:'+Image_tag+' --build-arg PACKAGE_PATH="./app/target/opics-cargo-server-4.6.0.0-SNAPSHOT" D:\\GIT_WA\\opics-product'
			
		
		}else if(product == 'opics-product-ui'){
			
			//sh 'npm config set unsafe-perm true'
            //sh 'npm install'
           // sh '/home/micloud/linux-amd64/draft create --pack win-uxp /localdisk/GIT_WA/opicsdraft/opics-product-ui'
           // sh 'docker build -t registry.misys.global.ad/fcopics/frontend --build-arg PACKAGE_PATH="./" .'
			
			bat 'cd D:\\GIT_WA\\opics-product-ui'
			bat 'npm install'
			bat 'draft create --pack opics-frontend  D:\\GIT_WA\\opics-product-ui'
		    bat 'docker build -t registry.misys.global.ad/fcopics/win_frontend:'+Image_tag+' --build-arg PACKAGE_PATH="./opics-product-ui_46/opics-product-ui_46_14May2019" D:\\GIT_WA\\opics-product-ui'
		
		}else{
		
			echo "Do Nothing"
		}
		
}
		
		
}