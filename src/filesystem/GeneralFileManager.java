package filesystem;

public class GeneralFileManager 
{    
    private FileManager fileManager;

    public GeneralFileManager() 
    {
    }

    
    public Boolean create_fileManager()
    {
        return true;
    }
    
    
    public void set_manager(FileManager new_fileManager) 
    {
        this.fileManager = new_fileManager;
    }
    
    
    public FileManager get_fileManager() 
    {
        return fileManager;
    }    
}
