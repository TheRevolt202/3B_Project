package javax.swing.filechooser;
//абстрактный вспомогательый файл
import java.io.File;

public abstract class FileFilter {
    
    public FileFilter() {
        throw new RuntimeException("Complited code");        
    }
    
    public abstract boolean accept(File f);
    
    public abstract String getDescription();
}