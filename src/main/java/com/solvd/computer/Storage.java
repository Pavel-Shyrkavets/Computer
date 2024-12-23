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
import com.solvd.computer.interfaces.IUse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Storage implements IUse {
    private final static int STORAGE_MASK = 70;
    private final static int MAX_SIZE_IN_GIGABYTES = 43008;
    private final static int MAX_TYPE_NAME_LENGTH = 100;
    private final static String SIZE_IN_GIGABYTES_MESSAGE =
            "The size of the storage should not be less than or equal to 0 "
            + "or exceed " + MAX_SIZE_IN_GIGABYTES + " GBs.";
    private final static String TYPE_NAME_LENGTH_MESSAGE =
            "The length of the storage type name should not be equal to 0 "
            + "or exceed " + MAX_TYPE_NAME_LENGTH + " characters.";
    private final static Logger LOGGER = LogManager.getLogger(Storage.class);

    private int sizeInGBs = 0;
    private String type = "";

    public Storage() {}

    public Storage(int sizeInGBs, String type)
            throws ZeroOrTooManyCharactersException {
        if (sizeInGBs <= 0 || sizeInGBs > MAX_SIZE_IN_GIGABYTES) {
            LOGGER.info(SIZE_IN_GIGABYTES_MESSAGE);
            System.exit(1);
        }

        if (type.isEmpty() || type.length() > MAX_TYPE_NAME_LENGTH) {
            LOGGER.error(TYPE_NAME_LENGTH_MESSAGE);
            throw new ZeroOrTooManyCharactersException(TYPE_NAME_LENGTH_MESSAGE);
        }

        this.sizeInGBs = sizeInGBs;
        this.type = type;
    }

    public int getSizeInGBs() {
        return sizeInGBs;
    }

    public void setSizeInGBs(int sizeInGBs) {
        if (sizeInGBs <= 0 || sizeInGBs > MAX_SIZE_IN_GIGABYTES) {
            LOGGER.info(SIZE_IN_GIGABYTES_MESSAGE);
            System.exit(1);
        }

        this.sizeInGBs = sizeInGBs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws ZeroOrTooManyCharactersException {
        if (type.isEmpty() || type.length() > MAX_TYPE_NAME_LENGTH) {
            throw new ZeroOrTooManyCharactersException(TYPE_NAME_LENGTH_MESSAGE);
        }

        this.type = type;
    }

    @Override
    public void use() {
        LOGGER.info("The storage is used.");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (hashCode() != object.hashCode()) return false;
        Storage storage = (Storage) object;
        return getSizeInGBs() == storage.getSizeInGBs()
                && Objects.equals(getType(), storage.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSizeInGBs(), getType(), STORAGE_MASK);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "sizeInGBs=" + sizeInGBs +
                ", type='" + type + '\'' +
                '}';
    }
}
