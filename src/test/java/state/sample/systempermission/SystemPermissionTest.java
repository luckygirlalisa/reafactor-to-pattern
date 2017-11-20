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
    public void shouldHaveRequestedAsInitialState() throws Exception {
        assertEquals("requested", RequestedPermissionState.REQUESTED, permission.getState());
    }

    @Test
    public void shouldNotChangeStateToGrantedAfterRequested() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.getState().grantedBy(admin, permission);

        assertEquals("requested", RequestedPermissionState.REQUESTED, permission.getState());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldNotChangeStateToGrantedWhenItemIsNotHandledByAdminWhoClaimIt() throws Exception {
        SystemAdmin claimAdmin = new SystemAdmin();
        permission.getState().claimedBy(claimAdmin, permission);
        SystemAdmin anotherAdmin = new SystemAdmin();
        permission.getState().grantedBy(anotherAdmin, permission);

        assertEquals("claimed", ClaimedPermissionState.CLAIMED, permission.getState());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldNotChangeStateToDeniedAfterRequested() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.getState().deniedBy(admin, permission);

        assertEquals("requested", RequestedPermissionState.REQUESTED, permission.getState());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldNotChangeStateToDeniedWhenNotDeniedByClaimedAdmin() throws Exception {
        SystemAdmin claimedAdmin = new SystemAdmin();
        permission.getState().claimedBy(claimedAdmin, permission);
        SystemAdmin anotherAdmin = new SystemAdmin();
        permission.getState().deniedBy(anotherAdmin, permission);

        assertEquals("claimed", ClaimedPermissionState.CLAIMED, permission.getState());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldNotBeAllBeClaimedAfterBeGranted() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.getState().claimedBy(admin, permission);
        permission.getState().claimedBy(admin, permission);

        assertEquals("denied", ClaimedPermissionState.CLAIMED, permission.getState());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldBeGrantedStateAfterBeGranted() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.getState().claimedBy(admin, permission);
        permission.getState().grantedBy(admin, permission);

        assertEquals("granted", GrantedPermissionState.GRANTED, permission.getState());
        assertTrue(permission.isGranted());
    }

    @Test
    public void shouldBeDeniedStateAfterBeDeniedByAdmin() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.getState().claimedBy(admin, permission);
        permission.getState().deniedBy(admin, permission);

        assertEquals("denied", DeniedPermissionState.DENIED, permission.getState());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldBeGrantedAfterSystemClaimedAndGrantedAndUnixGrantedWithUnixRequest() throws Exception {
        SystemUser user = new SystemUser();
        boolean isUnixPermissionRequired = true;
        SystemProfile profile = new SystemProfile(isUnixPermissionRequired);
        permission = new SystemPermission(user, profile);
        SystemAdmin admin = new SystemAdmin();

        permission.getState().claimedBy(admin, permission);
        permission.getState().grantedBy(admin, permission);
        assertFalse(permission.isGranted());
        assertEquals("Unix requested", UnixRequestedPermissionState.UNIX_REQUESTED, permission.getState());
        assertFalse(permission.isGranted());

        permission.getState().claimedBy(admin, permission);
        assertEquals("Unix claimed", UnixClaimedPermissionState.UNIX_CLAIMED, permission.getState());
        assertFalse(permission.isGranted());

        permission.getState().grantedBy(admin, permission);
        assertEquals("Granted", GrantedPermissionState.GRANTED, permission.getState());
        assertTrue(permission.isGranted());
    }

    @Test
    public void shouldBeDeniedAfterSystemRequestDeniedWithUnixRequestNeeded() throws Exception {
        SystemUser user = new SystemUser();
        boolean isUnixPermissionRequired = true;
        SystemProfile profile = new SystemProfile(isUnixPermissionRequired);
        permission = new SystemPermission(user, profile);
        SystemAdmin admin = new SystemAdmin();

        permission.getState().claimedBy(admin, permission);
        permission.getState().deniedBy(admin, permission);
        assertEquals("Denied", DeniedPermissionState.DENIED, permission.getState());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldBeDeniedAfterUnixRequestDeniedWithUnixRequestNeeded() throws Exception {
        SystemUser user = new SystemUser();
        boolean isUnixPermissionRequired = true;
        SystemProfile profile = new SystemProfile(isUnixPermissionRequired);
        permission = new SystemPermission(user, profile);
        SystemAdmin admin = new SystemAdmin();

        permission.getState().claimedBy(admin, permission);
        assertEquals("Claimed", ClaimedPermissionState.CLAIMED, permission.getState());
        permission.getState().grantedBy(admin, permission);
        assertEquals("Unix Requested", UnixRequestedPermissionState.UNIX_REQUESTED, permission.getState());
        assertFalse(permission.isGranted());

        permission.getState().claimedBy(admin, permission);
        permission.getState().deniedBy(admin, permission);
        assertEquals("Denied", DeniedPermissionState.DENIED, permission.getState());
        assertFalse(permission.isGranted());
    }
}