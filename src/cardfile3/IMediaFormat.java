package cardfile3;

import java.util.List;

public interface IMediaFormat {

    public String getExtension();

    public String getName();
    
    public String getMimeType();

    public boolean isGoodForOutgoing();
    
    public String getFFmpegAudioMediaFormat();
    public String getFFmpegVideoMediaFormat();

    public List<BrowserPriority> getLowestCompatibleBrowsers();

    public boolean canPlayOn(Browser browser);

    public boolean video();

    public boolean audio();

}
