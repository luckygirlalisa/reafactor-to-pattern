package state.sample.systempermission;

public class RequestedPermissionState extends PermissionState {

    public final static PermissionState REQUESTED = new RequestedPermissionState("REQUESTED");

    public RequestedPermissionState(String state) {
        super(state);
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
