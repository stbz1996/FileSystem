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
        this.directory_father = directory_father;
        this.directories = new ArrayList<Directory>();
        this.files = new ArrayList<File>();
    }

    
    public void create_file(String content, String name, String extension, ArrayList<Integer> sectors, String path, int size_kb)
    {
        File file = new File(content, name, extension, sectors, path, size_kb);
        files.add(file);
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
    
    public boolean search_file(String name)
    {
        for (File file : files) 
        {
            if (file.getName().equals(name)) 
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
    
    public Directory getDirectory(String name){
        
        for (Directory directory : directories) 
        {
            if (directory.getDirectory_name().equals(name)) 
            {
                return directory;
            }
        }
        return null;
        
    }

    
    public String getDirectoryContent()
    {
        String content = "Directories:";
        
        for(Directory directory : directories){
            content += "\n\t" + directory.getDirectory_name();
        }
        
        content += "\nFiles:";
        
        for(File file : files){
            content += "\n\t" + file.getName();
        }
        
        return content + "\n";
    }
    
    
    
    
    
    
    
    
    /*
    This function changed the file content and size
    Paramethers:
        String name: It is the name of the file.
        String content: It is the new content of the file.
        int sizekb: It is the size in KB of the nex content
    Returns:
        True: Everithing is ok
        false: Something is wrong 
    */
    public boolean setFileContent(String name, String content, int sizekb)
    {
        for (File file : files) 
        {
            if (file.getName().equals(name)) 
            {
                file.setContent(content);
                file.setSize_kb(sizekb);
                return true;
            }
        }
        return false;
    }
    
    
    
    public String showFileProperties(String name)
    {
        String properties = "";
        for (File file : files) 
        {
            if (file.getName().equals(name)) 
            {
                properties += "     Name:                   "+ file.getName() + "\n";
                properties += "     Extension:              " + file.getExtension() + "\n";
                properties += "     Creation date:          " + file.getCreation_date().toString()+ "\n";
                properties += "     Last modification date: " + file.getModification_date().toString()+ "\n";
                properties += "     Size in KB:             " + file.getSize_kb()+ "\n";
            }
        }
        return properties;
    }
    
    
    
    
    
    public String getDirectoryTree()
    {
        String content = " - " + getDirectory_name() + "\n";
        
        for(Directory directory : directories)
        {
            
            content += " - " + directory.getDirectoryTree();
        }
        /*
        content += "\nFiles:";
        
        for(File file : files){
            content += "\n\t" + file.getName();
        }
        */
        return content + "\n";
    }
}
