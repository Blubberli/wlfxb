/**
 * wlfxb - a library for creating and processing of TCF data streams.
 *
 * Copyright (C) Yana Panchenko.
 *
 * This file is part of wlfxb.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.clarin.weblicht.wlfxb.io;


/**
 * Indicates an exceptional condition that occurred due to errors or 
 * inconsistencies in TCF or {@link WLData}.
 * 
 * @author Yana Panchenko
 */
public class WLFormatException extends Exception {

    /**
     * Creates an exception with the given message.
     *
     * @param msg a string message.
     */
    public WLFormatException(String msg) {
        super(msg);
    }

    /**
     * Creates an exception from the given exception.
     *
     * @param ex an exception.
     */
    public WLFormatException(Throwable ex) {
        super.initCause(ex);
    }

    /**
     * Creates an exception with the given message from the given exception.
     *
     * @param msg a message.
     * @param ex an exception.
     */
    public WLFormatException(String msg, Throwable ex) {
        super(msg);
        super.initCause(ex);
    }
}