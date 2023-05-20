package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NBTFloatTest {

    static NBTFloat nbt = new NBTFloat("test", 1.0f);

    @Test
    void getName() {
        assertEquals("test", nbt.getName());
    }

    @Test
    void getValue() {
        assertEquals(1.0f, nbt.getValue());
    }

    @Test
    void getType() {
        assertEquals(NBTType.FLOAT, nbt.getType());
    }

    @Test
    void testToString() {
        assertEquals("NBTFloat{name='test', value=1.0}", nbt.toString());
    }

    @Test
    void toUncompressed() {
        assertEquals("TAG_Float('test'): 1.0", nbt.toUncompressed());
    }

    @Test
    void bytes() {
        assertArrayEquals(new byte[]{
                0x05, // NBTType.FLOAT
                0x00, 0x04, // name length (4)
                0x74, 0x65, 0x73, 0x74, // name ("test")
                0x3F, (byte) 0x80, 0x00, 0x00 // value (1.0)
        }, nbt.bytes());
    }

    @Test
    void fromBytes() {
        try {
            assertEquals(nbt, NBTFloat.fromBytes(new byte[]{
                    0x05, // NBTType.FLOAT
                    0x00, 0x04, // name length (4)
                    0x74, 0x65, 0x73, 0x74, // name ("test")
                    0x3F, (byte) 0x80, 0x00, 0x00 // value (1.0)
            }));
        } catch (Exception e) {
            fail(e);
        }
    }
}