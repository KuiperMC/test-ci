package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A list of <b>nameless</b> tags, all of the same type.
 * The list is prefixed with the {@code Type ID} of the items it contains (thus 1 byte),
 * and the length of the list as a <b>signed</b> integer (a further 4 bytes).
 * If the length of the list is 0 or negative, the type may be {@link NBTType#END 0} ({@link NBTEnd TAG_End}) but otherwize it must be any other type.
 * (The notchian implementation uses {@link NBTEnd TAG_End} in that situation, but another reference implementation by Mojang uses 1 instead; parsers should accept any type if the length us <= 0).
 *
 * @param <T> the type of the value
 */
public non-sealed class NBTList<T extends NBTTag<?>> extends NBTTag<List<T>> implements Iterable<T> {

    private final NBTType type;

    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     * @param type the type of the items in the list
     */
    public NBTList(@Nullable String name, @NotNull List<T> value, NBTType type) {
        super(name, value, NBTType.LIST);
        this.type = type;
    }

    protected NBTList(@Nullable String name, @NotNull List<T> value) {
        this(name, value, value.get(0).getType());
    }

    protected NBTList(DataInputStream dis) throws IOException {
        this(dis.readUTF(), dis);
    }

    protected NBTList(@Nullable String name, @NotNull DataInputStream dis) throws IOException {
        this(name, new ArrayList<>(), NBTType.fromId(dis.readByte()));
        int length = dis.readInt();
        for (int i = 0; i < length; i++) {
            value.add((T) NBTTag.read(null, dis, getElementType()));
        }
    }

    public int size() {
        return value.size();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return value.iterator();
    }

    /**
     * Get the type of the items in the list
     *
     * @return the type of the items in the list
     */
    public NBTType getElementType() {
        return type;
    }
}
