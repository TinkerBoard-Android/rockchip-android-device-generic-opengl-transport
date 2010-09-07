/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.eclipse.org/org/documents/epl-v10.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.android.ide.eclipse.adt.editors.layout.gscripts;

import com.android.ide.eclipse.adt.annotations.Nullable;

import groovy.lang.Closure;



/**
 * A Client Rules Engine is a set of methods that {@link IViewRule}s can use to
 * access the client public API of the Rules Engine. Rules can access it via
 * the property "_rules_engine" which is dynamically added to {@link IViewRule}
 * instances on creation.
 *
 * @see IViewRule#RULES_ENGINE
 */
public interface IClientRulesEngine {

    /**
     * Returns the FQCN for which the rule was loaded.
     */
    String getFqcn();

    /**
     * Prints a debug line in the Eclipse console using the ADT formatter.
     *
     * @param msg A String format message.
     * @param params Optional parameters for the message.
     */
    void debugPrintf(String msg, Object...params);

    /**
     * Loads and returns an {@link IViewRule} for the given FQCN.
     *
     * @param fqcn A non-null, non-empty FQCN for the rule to load.
     * @return The rule that best matches the given FQCN according to the
     *   inheritance chain. Rules are cached and requesting the same FQCN twice
     *   is fast and will return the same rule instance.
     */
    IViewRule loadRule(String fqcn);

    /**
     * Displays the given message string in an alert dialog with an "OK" button.
     */
    void displayAlert(String message);

    /**
     * Display a simple input alert dialog with an OK and Cancel buttons.
     *
     * @param message The message to display in the alert dialog.
     * @param value The initial value to display in the input field. Can be null.
     * @param filter An optional closure acting as a filter. It receives the current
     *   string as input. It must return an error string (possibly empty) or false if the
     *   validation fails. Otherwise it should return true or null if validation succeeds.
     * @return Null if canceled by the user. Otherwise the possibly-empty input string.
     * @null Return value is null if dialog was canceled by the user.
     */
    @Nullable
    String displayInput(String message, @Nullable String value, @Nullable Closure filter);
}

