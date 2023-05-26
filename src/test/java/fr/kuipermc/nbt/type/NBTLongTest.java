package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NBTLongTest {

    static NBTLong nbt = new NBTLong("test", 1L);

    @Test
    void getName() {
        assertEquals("test", nbt.getName());
    }

    @Test
    void getLong() {
        assertEquals(1L, nbt.getLong());
    }

    @Test
    void getType() {
        assertEquals(NBTType.LONG, nbt.getType());
    }

    @Test
    void testToString() {
        assertEquals("NBTLong{name='test', value=1}", nbt.toString());
    }

    @Test
    void toUncompressed() {
        assertEquals("TAG_Long('test'): 1", nbt.toUncompressed());
    }

    @Test
    void bytes() {
        assertArrayEquals(new byte[]{
                0x04, // NBTType.LONG
                0x00, 0x04, // name length (4)
                0x74, 0x65, 0x73, 0x74, // name ("test")
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0x00, 0x01, // value (1)
        }, nbt.bytes());
    }

    @Test
    void fromBytes() {
        try {
            assertEquals(nbt, NBTLong.fromBytes(new byte[]{
                    0x04, // NBTType.LONG
                    0x00, 0x04, // name length (4)
                    0x74, 0x65, 0x73, 0x74, // name ("test")
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0x00, 0x01, // value (1)
            }));
        } catch (Exception e) {
            fail(e);
        }
    }
}