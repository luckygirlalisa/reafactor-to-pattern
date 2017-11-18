package state.sample.systempermission;

public class PermissionState {

    public final static PermissionState REQUESTED = new PermissionState("REQUESTED");
    public final static PermissionState CLAIMED = new PermissionState("CLAIMED");
    public final static PermissionState GRANTED = new PermissionState("GRANTED");
    public final static PermissionState DENIED = new PermissionState("DENIED");
    public static final PermissionState UNIX_REQUESTED = new PermissionState("UNIX_REQUESTED");
    public static final PermissionState UNIX_CLAIMED = new PermissionState("UNIX_CLAIMED");
    private String state;

    public PermissionState(String state) {

        this.state = state;
    }

    public void claimedBy(SystemAdmin admin, SystemPermission systemPermission) {
        if (!systemPermission.getState().equals(REQUESTED) && !systemPermission.getState().equals(UNIX_REQUESTED))
            return;
        systemPermission.willBeHandledBy(admin);
        if (systemPermission.getState().equals(REQUESTED))
            systemPermission.setState(CLAIMED);
        else if (systemPermission.getState().equals(UNIX_REQUESTED))
            systemPermission.setState(UNIX_CLAIMED);
    }

    public void deniedBy(SystemAdmin admin, SystemPermission systemPermission) {
        if (!systemPermission.getState().equals(CLAIMED) && !systemPermission.getState().equals(UNIX_CLAIMED))
            return;
        if (!systemPermission.admin.equals(admin))
            return;
        systemPermission.granted = false;
        systemPermission.isUnixPermissionGranted = false;
        systemPermission.setState(DENIED);
        systemPermission.notifyUserOfPermissionRequestResult();
    }

    public void grantedBy(SystemAdmin admin, SystemPermission systemPermission) {
        if (!systemPermission.getState().equals(CLAIMED) && !systemPermission.getState().equals(UNIX_CLAIMED))
            return;
        if (!systemPermission.admin.equals(admin))
            return;

        if (systemPermission.profile.isUnixPermissionRequired() && systemPermission.getState().equals(UNIX_CLAIMED))
            systemPermission.isUnixPermissionGranted = true;
        else if (systemPermission.profile.isUnixPermissionRequired() && !systemPermission.profile.isUnixPermissionGranted()) {
            systemPermission.setState(UNIX_REQUESTED);
            systemPermission.notifyUnixAdminsOfPermissionRequest();
            return;
        }
        systemPermission.setState(GRANTED);
        systemPermission.granted = true;
        systemPermission.notifyUserOfPermissionRequestResult();
    }
}
