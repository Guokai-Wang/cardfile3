/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardfile3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ws.schild.jave.MultimediaInfo;

/**
 *
 * @author a.schild
 */
public class MediaFormat implements IMediaFormat {
    
    private final String name;
    private final String extension;
    private final String mimeType;
    private final boolean goodForOutgoing;
    private final boolean isVideoFormat;
    private final String ffmpegVideoFormatString;
    private final String ffmpegAudioFormatString;
    private final String videoCodec;
    private final String audioCodec;

    // List the browsers (the lowest version) where this format can play on
    private final List<BrowserPriority> compatibleBrowsers = new ArrayList<>();

    public MediaFormat(String name, 
            String extension, 
            String mimeType, 
            boolean goodForOutgoing, 
            String ffmpegVideoFormatString, 
            String ffmpegAudioFormatString, 
            boolean isVideoFormat,
            String videoCodec,
            String audioCodec) {
        this.name = name;
        this.extension = extension;
        this.mimeType = mimeType;
        this.goodForOutgoing = goodForOutgoing;
        this.ffmpegVideoFormatString = ffmpegVideoFormatString;
        this.ffmpegAudioFormatString = ffmpegAudioFormatString;
        this.isVideoFormat = isVideoFormat;
        this.videoCodec= videoCodec;
        this.audioCodec= audioCodec;
    }

    /**
     * @param browser
     * @return true when this browsre is capable of palying this format
     */
    @Override
    public boolean canPlayOn(Browser browser) {
        for (BrowserPriority one : compatibleBrowsers)
        {
            if (one.getBrowser().getBrowserType().equals(browser.getBrowserType()) && one.getBrowser().getVersion() <= browser.getVersion())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param browser
     * @return priority or -1 when no match
     */
    public int getPriority(Browser browser) {
        for (BrowserPriority one : compatibleBrowsers)
        {
            if (one.getBrowser().getBrowserType().equals(browser.getBrowserType()) && one.getBrowser().getVersion() <= browser.getVersion())
            {
                return one.getPriority();
            }
        }
        return -1;
    }    
    
    @Override
    public String getExtension() {
        return extension;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isGoodForOutgoing() {
        return goodForOutgoing;
    }
    

    @Override
    public String getFFmpegAudioMediaFormat() {
        return ffmpegAudioFormatString;
    }

    @Override
    public String getFFmpegVideoMediaFormat() {
        return ffmpegVideoFormatString;
    }
    
    @Override
    public String getMimeType() {
        return mimeType;
    }

    
    @Override
    public List<BrowserPriority> getLowestCompatibleBrowsers() {
        return compatibleBrowsers;
    }

    @Override
    public boolean audio() {
        return !isVideoFormat;
    }

    @Override
    public boolean video() {
        return isVideoFormat;
    }

    /**
     * @return the videoCodec
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    /**
     * @return the audioCodec
     */
    public String getAudioCodec() {
        return audioCodec;
    }
    
    /**
     * Is the required format identical to the one of the multi media object?
     * 
     * The format is found via regex matching
     * 
     * @param mi
     * @return 
     */
    public boolean isSameCodec(MultimediaInfo mi)
    {
        if (isVideoFormat)
        {
            String videoDecoderFormat= mi.getVideo().getDecoder();
            Pattern pattern = Pattern.compile(ffmpegVideoFormatString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(videoDecoderFormat);
            if (!matcher.find())
            {
                return false; // No need to check if audio codec is the same
            }
        }
        // Now test audio format if only audio or video codec identical
        {
            String decoderFormat= mi.getAudio().getDecoder();
            Pattern pattern = Pattern.compile(ffmpegAudioFormatString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(decoderFormat);
            return matcher.find();
        }
    }
    
    public String getDiskName()
    {
        return name;
    }
    
}

/*

TODO
Review how the system that serves the files after upload is working. This is necessary for cases of media files that might take a long time (up to 10mins) to process so that the system can have a way of checking if the processed files are ready and return their paths and if not, return the original file's path.

*/