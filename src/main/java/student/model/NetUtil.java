package student.model;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * A series of classes to help with pulling data from https://pokeapi.co/.
 * 
 * You can read more about the API at https://pokeapi.co/docs/v2
 */
public final class NetUtil {
    /**
     * Format required for the API request. There are many options, but keeping it simple for now.
     */
    private static final String API_URL_FORMAT = "https://pokeapi.co/api/v2/pokemon/";

    /**
     * Prevent instantiation.
     */
    private NetUtil() {
        // Prevent instantiation
    }


    /**
     * Returns the URL for the API request.
     * 
     * Defaults to XML format.
     * 
     * @param name The pokemon name to look up.
     * @return The URL for the API request.
     */
    public static String getApiUrl(String name) {
        return String.format(API_URL_FORMAT + name);
    }

    // /**
    //  * Returns the URL for the API request.
    //  * 
    //  * @param ip The IP address to look up.
    //  * @param format The format of the response.
    //  * @return The URL for the API request.
    //  */
    // public static String getApiUrl(String ip, Formats format) {
    //     return String.format(API_URL_FORMAT, ip, format.toString().toLowerCase());
    // }


    // /**
    //  * Looks up the IP address of a given hostname.
    //  * 
    //  * 
    //  * @param name the name to look up
    //  * @return the IP address of the name
    //  * @throws UnknownHostException if the hostname cannot be resolved
    //  */
    // public static String lookUpIp(String name) throws UnknownHostException {
    //     InetAddress address = InetAddress.getByName(name);
    //     String ip = address.getHostAddress();
    //     return ip;
    // }


    /**
     * Gets the contents of a URL as an InputStream.
     * 
     * @param urlStr the URL to get the contents of
     * @return the contents of the URL as an InputStream, or the null InputStream if the connection
     *         fails
     * 
     */
    public static InputStream getUrlContents(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            // con.setRequestProperty("Content-Type", "application/xml");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                            + "(KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
            int status = con.getResponseCode();
            if (status == 200) {
                return con.getInputStream();
            } else {
                System.err.println("Failed to connect to " + urlStr);
            }

        } catch (Exception ex) {
            System.err.println("Failed to connect to " + urlStr);
        }
        return InputStream.nullInputStream();
    }


    /**
     * Gets IP details using the ipapi.co API. Defaults to XML format.
     * 
     * @param name The pokemon name to look up.
     * @return the contents of the URL as an InputStream, or the null InputStream if the connection
     */
    public static InputStream getIpDetails(String name) {
        String urlStr = getApiUrl(name);
        return getUrlContents(urlStr);
    }


    // /**
    //  * Gets IP details using the ipapi.co API.
    //  * 
    //  * @param ip the IP address to get the information about
    //  * @param format the format of the response
    //  * @return the contents of the URL as an InputStream, or the null InputStream if the connection
    //  */
    // public static InputStream getIpDetails(String ip, Formats format) {
    //     String urlStr = getApiUrl(ip, format);
    //     return getUrlContents(urlStr);
    // }

}
