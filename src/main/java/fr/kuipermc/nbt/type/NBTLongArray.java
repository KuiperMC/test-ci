package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * A length-prefixed array of <b>signed</b> longs.
 * The prefix is a <b>signed</b> integer (thus 4 bytes) and indicates the number of 8 byte longs.
 */
public non-sealed class NBTLongArray extends NBTArray<long[]> {
    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     */
    public NBTLongArray(@Nullable String name, long @Nullable [] value) {
        super(name, value, NBTType.LONG_ARRAY);
    }

    protected NBTLongArray(DataInputStream dis) throws IOException {
        this(dis.readUTF(), dis);
    }

    protected NBTLongArray(String name, DataInputStream dis) throws IOException {
        this(name, new long[dis.readInt()]);
        for (int i = 0; i < Objects.requireNonNull(value).length; i++) {
            value[i] = dis.readLong();
        }
    }
}
