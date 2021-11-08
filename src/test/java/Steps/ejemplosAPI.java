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

    public void SOAPRequest(){
        String xmlEnvelope = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
        "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" +
        "<soap12:Body>" +
        "<Define xmlns=\"http://services.aonaware.com/webservices/\">" +
        "<word></word>" +
        "</Define>" +
        "</soap12:Body>" +
        "</soap12:Envelope>";

            given()
            .header("SOAPAction", "Define")
            .baseUri("https://api.blogEjemplo.com")
            .when()
            .body(xmlEnvelope)
            .post("/endpint");
    }
}



