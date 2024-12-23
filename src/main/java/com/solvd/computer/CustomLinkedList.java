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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLinkedList<E> {
    private final static Logger LOGGER =
            LogManager.getLogger(CustomLinkedList.class);

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void addFirst(E value) {
        Node<E> temp = new Node<>(value);
        temp.setNext(head);
        head = temp;

        if (tail == null) {
            tail = temp;
        }

        size++;
    }

    public Node<E> getFirst() {
        if (size == 0) {
            LOGGER.info("The CustomLinkedList is empty.");
            System.exit(1);
        }

        return head;
    }

    public void addLast(E value) {
        if (tail == null) {
            addFirst(value);
            return;
        }

        Node<E> temp = new Node<>(value);
        tail.setNext(temp);
        tail = temp;
        size++;
    }

    public Node<E> getLast() {
        if (size == 0) {
            LOGGER.info("The CustomLinkedList is empty.");
            System.exit(1);
        }

        return tail;
    }
}
