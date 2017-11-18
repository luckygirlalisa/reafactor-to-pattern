package state.sample.systempermission;

import org.junit.Test;

import static org.junit.Assert.*;

public class SystemPermissionTest {
    private SystemPermission permission;

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

    }
}