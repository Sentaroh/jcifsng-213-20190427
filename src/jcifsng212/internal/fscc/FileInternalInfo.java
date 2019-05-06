/*
 * © 2017 AgNO3 Gmbh & Co. KG
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package jcifsng212.internal.fscc;


import jcifsng212.internal.SMBProtocolDecodingException;
import jcifsng212.internal.util.SMBUtil;


/**
 * 
 * @author mbechler
 *
 */
public class FileInternalInfo implements FileInformation {

    private long indexNumber;


    /**
     * {@inheritDoc}
     *
     * @see jcifsng212.internal.fscc.FileInformation#getFileInformationLevel()
     */
    @Override
    public byte getFileInformationLevel () {
        return FILE_INTERNAL_INFO;
    }


    /**
     * @return the indexNumber
     */
    public long getIndexNumber () {
        return this.indexNumber;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng212.Decodable#decode(byte[], int, int)
     */
    @Override
    public int decode ( byte[] buffer, int bufferIndex, int len ) throws SMBProtocolDecodingException {
        this.indexNumber = SMBUtil.readInt8(buffer, bufferIndex);
        return 8;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng212.Encodable#size()
     */
    @Override
    public int size () {
        return 8;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng212.Encodable#encode(byte[], int)
     */
    @Override
    public int encode ( byte[] dst, int dstIndex ) {
        SMBUtil.writeInt8(this.indexNumber, dst, dstIndex);
        return 8;
    }


    @Override
    public String toString () {
        return new String("SmbQueryFileInternalInfo[" + "indexNumber=" + this.indexNumber + "]");
    }
}