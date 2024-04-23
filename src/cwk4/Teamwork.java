package cwk4; 


/**
 * Details of your team
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "CS43";
        
        details[1] = "Bakhodirov";
        details[2] = "Asadbek";
        details[3] = "22013684";

        details[4] = "Bashikov";
        details[5] = "Nail";
        details[6] = "22002273";

        details[7] = "Khudayberganov";
        details[8] = "Shokhrukhbek";
        details[9] = "22005083";


        details[10] = "Mirabzalov";
        details[11] = "Mirnosir";
        details[12] = "21093764";

    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
