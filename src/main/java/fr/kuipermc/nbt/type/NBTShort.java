package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * A single signed, big endian 16 bit integer
 */
public non-sealed class NBTShort extends NBTTag<Short> {
    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     */
    public NBTShort(String name, Short value) {
        super(name, value, NBTType.SHORT);
    }

    protected NBTShort(DataInputStream dis) throws IOException {
        this(dis.readUTF(), dis.readShort());
    }

    protected NBTShort(String name, DataInputStream dis) throws IOException {
        this(name, dis.readShort());
    }
}
