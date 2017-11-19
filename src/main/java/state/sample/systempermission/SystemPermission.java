package state.sample.systempermission;

public class SystemPermission {
    SystemProfile profile;
    private SystemUser requestor;
    SystemAdmin admin;
    boolean granted;
    boolean isUnixPermissionGranted;
    PermissionState state;

    public SystemPermission(SystemUser requestor, SystemProfile profile) {
        this.requestor = requestor;
        this.profile = profile;
        setState(RequestedPermissionState.REQUESTED);
        notifyAdminOfPermissionRequest();
    }

    private void notifyAdminOfPermissionRequest() {

    }

    void willBeHandledBy(SystemAdmin admin) {
        this.admin = admin;
    }

    void notifyUserOfPermissionRequestResult() {

    }

    void notifyUnixAdminsOfPermissionRequest() {

    }

    public PermissionState getState() {
        return this.state;
    }

    public boolean isGranted() {
        return getState().equals(GrantedPermissionState.GRANTED);
    }

    public void setState(PermissionState state) {
        this.state = state;
    }
}
