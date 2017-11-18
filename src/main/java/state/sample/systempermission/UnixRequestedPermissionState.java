package state.sample.systempermission;

public class UnixRequestedPermissionState extends PermissionState {
    public static final PermissionState UNIX_REQUESTED = new UnixRequestedPermissionState("UNIX_REQUESTED");

    public UnixRequestedPermissionState(String unix_requested) {
        super(unix_requested);
    }

    @Override
    public void claimedBy(SystemAdmin admin, SystemPermission systemPermission) {
        if (!systemPermission.getState().equals(RequestedPermissionState.REQUESTED) && !systemPermission.getState().equals(UnixRequestedPermissionState.UNIX_REQUESTED))
            return;
        systemPermission.willBeHandledBy(admin);
        if (systemPermission.getState().equals(RequestedPermissionState.REQUESTED))
            systemPermission.setState(ClaimedPermissionState.CLAIMED);
        else if (systemPermission.getState().equals(UnixRequestedPermissionState.UNIX_REQUESTED))
            systemPermission.setState(UnixClaimedPermissionState.UNIX_CLAIMED);
    }
}
