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
    /**
     * A length-prefixed array of <b>signed</b> bytes. The prefix is a <b>signed</b> integer (thus 4 bytes)
     */
    BYTE_ARRAY((byte) 7),
    /**
     * A length-prefixed <a href="https://docs.oracle.com/javase/8/docs/api/java/io/DataInput.html#modified-utf-8">modified UTF-8</a> string.
     * The prefix is a <b>unsigned</b> short (thus 2 bytes) signifying the length of the string in bytes
     */
    STRING((byte) 8),
    /**
     * A list of <b>nameless</b> tags, all of the same type.
     * The list is prefixed with the {@code Type ID} of the items it contains (thus 1 byte),
     * and the length of the list as a <b>signed</b> integer (a further 4 bytes).
     * If the length of the list is 0 or negative, the type may be {@link NBTType#END 0} ({@link NBTEnd TAG_End}) but otherwize it must be any other type.
     * (The notchian implementation uses {@link NBTEnd TAG_End} in that situation, but another reference implementation by Mojang uses 1 instead; parsers should accept any type if the length us <= 0).
     */
    LIST((byte) 9),
    /**
     * A length-prefixed array of <b>signed</b> integers.
     * The prefix is a <b>signed</b> integer (thus 4 bytes) and indicates the number of 4 byte integers.
     */
    INT_ARRAY((byte) 11),
    /**
     * A length-prefixed array of <b>signed</b> longs.
     * The prefix is a <b>signed</b> integer (thus 4 bytes) and indicates the number of 8 byte longs.
     */
    LONG_ARRAY((byte) 12)
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
