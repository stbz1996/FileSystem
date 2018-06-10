package filesystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        GeneralFileManager general = new GeneralFileManager();
        
        Scanner scanner = new Scanner(System.in);
        String action_received; 
        String command;
        
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+                           WELCOME                              +");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("--------------------> Available Command List <--------------------\n");
        System.out.println("    1) CRT: Create a new virtual disc ");
        System.out.println("    2) FLE: Create a new file "); 
        System.out.println("    3) MKDIR: Create a new directory ");
        System.out.println("    4) CHDIR: Change current directory ");
        System.out.println("    5) LDIR: List directory content");
        System.out.println("    6) MFLE: Change file content");
        System.out.println("    7) PPT: Display file properties");
        System.out.println("    8) VIEW: Display file content");
        System.out.println("    9) CPY: Copy file or directory");
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
        
               
        while(true){
             action_received = scanner.nextLine();
            command = action_received.split(" ")[0];
            
            switch (command){

                case "CRT": // num sectors, sector size, name
                    num_sectors = Integer.parseInt(action_received.split(" ")[1]);
                    sector_size = Integer.parseInt(action_received.split(" ")[2]);
                    name = action_received.split(" ")[3];

                    general.addFileSystem(num_sectors, sector_size, name);

                case "FLE": // name, extension, content
                    name = action_received.split(" ")[1];
                    extension = action_received.split(" ")[2];
                    length = command.length() + name.length() + extension.length();
                    content = action_received.substring(length+3);

                case "MKDIR": //  directory name
                    name = action_received.split(" ")[1];

                case "CHDIR": //path
                    path = action_received.split(" ")[1];

                case "LDIR": // no parameters, list directory content

                case "MFLE": //file name, new content
                    name = action_received.split(" ")[1];
                    length = command.length() + name.length();
                    content = action_received.substring(length+2);

                case "PPT": //no parameters, display file properties

                case "VIEW": //file name
                    name = action_received.split(" ")[1];

                case "CPY": //use flags to choose

                    switch(action_received.split(" ")[2]){
                        
                        case "-vv": // virtual to virtual path
                            name = action_received.split(" ")[3];
                            new_path = action_received.split(" ")[4];

                        case "-rv": //real to virtual path
                            path = action_received.split(" ")[3];
                            new_path = action_received.split(" ")[4];

                        case "-vr": //virtual to real path
                            name = action_received.split(" ")[3];
                            new_path = action_received.split(" ")[4];
                    }

                case "MOV": // file or directory name, new path, new name
                    name = action_received.split(" ")[1];
                    path = action_received.split(" ")[2];
                    rename = action_received.split(" ")[3];

                case "REM": // file or directory name
                    name = action_received.split(" ")[1];                

                case "TREE": //no parameters, display directory tree

                case "FIND": // file or directory name
                    name = action_received.split(" ")[1];

                default:
                    System.out.println("The command typed doesn't exist, type another one");

            } // end switch
        } //end while
    }
}