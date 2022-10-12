package com.qamar.mynewvncapp;

import java.util.Hashtable;
import java.util.Vector;

class CapsContainer {

    // Public methods

    public CapsContainer() {
        infoMap = new Hashtable<Integer, CapabilityInfo>(64, (float)0.25);
        orderedList = new Vector<Integer>(32, 8);
    }

    public void add(CapabilityInfo capinfo) {
        Integer key = new Integer(capinfo.getCode());
        infoMap.put(key, capinfo);
    }

    public void add(int code, String vendor, String name, String desc) {
        Integer key = new Integer(code);
        infoMap.put(key, new CapabilityInfo(code, vendor, name, desc));
    }

    public boolean isKnown(int code) {
        return infoMap.containsKey(new Integer(code));
    }

    public CapabilityInfo getInfo(int code) {
        return (CapabilityInfo)infoMap.get(new Integer(code));
    }

    public String getDescription(int code) {
        CapabilityInfo capinfo = (CapabilityInfo)infoMap.get(new Integer(code));
        if (capinfo == null)
            return null;

        return capinfo.getDescription();
    }

    public boolean enable(CapabilityInfo other) {
        Integer key = new Integer(other.getCode());
        CapabilityInfo capinfo = (CapabilityInfo)infoMap.get(key);
        if (capinfo == null)
            return false;

        boolean enabled = capinfo.enableIfEquals(other);
        if (enabled)
            orderedList.addElement(key);

        return enabled;
    }

    public boolean isEnabled(int code) {
        CapabilityInfo capinfo = (CapabilityInfo)infoMap.get(new Integer(code));
        if (capinfo == null)
            return false;

        return capinfo.isEnabled();
    }

    public int numEnabled() {
        return orderedList.size();
    }

    public int getByOrder(int idx) {
        int code;
        try {
            code = ((Integer)orderedList.elementAt(idx)).intValue();
        } catch (ArrayIndexOutOfBoundsException e) {
            code = 0;
        }
        return code;
    }

    // Protected data

    protected Hashtable<Integer, CapabilityInfo> infoMap;
    protected Vector<Integer> orderedList;
}
