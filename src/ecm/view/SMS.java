package ecm.view;

public class SMS
{
    public SMS()
    {
    }

    /**Sends an SMS to a given phonenumber
     * @param mobileNumber.
     * @param message*/
    public void sendSMS(long mobileNumber,String message)
    {
        System.out.println("SMS notification sent to " + mobileNumber + ": \"" + message + "\"");
    }
}
