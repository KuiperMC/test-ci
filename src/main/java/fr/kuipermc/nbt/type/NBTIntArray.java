package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * A length-prefixed array of <b>signed</b> integers.
 * The prefix is a <b>signed</b> integer (thus 4 bytes) and indicates the number of 4 byte integers.
 */
public non-sealed class NBTIntArray extends NBTArray<int[]> {
    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     */
    public NBTIntArray(@Nullable String name, int @Nullable [] value) {
        super(name, value, NBTType.INT_ARRAY);
    }

    protected NBTIntArray(DataInputStream dis) throws IOException {
        this(dis.readUTF(), dis);
    }

    protected NBTIntArray(String name, DataInputStream dis) throws IOException {
        this(name, new int[dis.readInt()]);
        for (int i = 0; i < value.length; i++) {
            value[i] = dis.readInt();
        }
    }
}
