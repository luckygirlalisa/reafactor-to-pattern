package state.sample.systempermission;

public class SystemProfile {
    private boolean isUnixPermissionRequired;
    private boolean isUnixPermissionGranted;

    public SystemProfile(boolean isUnixPermissionRequired) {
        this.isUnixPermissionRequired = isUnixPermissionRequired;
        this.isUnixPermissionGranted = false;
    }

    public SystemProfile() {
        this(false);
    }

    public boolean isUnixPermissionRequired() {
        return this.isUnixPermissionRequired;
    }

    public boolean isUnixPermissionGranted() {
        return this.isUnixPermissionGranted;
    }
}
