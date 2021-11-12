package Steps;

import static io.restassured.RestAssured.given;
import java.util.Base64;

import io.restassured.http.*;
import io.restassured.response.Response;

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

    public void basicAuth(String userName, String password){
        given().auth().basic(userName, password).when().get("AUTH_ENDPOINT").then().assertThat().statusCode(200);
    }

    public void formAuth(String userName, String password){
        given().auth().form(userName, password).when().get("SERVICES").then().assertThat().statusCode(200);
    }

    /*
    1. Obtener el código del servicio original para obtener el token
    2. Obtener el token, intercambiando el código que obtuvimos
    3. Acceder al recurso protegido, con nuestro token
    */
    
    public static String clienId = "";
    public static String redirectUri = "";
    public static String scope = "";
    public static String userName = "";
    public static String password = "";
    public static String grantType = "";
    public static String accessToken = "";

    public static String encode(String str1, String str2){
        return new String (Base64.getEncoder().encode((str1+ ":" + str2).getBytes()));
    }

    public static Response getCode(){
        String authorization = encode(userName, password);
        
        return
                given()
                .header("authorization", "Basic " + authorization)
                .contentType(ContentType.URLENC)
                .formParam("response_type", "code")
                .queryParam("client_id", clienId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", scope)
                .post("/oauth/authorize")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static String parseForOAuthCode(Response response){
        return response.jsonPath().getString("code");
    }

    public static Response getToken(String authCode){
        String authorization = encode(userName, password);

        return
            given()
                .header("authorization", "Basic " + authorization)
                .contentType(ContentType.URLENC)
                .formParam("response_type", authCode)                
                .queryParam("redirect_uri", redirectUri)
                .queryParam("grant_type", grantType)
                .post("/oauth/authorize")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static String parseForToken(Response loginResponse){
        return loginResponse.jsonPath().getString("access_token");
    }

    public static void getFinalService(){
        given()
        .header("Authorization", "Bearer "+ accessToken)
        .when()
        .get("/service")
        .then()
        .statusCode(200);
    }
}