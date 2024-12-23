/*
 * %W% %E% Pavel Shyrkavets
 *
 * Copyright (c) 2011-2024 Solvd, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Solvd,
 * Inc. ("Confidential Information.") You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Solvd.
 *
 * SOLVD MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SOLVD SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */
package com.solvd.computer;

import com.solvd.computer.exceptions.ZeroOrTooManyCharactersException;
import com.solvd.computer.interfaces.IOperate;
import com.solvd.computer.interfaces.IUse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class OperatingSystem implements IOperate, IUse {
    private final static int OPERATING_SYSTEM_MASK = 40;
    private final static int MAX_OPERATING_SYSTEM_NAME_LENGTH = 100;
    private final static String OPERATING_SYSTEM_NAME_MESSAGE =
            "The operating system name length should not be equal to 0 "
            + "or exceed " + MAX_OPERATING_SYSTEM_NAME_LENGTH + " characters.";
    private final static Logger LOGGER = LogManager.getLogger(OperatingSystem.class);

    private String name = "";

    public OperatingSystem() {}

    public OperatingSystem(String name) throws ZeroOrTooManyCharactersException {
        if (name.isEmpty() || name.length() > MAX_OPERATING_SYSTEM_NAME_LENGTH) {
            LOGGER.error(OPERATING_SYSTEM_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(OPERATING_SYSTEM_NAME_MESSAGE);
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ZeroOrTooManyCharactersException {
        if (name.isEmpty() || name.length() > MAX_OPERATING_SYSTEM_NAME_LENGTH) {
            LOGGER.error(OPERATING_SYSTEM_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(OPERATING_SYSTEM_NAME_MESSAGE);
        }

        this.name = name;
    }

    public OperatingSystem getOperatingSystem() {
        return this;
    }

    @Override
    public void use() {
        LOGGER.info("The operating system is used.");
    }

    @Override
    public void useTouchpad() {
        LOGGER.info("The touchpad is used.");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (hashCode() != object.hashCode()) return false;
        OperatingSystem that = (OperatingSystem) object;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), OPERATING_SYSTEM_MASK);
    }

    @Override
    public String toString() {
        return "OperatingSystem{" +
                "name='" + name + '\'' +
                '}';
    }
}
