package com.example.smartseal.webServer;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
// NOTE: If you're using NanoHTTPD >= 3.0.0 the namespace is different,
//       instead of the above import use the following:
// import org.nanohttpd.NanoHTTPD;

public class App extends NanoHTTPD {

    public App() throws IOException {
        super(8080);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning! Point your browsers to http://localhost:8080/ \n");
    }

    public static void main(String[] args) {
        try {
            new App();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    @Override
    public Response serve(IHTTPSession session) {
        /*String msg = "<html><body><h1>Hello server</h1>\n";
        Map<String, String> parms = session.getParms();
        if (parms.get("username") == null) {
            msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n";
        } else {
            msg += "<p>Hello, " + parms.get("username") + "!</p>";
        }
        return newFixedLengthResponse(msg + "</body></html>\n");*/

        return dispatcher(session);
    }

    private Response dispatcher(IHTTPSession session) {
        Method method = session.getMethod();
        String uri = session.getUri();
        System.out.println("analysis request: [method: " + method +"; uri: "+ uri +"]");
        if (!isStrEmpty(uri)){
            // 接收get请求
            if (Method.GET.equals(method)){
                if (uri.equals("/getTest")){
                    return newFixedLengthResponse("get test success");
                }
            }
            // 接收post请求
            if (Method.POST.equals(method)){
                if (uri.equals("/postTest")){
                    return newFixedLengthResponse("post test success");
                }
            }
            // 接收put请求
            if (Method.PUT.equals(method)){

            }
            // 接收delete请求
            if (Method.DELETE.equals(method)){

            }
            // 接收patch请求
            if (Method.PATCH.equals(method)){

            }
            // 接收options请求
            if (Method.OPTIONS.equals(method)){

            }
        }else {
            return newFixedLengthResponse("welcome smart seal");
        }


        return null;
    }

    private static boolean isStrEmpty(String str){
        return str==null || str.equals("");
    }
}
