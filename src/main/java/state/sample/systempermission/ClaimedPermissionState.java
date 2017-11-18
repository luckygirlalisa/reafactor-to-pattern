package state.sample.systempermission;

public class ClaimedPermissionState extends PermissionState {
    public final static PermissionState CLAIMED = new ClaimedPermissionState("CLAIMED");

    public ClaimedPermissionState(String claimed) {
        super(claimed);
    }


    @Override
    public void grantedBy(SystemAdmin admin, SystemPermission systemPermission) {
        if (!systemPermission.getState().equals(ClaimedPermissionState.CLAIMED) && !systemPermission.getState().equals(UnixClaimedPermissionState.UNIX_CLAIMED))
            return;
        if (!systemPermission.admin.equals(admin))
            return;

        if (systemPermission.profile.isUnixPermissionRequired() && systemPermission.getState().equals(UnixClaimedPermissionState.UNIX_CLAIMED))
            systemPermission.isUnixPermissionGranted = true;
        else if (systemPermission.profile.isUnixPermissionRequired() && !systemPermission.profile.isUnixPermissionGranted()) {
            systemPermission.setState(UnixRequestedPermissionState.UNIX_REQUESTED);
            systemPermission.notifyUnixAdminsOfPermissionRequest();
            return;
        }
        systemPermission.setState(GrantedPermissionState.GRANTED);
        systemPermission.granted = true;
        systemPermission.notifyUserOfPermissionRequestResult();
    }

    @Override
    public void deniedBy(SystemAdmin admin, SystemPermission systemPermission) {
        if (!systemPermission.getState().equals(ClaimedPermissionState.CLAIMED) && !systemPermission.getState().equals(UnixClaimedPermissionState.UNIX_CLAIMED))
            return;
        if (!systemPermission.admin.equals(admin))
            return;
        systemPermission.granted = false;
        systemPermission.isUnixPermissionGranted = false;
        systemPermission.setState(DeniedPermissionState.DENIED);
        systemPermission.notifyUserOfPermissionRequestResult();
    }
}
