package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * A length-prefixed <a href="https://docs.oracle.com/javase/8/docs/api/java/io/DataInput.html#modified-utf-8">modified UTF-8</a> string.
 * The prefix is a <b>unsigned</b> short (thus 2 bytes) signifying the length of the string in bytes
 */
public non-sealed class NBTString extends NBTTag<String> {
    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     */
    public NBTString(@Nullable String name, @Nullable String value) {
        super(name, value, NBTType.STRING);
    }

    protected NBTString(DataInputStream dis) throws IOException {
        this(dis.readUTF(), dis.readUTF());
    }

    protected NBTString(String name, DataInputStream dis) throws IOException {
        this(name, dis.readUTF());
    }
}
