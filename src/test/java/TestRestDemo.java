import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestRestDemo {
    @Test()
    public void testPost() {
        Map<String, String> headers = new HashMap<String, String>() {{
            put("Content-Type", ContentType.JSON.toString());
        }};
//        HashMap<String, Object> params = new HashMap<String, Object>(){{
//            put("p", "jhdfkashfoweihlefalf347jhla");
//        }};

        String apiURL = "http://localhost:8080/api/params";

        RequestSpecification spec = RestAssured.given();
        spec.headers(headers);
        spec.baseUri(apiURL);
        spec.body("{\"paramId\":34}");
        //spec.queryParams(params);
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

        Assert.assertEquals(resp.getString("status"), "Updated23");
//                .body()
//                .asString();
        System.out.println(resp);
    }

    @Test()
    public void testGetWithParams() {
        RequestSpecification spec = RestAssured.given();
        //String apiURL ="http://localhost:8080/api/mytest?param1=val1";
        String apiURL = "http://localhost:8080/api/mytest";
        spec.queryParam("param1", "val1");
        //String resp =
        spec
                .get(apiURL)
                .then()
                .assertThat()
                .contentType("text/plain")
                .and()
                .statusCode(200);

//                .extract()
//                .body()
//                .asString();
//        System.out.println(resp);
    }

    @Test()
    public void testGet() {
        RequestSpecification spec = RestAssured.given();
        String apiURL = "http://localhost:8080/api/mytest";
        String resp =
                spec
                        //.log().all()
                        .get(apiURL)
                        .then()
                        .assertThat()
                        .statusCode(201)
                        .and()
                        .contentType("text/plain")

                        .extract()
                        .body()
                        .asString();
        System.out.println(resp);
    }
}
