package middleware.messages;

import java.io.Serializable;

public class ReplyMessage implements Serializable {
  private static final long serialVersionUID = 1L;

  private int messageType;
  private int requestId;
  private byte[] result;

  public ReplyMessage(int messageType, int requestId, byte[] result) {
    this.messageType = messageType;
    this.requestId = requestId;
    this.result = result;
  }

  public int getMessageType() {
    return messageType;
  }

  public int getRequestId() {
    return requestId;
  }

  public byte[] getResult() {
    return result;
  }
}
