import java.io.File;
import java.io.FileOutputStream;

public class FileUtils {

	
	 public static void write(String text)
	  {
		  FileOutputStream fos;
	      File file;

	      try{
		      file=new File("C:\\Users\\aleyn\\Desktop\\alyn.html");
		      fos=new FileOutputStream(file);
		      byte[] bytes=text.getBytes();
	
		      fos.write(bytes);
		      fos.flush();
	      }
	      catch (Exception ex)
	      {
	    	  ex.printStackTrace();
	      }

	  }
}
