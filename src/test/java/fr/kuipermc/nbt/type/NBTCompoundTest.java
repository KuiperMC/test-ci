package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NBTCompoundTest {

    static NBTCompound nbt = new NBTCompound("test",
            new NBTByte("byte", (byte) 1),
            new NBTShort("short", (short) 2),
            new NBTInt("int", 3),
            new NBTLong("long", 4L),
            new NBTFloat("float", 5.0F),
            new NBTDouble("double", 6.0),
            new NBTByteArray("byteArray", new byte[]{7, 8, 9}),
            new NBTString("string", "Bananrama"),
            new NBTList<>("list", List.of(new NBTString("string", "String")), NBTType.STRING),
            new NBTCompound("compound", new NBTString("string", "String")),
            new NBTIntArray("intArray", new int[]{10, 11, 12}),
            new NBTLongArray("longArray", new long[]{13, 14, 15}));

    @Test
    void getName() {
        assertEquals("test", nbt.getName());
    }

    @Test
    void getValue() {
        assertEquals(List.of(
                new NBTByte("byte", (byte) 1),
                new NBTShort("short", (short) 2),
                new NBTInt("int", 3),
                new NBTLong("long", 4L),
                new NBTFloat("float", 5.0F),
                new NBTDouble("double", 6.0),
                new NBTByteArray("byteArray", new byte[]{7, 8, 9}),
                new NBTString("string", "Bananrama"),
                new NBTList<>("list", List.of(new NBTString("string", "String")), NBTType.STRING),
                new NBTCompound("compound", new NBTString("string", "String")),
                new NBTIntArray("intArray", new int[]{10, 11, 12}),
                new NBTLongArray("longArray", new long[]{13, 14, 15})), nbt.getValue());
    }

    @Test
    void getType() {
        assertEquals(NBTType.COMPOUND, nbt.getType());
    }

    @Test
    void testToString() {
        assertEquals("NBTCompound{name='test', value=[" +
                "NBTByte{name='byte', value=1}, " +
                "NBTShort{name='short', value=2}, " +
                "NBTInt{name='int', value=3}, " +
                "NBTLong{name='long', value=4}, " +
                "NBTFloat{name='float', value=5.0}, " +
                "NBTDouble{name='double', value=6.0}, " +
                "NBTByteArray{name='byteArray', value=[7, 8, 9]}, " +
                "NBTString{name='string', value='Bananrama'}, " +
                "NBTList{name='list', value=[NBTString{name='string', value='String'}]}, " +
                "NBTCompound{name='compound', value=[NBTString{name='string', value='String'}]}, " +
                "NBTIntArray{name='intArray', value=[10, 11, 12]}, " +
                "NBTLongArray{name='longArray', value=[13, 14, 15]}]}", nbt.toString());
    }

    @Test
    void toUncompressed() {
        assertEquals("TAG_Compound('test'): 12 entries\n" +
                "{\n" +
                "\tTAG_Byte('byte'): 1\n" +
                "\tTAG_Short('short'): 2\n" +
                "\tTAG_Int('int'): 3\n" +
                "\tTAG_Long('long'): 4\n" +
                "\tTAG_Float('float'): 5.0\n" +
                "\tTAG_Double('double'): 6.0\n" +
                "\tTAG_Byte_Array('byteArray'): [7, 8, 9]\n" +
                "\tTAG_String('string'): 'Bananrama'\n" +
                "\tTAG_List('list'): 1 entry\n" +
                "\t{\n" +
                "\t\tTAG_String('string'): 'String'\n" +
                "\t}\n" +
                "\tTAG_Compound('compound'): 1 entry\n" +
                "\t{\n" +
                "\t\tTAG_String('string'): 'String'\n" +
                "\t}\n" +
                "\tTAG_Int_Array('intArray'): [10, 11, 12]\n" +
                "\tTAG_Long_Array('longArray'): [13, 14, 15]\n" +
                "}", nbt.toUncompressed());
    }

    @Test
    void bytes() {
        assertArrayEquals(new byte[]{
                0x0A, // Compound ID
                0x00, 0x04, // Name length (4)
                0x74, 0x65, 0x73, 0x74, // Name (test)
                0x01, // Byte ID
                0x00, 0x04, // Name length (4)
                0x62, 0x79, 0x74, 0x65, // Name (byte)
                0x01, // Value (1)
                0x02, // Short ID
                0x00, 0x05, // Name length (5)
                0x73, 0x68, 0x6F, 0x72, 0x74, // Name (short)
                0x00, 0x02, // Value (2)
                0x03, // Int ID
                0x00, 0x03, // Name length (3)
                0x69, 0x6E, 0x74, // Name (int)
                0x00, 0x00, 0x00, 0x03, // Value (3)
                0x04, // Long ID
                0x00, 0x04, // Name length (4)
                0x6C, 0x6F, 0x6E, 0x67, // Name (long)
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x04, // Value (4)
                0x05, // Float ID
                0x00, 0x05, // Name length (5)
                0x66, 0x6C, 0x6F, 0x61, 0x74, // Name (float)
                0x40, (byte) 0xA0, 0x00, 0x00, // Value (5.0)
                0x06, // Double ID
                0x00, 0x06, // Name length (6)
                0x64, 0x6F, 0x75, 0x62, 0x6C, 0x65, // Name (double)
                0x40, 0x18, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // Value (6.0)
                0x07, // Byte array ID
                0x00, 0x09, // Name length (9)
                0x62, 0x79, 0x74, 0x65, 0x41, 0x72, 0x72, 0x61, 0x79, // Name (byteArray)
                0x00, 0x00, 0x00, 0x03, // Length (3)
                0x07, 0x08, 0x09, // Value ([7, 8, 9])
                0x08, // String ID
                0x00, 0x06, // Name length (6)
                0x73, 0x74, 0x72, 0x69, 0x6E, 0x67, // Name (string)
                0x00, 0x09, // Length (9)
                0x42, 0x61, 0x6E, 0x61, 0x6E, 0x72, 0x61, 0x6D, 0x61, // Value (Bananrama)
                0x09, // List ID
                0x00, 0x04, // Name length (4)
                0x6C, 0x69, 0x73, 0x74, // Name (list)
                0x08, // Type (string)
                0x00, 0x00, 0x00, 0x01, // Length (1)
                0x00, 0x06, // Length of first string (6)
                0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, // Value (String)
                0x0A, // Compound ID
                0x00, 0x08, // Name length (8)
                0x63, 0x6F, 0x6D, 0x70, 0x6F, 0x75, 0x6E, 0x64, // Name (compound)
                0x08, // Type (string)
                0x00, 0x06, // Name length (6)
                0x73, 0x74, 0x72, 0x69, 0x6E, 0x67, // Name (string)
                0x00, 0x06, // Length (6)
                0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, // Value (String)
                0x00, // End
                0x0B, // Int array ID
                0x00, 0x08, // Name length (8)
                0x69, 0x6E, 0x74, 0x41, 0x72, 0x72, 0x61, 0x79, // Name (intArray)
                0x00, 0x00, 0x00, 0x03, // Length (3)
                0x00, 0x00, 0x00, 0x0A, 0x00, 0x00, 0x00, 0x0B, 0x00, 0x00, 0x00, 0x0C, // Value ([10, 11, 12])
                0x0C, // Long array ID
                0x00, 0x09, // Name length (9)
                0x6C, 0x6F, 0x6E, 0x67, 0x41, 0x72, 0x72, 0x61, 0x79, // Name (longArray)
                0x00, 0x00, 0x00, 0x03, // Length (3)
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0D, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0E, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0F, 0x00 // Value ([13, 14, 15])
        }, nbt.bytes());
    }

    @Test
    void fromBytes() {
        try {
            assertEquals(nbt, NBTTag.fromBytes(new byte[]{
                    0x0A, // Compound ID
                    0x00, 0x04, // Name length (4)
                    0x74, 0x65, 0x73, 0x74, // Name (test)
                    0x01, // Byte ID
                    0x00, 0x04, // Name length (4)
                    0x62, 0x79, 0x74, 0x65, // Name (byte)
                    0x01, // Value (1)
                    0x02, // Short ID
                    0x00, 0x05, // Name length (5)
                    0x73, 0x68, 0x6F, 0x72, 0x74, // Name (short)
                    0x00, 0x02, // Value (2)
                    0x03, // Int ID
                    0x00, 0x03, // Name length (3)
                    0x69, 0x6E, 0x74, // Name (int)
                    0x00, 0x00, 0x00, 0x03, // Value (3)
                    0x04, // Long ID
                    0x00, 0x04, // Name length (4)
                    0x6C, 0x6F, 0x6E, 0x67, // Name (long)
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x04, // Value (4)
                    0x05, // Float ID
                    0x00, 0x05, // Name length (5)
                    0x66, 0x6C, 0x6F, 0x61, 0x74, // Name (float)
                    0x40, (byte) 0xA0, 0x00, 0x00, // Value (5.0)
                    0x06, // Double ID
                    0x00, 0x06, // Name length (6)
                    0x64, 0x6F, 0x75, 0x62, 0x6C, 0x65, // Name (double)
                    0x40, 0x18, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // Value (6.0)
                    0x07, // Byte array ID
                    0x00, 0x09, // Name length (9)
                    0x62, 0x79, 0x74, 0x65, 0x41, 0x72, 0x72, 0x61, 0x79, // Name (byteArray)
                    0x00, 0x00, 0x00, 0x03, // Length (3)
                    0x07, 0x08, 0x09, // Value ([7, 8, 9])
                    0x08, // String ID
                    0x00, 0x06, // Name length (6)
                    0x73, 0x74, 0x72, 0x69, 0x6E, 0x67, // Name (string)
                    0x00, 0x09, // Length (9)
                    0x42, 0x61, 0x6E, 0x61, 0x6E, 0x72, 0x61, 0x6D, 0x61, // Value (Bananrama)
                    0x09, // List ID
                    0x00, 0x04, // Name length (4)
                    0x6C, 0x69, 0x73, 0x74, // Name (list)
                    0x08, // Type (string)
                    0x00, 0x00, 0x00, 0x01, // Length (1)
                    0x00, 0x06, // Length of first string (6)
                    0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, // Value (String)
                    0x0A, // Compound ID
                    0x00, 0x08, // Name length (8)
                    0x63, 0x6F, 0x6D, 0x70, 0x6F, 0x75, 0x6E, 0x64, // Name (compound)
                    0x08, // Type (string)
                    0x00, 0x06, // Name length (6)
                    0x73, 0x74, 0x72, 0x69, 0x6E, 0x67, // Name (string)
                    0x00, 0x06, // Length (6)
                    0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, // Value (String)
                    0x00, // End
                    0x0B, // Int array ID
                    0x00, 0x08, // Name length (8)
                    0x69, 0x6E, 0x74, 0x41, 0x72, 0x72, 0x61, 0x79, // Name (intArray)
                    0x00, 0x00, 0x00, 0x03, // Length (3)
                    0x00, 0x00, 0x00, 0x0A, 0x00, 0x00, 0x00, 0x0B, 0x00, 0x00, 0x00, 0x0C, // Value ([10, 11, 12])
                    0x0C, // Long array ID
                    0x00, 0x09, // Name length (9)
                    0x6C, 0x6F, 0x6E, 0x67, 0x41, 0x72, 0x72, 0x61, 0x79, // Name (longArray)
                    0x00, 0x00, 0x00, 0x03, // Length (3)
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0D, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0E, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0F, 0x00 // Value ([13, 14, 15])
                    }
            ));
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    void get() {
        assertEquals((byte) 1, ((NBTByte) nbt.get("byte")).getValue());
        assertEquals((short) 2, ((NBTShort) nbt.get("short")).getValue());
        assertEquals(3, ((NBTInt) nbt.get("int")).getValue());
        assertEquals(4L, ((NBTLong) nbt.get("long")).getValue());
        assertEquals(5.0f, ((NBTFloat) nbt.get("float")).getValue());
        assertEquals(6.0, ((NBTDouble) nbt.get("double")).getValue());
        assertArrayEquals(new byte[]{7, 8, 9}, ((NBTByteArray) nbt.get("byteArray")).getValue());
        assertEquals("Bananrama", ((NBTString) nbt.get("string")).getValue());
        assertEquals(Collections.singletonList(new NBTString("string", "String")), ((NBTList) nbt.get("list")).getValue());
        assertEquals(Collections.singletonList(new NBTString("string", "String")), ((NBTCompound) nbt.get("compound")).getValue());
        assertArrayEquals(new int[]{10, 11, 12}, ((NBTIntArray) nbt.get("intArray")).getValue());
        assertArrayEquals(new long[]{13, 14, 15}, ((NBTLongArray) nbt.get("longArray")).getValue());
    }

    @Test
    void getWithType() {
        assertEquals((byte) 1, ((NBTByte) nbt.get("byte", NBTType.BYTE)).getValue());
        assertEquals((short) 2, ((NBTShort) nbt.get("short", NBTType.SHORT)).getValue());
        assertEquals(3, ((NBTInt) nbt.get("int", NBTType.INT)).getValue());
        assertEquals(4L, ((NBTLong) nbt.get("long", NBTType.LONG)).getValue());
        assertEquals(5.0f, ((NBTFloat) nbt.get("float", NBTType.FLOAT)).getValue());
        assertEquals(6.0, ((NBTDouble) nbt.get("double", NBTType.DOUBLE)).getValue());
        assertArrayEquals(new byte[]{7, 8, 9}, ((NBTByteArray) nbt.get("byteArray", NBTType.BYTE_ARRAY)).getValue());
        assertEquals("Bananrama", ((NBTString) nbt.get("string", NBTType.STRING)).getValue());
        assertEquals(Collections.singletonList(new NBTString("string", "String")), ((NBTList) nbt.get("list", NBTType.LIST)).getValue());
        assertEquals(Collections.singletonList(new NBTString("string", "String")), ((NBTCompound) nbt.get("compound", NBTType.COMPOUND)).getValue());
        assertArrayEquals(new int[]{10, 11, 12}, ((NBTIntArray) nbt.get("intArray", NBTType.INT_ARRAY)).getValue());
        assertArrayEquals(new long[]{13, 14, 15}, ((NBTLongArray) nbt.get("longArray", NBTType.LONG_ARRAY)).getValue());
    }

    @Test
    void getByte() {
        assertEquals((byte) 1, nbt.getByte("byte").getValue());
    }

    @Test
    void getShort() {
        assertEquals((short) 2, nbt.getShort("short").getValue());
    }

    @Test
    void getInt() {
        assertEquals(3, nbt.getInt("int").getValue());
    }

    @Test
    void getLong() {
        assertEquals(4L, nbt.getLong("long").getValue());
    }

    @Test
    void getFloat() {
        assertEquals(5.0f, nbt.getFloat("float").getValue());
    }

    @Test
    void getDouble() {
        assertEquals(6.0, nbt.getDouble("double").getValue());
    }

    @Test
    void getList() {
        assertEquals(Collections.singletonList(new NBTString("string", "String")), nbt.getList("list", NBTType.STRING).getValue());
    }

    @Test
    void getListWithTP() {
        assertEquals(Collections.singletonList(new NBTString("string", "String")), nbt.getList("list").getValue());
    }

    @Test
    void getString() {
        assertEquals("Bananrama", nbt.getString("string").getValue());
    }

    @Test
    void getCompound() {
        assertEquals(Collections.singletonList(new NBTString("string", "String")), nbt.getCompound("compound").getValue());
    }

    @Test
    void getIntArray() {
        assertArrayEquals(new int[]{10, 11, 12}, nbt.getIntArray("intArray").getValue());
    }

    @Test
    void getLongArray() {
        assertArrayEquals(new long[]{13, 14, 15}, nbt.getLongArray("longArray").getValue());
    }

    @Test
    void size() {
        assertEquals(12, nbt.size());
    }

    @Test
    void iterator() {
        Iterator<NBTTag<?>> iterator = nbt.iterator();
        assertEquals(new NBTByte("byte", (byte) 1), iterator.next());
        assertEquals(new NBTShort("short", (short) 2), iterator.next());
        assertEquals(new NBTInt("int", 3), iterator.next());
        assertEquals(new NBTLong("long", 4L), iterator.next());
        assertEquals(new NBTFloat("float", 5.0f), iterator.next());
        assertEquals(new NBTDouble("double", 6.0), iterator.next());
        assertEquals(new NBTByteArray("byteArray", new byte[]{7, 8, 9}), iterator.next());
        assertEquals(new NBTString("string", "Bananrama"), iterator.next());
        assertEquals(new NBTList("list", Collections.singletonList(new NBTString("string", "String")), NBTType.STRING), iterator.next());
        assertEquals(new NBTCompound("compound", Collections.singletonList(new NBTString("string", "String"))), iterator.next());
        assertEquals(new NBTIntArray("intArray", new int[]{10, 11, 12}), iterator.next());
        assertEquals(new NBTLongArray("longArray", new long[]{13, 14, 15}), iterator.next());
        assertFalse(iterator.hasNext());
    }
}