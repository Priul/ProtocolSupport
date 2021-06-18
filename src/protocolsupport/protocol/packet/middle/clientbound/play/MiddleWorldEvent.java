package protocolsupport.protocol.packet.middle.clientbound.play;

import io.netty.buffer.ByteBuf;
import protocolsupport.protocol.codec.PositionCodec;
import protocolsupport.protocol.packet.middle.ClientBoundMiddlePacket;
import protocolsupport.protocol.types.Position;

public abstract class MiddleWorldEvent extends ClientBoundMiddlePacket {

	protected MiddleWorldEvent(MiddlePacketInit init) {
		super(init);
	}

	protected int effectId;
	protected final Position position = new Position(0, 0, 0);
	protected int data;
	protected boolean disableRelative;

	@Override
	protected void decode(ByteBuf serverdata) {
		effectId = serverdata.readInt();
		PositionCodec.readPosition(serverdata, position);
		data = serverdata.readInt();
		disableRelative = serverdata.readBoolean();
	}

}
