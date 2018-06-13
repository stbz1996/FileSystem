package filesystem;
import java.util.ArrayList;

public class Directory 
{    
    private String directory_name;
    private File current_file;
    private Directory directory_father;
    private ArrayList<Directory> directories;
    private ArrayList<File> files;

    public Directory(String directory_name, Directory directory_father) 
    {
        this.directory_name = directory_name;
        this.directories = new ArrayList<Directory>();
        this.files = new ArrayList<File>();
    }

    
    public void create_file(String content, String extension, String name)
    {
        
    }
    
    
    public Directory create_directory(String name, Directory directory_father)
    {
        Directory directory = new Directory(name, directory_father);
        directories.add(directory);
        return directory;
    }
    
    
    public String display_files_and_directories()
    {
        return null;
    }
    
    
    public void move_file(File file, Directory new_directory)
    {
     
    }
    
    
    public void change_file_content(String new_content)
    {
        
    }
    
    
    public void remove_file()
    {
        
    }
    
    
    public void remove_directory()
    {
        
    }
    
    
    public String search_file(String name)
    {
        return null;
    }
    
    public boolean search_directory(String name)
    {
        for (Directory directory : directories) 
        {
            if (directory.getDirectory_name().equals(name)) 
            {
                return true;
            }
        }
        return false;
    }
    
    public void setDirectory_name(String directory_name) 
    {
        this.directory_name = directory_name;
    }

    
    public void setCurrent_file(File current_file) 
    {
        this.current_file = current_file;
    }


    public String getDirectory_name() 
    {
        return directory_name;
    }

    
    public File getCurrent_file() 
    {
        return current_file;
    }
    
    public Directory getDirectory_father()
    {
        return directory_father;
    }
    
}
