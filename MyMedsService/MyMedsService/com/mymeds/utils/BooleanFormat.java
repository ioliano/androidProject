package com.mymeds.utils;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 *
 * @author daniel.neves 05/05/2017
 */
public class BooleanFormat implements JsonSerializer<Boolean>, JsonDeserializer<Boolean> {

	@Override
	public Boolean deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		return jsonElement.getAsString().equals("S") ? true : false;
	}

	@Override
	public JsonElement serialize(Boolean aBoolean, Type type, JsonSerializationContext jsonSerializationContext) {
		if (aBoolean) {
			return new JsonPrimitive("S");
		}
		return new JsonPrimitive("N");
	}
}
