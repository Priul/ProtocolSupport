package protocolsupport.protocol.packet.middle.clientbound.play;

import io.netty.buffer.ByteBuf;
import protocolsupport.protocol.codec.MiscDataCodec;
import protocolsupport.protocol.packet.middle.ClientBoundMiddlePacket;
import protocolsupport.protocol.types.UsedHand;

public abstract class MiddleBookOpen extends ClientBoundMiddlePacket {

	protected MiddleBookOpen(MiddlePacketInit init) {
		super(init);
	}

	protected UsedHand hand;

	@Override
	protected void decode(ByteBuf serverdata) {
		hand = MiscDataCodec.readVarIntEnum(serverdata, UsedHand.CONSTANT_LOOKUP);
	}

}
