import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StepsClass {

    @Step("Send request for authorize user")
    public Response authorizeUser(RequestSpecification spec) {
        //build headers map
        Map<String,String> headers = new HashMap<>();
        headers.put("Origin",ConstantData.referer);
        headers.put("Referer",ConstantData.referer);
        headers.put("X-Requested-With","XMLHttpRequest");

        return given()
                .auth().none()
                .contentType("application/x-www-form-urlencoded")
                .spec(spec)
                .headers(headers)
                .when()
                .post(ConstantData.serviceUrl);
    }
}
