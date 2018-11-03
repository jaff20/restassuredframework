//Author : Jabbar Ali (QA Test Developer)
//Linked In: https://www.linkedin.com/in/jabbarali/
//Date : 3rd Nov 2018 11:45 AM
/*Test to Verify the Acceptance Criteria such as
    -- Name, CanReList, Promotions Name and Description */

package verifySandBoxRequests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class testAcceptanceCriteria {

    //Connection to the JSON Request
    RequestSpecification httpRequest = RestAssured.given();
    Response response = httpRequest.get(Constants.envURL);

    // Getting the JsonPath object instance from the Response interface
    JsonPath jsonPathEvaluator = response.jsonPath();


    @Test(priority=1)   //To control the sequence of running the tests
    public void testResponseCode() {

        int statusCode = response.getStatusCode();

        System.out.println("******************************************************************");
        System.out.println("The Status Code is -> " + statusCode);
        Assert.assertEquals(statusCode, 200);

    }


    @Test(priority=2,dependsOnMethods = {"testResponseCode"})
    public void testResponseName() {

        String Name = jsonPathEvaluator.get("Name");

        // Let us print the Name variable to see what we got
        System.out.println("The Name Response is  -> " + Name);

        // Validate the Acceptance Test for Name Node
        Assert.assertEquals(Name, "Carbon credits");

    }


    @Test(priority=3,dependsOnMethods = {"testResponseCode"})
    public void testResponseCanRelist() {

        Boolean CanRelist = jsonPathEvaluator.get("CanRelist");

        // Let us print the CanReList variable to see what we got
        System.out.println("The CanReList Response is -> " + CanRelist);

        // Validate the Acceptance Test for CanReList Node
        Assert.assertTrue(CanRelist);

    }


    @Test(priority=4,dependsOnMethods = {"testResponseCode"})
    public void testPromotionsList() {

        List<String> PromotionsName = jsonPathEvaluator.getList("Promotions.Name");
        List<String> PromotionsDesc = jsonPathEvaluator.getList("Promotions.Description");

        // Iterate over the list and print individual Promotion Node items
        for (String PName : PromotionsName) {
            for (String PDesc : PromotionsDesc) {
                if ((PName.equalsIgnoreCase("Gallery") && PDesc.contains("2x larger image"))) {

                    //Let us print the Promotions Name and Description variable to see what we got
                    System.out.println("Promotions Name Response is -> " + PName);
                    System.out.println("Promotions Description Response is -> " + PDesc);

                    Assert.assertEquals(PName, "Gallery");
                    Assert.assertTrue(PDesc.contains("2x larger image"));
                    System.out.println("******************************************************************");

                }
            }
        }
    }


}