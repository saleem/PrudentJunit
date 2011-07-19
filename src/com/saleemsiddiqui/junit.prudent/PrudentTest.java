package com.saleemsiddiqui.junit.prudent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
   Copyright 2011 Saleem Siddiqui

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PrudentTest {
    /**
     * Default empty exception
     */
    static class None extends Throwable {
        private static final long serialVersionUID = 1L;

        private None() {
        }
    }

    /**
     * Optionally specify <code>expected</code>, a Throwable, to cause a test method to succeed iff
     * an exception of the specified class is thrown by the method.
     */
    Class<? extends Throwable> expected() default None.class;

    /**
     * Optionally specify <code>timeout</code> in milliseconds to cause a test method to fail if it
     * takes longer than that number of milliseconds.
     */
    long timeout() default 0L;

    /**
     * Optionally specify <code>ifTrue</code>, a String property which will be looked up in the System
     * properties. If the property exists and its values is "true" (case insensitive), the test will be ignored.
     */
    String ifTrue() default "junit.prudent";
}