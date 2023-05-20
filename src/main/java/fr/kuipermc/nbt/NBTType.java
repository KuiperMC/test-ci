package fr.kuipermc.nbt;

import fr.kuipermc.nbt.type.NBTEnd;

import java.util.Arrays;

public enum NBTType {
    ;
    private final byte id;

    NBTType(byte id) {
        this.id = id;
    }

    public static NBTType fromId(byte id) {
        for (NBTType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown NBT type ID: " + id);
    }

    /**
     * Get the ID of this type
     *
     * @return the ID of this type
     */
    public byte getId() {
        return id;
    }

    public String toUncompressed() {
        return "TAG_"+ Arrays.stream(name().split("_"))
                .map(s -> s.charAt(0) + s.substring(1).toLowerCase())
                .reduce((a, b) -> a + "_" + b)
                .orElseThrow();
    }
}
