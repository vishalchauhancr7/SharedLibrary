def call(fs_path){

    def files=[]

    (fs_path as File).eachFile groovy.io.FileType.DIRECTORIES, {
        files << it
    }
    def result = files.sort{ a,b -> b.lastModified() <=> a.lastModified() }*.name

    def latestBuild =result[0]

    return latestBuild

}