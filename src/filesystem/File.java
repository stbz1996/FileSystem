package filesystem;
import java.util.ArrayList;
import java.util.Date;

public class File 
{
    private String content;
    private String name;
    private String extension;
    private ArrayList<Integer> sectors;
    private Date creation_date;
    private Date modification_date;
    private String path;
    private int size_kb;

    
    public File(String content, String name, String extension, ArrayList<Integer> sectors, String path, int size_kb)
    {
        this.content = content;
        this.name = name;
        this.extension = extension;
        this.sectors = sectors;
        //this.creation_date = ; 
        //this.modification_date = modification_date;
        this.path = path;
        this.size_kb = size_kb;
    }

    
    public void setContent(String content) 
    {
        this.content = content;
    }

    
    public void setName(String name) 
    {
        this.name = name;
    }

    
    public void setExtension(String extension) 
    {
        this.extension = extension;
    }

    
    public void setModification_date(Date modification_date) 
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

    
    public Date getCreation_date() 
    {
        return creation_date;
    }

    
    public Date getModification_date() 
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
