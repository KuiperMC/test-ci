package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents a NBT tag
 *
 * @param <T> the type of the value
 */
public sealed class NBTTag<T>
        permits NBTByte, NBTByteArray, NBTCompound, NBTDouble, NBTEnd, NBTFloat, NBTInt, NBTIntArray, NBTList, NBTLong,
        NBTLongArray, NBTShort, NBTString {
    @Nullable protected final String name;
    @Nullable protected final T value;
    @NotNull protected final NBTType type;

    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     * @param type  the type of the tag
     */
    protected NBTTag(@Nullable String name, @Nullable T value, @NotNull NBTType type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    /**
     * Get the name of the tag
     *
     * @return the name of the tag
     */
    @Nullable
    public String getName() {
        return name;
    }

    /**
     * Get the value of the tag
     *
     * @return the value of the tag
     */
    @Nullable
    public T getValue() {
        return value;
    }

    /**
     * Get the type of the tag
     *
     * @return the type of the tag
     */
    @NotNull
    public NBTType getType() {
        return type;
    }

    @Override
    public String toString() {
        String value = this.value == null ? null : this.value.toString();
        return this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    /**
     * Get the compressed string representation of this tag
     * @return the compressed string representation of this tag
     * @see #toUncompressed(int)
     */
    public String toUncompressed() {
        return toUncompressed(0);
    }

    /**
     * Get the uncompressed string representation of this tag
     * @param indent the indentation level
     * @return the uncompressed string representation of this tag
     * @see #toUncompressed()
     */
    public String toUncompressed(int indent) {
        StringBuilder builder = new StringBuilder("\t".repeat(indent) + type.toUncompressed() + "(" + (name == null ? "None" : "'" + name + "'") + "): ");
        return builder.toString();
    }

    /**
     * Get the bytes of the tag
     *
     * @return the bytes of the tag
     *
     * @see <a href="https://wiki.vg/NBT#Specification">NBT specification</a>
     * @apiNote this method is not thread-safe
     */
    public byte[] bytes() {
        List<Byte> bytes = new ArrayList<>();
        try (DataOutputStream dos = new DataOutputStream(new OutputStream() {
            @Override
            public void write(int b) {
                bytes.add((byte) b);
            }
        })) {
            dos.writeByte(type.getId());
            dos.writeUTF(name == null ? "" : name);
            dos.write(bytesValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bytesArray = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            bytesArray[i] = bytes.get(i);
        }
        return bytesArray;
    }

    protected byte[] bytesValue() throws IOException {
        List<Byte> bytes = new ArrayList<>();
        try (DataOutputStream dos = new DataOutputStream(new OutputStream() {
            @Override
            public void write(int b) {
                bytes.add((byte) b);
            }
        })) {
            if (this instanceof NBTByte b && b.getValue() != null) {
                dos.writeByte(b.getValue());
            } else if (this instanceof NBTDouble d && d.getValue() != null) {
                dos.writeDouble(d.getValue());
            } else if (this instanceof NBTFloat f && f.getValue() != null) {
                dos.writeFloat(f.getValue());
            } else if (this instanceof NBTInt i && i.getValue() != null) {
                dos.writeInt(i.getValue());
            } else if (this instanceof NBTLong l && l.getValue() != null) {
                dos.writeLong(l.getValue());
            } else if (this instanceof NBTShort s && s.getValue() != null) {
                dos.writeShort(s.getValue());
            }
        }
        byte[] bytesArray = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            bytesArray[i] = bytes.get(i);
        }
        return bytesArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NBTTag<?> tag)) {
            return false;
        }
        return Arrays.equals(bytes(), tag.bytes());
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + type.hashCode();
        return result;
    }

    protected static NBTTag<?> read(DataInputStream dis, NBTType type) throws IOException {
        return switch (type) {
            case BYTE -> new NBTByte(dis);
            case SHORT -> new NBTShort(dis);
            case INT -> new NBTInt(dis);
            case LONG -> new NBTLong(dis);
            case FLOAT -> new NBTFloat(dis);
            case DOUBLE -> new NBTDouble(dis);
        };
    }

    protected static NBTTag<?> read(@Nullable String name, DataInputStream dis, NBTType type) throws IOException {
        return switch (type) {
            case BYTE -> new NBTByte(name, dis);
            case SHORT -> new NBTShort(name, dis);
            case INT -> new NBTInt(name, dis);
            case LONG -> new NBTLong(name, dis);
            case FLOAT -> new NBTFloat(name, dis);
            case DOUBLE -> new NBTDouble(name, dis);
        };
    }

    public static NBTTag<?> fromBytes(byte[] bytes) throws IOException {
        if (bytes.length == 0) {
            throw new IllegalArgumentException("bytes cannot be empty");
        }
        try (DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes))) {
            byte id = dis.readByte();
            return read(dis, NBTType.fromId(id));
        }
    }
}
