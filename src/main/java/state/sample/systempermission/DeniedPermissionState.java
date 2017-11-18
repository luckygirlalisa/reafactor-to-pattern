package state.sample.systempermission;

public class DeniedPermissionState extends PermissionState {
    public final static PermissionState DENIED = new DeniedPermissionState("DENIED");

    public DeniedPermissionState(String denied) {
        super(denied);
    }
}
