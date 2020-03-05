def call(){

	def FS='\\\\blrwinopfilsrv01\\OpicsBUILDS\\NightlyBuilds\\4.0.0.0_trunk'

	def file_server_loc =latestFolder(FS)

	print(file_server_loc)

	def FS_PATH=FS+'\\'+file_server_loc+'\\OPXSVR\\'

	print(FS_PATH)


}

def latestFolder(fs_path){

		def files=[]

		(fs_path as File).eachFile groovy.io.FileType.DIRECTORIES, {
			files << it
		}
		def result = files.sort{ a,b -> b.lastModified() <=> a.lastModified() }*.name

		def latestBuild =result[0]
		
		return latestBuild

}



