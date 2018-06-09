package filesystem;

public class GeneralFileManager 
{    
    private FileManager fileManager;

    public GeneralFileManager(FileManager fileManager) 
    {
        this.fileManager = fileManager;
    }

    
    public void set_filemanager(FileManager new_fileManager) 
    {
        this.fileManager = new_fileManager;
    }
    
    
    public FileManager get_fileManager() 
    {
        return fileManager;
    }    
}
