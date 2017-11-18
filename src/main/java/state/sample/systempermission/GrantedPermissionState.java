package state.sample.systempermission;

public class GrantedPermissionState extends PermissionState {
    public final static PermissionState GRANTED = new GrantedPermissionState("GRANTED");

    public GrantedPermissionState(String granted) {
        super(granted);
    }

}
