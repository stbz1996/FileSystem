
package filesystem;

/*
 * Ruth Ulloa B
 */
public class FileManager {
    
    private Directory initial_directory;
    private Directory current_directory;
    private int kb_size;

    public FileManager(Directory initial_directory, Directory current_directory, int kb_size) {
        this.initial_directory = initial_directory;
        this.current_directory = current_directory;
        this.kb_size = kb_size;
    }

    public boolean change_current_directory(String path_directory)
    {
        return true;
    }
    
    public String display_files_and_directories()
    {
        return null;
    }
    
    public Directory get_initial_directory() {
        return initial_directory;
    }

    public void set_initial_directory(Directory initial_directory) {
        this.initial_directory = initial_directory;
    }

    public Directory get_current_directory() {
        return current_directory;
    }

    public void set_current_directory(Directory current_directory) {
        this.current_directory = current_directory;
    }

    public int get_kb_size() {
        return kb_size;
    }

    public void set_kb_size(int kb_size) {
        this.kb_size = kb_size;
    }
       
}
