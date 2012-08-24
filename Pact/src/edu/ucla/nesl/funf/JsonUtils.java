package edu.ucla.nesl.funf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import android.os.Bundle;

import java.lang.reflect.Type;
import java.util.Map;

import edu.mit.media.funf.Utils;

public class JsonUtils {

  public static Gson getGson() {
    return new GsonBuilder().setPrettyPrinting()
        .registerTypeAdapter(Bundle.class, new BundleJsonSerializer()).create();
  }

  public static class BundleJsonSerializer implements JsonSerializer<Bundle> {

    @Override
    public JsonElement serialize(Bundle bundle, Type type, JsonSerializationContext context) {
      JsonObject object = new JsonObject();
      for (Map.Entry<String, Object> entry : Utils.getValues(bundle).entrySet()) {
        object.add(entry.getKey(), context.serialize(entry.getValue()));
      }
      return object;
    }
  }
}
