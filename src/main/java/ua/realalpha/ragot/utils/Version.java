package ua.realalpha.ragot.utils;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public enum Version {

    v1_8(0, 8),
    v1_9(1, 9),
    v1_10(2, 10),
    v1_11(3, 11),
    v1_12(4, 12),
    v1_13(5, 13),
    v1_14(6, 14),
    v1_15(7, 15);

    private final int level;
    private final int version;
    private static Map<Integer, Version> map = new HashMap<>();

    Version(int level, int version) {
        this.level = level;
        this.version = version;

    }

    static {
        for (Version value : Version.values()) {
            map.put(value.version, value);
        }
    }

    public static Version getServerVersion(){
        String packageVersion = Bukkit.getServer().getClass().getPackage().getName();
        int version = Integer.parseInt(packageVersion.substring(26, 27));
        if (version == 1) version = Integer.parseInt(packageVersion.substring(26, 28));
        return getVersion(version);
    }

    public int getLevel() {
        return level;
    }

    public int getVersion() {
        return version;
    }

    private static Version getVersion(int i){
        return map.getOrDefault(i, Version.v1_15);
    }

}
