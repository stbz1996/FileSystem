package filesystem;

public class FileManager
{   
    private Directory initial_directory;
    private Directory current_directory;
    private int num_sectors;
    private int sector_size;


    public FileManager(String initial_directory, int num_sectors, int sector_size) 
    {
        this.initial_directory = new Directory(initial_directory, null);
        this.current_directory = this.initial_directory;
        this.num_sectors = num_sectors;
        this.sector_size = sector_size;
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

    
    public int getSector_size() 
    {
        return sector_size;
    }
    
    public String getPath(Directory directory)
    {
        String path = "";
        while(directory != null)
        {
            path = directory.getDirectory_name() + "\\" + path;
            directory = directory.getDirectory_father();
        }
        return initial_directory.getDirectory_name() + "\\:" + path;
    }
    
    public boolean changeDirectory(String path){
        
        Directory result = initial_directory;
        Directory temp;
        path = path.replace(path.split(":")[0]+":\\", "");
        
        for (String split : path.split("\\")) {
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
    
}
