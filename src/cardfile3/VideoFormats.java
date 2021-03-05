package cardfile3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Video conversion
 * https://opensource.com/article/17/6/ffmpeg-convert-media-file-formats
 *
 * Priorities:
 * lower = best codec
 * 
 * 10 = preferred codec, set on specific browsers
 * 
 * Codecs with priorities 100-1000 are ordered according to the resulting filesize
 * 
 *  100 = MP4
 *  120 = WEBM
 *  150 = OGV
 * 
 * @author a.schild
 *
 * @author dhura
 *
 */
public class VideoFormats {

    private static List<MediaFormat> videoFormats = null;

    private VideoFormats() {
        populate();
    }

    public static List<MediaFormat> getVideoFormats() {
        if (videoFormats == null)
        {
            videoFormats = new ArrayList<>();
            populate();
        }
        return videoFormats;
    }
    
    public static List<MediaFormat> getOutgoingFormats() {
        return getVideoFormats().stream().filter((t) ->
        {
            return t.isGoodForOutgoing();
        }).collect(Collectors.toList());
    }

    private static void populate() 
    {
        // https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types
        // https://wiki.xiph.org/index.php?title=MIME_Types_and_File_Extensions&mobileaction=toggle_view_desktop
    	MediaFormat mf;
        // List of formats which we generate to be able to deliver to the webbrowsers
        // MediaFormat mf = new MediaFormat("H264_AAC", ".mp4", "video/mp4", true, "(.)*avc1(.)*", "(.)*aac(.)*",  true, "h264", "aac");
        mf = new MediaFormat("H264_AAC", ".mp4", "video/mp4", true, "(.)*avc1(.)*", "(.)*aac(.)*",  true, "x264", "aac");
        addCompatibleBrowsersH264AAC(mf);
        videoFormats.add(mf);

        // mf = new MediaFormat("Theora_Vorbis", ".ogv", "video/ogg", true, "(.)*theora(.)*", "(.)*vorbis(.)*", true, "vp9", "vorbis");
        mf = new MediaFormat("Theora_Vorbis", ".ogg", "video/ogg", true, "(.)*theora(.)*", "(.)*vorbis(.)*", true, "vp9", "vorbis");
        addCompatibleBrowsersOGV(mf);
        videoFormats.add(mf);

        // mf = new MediaFormat("VP8_Vorbis", ".webm", "video/webm; codecs=\"vp8\"", true, "(.)*vp8(.)*", "(.)*vorbis(.)*", true, "libvpx", "libvorbis");
        mf = new MediaFormat("VP8_Vorbis", ".webm", "video/webm; codecs=\"vp8\"", true, "(.)*vp8(.)*", "(.)*vorbis(.)*", true, "vp9", "vorbis");
        addCompatibleBrowsersWEBM_VP8(mf);
        videoFormats.add(mf);

        // Other formats detected/allowed for upload
        // These formats are no fully implemented with the codecs and regex rules
        mf = new MediaFormat("avi", ".avi", "video/x-msvideo", false, "indeo5", null, true, null, null);
        videoFormats.add(mf);
        mf = new MediaFormat("Flash", ".flv", "video/x-flv", false, "flash", null, true, null, null);
        videoFormats.add(mf);
        mf = new MediaFormat("MP4", ".mpg4", "video/mp4", false, "mp4", null, true, null, null);
        videoFormats.add(mf);
        mf = new MediaFormat("H.264 and MP3", ".mp4", "video/mp4", false, "H.264_and_MP3", null, true, null, null);
        videoFormats.add(mf);
        mf = new MediaFormat("mov", ".mov", "video/quicktime", false, "mov", null, true, null, null);
        videoFormats.add(mf);
        mf = new MediaFormat("mpeg", ".mpeg", "video/mpeg", false, "mpeg", null, true, null, null);
        videoFormats.add(mf);
        mf = new MediaFormat("Ogg video", ".ogg", "video/ogg", false, "ogg", null, true, null, null);
        videoFormats.add(mf);
        mf = new MediaFormat("webm", ".webm", "video/webm", false, "webm", null, true, null, null);
        videoFormats.add(mf);
        mf = new MediaFormat("VP9 and Opus", ".webm", "video/webm; codecs=\"vp9,vorbis\"", false, "vp9", null, true, null, null);
        videoFormats.add(mf);
        mf = new MediaFormat("3GP mobile", ".3gp", "video/3gpp", false, "3gp", null, true, null, null);
        videoFormats.add(mf);
        mf = new MediaFormat("3g2", ".3g2", "video/3gpp2", false, "3g2", null, true, null, null);
        videoFormats.add(mf);
    }

    private static void addCompatibleBrowsersH264AAC(MediaFormat mf) {
        /**
         * https://caniuse.com/#feat=video Preferences in order of file size
         *
         * OGV/WEBM_VP8/MPEG4_MP3/MPGE4/WEBM_VP9
         */
        List<BrowserPriority> browserList = mf.getLowestCompatibleBrowsers();

        // https://caniuse.com/#feat=mpeg4
        browserList.add(new BrowserPriority(Browser.ie9, 10));
        browserList.add(new BrowserPriority(Browser.edge12, 10));
        browserList.add(new BrowserPriority(Browser.firefox35, 100));
        browserList.add(new BrowserPriority(Browser.chrome4, 100));
        browserList.add(new BrowserPriority(Browser.safari3_2, 100));
        browserList.add(new BrowserPriority(Browser.opera25, 100));

        browserList.add(new BrowserPriority(Browser.safari_ios3_2, 100));
        browserList.add(new BrowserPriority(Browser.webview_android4_4, 100));
        browserList.add(new BrowserPriority(Browser.opera_android12, 100));
        browserList.add(new BrowserPriority(Browser.chrome_android75, 100));
        //browserList.add(new BrowserPriority(Browser.firefox_android24, 100)); partial support
        browserList.add(new BrowserPriority(Browser.ie_mobile10, 100));
        browserList.add(new BrowserPriority(Browser.samsung_android4, 100));
        browserList.add(new BrowserPriority(Browser.firefox_os, 100));
    }

    private static void addCompatibleBrowsersOGV(MediaFormat mf) {
        /**
         * https://caniuse.com/#feat=video Preferences in order of file size
         *
         * OGV/WEBM_VP8/MPEG4_MP3/MPGE4/WEBM_VP9
         */
        List<BrowserPriority> browserList = mf.getLowestCompatibleBrowsers();

        // https://caniuse.com/#feat=ogv
        browserList.add(new BrowserPriority(Browser.edge17, 120));
        browserList.add(new BrowserPriority(Browser.firefox3_5, 120));
        browserList.add(new BrowserPriority(Browser.chrome4, 120));
        browserList.add(new BrowserPriority(Browser.opera11_5, 120));

        browserList.add(new BrowserPriority(Browser.firefox_android67, 120));
        browserList.add(new BrowserPriority(Browser.firefox_os1_01, 120));
    }

    private static void addCompatibleBrowsersWEBM_VP8(MediaFormat mf) {
        /**
         * https://caniuse.com/#feat=video Preferences in order of file size
         *
         * OGV/WEBM_VP8/MPEG4_MP3/MPGE4/WEBM_VP9
         */
        List<BrowserPriority> browserList = mf.getLowestCompatibleBrowsers();

        // https://caniuse.com/#feat=webm
        browserList.add(new BrowserPriority(Browser.edge76, 150));
        browserList.add(new BrowserPriority(Browser.firefox28, 10));
        browserList.add(new BrowserPriority(Browser.chrome25, 10));
        browserList.add(new BrowserPriority(Browser.opera16, 150));

        browserList.add(new BrowserPriority(Browser.webview_android67, 150));
        browserList.add(new BrowserPriority(Browser.chrome_android75, 150));
        browserList.add(new BrowserPriority(Browser.firefox_android67, 150));
        browserList.add(new BrowserPriority(Browser.samsung_android5, 150));
        browserList.add(new BrowserPriority(Browser.firefox_os1_01, 150));
    }

    public static boolean isVideoFormat(String extension)
    {
        List<MediaFormat> formatList= getVideoFormats();
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
