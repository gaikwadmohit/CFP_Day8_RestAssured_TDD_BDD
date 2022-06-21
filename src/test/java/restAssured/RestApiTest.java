package restAssured;

import java.net.URI;
import java.util.Random;

import javax.annotation.meta.When;
import javax.print.attribute.URISyntax;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import groovy.util.logging.Log;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class RestApiTest {
		String token="Bearer BQBexdV1PxWAOz9JqbEelTxmCvkdXp8JF42ifkZ-DDTc6sPyVdPNM1KLEBJW_6paXiMgtz_s3izij4Txp-kyzekcjDp73tnPLwKWLBif8vn3PUmV5sIrt83tx-zfxDKbPswrCRVsZmmSVPGx9yrXuNgKAkR9zD99-jO-tEZPT3stKE3uPyivRpfkGhvLF-H66__FJv1lAkpqgQsHWeY2NyzcuVzbxy1llqbJNNULRsHfMmClYFwegIwjAL-pK2YVBY2sFTv_TigUy-KBSwHLaJVwDMrF";
		String Userid="4oTAta0Yk9mX8k0P9hygtc";
		Random random = new Random();  
		int num = random.nextInt(50); 
		
	@Test
	public void getUserData() {      
		RequestSpecification rs=RestAssured.given();
		rs.contentType(ContentType.JSON);
		rs.header("Authorization",token);
		
		Response res=rs.request(Method.GET,"https://api.spotify.com/v1/me");
		res.prettyPrint();
		
		int statuscode=res.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		
		System.out.println("Status code is: " +statuscode);
//	    System.out.println(res.getBody().asString());
//   	System.out.println(num);
		
	}
	@Test
	public void testOf_createPlaylist() {
		String playlistID="31phqvitquseox6y24sbjy7prlo4";
		
		
		RequestSpecification rs=RestAssured.given();
		rs.contentType(ContentType.JSON);
		rs.header("Authorization",token);
		
		rs.body("{\r\n"
				+ "  \"name\": \"Mohit_Play"+num+"\",\r\n"
				+ "  \"description\": \"New playlist description\",\r\n"
				+ "  \"public\": false\r\n"
				+ "}");

		Response res=rs.request(Method.POST,"https://api.spotify.com/v1/users/"+playlistID+"/playlists");
		res.prettyPrint();
		
		int statuscode=res.getStatusCode();
		Assert.assertEquals(statuscode, 201);
	
}
	@Test
	public void getTrack() throws Exception {
		String getTrackID="7M587gUiVreE896xpUzQjR";
		
		RequestSpecification rs=RestAssured.given();
		rs.contentType(ContentType.JSON);
		rs.header("Authorization",token);
		

		Response res=rs.request(Method.GET,"https://api.spotify.com/v1/tracks/" + getTrackID);
		res.prettyPrint();
		
		int statuscode=res.getStatusCode();
		Assert.assertEquals(statuscode, 200);
}
	@Test
	public void Add_Item_To_Playlist() {
		String uri="spotify:track:7sk26bbMbWSTtWU0SbyvHv";
		String id_Add="58eJ5S6pShxp8KxYgecYPA";
		
		RequestSpecification rs=RestAssured.given();
		rs.contentType(ContentType.JSON);
		rs.header("Authorization",token);
		rs.queryParam("uris",uri);
		
		Response res=rs.request(Method.POST,"https://api.spotify.com/v1/playlists/"+id_Add+"/tracks");
		res.prettyPrint();
		
		int statuscode=res.getStatusCode();
		Assert.assertEquals(statuscode, 201);
}
	
	@Test
	public void Search_item()  {
		RequestSpecification rs=RestAssured.given();
		rs.contentType(ContentType.JSON);
		rs.header("Authorization",token);
		rs.queryParam("q","wwe");
		rs.queryParam("type","track");
		rs.queryParam("limit","3");
		
		Response res=rs.request(Method.GET,"https://api.spotify.com/v1/search");
		res.prettyPrint();
		
		int statuscode=res.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		
}
	
	@Test
	public void testOf_UpdatePlaylistData() {
		String UpdateId="58eJ5S6pShxp8KxYgecYPA";
		
		RequestSpecification rs=RestAssured.given();
		rs.contentType(ContentType.JSON);
		rs.header("Authorization",token);
		
	rs.body("{\r\n"
			+ "  \"range_start\": 1,\r\n"
			+ "  \"insert_before\": 3,\r\n"
			+ "  \"range_length\": 2\r\n"
			+ "}");
		
		/*JSONObject jsonobj=new JSONObject();
		jsonobj.put("range_start", 1);
		jsonobj.put("insert_before", 3);
		jsonobj.put("range_length", 2);
		
		rs.body(jsonobj.toJSONString());*/
		
		

		Response res=rs.request(Method.POST,"https://api.spotify.com/v1/playlists/"+UpdateId+"/tracks");
		res.prettyPrint();
		
		int statuscode=res.getStatusCode();
		Assert.assertEquals(statuscode, 201);
}
	

	@Test
	public void Remove_Playlist_Items() {
		String deleteId="58eJ5S6pShxp8KxYgecYPA";
		
		RequestSpecification rs=RestAssured.given();
		rs.contentType(ContentType.JSON);
		rs.header("Authorization",token);
		
		Response res=rs.request(Method.DELETE,"https://api.spotify.com/v1/playlists/"+deleteId+"/tracks");
		res.prettyPrint();
		
		int statuscode=res.getStatusCode();
		Assert.assertEquals(statuscode, 200);
}
   
	
	//	Rest Assured BDD approach
/*	@Test
	public void getUserData1() {   
		
		Response res=(Response) given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/me");
				
			res.then()
				.assertThat()
				.statusCode(200);
		res.prettyPrint();
	
			}
	
	@Test
	public void Varify_UserID() {   
		
		Response res=(Response) given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/me");
				
			Userid = res.path("id");
			res.then()
				.assertThat()
				.body("id",Matchers.equalTo(Userid));
			}
	@Test
	public void Create_Playlist2() {   
		Random random = new Random();  
		int num1 = random.nextInt(150); 
		
		Response res=(Response) given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.body("{\r\n"
						+ "  \"name\": \"Mohit_PLaylist"+num1+"\",\r\n"
						+ "  \"description\": \"New playlist description\",\r\n"
						+ "  \"public\": false\r\n"
						+ "}")
				.when()
				.post("	https://api.spotify.com/v1/users/"+Userid+"/playlists");
				
//			res.then()
//				.assertThat()
//				.statusCode(201);
		res.prettyPrint();
	
			}
	
	@Test
	public void Add_Item_To_Playlist2() {   
		
		Response res=(Response) 
		given()
				
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.queryParam("uris","spotify:track:4JkoixAiR0sZGQHZeiZF3L")
		.when()
				.post("https://api.spotify.com/v1/playlists/"+Userid+"/tracks");
			
			}
	
	@Test
	public void Varify_Playlist() {   
		Response res=(Response) 
				given()
						
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.header("Authorization",token)
						
				.when()
						.log().all()
						.get("https://api.spotify.com/v1/me/playlists");
				res.prettyPrint();
				
				JsonPath jp=res.jsonPath();
				
				String PlaylistName=jp.get("items[1].name");
				System.out.println("This is my playlist Name :"+PlaylistName);
				
				int playlistCount=res.path("total");
				System.out.println("No of playlist is: "+playlistCount);
				
				String statusLine=res.statusLine();
				System.out.println("StatusLine is: "+statusLine);
				
				Assert.assertEquals(PlaylistName,"Mohit_Play1");
				
		
	}
	@Test
	public void updatePlaylist2() {   
		
		Response res=(Response) given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.body("{\r\n"
						+ "  \"range_start\": 1,\r\n"
						+ "  \"insert_before\": 3,\r\n"
						+ "  \"range_length\": 2\r\n"
						+ "}")
				.when()
				.put("https://api.spotify.com/v1/playlists/"+Userid+"/tracks");
				
//			res.then()
//				.assertThat()
////				.statusCode(200);
				res.prettyPrint();
	
			}

		@Test
		public void Delete2() {   
			
			Response res=(Response) 
			given()
					
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Authorization",token)
					
			.when()
					.get("	https://api.spotify.com/v1/playlists/"+Userid+"/tracks");
					
				res.then()
			       .assertThat()
		           .statusCode(200);
			res.prettyPrint();
			}
	*/		
			}



