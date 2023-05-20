package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class NBTByteTest {

    static NBTByte nbt = new NBTByte("test", (byte) 1);

    @Test
    void getName() {
        assertEquals("test", nbt.getName());
    }

    @Test
    void getValue() {
        assertEquals((byte) 1, nbt.getValue());
    }

    @Test
    void getType() {
        assertEquals(NBTType.BYTE, nbt.getType());
    }

    @Test
    void testToString() {
        assertEquals("NBTByte{name='test', value=1}", nbt.toString());
    }

    @Test
    void toUncompressed() {
        assertEquals("TAG_Byte('test'): 1", nbt.toUncompressed());
    }

    @Test
    void bytes() {
        assertArrayEquals(new byte[]{
                0x01, // NBTType.BYTE
                0x00, 0x04, // name length (4)
                0x74, 0x65, 0x73, 0x74, // name ("test")
                0x01 // value (1)
        }, nbt.bytes());
    }

    @Test
    void fromBytes() {
        try {
            assertEquals(nbt, NBTByte.fromBytes(new byte[] {
                    0x01, // NBTType.BYTE
                    0x00, 0x04, // name length (4)
                    0x74, 0x65, 0x73, 0x74, // name ("test")
                    0x01 // value (1)
            }));
        } catch (IOException e) {
            fail(e);
        }
    }
}