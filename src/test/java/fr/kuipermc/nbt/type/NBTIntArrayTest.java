package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NBTIntArrayTest {

    static NBTIntArray nbt = new NBTIntArray("test", new int[]{1, 2, 3});

    @Test
    void getName() {
        assertEquals("test", nbt.getName());
    }

    @Test
    void getValue() {
        assertArrayEquals(new int[]{1, 2, 3}, nbt.getValue());
    }

    @Test
    void getType() {
        assertEquals(NBTType.INT_ARRAY, nbt.getType());
    }

    @Test
    void length() {
        assertEquals(3, nbt.length());
    }

    @Test
    void testToString() {
        assertEquals("NBTIntArray{name='test', value=[1, 2, 3]}", nbt.toString());
    }

    @Test
    void toUncompressed() {
        assertEquals("TAG_Int_Array('test'): [1, 2, 3]", nbt.toUncompressed());
    }

    @Test
    void bytes() {
        assertArrayEquals(new byte[]{
                0x0B, // NBTType.INT_ARRAY
                0x00, 0x04, // name length (4)
                0x74, 0x65, 0x73, 0x74, // name ("test")
                0x00, 0x00, 0x00, 0x03, // value length (3)
                0x00, 0x00, 0x00, 0x01, // value (1)
                0x00, 0x00, 0x00, 0x02, // value (2)
                0x00, 0x00, 0x00, 0x03, // value (3)
        }, nbt.bytes());
    }

    @Test
    void fromBytes() {
        try {
            assertEquals(nbt, NBTIntArray.fromBytes(new byte[]{
                    0x0B, // NBTType.INT_ARRAY
                    0x00, 0x04, // name length (4)
                    0x74, 0x65, 0x73, 0x74, // name ("test")
                    0x00, 0x00, 0x00, 0x03, // value length (3)
                    0x00, 0x00, 0x00, 0x01, // value (1)
                    0x00, 0x00, 0x00, 0x02, // value (2)
                    0x00, 0x00, 0x00, 0x03, // value (3)
            }));
        } catch (Exception e) {
            fail(e);
        }
    }
}