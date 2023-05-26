package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NBTShortTest {

    static NBTShort nbt = new NBTShort("test", (short) 1);

    @Test
    void getName() {
        assertEquals("test", nbt.getName());
    }

    @Test
    void getShort() {
        assertEquals((short) 1, nbt.getShort());
    }

    @Test
    void getType() {
        assertEquals(NBTType.SHORT, nbt.getType());
    }

    @Test
    void testToString() {
        assertEquals("NBTShort{name='test', value=1}", nbt.toString());
    }

    @Test
    void toUncompressed() {
        assertEquals("TAG_Short('test'): 1", nbt.toUncompressed());
    }

    @Test
    void bytes() {
        assertArrayEquals(new byte[]{
                0x02, // NBTType.SHORT
                0x00, 0x04, // name length (4)
                0x74, 0x65, 0x73, 0x74, // name ("test")
                0x00, 0x01, // value (1)
        }, nbt.bytes());
    }

    @Test
    void fromBytes() {
        try {
            assertEquals(nbt, NBTShort.fromBytes(new byte[]{
                    0x02, // NBTType.SHORT
                    0x00, 0x04, // name length (4)
                    0x74, 0x65, 0x73, 0x74, // name ("test")
                    0x00, 0x01, // value (1)
            }));
        } catch (Exception e) {
            fail(e);
        }
    }
}