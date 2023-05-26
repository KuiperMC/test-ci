package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A length-prefixed array of <b>signed</b> bytes. The prefix is a <b>signed</b> integer (thus 4 bytes)
 */
public non-sealed class NBTByteArray extends NBTArray<byte[]> {
    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     */
    public NBTByteArray(@Nullable String name, byte @Nullable [] value) {
        super(name, value, NBTType.BYTE_ARRAY);
    }

    protected NBTByteArray(DataInputStream dis) throws IOException {
        this(dis.readUTF(), new byte[dis.readInt()]);
        dis.readFully(Objects.requireNonNull(value));
    }

    protected NBTByteArray(String name, DataInputStream dis) throws IOException {
        this(name, new byte[dis.readInt()]);
        dis.readFully(Objects.requireNonNull(value));
    }

    public Stream<Byte> stream() {
        return value == null ? Stream.empty() : IntStream.range(0, value.length).mapToObj(i -> value[i]);
    }
}
