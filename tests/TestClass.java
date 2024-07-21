package tests;

//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.json.simple.JSONArray;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import rest.Context;
import rest.E;
import rest.JsonParseMethods;
import rest.Methods;

public class TestClass {

	
	String mockUrl = "https://run.mocky.io/v3";
	JsonParseMethods jp = new JsonParseMethods();

@Test
public <T> void test00() throws JSONException, ParseException, JsonMappingException, JsonProcessingException {

Context context = new Context();
context.baseURL = mockUrl;
context.URI="/078e008f-8c2b-4b7a-b65b-dba383acb52a";
Response resp= Methods.GET(context);
//resp.then().log().all();

List list=jp.jsonParse4(resp, "movies");



System.out.println(list);



	
}


	













}
