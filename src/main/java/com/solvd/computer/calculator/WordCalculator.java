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
package com.solvd.computer.calculator;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class WordCalculator {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(FileUtils.readFileToString(new File("src/main/resources/text.txt"), StandardCharsets.UTF_8).replaceAll("[^a-zA-Z0-9 ]", "").split(" ")).collect(Collectors.groupingBy(String::valueOf)).forEach((k, v) -> stringBuilder.append(k).append(": ").append(v.size()).append("\n"));
        FileUtils.writeStringToFile(new File("logs/result.txt"), stringBuilder.toString(), StandardCharsets.UTF_8);
    }
}
