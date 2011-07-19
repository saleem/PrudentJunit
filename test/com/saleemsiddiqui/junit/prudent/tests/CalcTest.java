package com.saleemsiddiqui.junit.prudent.tests;

import com.saleemsiddiqui.junit.prudent.PrudentTest;
import com.saleemsiddiqui.junit.prudent.PrudentTestRunner;
import com.saleemsiddiqui.junit.prudent.domain.Calc;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

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
@RunWith(value=PrudentTestRunner.class)
public class CalcTest {

    private Calc calc;

    @Before
    public void beforeEach() {
        calc = new Calc();
    }

    @Test
    public void testMultiply() throws Exception {
        assertEquals(15, calc.multiply(5, 3));
    }

    @PrudentTest(ifTrue = "junit.prudent.add")
    public void testAdd() throws Exception {
        assertEquals(8, calc.add(5, 3));
    }

    @PrudentTest
    public void testSubtract() throws Exception {
        assertEquals(2, calc.subtract(5, 3));
    }

}