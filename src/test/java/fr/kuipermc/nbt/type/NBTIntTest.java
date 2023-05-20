package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NBTIntTest {

    static NBTInt nbt = new NBTInt("test", 1);

    @Test
    void getName() {
        assertEquals("test", nbt.getName());
    }

    @Test
    void getValue() {
        assertEquals(1, nbt.getValue());
    }

    @Test
    void getType() {
        assertEquals(NBTType.INT, nbt.getType());
    }

    @Test
    void testToString() {
        assertEquals("NBTInt{name='test', value=1}", nbt.toString());
    }

    @Test
    void toUncompressed() {
        assertEquals("TAG_Int('test'): 1", nbt.toUncompressed());
    }

    @Test
    void bytes() {
        assertArrayEquals(new byte[]{
                0x03, // NBTType.INT
                0x00, 0x04, // name length (4)
                0x74, 0x65, 0x73, 0x74, // name ("test")
                0x00, 0x00, 0x00, 0x01, // value (1)
        }, nbt.bytes());
    }

    @Test
    void fromBytes() {
        try {
            assertEquals(nbt, NBTInt.fromBytes(new byte[]{
                    0x03, // NBTType.INT
                    0x00, 0x04, // name length (4)
                    0x74, 0x65, 0x73, 0x74, // name ("test")
                    0x00, 0x00, 0x00, 0x01, // value (1)
            }));
        } catch (Exception e) {
            fail(e);
        }
    }
}