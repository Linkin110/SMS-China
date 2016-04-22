package com.dzy.cmpp.message;

import com.dzy.cmpp.util.ByteUtil;

public class CMPPConstants {

    public static final int CMPP_VERSION = 2*16 + 0;

    public static final int CMPP_CONNECT         = 0x00000001;
    public static final int CMPP_CONNECT_RESP    = 0x80000001;
    public static final int CMPP_TERMINATE       = 0x00000002;
    public static final int CMPP_TERMINATE_RESP  = 0x80000002;
    public static final int CMPP_SUBMIT          = 0x00000004;
    public static final int CMPP_SUBMIT_RESP     = 0x80000004;
    public static final int CMPP_DELIVER         = 0x00000005;
    public static final int CMPP_DELIVER_RESP    = 0x80000005;
    public static final int CMPP_QUERY           = 0x00000006;
    public static final int CMPP_QUERY_RESP      = 0x80000006;
    public static final int CMPP_CANCEL          = 0x00000007;
    public static final int CMPP_CANCEL_RESP     = 0x80000007;
    public static final int CMPP_ACTIVE_TEST     = 0x00000008;
    public static final int CMPP_ACTIVE_TEST_RESP= 0x80000008;


    public static CMPPMessageHeader fromBytes(byte[] bytes) throws Exception {

        if (bytes == null) {
            return null;
        }
        if (bytes.length < CMPPMessageHeader.SZ_HEADER) {
            return null;
        }

        byte headBytes[] = new byte[4];
        int offset = 0;
        ByteUtil.bytesCopy(bytes, headBytes, offset, offset + 3, 0);
        int totalLength = ByteUtil.Bytes4ToInt(headBytes);
        offset += 4;
        ByteUtil.bytesCopy(bytes, headBytes, offset, offset + 3, 0);
        int commandId = ByteUtil.Bytes4ToInt(headBytes);
        offset += 4;
        ByteUtil.bytesCopy(bytes, headBytes, offset, offset + 3, 0);
        int sequenceId = ByteUtil.Bytes4ToInt(headBytes);

        CMPPMessageHeader baseMsg = null;
        switch (commandId) {
            case CMPPConstants.CMPP_CONNECT:
                baseMsg = new CMPPConnectMessage();
                break;
            case CMPPConstants.CMPP_CONNECT_RESP:
                baseMsg = new CMPPConnectRespMessage();
                break;
            case CMPPConstants.CMPP_TERMINATE:
                baseMsg = new CMPPTerminateMessage();
                break;
            case CMPPConstants.CMPP_TERMINATE_RESP:
                baseMsg = new CMPPTerminateRespMessage();
                break;
            case CMPPConstants.CMPP_SUBMIT:
                baseMsg = new CMPPSubmitMessageHeader();
                break;
            case CMPPConstants.CMPP_SUBMIT_RESP:
                baseMsg = new CMPPSubmitRespMessageHeader();
                break;
            case CMPPConstants.CMPP_DELIVER:
                baseMsg = new CMPPDeliverMessageHeader();
                break;
            case CMPPConstants.CMPP_DELIVER_RESP:
                baseMsg = new CMPPDeliverRespMessageHeader();
                break;
            case CMPPConstants.CMPP_QUERY:
                baseMsg = new CMPPQueryMessageHeader();
                break;
            case CMPPConstants.CMPP_QUERY_RESP:
                baseMsg = new CMPPQueryRespMessageHeader();
                break;
            case CMPPConstants.CMPP_CANCEL:
                baseMsg = new CMPPCancelMessageHeader();
                break;
            case CMPPConstants.CMPP_CANCEL_RESP:
                baseMsg = new CMPPCancelRespMessageHeader();
                break;
            case CMPPConstants.CMPP_ACTIVE_TEST:
                baseMsg = new CMPPActiveTestMessageHeader();
                break;
            case CMPPConstants.CMPP_ACTIVE_TEST_RESP:
                baseMsg = new CMPPActiveTestRespMessageHeader();
                break;
            default:
                baseMsg = null;
        }
        baseMsg.fromBytes(bytes);
        return baseMsg;
    }
}