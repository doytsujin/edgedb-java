package edgedb.protocol.server;

import edgedb.protocol.client.Header;
import lombok.Data;

import static edgedb.protocol.constants.MessageType.PREPARE_COMPLETE;

@Data
public class PrepareComplete {
    byte mType = (byte) PREPARE_COMPLETE;
    int messageLength;

    short headersLength;
    Header[] headers;

    byte cardinality;

    byte[] argumentDataDescriptorID;
    byte[] resultDataDescriptorID;

}