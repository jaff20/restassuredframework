//Author : Jabbar Ali (QA Test Developer)
//Linked In: https://www.linkedin.com/in/jabbarali/
//Date : 3rd Nov 2018 11:45 AM
//Usage : The package to hold the abstract methods which will control to void duplicate test and controlled execution

package verifySandBoxRequests;

public abstract class ApiWrappers {

    //Connection Request
    abstract void testResponseCode() throws Exception;

    //Get Response Request for Node - Name
    abstract void testResponseName() throws Exception;

    //Get Response Request for Node - CanRelist
    abstract void testResponseCanRelist() throws Exception;

    //Get Response Request for Node - Promotion Name & Description
    abstract void testPromotionsList() throws Exception;


}
