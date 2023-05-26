package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Effectively a list of <b>named</b> tags. Order not guaranteed.
 */
public non-sealed class NBTCompound extends NBTTag<List<NBTTag<?>>> implements Iterable<NBTTag<?>> {
    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     */
    public NBTCompound(@Nullable String name, @Nullable List<NBTTag<?>> value) {
        super(name, value, NBTType.COMPOUND);
    }

    protected NBTCompound(DataInputStream dis) throws IOException {
        this(dis.readUTF(), dis);
    }

    protected NBTCompound(String name, DataInputStream dis) throws IOException {
        this(name, new ArrayList<>());
        NBTType type;
        while ((type = NBTType.fromId(dis.readByte())) != NBTType.END) {
            Objects.requireNonNull(value).add(NBTTag.read(dis, type));
        }
    }

    /**
     * Create a new NBT tag
     *
     * @param value the value of the tag
     */
    public NBTCompound(@NotNull NBTTag<?>... value) {
        this(null, value);
    }

    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     */
    public NBTCompound(@Nullable String name, @NotNull NBTTag<?>... value) {
        this(name, List.of(value));
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public NBTTag<?> get(String name) {
        return value == null ? null : value.stream()
                .filter(tag -> tag.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @param type the type of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public NBTTag<?> get(String name, NBTType type) {
        NBTTag<?> tag = get(name);
        return tag != null && tag.getType() == type ? tag : null;
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public NBTByte getByte(String name) {
        return (NBTByte) get(name, NBTType.BYTE);
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public NBTShort getShort(String name) {
        return (NBTShort) get(name, NBTType.SHORT);
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public NBTInt getInt(String name) {
        return (NBTInt) get(name, NBTType.INT);
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public NBTLong getLong(String name) {
        return (NBTLong) get(name, NBTType.LONG);
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public NBTFloat getFloat(String name) {
        return (NBTFloat) get(name, NBTType.FLOAT);
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public NBTDouble getDouble(String name) {
        return (NBTDouble) get(name, NBTType.DOUBLE);
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public <T extends Number> NBTNumber<T> getNumber(String name) {
        NBTTag<?> tag = get(name);
        return tag != null &&
                (tag.getType() == NBTType.BYTE || tag.getType() == NBTType.SHORT
                        || tag.getType() == NBTType.INT || tag.getType() == NBTType.LONG
                        || tag.getType() == NBTType.FLOAT || tag.getType() == NBTType.DOUBLE)
                ? (NBTNumber<T>) tag : null;
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @param type the type of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public NBTList getList(String name, NBTType type) {
        NBTTag<?> tag = get(name);
        return tag != null && tag.getType() == NBTType.LIST && ((NBTList) tag).getElementType() == type ? (NBTList) tag : null;
    }

    @Nullable
    public <T extends NBTTag<?>> NBTList<T> getList(String name) {
        NBTTag<?> tag = get(name);
        return tag != null && tag.getType() == NBTType.LIST ? (NBTList<T>) tag : null;
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public NBTString getString(String name) {
        return (NBTString) get(name, NBTType.STRING);
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public NBTCompound getCompound(String name) {
        return (NBTCompound) get(name, NBTType.COMPOUND);
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public NBTIntArray getIntArray(String name) {
        NBTTag<?> tag = get(name);
        return tag != null && tag.getType() == NBTType.INT_ARRAY ? (NBTIntArray) tag : null;
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public NBTLongArray getLongArray(String name) {
        NBTTag<?> tag = get(name);
        return tag != null && tag.getType() == NBTType.LONG_ARRAY ? (NBTLongArray) tag : null;
    }

    /**
     * Get a tag from this compound
     *
     * @param name the name of the tag
     * @return the tag, or null if not found
     */
    @Nullable
    public <T> NBTArray<T> getArray(String name) {
        NBTTag<?> tag = get(name);
        return tag != null &&
                (tag.getType() == NBTType.BYTE_ARRAY || tag.getType() == NBTType.INT_ARRAY || tag.getType() == NBTType.LONG_ARRAY)
                ? (NBTArray<T>) tag : null;
    }

    /**
     * Get the size of this compound
     *
     * @return the size
     */
    public int size() {
        return getValue() == null ? 0 : getValue().size();
    }


    @NotNull
    @Override
    public Iterator<NBTTag<?>> iterator() {
        return value.iterator();
    }
}
