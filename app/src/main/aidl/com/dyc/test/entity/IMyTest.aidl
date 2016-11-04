// IMyTest.aidl
package com.dyc.test.entity;
// Declare any non-default types here with import statements
import com.dyc.test.entity.TestEntity;
interface IMyTest {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
String getdata(in TestEntity ent);
}
