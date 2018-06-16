package filesystem;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileManager
{   
    private Directory initial_directory;
    private Directory current_directory;
    private int num_sectors;
    private int sector_size;
    private int free_sectors;
    private ArrayList<Boolean> virtual_disk;


    public FileManager(String initial_directory, int num_sectors, int sector_size) throws IOException 
    {
        this.initial_directory = new Directory(initial_directory, null);
        this.current_directory = this.initial_directory;
        this.num_sectors = num_sectors;
        this.sector_size = sector_size;
        this.free_sectors = num_sectors;
        this.virtual_disk = getInitial_disk();
    }
    
    public ArrayList<Boolean> getInitial_disk() throws FileNotFoundException, IOException {
        ArrayList<Boolean> virtual_disk = new ArrayList<>();
        FileOutputStream file_virtual_disk = new FileOutputStream("virtual_disk.txt");
        BufferedWriter writter = new BufferedWriter(new OutputStreamWriter(file_virtual_disk));

        for (int i = 0; i < num_sectors; i++) {
            virtual_disk.add(false);
            writter.write("-");
            writter.newLine();
        }
        writter.close();
        return virtual_disk;
    }
    
    
    public String display_files_and_directories()
    {
        return current_directory.getDirectoryContent();
    }
    
    
    public void setInitial_directory(Directory initial_directory) 
    {
        this.initial_directory = initial_directory;
    }

    
    public void setCurrent_directory(Directory current_directory)
    {
        this.current_directory = current_directory;
    }

    
    public void setSector_size(int sector_size)
    {
        this.sector_size = sector_size;
    }
    
    
    public void setNum_sectors(int num_sectors)
    {
        this.num_sectors = num_sectors;
    }
    
    public void setFree_sectors(int free_sectors)
    {
        this.free_sectors = free_sectors;
    }
    
    public void setVirtual_disk(ArrayList<Boolean> virtual_disk)
    {
        this.virtual_disk = virtual_disk;
    }

   
    public Directory getInitial_directory() 
    {
        return initial_directory;
    }

    
    public Directory getCurrent_directory() 
    {
        return current_directory;
    }

    
    public int getNum_sectors() 
    {
        return num_sectors;
    }

    public int getFree_sectors() 
    {
        return free_sectors;
    }
    
    public int getSector_size() 
    {
        return sector_size;
    }
    
    public ArrayList<Boolean> getVirtual_disk()
    {
        return virtual_disk;
    }
    
    public String getPath(Directory directory)
    {
        String path = "";
        while(directory.getDirectory_father() != null)
        {
            path = directory.getDirectory_name() + "\\" + path;
            directory = directory.getDirectory_father();
        }
        return initial_directory.getDirectory_name() + ":\\" + path;
    }
    
    
    
    public boolean changeDirectory(String path){    
        Directory result = initial_directory;
        Directory temp;
       
        path = path.replace("\\\\", ",");
        path = path.replaceFirst(path.split(":")[0]+":,", "");
        
        for (String split : path.split(",")) {
            temp = result.getDirectory(split);
            if(temp != null){
                result = temp;
            }else{
                return false;
            }
        }  
        current_directory = result;
        return true;
        
    }
    
    
    public String display_Three(int sublevel)
    {
        return initial_directory.getDirectoryTree(sublevel);
    }
    
}
