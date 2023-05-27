package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a NBT tag
 *
 * @param <T> the type of the value
 */
public sealed class NBTTag<T>
        permits NBTArray, NBTNumber,
        NBTCompound, NBTEnd, NBTList, NBTString {
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
        if (this instanceof NBTString) {
            value = "'" + value + "'";
        } else if (this instanceof NBTEnd) {
            return this.getClass().getSimpleName() + "{}";
        }
        return this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    /**
     * Get the compressed string representation of this tag
     *
     * @return the compressed string representation of this tag
     * @see #toUncompressed(int)
     */
    public String toUncompressed() {
        return toUncompressed(0);
    }

    /**
     * Get the uncompressed string representation of this tag
     *
     * @param indent the indentation level
     * @return the uncompressed string representation of this tag
     * @see #toUncompressed()
     */
    public String toUncompressed(int indent) {
        if (this instanceof NBTEnd) {
            return "";
        }
        StringBuilder builder = new StringBuilder("\t".repeat(indent) + type.toUncompressed() + "(" + (name == null ? "None" : "'" + name + "'") + "): ");
        if (this instanceof NBTList<?> list) {
            builder.append(list.size())
                    .append(list.size() == 1 ? " entry\n" : " entries\n")
                    .append("\t".repeat(indent))
                    .append("{\n");
            for (NBTTag<?> tag : list) {
                builder.append(tag.toUncompressed(indent + 1)).append("\n");
            }
            builder.append("\t".repeat(indent)).append("}");
        } else if (this instanceof NBTCompound compound) {
            builder.append(compound.size())
                    .append(compound.size() == 1 ? " entry\n" : " entries\n")
                    .append("\t".repeat(indent))
                    .append("{\n");
            for (NBTTag<?> tag : compound) {
                builder.append(tag.toUncompressed(indent + 1)).append("\n");
            }
            builder.append("\t".repeat(indent)).append("}");
        } else if (this instanceof NBTString string) {
            builder.append("'").append(string.getValue()).append("'");
        } else if (this instanceof NBTArray<?> array) {
            builder.append(array.valueToString());
        } else {
            builder.append(value);
        }
        return builder.toString();
    }

    /**
     * Get the bytes of the tag
     *
     * @return the bytes of the tag
     * @apiNote this method is not thread-safe
     * @see <a href="https://wiki.vg/NBT#Specification">NBT specification</a>
     */
    public byte[] bytes() {
        if (this instanceof NBTEnd) {
            return new byte[]{type.getId()};
        }
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
            if (this instanceof NBTList<?> list) {
                dos.writeByte(list.getElementType().getId());
                dos.writeInt(list.size());
                for (NBTTag<?> tag : list) {
                    dos.write(tag.bytesValue());
                }
            } else if (this instanceof NBTCompound compound) {
                for (NBTTag<?> tag : compound) {
                    dos.write(tag.bytes());
                }
                dos.writeByte(NBTType.END.getId());
            } else if (this instanceof NBTString string && string.getValue() != null) {
                dos.writeUTF(string.getValue());
            } else if (this instanceof NBTIntArray array && array.getValue() != null) {
                dos.writeInt(array.getValue().length);
                for (int i : array.getValue()) {
                    dos.writeInt(i);
                }
            } else if (this instanceof NBTLongArray array && array.getValue() != null) {
                dos.writeInt(array.getValue().length);
                for (long l : array.getValue()) {
                    dos.writeLong(l);
                }
            } else if (this instanceof NBTByteArray array && array.getValue() != null) {
                dos.writeInt(array.getValue().length);
                for (byte b : array.getValue()) {
                    dos.writeByte(b);
                }
            } else if (this instanceof NBTByte b && b.getValue() != null) {
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
            case END -> NBTEnd.INSTANCE;
            case BYTE -> new NBTByte(dis);
            case SHORT -> new NBTShort(dis);
            case INT -> new NBTInt(dis);
            case LONG -> new NBTLong(dis);
            case FLOAT -> new NBTFloat(dis);
            case DOUBLE -> new NBTDouble(dis);
            case BYTE_ARRAY -> new NBTByteArray(dis);
            case STRING -> new NBTString(dis);
            case LIST -> new NBTList<>(dis);
            case COMPOUND -> new NBTCompound(dis);
            case INT_ARRAY -> new NBTIntArray(dis);
            case LONG_ARRAY -> new NBTLongArray(dis);
        };
    }

    protected static NBTTag<?> read(@Nullable String name, DataInputStream dis, NBTType type) throws IOException {
        return switch (type) {
            case END -> NBTEnd.INSTANCE;
            case BYTE -> new NBTByte(name, dis);
            case SHORT -> new NBTShort(name, dis);
            case INT -> new NBTInt(name, dis);
            case LONG -> new NBTLong(name, dis);
            case FLOAT -> new NBTFloat(name, dis);
            case DOUBLE -> new NBTDouble(name, dis);
            case BYTE_ARRAY -> new NBTByteArray(name, dis);
            case STRING -> new NBTString(name, dis);
            case LIST -> new NBTList<>(name, dis);
            case COMPOUND -> new NBTCompound(name, dis);
            case INT_ARRAY -> new NBTIntArray(name, dis);
            case LONG_ARRAY -> new NBTLongArray(name, dis);
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
