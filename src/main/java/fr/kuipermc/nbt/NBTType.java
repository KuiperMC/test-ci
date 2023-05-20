package fr.kuipermc.nbt;

import fr.kuipermc.nbt.type.NBTEnd;

import java.util.Arrays;

public enum NBTType {
    /**
     * A single signed byte
     */
    BYTE((byte) 1),
    /**
     * A single signed, big endian 16 bit integer
     */
    SHORT((byte) 2),
    /**
     * A single signed, big endian 32 bit integer
     */
    INT((byte) 3),
    /**
     * A single signed, big endian 64 bit integer
     */
    LONG((byte) 4),
    /**
     * A single, big endian <a href="https://en.wikipedia.org/wiki/IEEE_754">IEEE-754</a>
     * single-precision floating point number (<a href="https://en.wikipedia.org/wiki/NaN">NaN</a> possible)
     */
    FLOAT((byte) 5),
    /**
     * A single, big endian <a href="https://en.wikipedia.org/wiki/IEEE_754">IEEE-754</a>
     * double-precision floating point number (<a href="https://en.wikipedia.org/wiki/NaN">NaN</a> possible)
     */
    DOUBLE((byte) 6),
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
