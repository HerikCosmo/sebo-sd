package middleware.messages;

import java.io.Serializable;

public class RequestMessage implements Serializable {
  private static final long serialVersionUID = 1L;

  private int messageType;
  private int requestId;
  private String objectReference;
  private int methodId;
  private byte[] arguments;

  public RequestMessage(int messageType, int requestId, String objectReference, int methodId, byte[] arguments) {
    this.messageType = messageType;
    this.requestId = requestId;
    this.objectReference = objectReference;
    this.methodId = methodId;
    this.arguments = arguments;
  }

  public int getMessageType() {
    return messageType;
  }

  public int getRequestId() {
    return requestId;
  }

  public String getObjectReference() {
    return objectReference;
  }

  public int getMethodId() {
    return methodId;
  }

  public byte[] getArguments() {
    return arguments;
  }
}
