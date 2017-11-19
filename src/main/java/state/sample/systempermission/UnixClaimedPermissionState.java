package state.sample.systempermission;

public class UnixClaimedPermissionState extends PermissionState {
    public static final PermissionState UNIX_CLAIMED = new UnixClaimedPermissionState();

    public void setDenyHelper(DenyHelper denyHelper) {
        this.denyHelper = denyHelper;
    }

    DenyHelper denyHelper;

    public UnixClaimedPermissionState() {
        super();
        setDenyHelper(new DenyHelper());
    }

    @Override
    public void grantedBy(SystemAdmin admin, SystemPermission systemPermission) {
        if (!systemPermission.admin.equals(admin))
            return;

        if (systemPermission.profile.isUnixPermissionRequired())
            systemPermission.isUnixPermissionGranted = true;
        else if (systemPermission.profile.isUnixPermissionRequired() && !systemPermission.profile.isUnixPermissionGranted()) {
            systemPermission.setState(UnixRequestedPermissionState.UNIX_REQUESTED);
            systemPermission.notifyUnixAdminsOfPermissionRequest();
            return;
        }
        systemPermission.setState(GrantedPermissionState.GRANTED);
        systemPermission.notifyUserOfPermissionRequestResult();
    }

    @Override
    public void deniedBy(SystemAdmin admin, SystemPermission systemPermission) {
        denyHelper.denyBy(admin, systemPermission);
    }
}
