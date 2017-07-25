package main.java.com.djrapitops.plan.ui.webserver.response;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Rsl1122
 * @since 3.5.2
 */
public abstract class Response {

    private final OutputStream output;

    private String header;
    private String content;

    /**
     * Class Constructor.
     *
     * @param output Website OutputStream to write the response to.
     */
    public Response(OutputStream output) {
        this.output = output;
    }

    /**
     * Writes the HTML to the OutputStream according to the requested page.
     *
     * @throws IOException
     */
    public void sendStaticResource() throws IOException {
        String response = header + "\r\n"
                + "Content-Type: text/html;\r\n"
                + "Content-Length: " + content.length() + "\r\n"
                + "\r\n"
                + content;
//        Log.debug("Response: " + response); // Responses should not be logged, html content large.
        output.write(response.getBytes());
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
