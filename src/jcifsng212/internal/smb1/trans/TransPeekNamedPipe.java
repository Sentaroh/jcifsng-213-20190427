/* jcifs smb client library in Java
 * Copyright (C) 2002  "Michael B. Allen" <jcifs at samba dot org>
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

package jcifsng212.internal.smb1.trans;


import jcifsng212.Configuration;
import jcifsng212.internal.util.SMBUtil;


/**
 * 
 */
public class TransPeekNamedPipe extends SmbComTransaction {

    private int fid;


    /**
     * 
     * @param config
     * @param pipeName
     * @param fid
     */
    public TransPeekNamedPipe ( Configuration config, String pipeName, int fid ) {
        super(config, SMB_COM_TRANSACTION, TRANS_PEEK_NAMED_PIPE);
        this.name = pipeName;
        this.fid = fid;
        this.timeout = 0xFFFFFFFF;
        this.maxParameterCount = 6;
        this.maxDataCount = 1;
        this.maxSetupCount = (byte) 0x00;
        this.setupCount = 2;
    }


    @Override
    protected int writeSetupWireFormat ( byte[] dst, int dstIndex ) {
        dst[ dstIndex++ ] = this.getSubCommand();
        dst[ dstIndex++ ] = (byte) 0x00;
        // this says "Transaction priority" in netmon
        SMBUtil.writeInt2(this.fid, dst, dstIndex);
        return 4;
    }


    @Override
    protected int readSetupWireFormat ( byte[] buffer, int bufferIndex, int len ) {
        return 0;
    }


    @Override
    protected int writeParametersWireFormat ( byte[] dst, int dstIndex ) {
        return 0;
    }


    @Override
    protected int writeDataWireFormat ( byte[] dst, int dstIndex ) {
        return 0;
    }


    @Override
    protected int readParametersWireFormat ( byte[] buffer, int bufferIndex, int len ) {
        return 0;
    }


    @Override
    protected int readDataWireFormat ( byte[] buffer, int bufferIndex, int len ) {
        return 0;
    }


    @Override
    public String toString () {
        return new String("TransPeekNamedPipe[" + super.toString() + ",pipeName=" + this.name + "]");
    }
}
