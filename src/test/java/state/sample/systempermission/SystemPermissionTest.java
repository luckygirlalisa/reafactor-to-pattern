package state.sample.systempermission;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SystemPermissionTest {
    private SystemPermission permission;

    @Before
    public void setUp() {
        SystemUser user = new SystemUser();
        boolean isUnixPermissionRequired = false;
        SystemProfile profile = new SystemProfile(isUnixPermissionRequired);
        permission = new SystemPermission(user, profile);
    }

    @Test
    public void shouldHaveRequestedAsInitialState() throws Exception {
        assertEquals("requested", PermissionState.REQUESTED, permission.getState());
    }

    @Test
    public void shouldNotChangeStateToGrantedAfterRequested() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.state.grantedBy(admin, permission);

        assertEquals("requested", PermissionState.REQUESTED, permission.getState());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldChangeStateToGrantedOnlyAfterClaimed() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.state.claimedBy(admin, permission);
        permission.state.grantedBy(admin, permission);

        assertEquals("granted", PermissionState.GRANTED, permission.getState());
        assertTrue(permission.isGranted());
    }

    @Test
    public void shouldNotChangeStateToGrantedWhenItemIsNotHandledByAdminWhoClaimIt() throws Exception {
        SystemAdmin claimAdmin = new SystemAdmin();
        permission.state.claimedBy(claimAdmin, permission);
        SystemAdmin anotherAdmin = new SystemAdmin();
        permission.state.grantedBy(anotherAdmin, permission);

        assertEquals("claimed", PermissionState.CLAIMED, permission.getState());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldNotChangeStateToDeniedAfterRequested() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.state.deniedBy(admin, permission);

        assertEquals("requested", PermissionState.REQUESTED, permission.getState());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldNotChangeStateToDeniedWhenNotDeniedByClaimedAdmin() throws Exception {
        SystemAdmin claimedAdmin = new SystemAdmin();
        permission.state.claimedBy(claimedAdmin, permission);
        SystemAdmin anotherAdmin = new SystemAdmin();
        permission.state.deniedBy(anotherAdmin, permission);

        assertEquals("claimed", PermissionState.CLAIMED, permission.getState());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldChangeStateToDeniedAfterClaimed() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.state.claimedBy(admin, permission);
        permission.state.deniedBy(admin, permission);

        assertEquals("denied", PermissionState.DENIED, permission.getState());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldNotBeAllBeClaimedAfterBeGranted() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.state.claimedBy(admin, permission);
        permission.state.claimedBy(admin, permission);

        assertEquals("denied", PermissionState.CLAIMED, permission.getState());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldBeUnixClaimedAfterSystemClaimedAndGrantedWithUnixRequest() throws Exception {
        SystemUser user = new SystemUser();
        boolean isUnixPermissionRequired = true;
        SystemProfile profile = new SystemProfile(isUnixPermissionRequired);
        permission = new SystemPermission(user, profile);
        SystemAdmin admin = new SystemAdmin();

        permission.state.claimedBy(admin, permission);
        permission.state.grantedBy(admin, permission);
        assertEquals("Unix requested", PermissionState.UNIX_REQUESTED, permission.getState());
        assertFalse(permission.isGranted());

        permission.state.claimedBy(admin, permission);
        assertEquals("Unix claimed", PermissionState.UNIX_CLAIMED, permission.getState());

        permission.state.grantedBy(admin, permission);
        assertEquals("Granted", PermissionState.GRANTED, permission.getState());
    }
}