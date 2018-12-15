package sample;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IconHelper {
    private static OkHttpClient client = new OkHttpClient();
    private static JsonParser parser = new JsonParser();
    private static String client_ID = "Rma9CCiBOuilUBlg8nD3Yg4XBm1Va9SH7LD3cbZmqZWoOrd66HBODq8ilw1rfXjN";
    private static String client_secret = "FEH49reZ7xSRJugvHU2wDvvmTbFiD6jkDX7p6ZHTLNNA4C58VzhpPPmAbxW7Id85";
    private static String query;
    //static final HttpUrl ICONS_REQ;
    static {

    }
    static ArrayList<ImageView> userService()
    {
        ArrayList<ImageView> imageViewArrayList = new ArrayList<>();

        try {
            getIcons(imageViewArrayList,query);
            return imageViewArrayList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageViewArrayList;
    }
    static  void  getIcons(ArrayList<ImageView> icon_url_array,String query) throws IOException {

          //= new HttpUrl.Builder()
        //                .scheme("https")
        //                .host("www.iconfinder.com")
        //                .addPathSegment("api")
        //                .addPathSegment("v3")
        //                .addPathSegment("oauth2")
        //                .addPathSegment("token")
        //                .build();
//        for (int i=0;i<100;i++)
//        {
            HttpUrl httpUrl = new  HttpUrl.Builder()
                .scheme("https")
                .host("api.iconfinder.com")
                .addPathSegment("v3")
                .addPathSegment("icons")
                .addPathSegment("search")
                .addEncodedQueryParameter("query",query)
                .addEncodedQueryParameter("count","50")
                .addEncodedQueryParameter("client_id",client_ID)
                .addEncodedQueryParameter("client_secret",client_secret)
                .build();

        Request baseRequest = new Request.Builder()
                .get()
                .url(httpUrl)
                .build();

        Response response = client.newCall(baseRequest).execute();

        if (response.code() == 200) {
            JsonParser parser = new JsonParser();
            JsonObject o = parser.parse(response.body().string()).getAsJsonObject();
            //accessToken = AccessToken.buildAccessToken(o);
            System.out.println(o.toString());
            JsonArray icons = (JsonArray)o.get("icons");
            for (JsonElement iconInfo:icons) {
                System.out.println(iconInfo.toString());
                JsonArray rasters = iconInfo.getAsJsonObject().get("raster_sizes").getAsJsonArray();

                System.out.println(rasters.toString());
                JsonArray formats = rasters.get(/*rasters.size()-1*/0).getAsJsonObject().get("formats").getAsJsonArray();
                if (formats.size()>0) {
                    System.out.println(formats.get(0).toString());
                    JsonPrimitive icon_url = formats.get(0).getAsJsonObject().get("preview_url")
                            .getAsJsonPrimitive();

                    System.out.println(icon_url.toString());
                    icon_url_array.add(new ImageView(new Image(icon_url.getAsString())));
                }
            }
        }
        //}
    }

    public static void setQuery(String query) {
        IconHelper.query = query;
    }

    public static String getQuery() {
        return query;
    }
//    static final HttpUrl TOKEN_REQ_PATH;
//    static final String CLIENT_ID = "Rma9CCiBOuilUBlg8nD3Yg4XBm1Va9SH7LD3cbZmqZWoOrd66HBODq8ilw1rfXjN";
//    static final String CLIENT_SECRET = "FEH49reZ7xSRJugvHU2wDvvmTbFiD6jkDX7p6ZHTLNNA4C58VzhpPPmAbxW7Id85";
//    static {
//
//
//        ICONS_REQ = new HttpUrl.Builder()
//                .scheme("https")
//                .host("https://api.iconfinder.com/")
//                .addPathSegment("v3")
//                .addPathSegment("icons")
//                .addPathSegment("search")
//                .build();
//
//
//
//        TOKEN_REQ_PATH = new HttpUrl.Builder()
//                .scheme("https")
//                .host("www.iconfinder.com")
//                .addPathSegment("api")
//                .addPathSegment("v3")
//                .addPathSegment("oauth2")
//                .addPathSegment("token")
//                .build();
//
//
//        baseRequest = new Request.Builder()
//                .url(TOKEN_REQ_PATH)
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
//                .get()
//                .build();
//    }
//
//    static Request getRequest() {
//        return baseRequest.newBuilder()
//                //.url(url)
//                //.header("Authorization", "OAuth " + token)
//                .get()
//                .tag(null)
//                .build();
//
//
//    }
//    static void print()
//    {
//
//    }
}
