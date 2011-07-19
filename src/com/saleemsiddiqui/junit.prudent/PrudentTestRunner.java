package com.saleemsiddiqui.junit.prudent;

import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.TestClass;

import java.util.ArrayList;
import java.util.List;

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
public class PrudentTestRunner extends BlockJUnit4ClassRunner {
    private final TestClass fTestClass;

    public PrudentTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        fTestClass = new TestClass(testClass);
    }

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        Description description = describeChild(method);
        if (ignoreTest(method)) {
            notifier.fireTestIgnored(description);
        } else {
            runLeaf(methodBlock(method), description, notifier);
        }
    }

    private boolean ignoreTest(FrameworkMethod method) {
        if (method.getAnnotation(Ignore.class) != null) return true;
        if (method.getAnnotation(PrudentTest.class) != null) {
           PrudentTest tt = method.getAnnotation(PrudentTest.class);
           return isTrue(tt.ifTrue());
        }
        else {
            return false;
        }
    }

    private boolean isTrue(String propertyName) {
        if (propertyName == null) return false;
        return Boolean.getBoolean(propertyName);
    }

    @Override
    protected List<FrameworkMethod> getChildren() {
        List<FrameworkMethod> allAnnotatedTests = new ArrayList<FrameworkMethod>();
        allAnnotatedTests.addAll(super.getChildren());
        allAnnotatedTests.addAll(computeToggleTestMethods());
        return allAnnotatedTests;
    }

    private List<FrameworkMethod> computeToggleTestMethods() {
        return fTestClass.getAnnotatedMethods(PrudentTest.class);
    }
}
