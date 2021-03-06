package edgedb.protocol.server.reader;

import edgedb.exceptions.OverReadException;
import edgedb.protocol.server.ProtocolExtension;
import edgedb.protocol.server.ServerHandshake;
import edgedb.protocol.server.readerhelper.ReaderHelper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.IOException;

@Data
@Slf4j
public class ServerHandshakeReader extends BaseReader {

    public ServerHandshakeReader(DataInputStream dataInputStream, ReaderHelper readerHelper) {
        super(dataInputStream, readerHelper);
    }

    public ServerHandshakeReader(DataInputStream dataInputStream) {
        super(dataInputStream);
    }

    public ServerHandshake read() throws IOException {
        log.debug("Trying to read Server Handshake");
        ServerHandshake serverHandshake = new ServerHandshake();
        try {
            log.debug("~~~~~~~~~~~~~~~~~~~~1");
            int messageLength = readerHelper.readUint32();
            serverHandshake.setMessageLength(messageLength);
            readerHelper.setMessageLength(messageLength);

            log.debug("~~~~~~~~~~~~~~~~~~~~2");
            serverHandshake.setMajorVersion(readerHelper.readUint16());
            serverHandshake.setMinorVersion(readerHelper.readUint16());

            log.debug("~~~~~~~~~~~~~~~~~~~~3");
            short protocolExtensionLength = readerHelper.readUint16();
            log.debug("Read protocolExtensionLength {}", protocolExtensionLength);
            serverHandshake.setProtocolExtensionLength(protocolExtensionLength);
            ProtocolExtension[] protocolExtensions = new ProtocolExtension[protocolExtensionLength];
            ProtocolExtensionReader peReader = new ProtocolExtensionReader(dataInputStream, readerHelper);
            for (int i = 0; i < protocolExtensionLength; i++) {
                protocolExtensions[i] = peReader.read();
            }
            log.debug("Completed reading Server Handshake");
            return serverHandshake;
        } catch (OverReadException e) {
            e.printStackTrace();
            return serverHandshake;
        } catch (IOException e) {
            throw e;
        }
    }
}
