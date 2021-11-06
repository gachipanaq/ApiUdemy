package Steps;

import static io.restassured.RestAssured.given;

public class ejemplosAPI {

    public void GETrequest(){
        given()
        .baseUri("https://api.github.com")
        .when()
        .get("/users/TheFreeRangeTester/repos")
        .getBody().toString();
    }

    public void POSTrequest(){
        given()
        .baseUri("baseUri")
        .when()
        .post("","");
    }

}

