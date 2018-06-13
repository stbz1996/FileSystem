package filesystem;

import java.util.ArrayList;

public class GeneralFileManager 
{    
    private ArrayList<FileManager> file_managers = new ArrayList<>(); 
    private FileManager current_filemanager;

    public GeneralFileManager() {}

    public void addFileSystem(int num_sectors, int sector_size, String directory_name){
        boolean exist = false;
        for(FileManager root : file_managers){
            if(root.getInitial_directory().getDirectory_name() == directory_name){
                exist = true;
            }
        }
        if(exist){
            System.out.println("The virtual disc joined already exist");
        }else{
            current_filemanager = new FileManager(directory_name, num_sectors, sector_size);
            file_managers.add(current_filemanager);
            System.out.println("The new virtual disc was created successfully");
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
}
