package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.StringJoiner;

public sealed class NBTArray<T> extends NBTTag<T> permits NBTByteArray, NBTIntArray, NBTLongArray {
    /**
     * Create a new NBT tag
     *
     * @param name  the name of the tag
     * @param value the value of the tag
     * @param type  the type of the tag
     */
    protected NBTArray(@Nullable String name, @Nullable T value, @NotNull NBTType type) {
        super(name, value, type);
    }

    public int length() {
        return Array.getLength(this.getValue());
    }

    public T get(int index) {
        return (T) Array.get(this.getValue(), index);
    }

    public void set(int index, T value) {
        Array.set(this.getValue(), index, value);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", value=" + valueToString() +
                '}';
    }

    public String valueToString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < this.length(); i++) {
            joiner.add(this.get(i).toString());
        }
        return joiner.toString();
    }
}
