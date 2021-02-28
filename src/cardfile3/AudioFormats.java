package cardfile3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Priorities:
 * lower = best codec
 * 
 * 10 = preferred codec, set on specific browsers
 * 
 * Codecs with priorities 100-1000 are ordered according to the resulting filesize
 * 
 *  100 = OGA
 *  120 = MP3
 *  150 = AAC
 * 1000 = WAV uncompressed, big files
 * 
 * @author a.schild
 */
public class AudioFormats {

    private static List<MediaFormat> audioFormats = null;

    private AudioFormats() {
        populate();
    }

    public static List<MediaFormat> getAudioFormats() {
        if (audioFormats == null)
        {
            audioFormats = new ArrayList<>();
            populate();
        }
        return audioFormats;
    }

    public static List<MediaFormat> getOutgoingFormats() {
        return getAudioFormats().stream().filter((t) ->
        {
            return t.isGoodForOutgoing();
        }).collect(Collectors.toList());
    }

    
    private static void populate() {
        // https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types
        // https://wiki.xiph.org/index.php?title=MIME_Types_and_File_Extensions&mobileaction=toggle_view_desktop

        // List of formats which we generate to be able to deliver to the webbrowsers
        // OGA/AAC/MP3/OPUS/FLAC/WAV
        MediaFormat mf = new MediaFormat("AAC", ".aac", "audio/aac", true, null, "(.)*mp4a(.)*", false, null, "aac");
        addCompatibleBrowsersAAC(mf);
        audioFormats.add(mf);
        mf = new MediaFormat("MP3", ".mp3", "audio/mpeg", true, null, "(.)*mpga(.)*", false, null, "mp3");
        addCompatibleBrowsersMP3(mf);
        audioFormats.add(mf);
        mf = new MediaFormat("PCM_WAVE", ".wav", "audio/x-wav", true, null, "PCM(.)*", false, null, "pcm_u8");
        addCompatibleBrowsersWAV(mf);
        audioFormats.add(mf);

        // Other formats detected/allowed for upload
        // These formats are no fully implemented with the codecs and regex rules
        audioFormats.add(new MediaFormat("ALAW", ".au", "audio/alaw", false, null, "alaw", false, null, null));
        audioFormats.add(new MediaFormat("AU", ".au", "audio/basic", false, null, "au", false, null, null));
        audioFormats.add(new MediaFormat("CAF", ".caf", "audio/x-caf", false, null, "caf", false, null, null));
        audioFormats.add(new MediaFormat("FLAC", ".flac", "audio/flac", false, null, "flac", false, null, null));
        audioFormats.add(new MediaFormat("FLAC in Ogg", ".ogg", "audio/ogg", false, null, "flac_ogg", false, null, null));
        audioFormats.add(new MediaFormat("MID", ".mid", "audio/midi", false, null, "midi", false, null, null));
        audioFormats.add(new MediaFormat("MIDI", ".midi", "audio/midi", false, null, "midi", false, null, null));
        audioFormats.add(new MediaFormat("MP4", ".m4a", "audio/mp4", false, null, "mp4", false, null, null));
        audioFormats.add(new MediaFormat("MPEG4", ".mpeg", "audio/mp4", false, null, "mpeg4", false, null, null));
        audioFormats.add(new MediaFormat("Ogg", ".ogg", "audio/ogg", false, null, "ogg", false, null, null));
        audioFormats.add(new MediaFormat("Opus", ".opus", "audio/ogg; codecs=opus", false, null, "opus", false, null, null));
        audioFormats.add(new MediaFormat("Linear PCM", ".pcm", "audio/L24", false, null, "pcm", false, null, null));
        audioFormats.add(new MediaFormat("RMI", ".rmi", "audio/midi", false, null, "rmi", false, null, null));
        audioFormats.add(new MediaFormat("SND", ".snd", "audio/basic", false, null, "snd", false, null, null));
        audioFormats.add(new MediaFormat("PCM in WAVE", ".wave", "audio/wave", false, null, "wave", false, null, null));
        audioFormats.add(new MediaFormat("WebA", ".weba", "audio/webm", false, null, "weba", false, null, null));
        audioFormats.add(new MediaFormat("WebM", ".webm", "audio/webm", false, null, "webm", false, null, null));
        audioFormats.add(new MediaFormat("3gp", ".3gp", "audio/3gpp", false, null, "3gp", false, null, null));
        audioFormats.add(new MediaFormat("3g2", ".3g2", "audio/3gpp2", false, null, "3g2", false, null, null));

    }
    
    private static void addCompatibleBrowsersMP3(MediaFormat mf) {
        List<BrowserPriority> browserList = mf.getLowestCompatibleBrowsers();

        // https://caniuse.com/#feat=mp3
        browserList.add(new BrowserPriority(Browser.ie9, 10));
        browserList.add(new BrowserPriority(Browser.edge12, 120));
        browserList.add(new BrowserPriority(Browser.firefox22, 120));
        browserList.add(new BrowserPriority(Browser.chrome, 120));
        browserList.add(new BrowserPriority(Browser.safari4, 120));
        browserList.add(new BrowserPriority(Browser.opera15, 120));

        // Mobile
        browserList.add(new BrowserPriority(Browser.safari_ios4, 10));
        browserList.add(new BrowserPriority(Browser.webview_android2_3, 120));
        browserList.add(new BrowserPriority(Browser.opera_android12, 120));
        browserList.add(new BrowserPriority(Browser.chrome_android75, 120));
        browserList.add(new BrowserPriority(Browser.firefox_android67, 120));
        browserList.add(new BrowserPriority(Browser.ie_mobile10, 10));
        browserList.add(new BrowserPriority(Browser.samsung_android4, 120));

        browserList.add(new BrowserPriority(Browser.firefox_os1_01, 120));
    }

    private static void addCompatibleBrowsersOGA(MediaFormat mf) {
        List<BrowserPriority> browserList = mf.getLowestCompatibleBrowsers();

        // OGA is smallest file
        browserList.add(new BrowserPriority(Browser.edge17, 100));
        browserList.add(new BrowserPriority(Browser.firefox3_5, 10));
        browserList.add(new BrowserPriority(Browser.chrome, 10));
        browserList.add(new BrowserPriority(Browser.opera11_5, 10));

        browserList.add(new BrowserPriority(Browser.webview_android2_3, 100));
        browserList.add(new BrowserPriority(Browser.opera_android12, 100));
        browserList.add(new BrowserPriority(Browser.chrome_android75, 100));
        browserList.add(new BrowserPriority(Browser.firefox_android67, 100));
        browserList.add(new BrowserPriority(Browser.samsung_android4, 100));
        browserList.add(new BrowserPriority(Browser.firefox_os1_01, 100));
    }

    private static void addCompatibleBrowsersAAC(MediaFormat mf) {
        List<BrowserPriority> browserList = mf.getLowestCompatibleBrowsers();

        // https://caniuse.com/#feat=aac
        browserList.add(new BrowserPriority(Browser.ie9, 150));
        browserList.add(new BrowserPriority(Browser.edge12, 150));
        browserList.add(new BrowserPriority(Browser.chrome12, 150));
        //browserList.add(new BrowserPriority(Browser.firefox, 150)); only inside MP4 container
        browserList.add(new BrowserPriority(Browser.safari4, 150));
        browserList.add(new BrowserPriority(Browser.opera15, 150));

        browserList.add(new BrowserPriority(Browser.safari_ios4, 150));
        browserList.add(new BrowserPriority(Browser.webview_android3, 150));
        browserList.add(new BrowserPriority(Browser.opera_android46, 150));
        browserList.add(new BrowserPriority(Browser.ie_mobile10, 150));
        browserList.add(new BrowserPriority(Browser.samsung_android4, 150));
        browserList.add(new BrowserPriority(Browser.firefox_os1_01, 150));
    }

    private static void addCompatibleBrowsersWAV(MediaFormat mf) {
        List<BrowserPriority> browserList = mf.getLowestCompatibleBrowsers();

        browserList.add(new BrowserPriority(Browser.edge12, 1000));
        browserList.add(new BrowserPriority(Browser.firefox3_5, 1000));
        browserList.add(new BrowserPriority(Browser.chrome8, 1000));
        browserList.add(new BrowserPriority(Browser.safari4, 1000));
        browserList.add(new BrowserPriority(Browser.opera11_5, 1000));

        browserList.add(new BrowserPriority(Browser.safari_ios3_2, 1000));
        browserList.add(new BrowserPriority(Browser.webview_android2_3, 1000));
        browserList.add(new BrowserPriority(Browser.opera_android12, 1000));
        browserList.add(new BrowserPriority(Browser.chrome_android75, 1000));
        browserList.add(new BrowserPriority(Browser.firefox_android67, 1000));
        browserList.add(new BrowserPriority(Browser.samsung_android4, 1000));
        browserList.add(new BrowserPriority(Browser.firefox_os1_01, 1000));
    }

//    private static void addCompatibleBrowsersOPUS(MediaFormat mf) {
//        List<BrowserPriority> browserList = mf.getLowestCompatibleBrowsers();
//
//        browserList.add(new BrowserPriority(Browser.edge14, 500));
//        browserList.add(new BrowserPriority(Browser.firefox15, 500));
//        browserList.add(new BrowserPriority(Browser.chrome33, 500));
//        //browserList.add(new BrowserPriority(Browser.safari4, 500)); only inside caf ans ios 11
//        browserList.add(new BrowserPriority(Browser.opera20, 500));
//
//        //browserList.add(new BrowserPriority(Browser.safari_ios3_2, 500)); only inside caf
//        browserList.add(new BrowserPriority(Browser.chrome_android75, 500));
//        browserList.add(new BrowserPriority(Browser.firefox_android67, 500));
//        browserList.add(new BrowserPriority(Browser.samsung_android5, 500));
//    }
//
//    private static void addCompatibleBrowsersFLAC(MediaFormat mf) {
//        List<BrowserPriority> browserList = mf.getLowestCompatibleBrowsers();
//
//        browserList.add(new BrowserPriority(Browser.edge16, 2000));
//        browserList.add(new BrowserPriority(Browser.firefox51, 2000));
//        browserList.add(new BrowserPriority(Browser.chrome56, 2000));
//        //browserList.add(new BrowserPriority(Browser.safari4, 2000)); Needs highsierra or better
//        browserList.add(new BrowserPriority(Browser.opera42, 2000));
//
//        browserList.add(new BrowserPriority(Browser.safari_ios11, 2000));
//        browserList.add(new BrowserPriority(Browser.webview_android67, 2000));
//        browserList.add(new BrowserPriority(Browser.opera_android12_1, 2000));
//        browserList.add(new BrowserPriority(Browser.chrome_android75, 2000));
//        browserList.add(new BrowserPriority(Browser.firefox_android67, 2000));
//        browserList.add(new BrowserPriority(Browser.samsung_android5, 2000));
//    }

    public static boolean isAudioFormat(String extension)
    {
        List<MediaFormat> formatList= getAudioFormats();
        for (MediaFormat mf : formatList)
        {
            if (mf.getExtension().equalsIgnoreCase(extension))
            {
                return true;
            }
        }
        return false;
    }
    
}
