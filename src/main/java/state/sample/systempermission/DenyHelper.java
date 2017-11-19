package state.sample.systempermission;

public class DenyHelper {

    void denyBy(SystemAdmin admin, SystemPermission systemPermission) {
        if (!systemPermission.admin.equals(admin))
            return;
        systemPermission.isUnixPermissionGranted = false;
        systemPermission.setState(DeniedPermissionState.DENIED);
        systemPermission.notifyUserOfPermissionRequestResult();
    }
}
