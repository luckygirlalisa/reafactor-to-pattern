package state.sample.systempermission;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SystemPermissionTest {
    private SystemPermission permission;

    @Before
    public void setUp() {
        SystemUser user = new SystemUser();
        SystemProfile profile = new SystemProfile();
        permission = new SystemPermission(user, profile);
    }

    @Test
    public void testGrantedBy() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.grantedBy(admin);
        assertEquals("requested", permission.REQUESTED, permission.state());
        assertEquals("not granted", false, permission.isGranted());

        permission.claimedBy(admin);
        permission.grantedBy(admin);
        assertEquals("granted", permission.GRANTED, permission.state());
        assertEquals("granted", true, permission.isGranted());
    }
}