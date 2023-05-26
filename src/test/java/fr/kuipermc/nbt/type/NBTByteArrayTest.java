package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NBTByteArrayTest {

    static NBTByteArray nbt = new NBTByteArray("test", new byte[] { 1, 2, 3 });

    @Test
    void getName() {
        assertEquals("test", nbt.getName());
    }

    @Test
    void getValue() {
        assertArrayEquals(new byte[] { 1, 2, 3 }, nbt.getValue());
    }

    @Test
    void getType() {
        assertEquals(NBTType.BYTE_ARRAY, nbt.getType());
    }

    @Test
    void testToString() {
        assertEquals("NBTByteArray{name='test', value=[1, 2, 3]}", nbt.toString());
    }

    @Test
    void toUncompressed() {
        assertEquals("TAG_Byte_Array('test'): [1, 2, 3]", nbt.toUncompressed());
    }

    @Test
    void length() {
        assertEquals(3, nbt.length());
    }

    @Test
    void bytes() {
        assertArrayEquals(new byte[]{
                0x07, // NBTType.BYTE_ARRAY
                0x00, 0x04, // name length (4)
                0x74, 0x65, 0x73, 0x74, // name ("test")
                0x00, 0x00, 0x00, 0x03, // value length (3)
                0x01, 0x02, 0x03 // value ([1, 2, 3])
        }, nbt.bytes());
    }

    @Test
    void fromBytes() {
        try {
            assertEquals(nbt, NBTByteArray.fromBytes(new byte[] {
                    0x07, // NBTType.BYTE_ARRAY
                    0x00, 0x04, // name length (4)
                    0x74, 0x65, 0x73, 0x74, // name ("test")
                    0x00, 0x00, 0x00, 0x03, // value length (3)
                    0x01, 0x02, 0x03 // value ([1, 2, 3])
            }));
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void stream() {
        assertStreamEquals(nbt.stream(), IntStream.range(0, 3).mapToObj(i -> (byte) (i + 1)));
    }

    static void assertStreamEquals(Stream<?> s1, Stream<?> s2) {
        Iterator<?> iter1 = s1.iterator(), iter2 = s2.iterator();
        while(iter1.hasNext() && iter2.hasNext())
            assertEquals(iter1.next(), iter2.next());
        assert !iter1.hasNext() && !iter2.hasNext();
    }
}