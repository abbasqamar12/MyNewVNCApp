package com.qamar.mynewvncapp;

class CapabilityInfo {
    // Public methods

    public CapabilityInfo(int code,
                          String vendorSignature,
                          String nameSignature,
                          String description) {
        this.code = code;
        this.vendorSignature = vendorSignature;
        this.nameSignature = nameSignature;
        this.description = description;
        enabled = false;
    }

    public CapabilityInfo(int code,
                          byte[] vendorSignature,
                          byte[] nameSignature) {
        this.code = code;
        this.vendorSignature = new String(vendorSignature);
        this.nameSignature = new String(nameSignature);
        this.description = null;
        enabled = false;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        enabled = true;
    }

    public boolean equals(CapabilityInfo other) {
        return (other != null && this.code == other.code &&
                this.vendorSignature.equals(other.vendorSignature) &&
                this.nameSignature.equals(other.nameSignature));
    }

    public boolean enableIfEquals(CapabilityInfo other) {
        if (this.equals(other))
            enable();

        return isEnabled();
    }

    // Protected data

    protected int code;
    protected String vendorSignature;
    protected String nameSignature;

    protected String description;
    protected boolean enabled;
}
