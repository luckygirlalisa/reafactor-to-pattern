package state.sample.systempermission;

public abstract class PermissionState {

    private String state;

    public PermissionState(String state) {

        this.state = state;
    }

    public void deniedBy(SystemAdmin admin, SystemPermission systemPermission) {

    }

    public void grantedBy(SystemAdmin admin, SystemPermission systemPermission) {

    }

    public void claimedBy(SystemAdmin admin, SystemPermission systemPermission) {

    }
}
