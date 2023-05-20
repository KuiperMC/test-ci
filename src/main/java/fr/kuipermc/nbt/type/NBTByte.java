package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * A single signed byte
 */
public non-sealed class NBTByte extends NBTTag<Byte> {
    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     */
    public NBTByte(String name, Byte value) {
        super(name, value, NBTType.BYTE);
    }

    protected NBTByte(DataInputStream dis) throws IOException {
        this(dis.readUTF(), dis.readByte());
    }

    protected NBTByte(String name, DataInputStream dis) throws IOException {
        this(name, dis.readByte());
    }
}
