package protocolsupport.protocol.packet.middle.clientbound.play;

import io.netty.buffer.ByteBuf;
import protocolsupport.protocol.codec.VarNumberCodec;
import protocolsupport.protocol.packet.middle.ClientBoundMiddlePacket;

public abstract class MiddleEntity extends ClientBoundMiddlePacket {

	protected MiddleEntity(MiddlePacketInit init) {
		super(init);
	}

	protected int entityId;

	@Override
	protected void decode(ByteBuf serverdata) {
		entityId = VarNumberCodec.readVarInt(serverdata);
	}

}
