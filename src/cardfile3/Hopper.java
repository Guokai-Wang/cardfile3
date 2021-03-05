/****
 * Major methods to call CloudConvert
 * Author: Guokai Wang
 * Date: 2021-Feb-25
 */
package cardfile3;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Hopper 
{
	public Hopper() 
	{
		
	}
	
	public void convertCloud(String inFileName)
	{
		CloudConverter converter = new CloudConverter("fiM9k9Ge1YTIVCtKW69thWDcWAzqBql5Jifsklg99KPDnPnQSy3zDHZeFi8aWRu6");
		File inFile = new File(uFilePath() + inFileName);
		FileLogic fLogic = new FileLogic(inFileName);
		HashMap<String, String> options;
		List<MediaFormat> goodFormats;
		String outputExtension, outputFileName;
		
		try
		{
			// System.out.println("convert():" + playAorVFormat(inFile));
			switch(playAorVFormat(inFile))
			{
			case 'A':
				goodFormats = AudioFormats.getOutgoingFormats();
			    break;
			case 'V':
				goodFormats = VideoFormats.getOutgoingFormats();
				break;
			default:
				goodFormats = null;
			}
			
			if(goodFormats != null)
			{
				for(MediaFormat element : goodFormats)
				{
					outputExtension = element.getExtension();
					outputFileName = "o" + fLogic.getFileName() + outputExtension;
					options = new HashMap<String, String>();
					if(element.getAudioCodec() != null)
					{
						options.put("audio_codec", element.getAudioCodec());
					}
					if(element.getVideoCodec() != null)
					{
						options.put("video_resolution", "960x720");
						options.put("video_codec", element.getVideoCodec());
					}
					System.out.println("converter():starting:" + inFileName);
					converter.convert(inFile, new File(uFilePath() + outputFileName), fLogic.getExtension().substring(1), outputExtension.substring(1), options);
					System.out.println("converter():" + fLogic.getExtension()  + " -> " + outputExtension + " success;");
				}
			}
			// converter.convert(new File(uFilePath() + inFileName), new File("output.png"), "jpg", "png", options);
		}
		catch(Exception exception) {exception.printStackTrace();}
		System.out.println("The End");
	}
	
	/* verb on 20210224: plasticize */
	public char playAorVFormat(File file)
	{
		char result;
		FileLogic fLogic = new FileLogic(file);
		
		// System.out.println("ext:" + fLogic.getExtension());
		if(AudioFormats.isAudioFormat(fLogic.getExtension())) result = 'A';
		else if(VideoFormats.isVideoFormat(fLogic.getExtension())) result = 'V';
		else result = ' ';		
		
		return result;
	}
	
	public String uFilePath()
	{
		// return "c:\\temp\\cloudc\\";
		return "C:" + File.separator + "temp" + File.separator + "Lirian" + File.separator + "samplefile" + File.separator;
	}
	
	public static void main(String[] args)
	{
		Hopper h = new Hopper();
		h.convertCloud("file_example_700.mp3");
	}
}
