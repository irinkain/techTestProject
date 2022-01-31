import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestClass {
    StepsClass stepsClass = new StepsClass();

    @Test()
    public void authorizeUserTest() {
        //build a request params & header
        RequestSpecification specification = new RequestSpecBuilder()
                .addQueryParam("req", "login")
                .addQueryParam("userIdentifier", ConstantData.username)
                .addQueryParam("password", ConstantData.password).build();

        //Send request and get response
        Response response = stepsClass.authorizeUser(specification);

        //check if status code is success
        Assert.assertEquals(response.getStatusCode(), 200);
        if (response.getStatusCode() == 200) {
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Something went wrong...");
        }
    }
}
