/**
 * Conversion, calling cloudconvert.com API-v1
 * Author: Guokai Wang
 * Date: 2021-Feb-25
 */
package cardfile3;
import java.io.File;
import java.util.HashMap;
import org.aioobe.cloudconvert.ConvertProcess;
import org.aioobe.cloudconvert.CloudConvertService;
import org.aioobe.cloudconvert.ProcessStatus;

public class CloudConverter 
{
	// private File inputFile, outputFile;
	private String key;
	
	public CloudConverter(String inKey)
	{
		key = inKey;
	}
	
	public void convert(File inputFile, File outputFile, String inputType, String outputType, HashMap inOptions)
	       throws Exception
	{
    	    CloudConvertService service;	
            ConvertProcess process;
            ProcessStatus status;
            
            // options.put("audio_codec", "400");
    	
        try
        {
        // Create service object
            service = new CloudConvertService(key); 

// Create conversion process
            // ConvertProcess process = service.startProcess("txt", "doc");
            process = service.startProcess(inputType, outputType);

// Perform conversion
 /* process.startConversion(new File("test.jpg")); */
            process.startConversion(inputFile);

// Wait for result
            waitLoop: while (true) 
            {
            	status = process.getStatus();
            	if(inOptions != null && inOptions.size() > 0)
            		status.converter.options = inOptions;
    
            	switch (status.step) 
            	{
            	
            	case FINISHED: break waitLoop;
                case ERROR: throw new RuntimeException(status.message);
            	}
    
    // Be gentle
            	Thread.sleep(200);
            }

// Download result
            // service.download(status.output.url, new File("output.png"));
            service.download(status.output.url, outputFile);

// Clean up
            process.delete();
        }
        catch(Exception exception)
        {
        	// exception.printStackTrace();
        	throw exception;
        }
        
        // return result;
	}
}
