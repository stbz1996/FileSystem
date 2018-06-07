package filesystem;

import java.util.ArrayList;

/*
 * Ruth Ulloa B
 */
public class Directory {
    
    private String directory_name;
    private File current_file;
    private ArrayList<Directory> directories;
    private ArrayList<File> files;

    public Directory(String directory_name) 
    {
        this.directory_name = directory_name;
        this.directories = new ArrayList<Directory>();
        this.files = new ArrayList<File>();
    }
    
    public boolean create_file(String content, String extension, String name)
    {
        return true;
    }
    
    public boolean create_directory(String name)
    {
        return true;
    }
    
    public String display_files_and_directories()
    {
        return null;
    }
    
    public Boolean move_file(File file, Directory new_directory)
    {
        return true;
    }
    
    public Boolean change_file_content(String new_content)
    {
        return true;
    }
    
    public Boolean remove_file_or_directory()
    {
        return true;
    }
    
    public String search_file(String name)
    {
        return null;
    }
    
    public String get_directory_name() 
    {
        return directory_name;
    }

    public void set_directory_name(String directory_name) 
    {
        this.directory_name = directory_name;
    }

    public File get_current_file() 
    {
        return current_file;
    }

    public void set_current_file(File current_file) 
    {
        this.current_file = current_file;
    }

    
    
    
    
    
}
