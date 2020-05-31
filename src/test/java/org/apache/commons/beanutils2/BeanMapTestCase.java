/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.beanutils2;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.beanutils2.bugs.other.Jira87BeanFactory;
import org.apache.commons.collections.BulkTest;
import org.apache.commons.collections.map.AbstractTestMap;

import junit.framework.Test;
import junit.textui.TestRunner;

/**
 * Test cases for BeanMap
 *
 */
@SuppressWarnings("deprecation")
public class BeanMapTestCase extends AbstractTestMap {

    public BeanMapTestCase(final String testName) {
        super(testName);
    }

    public static void main(final String[] args) {
        TestRunner.run(suite());
    }

    public static Test suite() {
        return BulkTest.makeSuite(BeanMapTestCase.class);
    }

/*
  note to self.  The getter and setter methods were generated by copying the
  field declarations and using the following regular expression search and
  replace:

  From:
        private \(.*\) some\(.*\);
  To:
        public \1 getSome\2Value() {
            return some\2;
        }
        public void setSome\2Value(\1 value) {
            some\2 = value;
        }

  Also note:  The sample keys and mappings were generated manually.
*/


    public static class BeanWithProperties implements Serializable {
        private int someInt;
        private long someLong;
        private double someDouble;
        private float someFloat;
        private short someShort;
        private byte someByte;
        private char someChar;
        private Integer someInteger;
        private String someString;
        private Object someObject;

        public int getSomeIntValue() {
            return someInt;
        }
        public void setSomeIntValue(final int value) {
            someInt = value;
        }

        public long getSomeLongValue() {
            return someLong;
        }
        public void setSomeLongValue(final long value) {
            someLong = value;
        }

        public double getSomeDoubleValue() {
            return someDouble;
        }
        public void setSomeDoubleValue(final double value) {
            someDouble = value;
        }

        public float getSomeFloatValue() {
            return someFloat;
        }
        public void setSomeFloatValue(final float value) {
            someFloat = value;
        }

        public short getSomeShortValue() {
            return someShort;
        }
        public void setSomeShortValue(final short value) {
            someShort = value;
        }

        public byte getSomeByteValue() {
            return someByte;
        }
        public void setSomeByteValue(final byte value) {
            someByte = value;
        }

        public char getSomeCharValue() {
            return someChar;
        }
        public void setSomeCharValue(final char value) {
            someChar = value;
        }

        public String getSomeStringValue() {
            return someString;
        }
        public void setSomeStringValue(final String value) {
            someString = value;
        }

        public Integer getSomeIntegerValue() {
            return someInteger;
        }
        public void setSomeIntegerValue(final Integer value) {
            someInteger = value;
        }

        public Object getSomeObjectValue() {
            return someObject;
        }
        public void setSomeObjectValue(final Object value) {
            someObject = value;
        }
    }

    public static class BeanThrowingExceptions extends BeanWithProperties {
        private static final long serialVersionUID = 1L;
        public void setValueThrowingException(final String value) {
            throw new TestException();
        }
        public String getValueThrowingException() {
            throw new TestException();
        }
    }

    /**
     * Exception for testing exception handling.
     */
    public static class TestException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    // note to self.  The Sample keys were generated by copying the field
    // declarations and using the following regular expression search and replace:
    //
    // From:
    //    private \(.*\) some\(.*\);
    // To:
    //    "some\2Value",
    //
    // Then, I manually added the "class" key, which is a property that exists for
    // all beans (and all objects for that matter.
    @Override
    public Object[] getSampleKeys() {
        final Object[] keys = new Object[] {
            "someIntValue",
            "someLongValue",
            "someDoubleValue",
            "someFloatValue",
            "someShortValue",
            "someByteValue",
            "someCharValue",
            "someIntegerValue",
            "someStringValue",
            "someObjectValue",
            "class",
        };
        return keys;
    }

    /**
     *  An object value that will be stored in the bean map as a value.  Need
     *  to save this externally so that we can make sure the object instances
     *  are equivalent since getSampleValues() would otherwise construct a new
     *  and different Object each time.
     **/
    private final Object objectInFullMap = new Object();

    // note to self: the sample values were created manually
    @Override
    public Object[] getSampleValues() {
        final Object[] values = new Object[] {
            new Integer(1234),
            new Long(1298341928234L),
            new Double(123423.34),
            new Float(1213332.12f),
            new Short((short)134),
            new Byte((byte)10),
            new Character('a'),
            new Integer(1432),
            "SomeStringValue",
            objectInFullMap,
            BeanWithProperties.class,
        };
        return values;
    }

    @Override
    public Object[] getNewSampleValues() {
        final Object[] values = new Object[] {
            new Integer(223),
            new Long(23341928234L),
            new Double(23423.34),
            new Float(213332.12f),
            new Short((short)234),
            new Byte((byte)20),
            new Character('b'),
            new Integer(232),
            "SomeNewStringValue",
            new Object(),
            null,
        };
        return values;
    }

    /**
     * Values is a dead copy in BeanMap, so refresh each time.
     */
    @Override
    public void verifyValues() {
        values = map.values();
        super.verifyValues();
    }

    /**
     * The mappings in a BeanMap are fixed on the properties the underlying
     * bean has.  Adding and removing mappings is not possible, thus this
     * method is overridden to return false.
     */
    @Override
    public boolean isPutAddSupported() {
        return false;
    }

    /**
     * The mappings in a BeanMap are fixed on the properties the underlying
     * bean has.  Adding and removing mappings is not possible, thus this
     * method is overridden to return false.
     */
    @Override
    public boolean isRemoveSupported() {
        return false;
    }

    @Override
    public Map<String, Object> makeFullMap() {
        // note: These values must match (i.e. .equals() must return true)
        // those returned from getSampleValues().
        final BeanWithProperties bean = new BeanWithProperties();
        bean.setSomeIntValue(1234);
        bean.setSomeLongValue(1298341928234L);
        bean.setSomeDoubleValue(123423.34);
        bean.setSomeFloatValue(1213332.12f);
        bean.setSomeShortValue((short)134);
        bean.setSomeByteValue((byte)10);
        bean.setSomeCharValue('a');
        bean.setSomeIntegerValue(new Integer(1432));
        bean.setSomeStringValue("SomeStringValue");
        bean.setSomeObjectValue(objectInFullMap);
        return new BeanMap(bean);
    }

    @Override
    public Map<String, Object> makeEmptyMap() {
        return new BeanMap();
    }

    @Override
    public String[] ignoredTests() {
        // Ignore the serialization tests on collection views.
        return new String[] {
         "TestBeanMap.bulkTestMapEntrySet.testCanonicalEmptyCollectionExists",
         "TestBeanMap.bulkTestMapEntrySet.testCanonicalFullCollectionExists",
         "TestBeanMap.bulkTestMapKeySet.testCanonicalEmptyCollectionExists",
         "TestBeanMap.bulkTestMapKeySet.testCanonicalFullCollectionExists",
         "TestBeanMap.bulkTestMapValues.testCanonicalEmptyCollectionExists",
         "TestBeanMap.bulkTestMapValues.testCanonicalFullCollectionExists",
         "TestBeanMap.bulkTestMapEntrySet.testSimpleSerialization",
         "TestBeanMap.bulkTestMapKeySet.testSimpleSerialization",
         "TestBeanMap.bulkTestMapEntrySet.testSerializeDeserializeThenCompare",
         "TestBeanMap.bulkTestMapKeySet.testSerializeDeserializeThenCompare"
        };
    }

    /**
     * Need to override this method because the "clear()" method on the bean
     * map just returns the bean properties to their default states.  It does
     * not actually remove the mappings as per the map contract.  The default
     * testClear() methods checks that the clear method throws an
     * UnsupportedOperationException since this class is not add/remove
     * modifiable.  In our case though, we do not always throw that exception.
     */
    @Override
    public void testMapClear() {
        //TODO: make sure a call to BeanMap.clear returns the bean to its
        //default initialization values.
    }

    /**
     * Need to override this method because the "put()" method on the bean
     * doesn't work for this type of Map.
     */
    @Override
    public void testMapPut() {
        // see testBeanMapPutAllWriteable
    }

    public void testBeanMapClone() {
        final BeanMap map = (BeanMap)makeFullMap();
        try {
            final BeanMap map2 = (BeanMap)map.clone();

            // make sure containsKey is working to verify the bean was cloned
            // ok, and the read methods were properly initialized
            final Object[] keys = getSampleKeys();
            for (final Object key : keys) {
                assertTrue("Cloned BeanMap should contain the same keys",
                           map2.containsKey(key));
            }
        } catch (final CloneNotSupportedException exception) {
            fail("BeanMap.clone() should not throw a " +
                 "CloneNotSupportedException when clone should succeed.");
        }
    }

    public void testBeanMapPutAllWriteable() {
        final BeanMap map1 = (BeanMap)makeFullMap();
        final BeanMap map2 = (BeanMap)makeFullMap();
        map2.put("someIntValue", new Integer(0));
        map1.putAllWriteable(map2);
        assertEquals(map1.get("someIntValue"), new Integer(0));
    }

    public void testMethodAccessor() throws Exception {
        final BeanMap map = (BeanMap) makeFullMap();
        final Method method = BeanWithProperties.class.getDeclaredMethod("getSomeIntegerValue");
        assertEquals(method, map.getReadMethod("someIntegerValue"));
    }

    public void testMethodMutator() throws Exception {
        final BeanMap map = (BeanMap) makeFullMap();
        final Method method = BeanWithProperties.class.getDeclaredMethod("setSomeIntegerValue", Integer.class);
        assertEquals(method, map.getWriteMethod("someIntegerValue"));
    }

    /**
     *  Test the default transformers using the getTypeTransformer() method
     */
    public void testGetTypeTransformerMethod() {
        final BeanMap beanMap = new BeanMap();
        assertEquals("Boolean.TYPE",   Boolean.TRUE,        beanMap.getTypeTransformer(Boolean.TYPE).apply("true"));
        assertEquals("Character.TYPE", new Character('B'),  beanMap.getTypeTransformer(Character.TYPE).apply("BCD"));
        assertEquals("Byte.TYPE",      new Byte((byte)1),   beanMap.getTypeTransformer(Byte.TYPE).apply("1"));
        assertEquals("Short.TYPE",     new Short((short)2), beanMap.getTypeTransformer(Short.TYPE).apply("2"));
        assertEquals("Integer.TYPE",   new Integer(3),      beanMap.getTypeTransformer(Integer.TYPE).apply("3"));
        assertEquals("Long.TYPE",      new Long(4),         beanMap.getTypeTransformer(Long.TYPE).apply("4"));
        assertEquals("Float.TYPE",     new Float("5"),      beanMap.getTypeTransformer(Float.TYPE).apply("5"));
        assertEquals("Double.TYPE",    new Double("6"),     beanMap.getTypeTransformer(Double.TYPE).apply("6"));
    }

    /**
     * Test that the cause of exception thrown by a clone() is initialised.
     */
    public void testExceptionThrowFromClone() {

        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("testExceptionThrowFromClone() skipped on pre 1.4 JVM");
            return;
        }

        // Test cloning a non-public bean (instantiation exception)
        try {
            final Object bean = Jira87BeanFactory.createMappedPropertyBean();
            final BeanMap map = new BeanMap(bean);
            map.clone();
            fail("Non-public bean clone() - expected CloneNotSupportedException");
        } catch (final CloneNotSupportedException e) {
            Throwable cause = null;
            try {
                cause = (Throwable)PropertyUtils.getProperty(e, "cause");
            } catch (final Exception e2) {
                fail("Non-public bean - retrieving the cause threw " + e2);
            }
            assertNotNull("Non-public bean cause null", cause);
            assertEquals("Non-public bean cause", IllegalAccessException.class, cause.getClass());
        }

        // Test cloning a bean that throws exception
        try {
            final BeanMap map = new BeanMap(new BeanThrowingExceptions());
            map.clone();
            fail("Setter Exception clone() - expected CloneNotSupportedException");
        } catch (final CloneNotSupportedException e) {
            Throwable cause = null;
            try {
                cause = (Throwable)PropertyUtils.getProperty(e, "cause");
            } catch (final Exception e2) {
                fail("Setter Exception - retrieving the cause threw " + e2);
            }
            assertNotNull("Setter Exception cause null", cause);
            assertEquals("Setter Exception cause", IllegalArgumentException.class, cause.getClass());
        }
    }

    /**
     * Test that the cause of exception thrown by clear() is initialised.
     */
    public void testExceptionThrowFromClear() {

        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("testExceptionThrowFromClear() skipped on pre 1.4 JVM");
            return;
        }

        try {
            final Object bean = Jira87BeanFactory.createMappedPropertyBean();
            final BeanMap map = new BeanMap(bean);
            map.clear();
            fail("clear() - expected UnsupportedOperationException");
        } catch (final UnsupportedOperationException e) {
            Throwable cause = null;
            try {
                cause = (Throwable)PropertyUtils.getProperty(e, "cause");
            } catch (final Exception e2) {
                fail("Retrieving the cause threw " + e2);
            }
            assertNotNull("Cause null", cause);
            assertEquals("Cause", IllegalAccessException.class, cause.getClass());
        }
    }

    /**
     * Test that the cause of exception thrown by put() is initialized.
     */
    public void testExceptionThrowFromPut() {

        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("testExceptionThrowFromPut() skipped on pre 1.4 JVM");
            return;
        }

        try {
            final Map<String, Object> map = new BeanMap(new BeanThrowingExceptions());
            map.put("valueThrowingException", "value");
            fail("Setter exception - expected IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Throwable cause1 = null;
            Throwable cause2 = null;
            try {
                cause1 = (Throwable)PropertyUtils.getProperty(e, "cause");
                cause2 = (Throwable)PropertyUtils.getProperty(e, "cause.cause");
            } catch (final Exception e2) {
                fail("Setter exception - retrieving the cause threw " + e2);
            }
            assertNotNull("Setter exception cause 1 null", cause1);
            assertEquals("Setter exception cause 1", InvocationTargetException.class, cause1.getClass());
            assertNotNull("Setter exception cause 2 null", cause2);
            assertEquals("Setter exception cause 2", TestException.class, cause2.getClass());
        }
    }
}
