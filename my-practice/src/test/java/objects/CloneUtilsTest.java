package objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloneUtilsTest {

    @Test
    void testClone() {
    }

    public static void main(String[] args) {
//        DemoClass generate object is 38 byte
//        DemoClass d1 = new DemoClass();
//        DemoClass d2 = CloneUtils.clone(d1);

////      DemoClassWithInt generate object is 57 byte;
//        DemoClassWithInt int1 = new DemoClassWithInt();
//        DemoClassWithInt int2 = CloneUtils.clone(int1);
//
//        DemoClassWithInt int3 = new DemoClassWithInt(2222);
//        DemoClassWithInt int4 = CloneUtils.clone(int1);
//
////      DemoClassWithInteger generate object is 82 byte;
//        DemoClassWithInteger i = new DemoClassWithInteger();
//        DemoClassWithInteger i1 = CloneUtils.clone(i);

////        DemoClassWithInteger generate object is 82 byte;
//        DemoClassWithInteger i2 = new DemoClassWithInteger(21222);
//        DemoClassWithInteger i3 = CloneUtils.clone(i);

//        DemoClassWithTwoInteger generate object is 91 byte;
//        DemoClassWithTwoInteger twoI0 = new DemoClassWithTwoInteger();
//        DemoClassWithTwoInteger twoI1 = CloneUtils.clone(twoI0);

//         104 byte
//        DemoClassWithThreeInteger threeI0 = new DemoClassWithThreeInteger();
//        DemoClassWithThreeInteger threeI1 = CloneUtils.clone(threeI0);

//        38 -> 82 -> 91 -> 104
//         114 byte
//        DemoClassWithFourInteger fourI0 = new DemoClassWithFourInteger();
//        DemoClassWithFourInteger fourI1 = CloneUtils.clone(fourI0);

        Integer i1 = 0;
        Integer i1Clone = CloneUtils.clone(i1);

        Long l1 = 111111L;
        Long l1Clone = CloneUtils.clone(l1);

    }

}