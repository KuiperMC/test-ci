package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * A single, big endian <a href="http://en.wikipedia.org/wiki/IEEE_754-2008">IEEE-754</a>
 * double-precision floating point number (<a href="http://en.wikipedia.org/wiki/NaN">NaN</a> possible)
 */
public non-sealed class NBTDouble extends NBTNumber<Double> {
    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     */
    public NBTDouble(@Nullable String name, @Nullable Double value) {
        super(name, value, NBTType.DOUBLE);
    }

    protected NBTDouble(DataInputStream dis) throws IOException {
        this(dis.readUTF(), dis.readDouble());
    }

    protected NBTDouble(String name, DataInputStream dis) throws IOException {
        this(name, dis.readDouble());
    }
}
