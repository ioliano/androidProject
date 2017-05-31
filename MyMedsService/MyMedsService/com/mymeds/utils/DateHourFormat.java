package com.mymeds.utils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class DateHourFormat implements JsonSerializer<Date>, JsonDeserializer<Date> {

	private static final SimpleDateFormat dateHourFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	@Override
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		try {
			return dateHourFormat.parse(json.getAsString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(dateHourFormat.format(src));
	}

}
