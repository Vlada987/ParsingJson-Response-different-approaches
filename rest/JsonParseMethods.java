package rest;

import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.Book;

public class JsonParseMethods<T> {

//******************************************************************
//different methods and approaches for parsing and extractin certain
//property from jsonarrays,objects etc... 
//The methods are reusable and tested with every datatype.

	
	
//using JsonPath Class
	public List<T> jsonParse_0(Response resp, String query) {

		JsonPath jsonPath = new JsonPath(resp.asInputStream());
		List<T> result = jsonPath.getList(query);

		if (result.get(0) instanceof List) {
			List<List> result1 = (List<List>) result;
			List<T> result2 = (List<T>) result1.stream().flatMap(List::stream).collect(Collectors.toList());
			result = result2;
		}
		return result;

	}

	public List<T> jsonParse_1(Response resp, String query) {

		List<T> result = new ArrayList<>();
		int size = 0;
		while (!(resp.jsonPath().get(query + "[" + String.valueOf(size) + "]") == null)) {
			size++;
		}

		for (int a = 0; a < size; a++) {
			result.add((T) resp.jsonPath().get((query + "[" + String.valueOf(a) + "]")));
		}
		if (result.get(0) instanceof List) {
			List<List> result1 = (List<List>) result;
			List<Object> result2 = new ArrayList<>();
			result1.forEach(result2::addAll);
			result = (List<T>) result2;
		}

		return result;
	}

//Standard JSON (not json simple)
	public List<T> jsonParse_2(Response resp, String query, String type) throws JSONException {

		List<T> result = new ArrayList<>();
		List resultAsList = new ArrayList<>();
		JSONArray jsonArray = new JSONArray(resp.body().asString());

		for (int a = 0; a < jsonArray.length(); a++) {
			if (type == E.TEXT.value) {
				JSONObject object = jsonArray.getJSONObject(a);
				result.add((T) object.getString(query));
			} else if (type == E.INTEGER_VALUE.value) {
				JSONObject object = jsonArray.getJSONObject(a);
				Integer i = object.getInt(query);
				result.add((T) i);
			} else if (type == E.BOOLEAN_VALUE.value) {
				JSONObject object = jsonArray.getJSONObject(a);
				Boolean i = object.getBoolean(query);
				result.add((T) i);
			} else if (type == E.LIST_VALUE.value) {
				JSONObject object = jsonArray.getJSONObject(a);
				JSONArray json = object.getJSONArray(query);
				result.add((T) json);
			}
		}
		if (type == E.LIST_VALUE.value) {
			for (int q = 0; q < result.size(); q++) {
				JSONArray j = (JSONArray) result.get(q);
				for (int w = 0; w < j.length(); w++) {
					Object o = j.get(w);
					resultAsList.add(o);
				}
			}
			result = resultAsList;
		}
		return result;
	}

//This method needs pojo class
	public List<T> jsonParse_3(Response resp, String query)
			throws JSONException, JsonMappingException, JsonProcessingException {

		ObjectMapper oMapper = new ObjectMapper();
		List<T> result = new ArrayList<>();

		List<Book> result0 = (List<Book>) oMapper.readValue(resp.body().asString(), new TypeReference<List<T>>() {
		});

		for (int a = 0; a < result0.size(); a++) {

			Map<String, Object> myMap = oMapper.convertValue(result0.get(a), new TypeReference<Map<String, Object>>() {
			});
			result.add((T) myMap.get(query));
		}
		return result;
	}

	public List<T> jsonParse4(Response resp, String value)
			throws JSONException, JsonMappingException, JsonProcessingException {

		List<T> result = new ArrayList<>();
		JSONArray jar = new JSONArray(resp.body().asString());
		for (int a = 0; a < jar.length(); a++) {
			JSONObject job = jar.getJSONObject(a);
			HashMap<String, Object> map = new ObjectMapper().readValue(job.toString(), HashMap.class);
			result.add((T) map.get(value));

		}
		if (result.get(0) instanceof List) {
			List<List> result1 = (List<List>) result;
			List<Object> result2 = new ArrayList<>();
			result1.forEach(result2::addAll);
			result = (List<T>) result2;
		}

		return result;
	}

}
