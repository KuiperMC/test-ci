package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * A single signed, big endian 64 bit integer
 */
public non-sealed class NBTLong extends NBTNumber<Long> {
    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     */
    public NBTLong(String name, long value) {
        super(name, value, NBTType.LONG);
    }

    protected NBTLong(DataInputStream dis) throws IOException {
        this(dis.readUTF(), dis.readLong());
    }

    protected NBTLong(String name, DataInputStream dis) throws IOException {
        this(name, dis.readLong());
    }
}
