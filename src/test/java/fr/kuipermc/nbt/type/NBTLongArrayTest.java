package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NBTLongArrayTest {

    static NBTLongArray nbt = new NBTLongArray("test", new long[]{1L, 2L, 3L});

    @Test
    void getName() {
        assertEquals("test", nbt.getName());
    }

    @Test
    void getValue() {
        assertArrayEquals(new long[]{1L, 2L, 3L}, nbt.getValue());
    }

    @Test
    void getType() {
        assertEquals(NBTType.LONG_ARRAY, nbt.getType());
    }

    @Test
    void testToString() {
        assertEquals("NBTLongArray{name='test', value=[1, 2, 3]}", nbt.toString());
    }

    @Test
    void toUncompressed() {
        assertEquals("TAG_Long_Array('test'): [1, 2, 3]", nbt.toUncompressed());
    }

    @Test
    void bytes() {
        assertArrayEquals(new byte[]{
                0x0C, // NBTType.LONG_ARRAY
                0x00, 0x04, // name length (4)
                0x74, 0x65, 0x73, 0x74, // name ("test")
                0x00, 0x00, 0x00, 0x03, // length (3)
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0x00, 0x01, // value (1)
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0x00, 0x02, // value (2)
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0x00, 0x03, // value (3)
        }, nbt.bytes());
    }

    @Test
    void fromBytes() {
        try {
            assertEquals(nbt, NBTLongArray.fromBytes(new byte[]{
                    0x0C, // NBTType.LONG_ARRAY
                    0x00, 0x04, // name length (4)
                    0x74, 0x65, 0x73, 0x74, // name ("test")
                    0x00, 0x00, 0x00, 0x03, // length (3)
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0x00, 0x01, // value (1)
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0x00, 0x02, // value (2)
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0x00, 0x03, // value (3)
            }));
        } catch (Exception e) {
            fail(e);
        }
    }
}