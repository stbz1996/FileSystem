package filesystem;

import java.util.ArrayList;

public class GeneralFileManager 
{    
    private ArrayList<FileManager> file_managers = new ArrayList<>(); 
    private FileManager current_filemanager;

    /* 
    Constructor 
    */
    public GeneralFileManager() {}

    
    /*
    This function create a new virtual disk that is the main directory
    Parameters: 
        int num_sectors: It is the number of sectors of the disk
        int sector_size: It is the size in KB of each sector 
        String directory_name: It is the name of the virtual disk
    */
    public void addFileSystem(int num_sectors, int sector_size, String directory_name){
        if (current_filemanager == null) {
            current_filemanager = new FileManager(directory_name, num_sectors, sector_size);
            System.out.println("The new virtual disc was created successfully");
        }
        else{
            System.out.println("There is a virtual disk now");
        }
    }
    
    
    public void set_current_filemanager(String fileManager) 
    {
        for(FileManager root : file_managers){
            if(root.getInitial_directory().getDirectory_name() == fileManager){
                current_filemanager = root;
            }
        }
    }
    
    
    public FileManager get_current_fileManager() 
    {
        return current_filemanager;
    }   
    
    
    public boolean search_directory(String name)
    {
        if(current_filemanager.getCurrent_directory().search_directory(name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    public void add_directory(String name)
    {
        Directory directory = current_filemanager.getCurrent_directory().create_directory(name, current_filemanager.getCurrent_directory());
        current_filemanager.setCurrent_directory(directory);
    }
    
    
    public String getActual_path()
    {
        return current_filemanager.getPath(current_filemanager.getCurrent_directory());
    }
    
    
    public Boolean changeDirectory(String path){
        
        return current_filemanager.changeDirectory(path);
    }
    
    
    public String display_files_and_directories()
    {
        return current_filemanager.display_files_and_directories();
        
    }
}
