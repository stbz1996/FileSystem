
package filesystem;

/*
 * Ruth Ulloa B
 */
public class GeneralFileManager {
    
    private FileManager manager;

    public GeneralFileManager() 
    {
    }

    public Boolean create_manager()
    {
        return true;
    }
    
    public FileManager get_manager() {
        return manager;
    }

    public void set_manager(FileManager manager) {
        this.manager = manager;
    }
    
    
    
    
    
}
