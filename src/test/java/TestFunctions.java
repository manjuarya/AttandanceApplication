import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestFunctions {
    @Test
    public void testDisplayMembersDetails() {
        Functions function = new Functions();
        HashMap<String, String> membersDetails = function.accessDetailsFromAFile();
        int result = membersDetails.size();
        assertEquals(8, result);
    }

    @Test
    public void testMembersDetails() {
        Functions function = new Functions();
        HashMap<String, String> membersDetails = function.accessDetailsFromAFile();
        int result = membersDetails.size();
        assertEquals(8, result);
    }
}
