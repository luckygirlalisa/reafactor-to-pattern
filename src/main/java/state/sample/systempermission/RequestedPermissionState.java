package state.sample.systempermission;

public class RequestedPermissionState extends PermissionState {

    public final static PermissionState REQUESTED = new RequestedPermissionState("REQUESTED");

    public RequestedPermissionState(String state) {
        super(state);
    }

    @Override
    public void claimedBy(SystemAdmin admin, SystemPermission systemPermission) {
        systemPermission.willBeHandledBy(admin);
        systemPermission.setState(ClaimedPermissionState.CLAIMED);
    }
}
