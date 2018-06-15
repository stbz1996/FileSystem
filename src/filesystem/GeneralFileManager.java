package filesystem;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
    Returns:
        0: There is a virtual disk now
        1: The virtual disc was created successfully
    */
    public int addFileSystem(int num_sectors, int sector_size, String directory_name){
        if (current_filemanager == null) {
            current_filemanager = new FileManager(directory_name, num_sectors, sector_size);
            return 1;
        }
        else{
            return 0;
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
       

    public boolean search_file(String name)
    {
        if(current_filemanager.getCurrent_directory().search_file(name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /*
    This function add a new directory in the direction that the system is located
    Paramethers:
        String name: is the name of the directory
    Returns:
        0: There's already a directory with the same name
        1: Directory created successfully
    */
    public int add_directory(String name)
    {
        if(search_directory(name))
        {
            return 0;
        }
        else
        {
            Directory directory = current_filemanager.getCurrent_directory().create_directory(name, current_filemanager.getCurrent_directory());
            current_filemanager.setCurrent_directory(directory);
            return 1;
        }
    }
    
    
    /*
    This function add a new file in the current directory 
    Paramethers:
        String content: It is the file content 
        String name: It is the file name
        String extension: It is the file extention
        int size_kb: It is the size in KB of the file
    Returns:
        0: Means "There's already a file with the same name."
        1: Means "There isn't enough space for this file"
        2: Means "Directory created successfully"
    */
    public int add_file(String content, String name, String extension, int size_kb) throws IOException
    {
        if(search_file(name))
        {
            return 0;
        }
        else
        {
            float cant_sectors = (float) size_kb / current_filemanager.getSector_size();  //number of sectors needed for this file
            if((int) Math.ceil(cant_sectors) > current_filemanager.getFree_sectors())
            {
                return 1;
            }
            else
            {
                String path = getActual_path() + "\\" + name;
                ArrayList<Integer> sectors = write_in_virtual_disk(path,(int) Math.ceil(cant_sectors));
                current_filemanager.getCurrent_directory().create_file(content, name, extension, sectors, path, size_kb);
                return 2;
            }
        }
    }
    
   
    
    
    
    
    
    
    public ArrayList<Integer> write_in_virtual_disk(String path, int cant_sectors) throws FileNotFoundException, IOException
    {
        FileOutputStream file_virtual_disk = new FileOutputStream("virtual_disk.txt");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(file_virtual_disk));
        
        ArrayList<Boolean> virtual_disk = current_filemanager.getVirtual_disk();
        ArrayList<Integer> sectors = new ArrayList<>();
        
        int index = 0;
        while(index < virtual_disk.size() && cant_sectors > 0)
        {
            writer.newLine();
            if (virtual_disk.get(index) == false)
            {
                writer.write(path);
                writer.newLine();
                cant_sectors--;
                virtual_disk.set(index, Boolean.TRUE);
                sectors.add(index);
            }
            index++;
        }
        writer.close();
        file_virtual_disk.close();
        current_filemanager.setVirtual_disk(virtual_disk);
        return sectors;
    }
    
    
    public String getActual_path()
    {
        return current_filemanager.getPath(current_filemanager.getCurrent_directory());
    }
    
    
    public Boolean changeDirectory(String path)
    {
        return current_filemanager.changeDirectory(path);
    }
    
    
    public String display_files_and_directories()
    {
        return current_filemanager.display_files_and_directories();    
    }
}