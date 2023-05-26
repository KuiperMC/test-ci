package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public sealed class NBTNumber<T extends Number> extends NBTTag<T>
        permits NBTByte, NBTDouble, NBTFloat, NBTInt, NBTLong, NBTShort {
    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     * @param type  the type of the tag
     */
    protected NBTNumber(@Nullable String name, @Nullable T value, @NotNull NBTType type) {
        super(name, value, type);
    }

    public byte getByte() {
        return this.getValue().byteValue();
    }

    public short getShort() {
        return this.getValue().shortValue();
    }

    public int getInt() {
        return this.getValue().intValue();
    }

    public long getLong() {
        return this.getValue().longValue();
    }

    public float getFloat() {
        return this.getValue().floatValue();
    }

    public double getDouble() {
        return this.getValue().doubleValue();
    }
}
