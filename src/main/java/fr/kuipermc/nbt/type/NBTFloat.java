package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * A single, big endian <a href="http://en.wikipedia.org/wiki/IEEE_754-2008">IEEE-754</a>
 * single-precision floating point number (<a href="http://en.wikipedia.org/wiki/NaN">NaN</a> possible)
 */
public non-sealed class NBTFloat extends NBTTag<Float> {
    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     */
    public NBTFloat(@Nullable String name, @Nullable Float value) {
        super(name, value, NBTType.FLOAT);
    }

    protected NBTFloat(DataInputStream dis) throws IOException {
        this(dis.readUTF(), dis.readFloat());
    }

    protected NBTFloat(String name, DataInputStream dis) throws IOException {
        this(name, dis.readFloat());
    }
}
