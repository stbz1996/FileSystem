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
    
    
    public void add_directory(String name)
    {
        Directory directory = current_filemanager.getCurrent_directory().create_directory(name, current_filemanager.getCurrent_directory());
        //current_filemanager.setCurrent_directory(directory);
    }
    
    
    public void add_file(String content, String name, String extension, int size_kb) throws IOException
    {
        float cant_sectors = (float) size_kb / current_filemanager.getSector_size();  //number of sectors needed for this file
        if((int) Math.ceil(cant_sectors) > current_filemanager.getFree_sectors())
        {
            System.out.println("There isn't enough space for this file.");
        }
        else
        {
            String path = getActual_path() + "\\" + name;
            ArrayList<Integer> sectors = write_in_virtual_disk(path,(int) Math.ceil(cant_sectors));
            current_filemanager.getCurrent_directory().create_file(content, name, extension, sectors, path, size_kb);
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
    
    public Boolean changeDirectory(String path){
        
        return current_filemanager.changeDirectory(path);
    }
    
    public String display_files_and_directories()
    {
        return current_filemanager.display_files_and_directories();
        
    }
}
