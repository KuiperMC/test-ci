package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;

import java.io.DataInputStream;

/**
 * Signifies the end of a {@link NBTCompound TAG_Compound}.
 * It is only ever used inside a {@link NBTCompound TAG_Compound}, and is not named despite being in a {@link NBTCompound TAG_Compound}
 */
public non-sealed class NBTEnd extends NBTTag<Void> {
    public static final NBTEnd INSTANCE = new NBTEnd();

    /**
     * Create a new NBT tag
     */
    protected NBTEnd() {
        super(null, null, NBTType.END);
    }

    protected NBTEnd(DataInputStream dis) {
        super(null, null, NBTType.END);
    }

    /**
     * @deprecated The end tag has no name
     */
    @Override
    @Deprecated
    public String getName() {
        throw new UnsupportedOperationException("NBTEnd has no name");
    }

    /**
     * @deprecated The end tag has no value
     */
    @Override
    @Deprecated
    public Void getValue() {
        throw new UnsupportedOperationException("NBTEnd has no value");
    }
}
