package filesystem;

import java.io.IOException;
import java.io.RandomAccessFile;
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
                    num_sectors = Integer.parseInt(action_received.split(" ")[1]);
                    sector_size = Integer.parseInt(action_received.split(" ")[2]);
                    name = action_received.split(" ")[3];
                    ret = general.addFileSystem(num_sectors, sector_size, name);
                    if (ret == 0) {
                        System.out.println("#################################");
                        System.out.println("## There is a virtual disk now ##");
                        System.out.println("#################################");
                    }
                    break;
                    
                    
                case "FLE": // name, extension, content
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
                            if(action.equals("Y"))
                            {
                                System.out.println("Caso 2 pendiente...");
                               //HAY QUE BORRAR EL ARCHIVO DEL FILE SYSTEM Y DEL ARCHIVO (DISCO VIRTUAL) Y CREARLO DE NUEVO
                               //FALTA ESO!
                            } 
                            break;
                            
                        case 1:
                            System.out.println("############################################");
                            System.out.println("## There isn't enough space for this file ##");
                            System.out.println("############################################");
                            break;
                    }
                    break;
 
                    
                case "MKDIR": //  directory name
                    name = action_received.split(" ")[1];
                    ret = general.add_directory(name);
                    switch (ret){
                        case 0:
                            System.out.println("####################################################################################");
                            System.out.println("## There's already a directory with the same name. Do you want to replace it? Y/N ##");
                            System.out.println("####################################################################################");
                            String action = scanner.nextLine();
                            if(action.equals("Y"))
                            {
                                System.out.println("Caso 1 pendiente...");
                                // Borramos el archivo y creamos otro nuevo 
                                // FALTA HACERLO
                            }
                            break;
                    }
                    break;
<<<<<<< HEAD

                    
                case "CHDIR": //path
                    path = action_received.split(" ")[1];
                    flag = general.changeDirectory(path);
                    if(flag == false){
=======
     
                case "CHDIR": //path
                    path = action_received.split(" ")[1];
                    boolean ret1 = general.changeDirectory(path);
                    
                    /*if(){
                        System.out.println(general.getActual_path());
                    }else{
>>>>>>> f09fd9fdbcb9aa6e2b4987f4b109fb868bc48dca
                        System.out.println("The directory joined does not exist.\n");
                    }
                    break;
<<<<<<< HEAD

                    
=======
                
>>>>>>> f09fd9fdbcb9aa6e2b4987f4b109fb868bc48dca
                case "LDIR": // no parameters, list directory content
                    System.out.println(general.display_files_and_directories());
                    break;
                    
<<<<<<< HEAD
                    
                case "MFLE": //file name, new content
=======
                 case "MFLE": //file name, new content
>>>>>>> f09fd9fdbcb9aa6e2b4987f4b109fb868bc48dca
                    name = action_received.split(" ")[1];
                    length = command.length() + name.length();
                    content = action_received.substring(length+2);
                    flag = general.setFileContent(name, content, length);
                    if (flag == false) {
                        System.out.println("The file does not exist");
                    }
                    break;

                    
                case "PPT": //no parameters, display file properties
                    name = action_received.split(" ")[1];
                    System.out.println(general.showFileProperties(name));
                    break;
<<<<<<< HEAD

                    
                    
                    
                case "VIEW": //file name
=======
                
                case "VIEW": //file name, it needs the extension (example.txt)
>>>>>>> f09fd9fdbcb9aa6e2b4987f4b109fb868bc48dca
                    name = action_received.split(" ")[1];
                    ret = general.display_file_content(name);
                    switch (ret)
                    {
                        case 0:
                            System.out.println("File not found");
                            break;
                    }
                    break;

                case "CPY": //use flags to choose
                    switch(action_received.split(" ")[2]){
                        
                        case "-vv": // virtual to virtual path
                            name = action_received.split(" ")[3];
                            new_path = action_received.split(" ")[4];
                            break;

                        case "-rv": //real to virtual path
                            path = action_received.split(" ")[3];
                            new_path = action_received.split(" ")[4];
                            break;

                        case "-vr": //virtual to real path
                            name = action_received.split(" ")[3];
                            new_path = action_received.split(" ")[4];
                            break;
                    }
                    break;

                case "MOV": // file or directory name, new path, new name
                    name = action_received.split(" ")[1];
                    path = action_received.split(" ")[2];
                    rename = action_received.split(" ")[3];
                    break;

                case "REM": // file or directory name
                    name = action_received.split(" ")[1];   
                    break;

                    
                    
                    
                    
                 
                case "TREE": //no parameters, display directory tree
                    System.out.println(general.display_Three());
                    break;

                    
                    
                    
                    
                    
                case "FIND": // file or directory name
                    name = action_received.split(" ")[1];
                    break;

                default:
                    System.out.println("The command typed doesn't exist, type another one");

            } // end switch
        } //end while
    }
}