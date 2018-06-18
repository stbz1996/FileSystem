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

    
    public File create_file(String content, String name, String extension, ArrayList<Integer> sectors, String path, int size_kb)
    {
        File file = new File(content, name, extension, sectors, path, size_kb);
        files.add(file);
        return file;
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
    

    public Directory remove_directory(String name)
    {
        Directory directory_removed;
        for(Directory directory : directories)
        {
            if(directory.getDirectory_name().equals(name))
            {
               directory_removed = directory;
               directories.remove(directory);
               return directory_removed;
            }
        }
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
    
    
    public File getFile(String name)
    {
        
        for (File file : files) 
        {
            if (file.getName().equals(name)) 
            {
                return file;
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
        File: Everithing is ok
        null: Something is wrong 
    */
    public File setFileContent(String name, String content, int sizekb)
    {
        for (File file : files) 
        {
            if (file.getName().equals(name)) 
            {
                file.setContent(content);
                file.setSize_kb(sizekb);
                file.setModification_date();
                return file;
            }
        }
        return null;
    }
    
    
    /*
    This function shows the file propeties
    Parameters
        String name: It is the name of the file
    Returns
        String with the properties of the file 
    */
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
    
    
    public String getDirectoryTree(int sublevel)
    {
        String content = "";
        for(Directory directory : directories)
        {
            content += atachTabLevel(sublevel) + "|__" + directory.getDirectory_name() + "\n";
            content += directory.getDirectoryTree(sublevel + 1);
        }
        
        for(File file : files){
            content += atachTabLevel(sublevel) + "|__" + file.getName()+ "."+ file.getExtension() + "\n";
            
        }
        
        return  content;
    }
    
    private String atachTabLevel(int sublevel){
        String tabs = "";
        
        for(int i = 0 ; i < sublevel; i++){
            tabs+= "   ";
        }
            
        return tabs;
    }
    

    public String get_path(){
        String path = "";
        Directory directory = this;
        
        while(directory.getDirectory_father() != null)
        {
            path = directory.getDirectory_name() + "\\" + path;
            directory = directory.getDirectory_father();
        }
        return path;
    }

    public String list_files_by_name(String name)
    {
        String content = "";
        if (directory_name.equals(name)) {
            // Falta tomar el path del directorio 
            content += get_path() + "\n";
        }
        for(Directory directory : directories){
            content += directory.list_files_by_name(name);
        }
        for(File file : files){
            if(file.getName().equals(name)){
                content += file.getPath() + "\n";
            }
        }
        return content;
    }
    
    public ArrayList<Integer> remove_file(String name)
    {
        for (File file : files) 
        {
            if (file.getName().equals(name)) 
            {
                files.remove(file);
                return file.getSectors();
            }
        }
        return null;
    }
    
    public ArrayList<Integer> clean_directory(){
         ArrayList<Integer> index = new ArrayList<>();
        
        for(File file : files){
            index.addAll(file.getSectors());
        }
        
        for(Directory directory : directories){
            index.addAll(directory.clean_directory());
        }
        return index;
    }
    
}
