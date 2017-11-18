package state.sample.systempermission;

public class UnixRequestedPermissionState extends PermissionState {
    public static final PermissionState UNIX_REQUESTED = new UnixRequestedPermissionState();

    @Override
    public void claimedBy(SystemAdmin admin, SystemPermission systemPermission) {
        systemPermission.willBeHandledBy(admin);
        systemPermission.setState(UnixClaimedPermissionState.UNIX_CLAIMED);
    }
}
