package state.sample.systempermission;

public class UnixRequestedPermissionState extends PermissionState {
    public static final PermissionState UNIX_REQUESTED = new UnixRequestedPermissionState("UNIX_REQUESTED");

    public UnixRequestedPermissionState(String unix_requested) {
        super(unix_requested);
    }

    @Override
    public void claimedBy(SystemAdmin admin, SystemPermission systemPermission) {
        systemPermission.willBeHandledBy(admin);
        systemPermission.setState(UnixClaimedPermissionState.UNIX_CLAIMED);
    }
}
