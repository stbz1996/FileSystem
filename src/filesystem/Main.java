package filesystem;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        
        String sDirectorioTrabajo = System.getProperty("user.dir");
        
        GeneralFileManager general = new GeneralFileManager();
        
        Scanner scanner = new Scanner(System.in);
        String action_received; 
        String command;

        
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+                           WELCOME                              +");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("--------------------> Available Command List <--------------------\n");
        System.out.println("    1)  CRT: Create a new virtual disc ");
        System.out.println("    2)  FLE: Create a new file "); 
        System.out.println("    3)  MKDIR: Create a new directory ");
        System.out.println("    4)  CHDIR: Change current directory ");
        System.out.println("    5)  LDIR: List directory content");
        System.out.println("    6)  MFLE: Change file content");
        System.out.println("    7)  PPT: Display file properties");
        System.out.println("    8)  VIEW: Display file content");
        System.out.println("    9)  CPY: Copy file or directory");
        System.out.println("    10) MOV: Move file or directory ");
        System.out.println("    11) REM: Remove file or directory");
        System.out.println("    12) TREE: Display flie system structure");
        System.out.println("    13) FIND: Look for a file at all file system");
        System.out.println("\n");
        
        String content;
        String name;
        String rename;
        String extension;
        String path;
        String new_path;
        int num_sectors;
        int sector_size;
        int length = 0;
        int ret;
        boolean flag;
               
        while(true){
            if(general.get_current_fileManager()!= null)
            {
                System.out.println("--> " + general.getActual_path());
            }
            action_received = scanner.nextLine();
            command = action_received.split(" ")[0];
            command = command.toUpperCase();
            
            switch (command)
            {
                case "CRT": // num sectors, sector size, name
                    try{
                        num_sectors = Integer.parseInt(action_received.split(" ")[1]);
                        sector_size = Integer.parseInt(action_received.split(" ")[2]);
                        name = action_received.split(" ")[3];
                        ret = general.addFileSystem(num_sectors, sector_size, name);
                        if (ret == 0) {
                            System.out.println("#################################");
                            System.out.println("## There is a virtual disk now ##");
                            System.out.println("#################################");
                        }
                    }
                    catch(Exception e){
                        System.out.println("Incorrect parameters for CRT");
                    }
                    break;
                    
                    
                case "FLE": // name, extension, content
                    try{
                        name = action_received.split(" ")[1];
                        extension = action_received.split(" ")[2];
                        length = command.length() + name.length() + extension.length();
                        content = action_received.substring(length+3);
                        ret = general.add_file(content, name, extension, length);
                        switch (ret)
                        {
                            case 0:
                                System.out.println("###############################################################################");
                                System.out.println("## There's already a file with the same name. Do you want to replace it? Y/N ##");
                                System.out.println("###############################################################################");
                                String action = scanner.nextLine();
                                action = action.toUpperCase();
                                if(action.equals("Y"))
                                {
                                    general.remove_file(name);
                                    ret = general.add_file(content, name, extension, length);
                                    if (ret == 1)
                                    {
                                        System.out.println("There isn't enough space for this file.");
                                    }
                                    else
                                    {
                                        System.out.println("File created successfully.");
                                    }
                                } 
                                break;

                            case 1:
                                System.out.println("############################################");
                                System.out.println("## There isn't enough space for this file ##");
                                System.out.println("############################################");
                                break;
                                
                            case 2:
                                System.out.println("File created successfully.");
                                break;
                        }
                    }
                    catch(Exception e){
                        System.out.println("Incorrect parameters for FLE");
                    }
                    break;
 
                    
                case "MKDIR": //  directory name
                    try{
                        name = action_received.split(" ")[1];
                        ret = general.add_directory(name);
                        switch (ret){
                            case 0:
                                System.out.println("####################################################################################");
                                System.out.println("## There's already a directory with the same name. Do you want to replace it? Y/N ##");
                                System.out.println("####################################################################################");
                                String action = scanner.nextLine();
                                action = action.toUpperCase();
                                if(action.equals("Y"))
                                {
                                    general.remove_directory(name);
                                    general.add_directory(name);
                                    System.out.println("Directory created successfully.");
                                }
                                break;
                            case 1:
                                System.out.println("Directory created successfully.");
                        }
                    }
                    catch(Exception e){
                        System.out.println("Incorrect parameters for MKDIR");
                    }
                    break;

                    
                case "CHDIR": //path
                    try{
                        path = action_received.split(" ")[1];
                        flag = general.changeDirectory(path);
                        if(flag == false)
                        {
                            System.out.println("The directory joined does not exist.\n");
                        }
                    }
                    catch(Exception e){
                         System.out.println("Incorrect parameters for CHDIR");
                    }
                    break;

                    
                case "LDIR": // no parameters, list directory content
                    System.out.println(general.display_files_and_directories());
                    break;
                    

                 case "MFLE": //file name, new content
                     try
                     {
                        name = action_received.split(" ")[1];
                        length = command.length() + name.length();
                        content = action_received.substring(length+2);
                        flag = general.setFileContent(name, content, length);
                        if (flag == false) {
                            System.out.println("The file does not exist");
                        }
                     }
                     catch(Exception e)
                     {
                         System.out.println("Incorrect parameters for MFLE");
                     }
                     break;

                    
                case "PPT": //no parameters, display file properties
                    try
                    {
                      name = action_received.split(" ")[1];
                      System.out.println(general.showFileProperties(name));                      
                    }
                    catch(Exception e)
                    {
                        System.out.println("Incorrect parameters for PPT");
                    }
                    break;

                
                case "VIEW": //file name, it needs the extension (example.txt)
                    try
                    {
                        name = action_received.split(" ")[1];
                        ret = general.display_file_content(name);
                        switch (ret)
                        {
                            case 0:
                                System.out.println("File not found");
                                break;
                        } 
                    }
                    catch(Exception e)
                    {
                        System.out.println("Incorrect parameters for VIEW");
                    }
                    break;

                    
                case "CPY": //use flags to choose
                    switch(action_received.split(" ")[1]){
                        case "-vv": // virtual to virtual path
                            try
                            {
                               System.out.println("entre -vv");
                                name = action_received.split(" ")[2];
                                new_path = action_received.split(" ")[3];
                                ret = general.copy_file_vv(name, new_path);
                                switch (ret) {
                                    case 0:
                                        System.out.println("There's already a file with the same name.");
                                        break;
                                    case 1:
                                        System.out.println("There isn't enough space for this file");
                                        break;
                                    case 2:
                                        System.out.println("File created successfully");
                                        break;
                                    case 3:
                                        System.out.println("File not found");
                                        break;
                                    case 4:
                                        System.out.println("The directory joined does not exist");
                                        break;
                                }
                                break; 
                            }
                            catch(Exception e)
                            {
                                System.out.println("Incorrect parameters for COPY -vv");
                            }
                            break;

                        case "-rv": //real to virtual path
                            try
                            {
                                path = action_received.split(" ")[2];
                                new_path = action_received.split(" ")[3];
                                ret = general.copy_file_rv(path, new_path);
                                switch (ret) {
                                    case 0:
                                        System.out.println("There's already a file with the same name.");
                                        break;

                                    case 1:
                                        System.out.println("There isn't enough space for this file.");
                                        break;

                                    case 2:
                                        System.out.println("File created successfully.");
                                        break;

                                    case 3:
                                        System.out.println("Path not found");
                                        break;
                                }
                            }
                            catch(Exception e)
                            {
                                System.out.println("Incorrect parameters for COPY -rv");
                            }

                            break;

                        case "-vr": //virtual to real path
                            try
                            {
                                name = action_received.split(" ")[2];
                                new_path = action_received.split(" ")[3]; 
                                ret = general.copy_file_vr(name, new_path);
                                switch (ret) {
                                    case 0:
                                        System.out.println("File created successfully.");
                                        break;

                                    case 1:
                                        System.out.println("File not found.");
                                        break;
                                }
                                    
                            }
                            catch(Exception e)
                            {
                                System.out.println("Incorrect parameters for COPY -vr");
                            }
                            break;
                            
                        default:
                            System.out.println("Please use the flags -vv, -rv or -vr");
                    }
                    break;

                case "MOV": // file or directory name, new path, new name
                    name = action_received.split(" ")[1];
                    path = action_received.split(" ")[2];
                    rename = action_received.split(" ")[3];
                    break;

                case "REM": // file or directory name
                    if(action_received.contains("-n")){     //Remove n files
                        String result = general.remove_n_file(action_received.split(" ", 3)[2]);
                        if(result == ""){
                            System.out.println("Files removed succesfully");
                        }else{
                            System.out.println("Cannot find the following files: \n" + result);
                        }
                    }else{
                        name = action_received.split(" ")[1];

                        if(action_received.contains(".")){
                            if(general.remove_file(name.split("\\.")[0])){
                                System.out.println("File removed successfully\n");
                            }else{
                                System.out.println("The file was not found");
                            }
                        }else{
                            if(general.remove_directory(name)){
                                System.out.println("Directory removed succesfully\n");
                            }else{
                                System.out.println("The directory was not found");
                            }
                        }
                    }
                    break;

                case "TREE": //no parameters, display directory tree
                    System.out.println(general.display_Three());
                    break;

                case "FIND": // file or directory name
                    name = action_received.split(" ")[1];
                    System.out.println(general.list_files_by_name(name));
                    break;

               default:
                    System.out.println("The command typed doesn't exist, type another one");

            } // end switch
        } //end while
    }
}