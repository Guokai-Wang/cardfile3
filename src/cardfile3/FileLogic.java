package cardfile3;
import java.io.File;

/* dictionary2 on 20210226: logic n. */
public class FileLogic 
{
	private String fileName;
	private String extension;
	
	public FileLogic(File inFile)
	{
		set(inFile.getName());
	}
	
	public FileLogic(String inFileName)
	{
		set(inFileName);
	}
	
	public String getFileName() {return fileName;}
	public void setFileName(String inName) {fileName = inName;}
	public String getExtension() {return extension;}
	public void setExtension(String inExtension) {extension = inExtension;}
	
	private void set(String inFileName)
	{
		int position = inFileName.lastIndexOf(".");
		
		fileName = inFileName.substring(0, position - 1);
		extension = inFileName.substring(position);
	}
}
