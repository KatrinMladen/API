import data.User;
import helper.Helper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestRestTask {
    @Test()
    public void testGet(){
        RequestSpecification spec = RestAssured.given();

        //String apiURL ="http://localhost:8080/api/mytest?param1=val1";
        String apiURL ="http://localhost:8080/api/users";
        //spec.queryParam("param1", "val1");
        ValidatableResponse resp = spec.get(apiURL).then();
        System.out.println(resp.extract().statusCode());
        System.out.println(resp.extract().contentType());
        System.out.println(resp.extract().body().asString());
        //System.out.println("Response: "+obj.getString("users[0].userName"));

    }

    @Test()
    public void testPost(){
        Map<String, String> headers = new HashMap<String, String>() {{
            put("Content-Type", ContentType.JSON.toString());
        }};
        String apiURL ="http://localhost:8080/api/users";

        RequestSpecification spec = RestAssured.given();
        spec.headers(headers);
        //spec.baseUri(apiURL);
        spec.body("{ \"userName\": \"Jack\", \"userId\": 15 }");
        //spec.queryParams("userName", "Jack");
        JsonPath resp =
                spec
                        .log().all()
                        .post(apiURL)
                        .then()
                        .assertThat()
                        .statusCode(201)
                        .and()
                        .contentType(ContentType.JSON.toString())
                        .extract()
                        .body()
                        .jsonPath();

        System.out.println("Response: "+ resp.prettyPrint());
        Assert.assertEquals(resp.getString("status"), "Created");
//                .body()
//                .asString();
        //System.out.println(resp);
    }

    @Test()
    public void testGetWithParams(){
        RequestSpecification spec = RestAssured.given();
        String apiURL ="http://localhost:8080/api/users";
        //TODO:
        spec.body("{ \"userName\": \"Andry\", \"userId\": 23 }");
        spec
                .get(apiURL)
                .then()
                .assertThat()
                .contentType("application/json")
                .and()
                .statusCode(200);
        //System.out.println(spec);

    }

    @Test()
    public void testGetWithParamsXml(){
        RequestSpecification spec = RestAssured.given();

        //String apiURL ="http://localhost:8080/api/mytest?param1=val1";
        String apiURL ="http://localhost:8080/api/users";
        spec.queryParam("userId", 25);
        ValidatableResponse resp = spec.get(apiURL).then();
        String body = resp.extract().body().asString();

        User user = Helper.initUserFromXml(body);
        System.out.println(user);
        //resp.extract().body().as(JsonPath.class);
        /*System.out.println(resp.extract().statusCode());
        System.out.println(resp.extract().contentType());
        System.out.println(resp.extract().body().asString());*/
    }

    @Test()
    public void initFromJsonUser(){
        RequestSpecification spec = RestAssured.given();
        String apiURL ="http://localhost:8080/api/users";
        spec.queryParam("userId", 23);
        ValidatableResponse resp = spec.get(apiURL).then();
        String body = resp.extract().body().asString();

        User user = Helper.initFromJsonUser(body);
        System.out.println(user);
    }

}
