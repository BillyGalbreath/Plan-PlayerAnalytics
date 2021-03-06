/* 
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.systems.webserver.response.api;

import com.google.gson.Gson;
import main.java.com.djrapitops.plan.systems.webserver.response.Response;
import main.java.com.djrapitops.plan.systems.webserver.response.ResponseType;

/**
 * @author Fuzzlemann
 */
public class JsonResponse extends Response {

    public <T> JsonResponse(T object) {
        super(ResponseType.JSON);
        Gson gson = new Gson();

        super.setHeader("HTTP/1.1 200 OK");
        super.setContent(gson.toJson(object));
    }
}
