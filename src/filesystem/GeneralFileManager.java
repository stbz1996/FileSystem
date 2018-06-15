package filesystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
    public int addFileSystem(int num_sectors, int sector_size, String directory_name) throws IOException{
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
    This function displays the file content
    Paramethers:
        String name: It is the file name
    Returns:
        0: Means "There's no a file with that name."
        1: Means "The content was displayed successfully"
    */
    public int display_file_content(String file_name)
    {
        if(search_file(file_name))
        {
            System.out.println(current_filemanager.getCurrent_directory().getFile(file_name).getContent());
            return 1;
        }
        else
        {
            return 0;
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
        2: Means "File created successfully"
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
                String path = getActual_path() + "\\" + name + "." + extension + " Content: " + content + " Size:" + size_kb;
                ArrayList<Integer> sectors = write_in_virtual_disk(path, (int) Math.ceil(cant_sectors));
                File file = current_filemanager.getCurrent_directory().create_file(content, name, extension, sectors, path, size_kb);
                current_filemanager.setFree_sectors(current_filemanager.getFree_sectors() - (int) Math.ceil(cant_sectors));
                return 2;
            }
        }
    }
    
    
    /*
    This function add a new file in the virtual disk
    Paramethers:
        String path: It is the file path
        int cant sectors: number of sectors that the file needs
    Returns:
        Array<integer> sectors: It contains the index sectors for this file
     */
    public ArrayList<Integer> write_in_virtual_disk(String path, int cant_sectors) throws FileNotFoundException, IOException {
        ArrayList<Boolean> virtual_disk = current_filemanager.getVirtual_disk();
        ArrayList<String> file_virtual_disk = read_virtual_disk();
        ArrayList<Integer> sectors = new ArrayList<>();

        int index = 0;
        while (index < virtual_disk.size() && cant_sectors > 0) {
            if (file_virtual_disk.get(index).equals("-")) {
                file_virtual_disk.set(index, path);
                virtual_disk.set(index, Boolean.TRUE);
                cant_sectors--;
            }
            index++;
        }
        write_virtual_disk(file_virtual_disk);
        current_filemanager.setVirtual_disk(virtual_disk);
        return sectors;
    }

    
    /*
    This function returns an arraylist with the content of the virtual disk
     */
    public ArrayList<String> read_virtual_disk() throws FileNotFoundException, IOException {
        ArrayList<String> virtual_disk = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("virtual_disk.txt"));
            String line = reader.readLine();
            while (line != null) {
                virtual_disk.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return virtual_disk;
    }

    /*
    This function writes in the virtual disk the arraylist content
     */
    public void write_virtual_disk(ArrayList<String> virtual_disk) throws IOException {
        FileOutputStream file_virtual_disk = new FileOutputStream("virtual_disk.txt");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(file_virtual_disk));

        for (String line : virtual_disk) {
            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }
    
    /*
    This function moves a file/directory to another directory
    Paramethers:
        String name: It is the name of the file/directory
        String path: It is the new path
        String rename: It is the new name for this file/directory
    Returns:
        0: Means "There's already a file with the same name."
        1: Means "There isn't enough space for this file"
        2: Means "File/Directory not found"
        3: Means "File moved successfully"
        4: Means "Directory moved successfully"
        5: Means "Path not found"
     */
    public int move_file_or_directory(String name, String path, String rename)
    {
        if(search_directory(name))
        {
            Directory directory = current_filemanager.getCurrent_directory().remove_directory(name);
            if(changeDirectory(path))
            {
                current_filemanager.getCurrent_directory().create_directory(name, current_filemanager.getCurrent_directory());
                //add files and directories
                return 4;
            }
            else
            {
                return 5;
            }
        }
        else if(search_file(name))
        {
            return 3;
        }
        else
        {
            return 2;
        }
    }
    
    
    public String getActual_path()
    {
        return current_filemanager.getPath(current_filemanager.getCurrent_directory());
    }
    
    /*
    This function change the current directory of the file sistem. 
    Paramethers:
        String path: It is the path of the new directory
    Returns
        0: The directory does not exist
        1: The directory exist
    */
    public Boolean changeDirectory(String path)
    {
        return current_filemanager.changeDirectory(path);
    }
    
    
    public String display_files_and_directories()
    {
        return current_filemanager.display_files_and_directories();    
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
    public boolean setFileContent(String fileName, String content, int sizeKB)
    {
        boolean flag = current_filemanager.getCurrent_directory().setFileContent(fileName, content, sizeKB);
        return flag;
    }
    
    
    /*
    This function show the properties of the file
    Paramethers:
        String name: It is the name of the file to search
    Returns:
        String: It is the properties of the file
    */
    public String showFileProperties(String name)
    {
        return current_filemanager.getCurrent_directory().showFileProperties(name); 
    }


    public String display_Three()
    {
        return current_filemanager.display_Three();    
    }
}