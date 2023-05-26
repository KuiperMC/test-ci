package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * A single signed, big endian 32 bit integer
 */
public non-sealed class NBTInt extends NBTNumber<Integer> {
    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     */
    public NBTInt(String name, int value) {
        super(name, value, NBTType.INT);
    }

    protected NBTInt(DataInputStream dis) throws IOException {
        this(dis.readUTF(), dis.readInt());
    }

    protected NBTInt(String name, DataInputStream dis) throws IOException {
        this(name, dis.readInt());
    }
}
