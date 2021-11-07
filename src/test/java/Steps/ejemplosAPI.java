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
        .baseUri("https://api.blogejemplo.com")
        .when()
        .post("/posts","Título:Texto");
    }

    public void PUTrequest(){
        given()
        .baseUri("")
        .when()
        .put("","");
    }

    public void DELETErequest(){
        given()
        .baseUri("https://api.blogejemplo.com/posts/Texto")
        .when()
        .delete();
    }
}


