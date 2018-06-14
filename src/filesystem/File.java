package filesystem;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class File 
{
    private String content;
    private String name;
    private String extension;
    private ArrayList<Integer> sectors;
    private String creation_date;
    private String modification_date;
    private String path;
    private int size_kb;

    
    public File(String content, String name, String extension, ArrayList<Integer> sectors, String path, int size_kb)
    {
        this.content = content;
        this.name = name;
        this.extension = extension;
        this.sectors = sectors;
        this.creation_date = getActual_date(); 
        this.modification_date = getActual_date();
        this.path = path;
        this.size_kb = size_kb;
    }

    File(String outtxt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public String getActual_date()
    {
        Date now = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(now);   
    }
    
    
    public void setContent(String content) 
    {
        this.content = content;
        this.modification_date = getActual_date();
    }

    
    public void setName(String name) 
    {
        this.name = name;
        this.modification_date = getActual_date();
    }

    
    public void setExtension(String extension) 
    {
        this.extension = extension;
        this.modification_date = getActual_date();
    }

    
    public void setModification_date(String modification_date) 
    {
        this.modification_date = modification_date;
    }

    
    public void setPath(String path) 
    {
        this.path = path;
        
    }

    
    public void setSize_kb(int size_kb) 
    {
        this.size_kb = size_kb;
    }

    
    public String getContent() 
    {
        return content;
    }

    
    public String getName() 
    {
        return name;
    }

    
    public String getExtension() 
    {
        return extension;
    }

    
    public String getCreation_date() 
    {
        return creation_date;
    }

    
    public String getModification_date() 
    {
        return modification_date;
    }

    
    public String getPath() 
    {
        return path;
    }

    
    public int getSize_kb() 
    {
        return size_kb;
    } 
    
    
    public ArrayList<Integer> getSectors() 
    {
        return sectors;
    }
}
